/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function nouveauPays(){
    document.getElementById('idNouveauPays').innerHTML = "";
    document.getElementById('idNouveauPays').innerHTML += "\
    <form action=\"nouveauPays\">\n\
    <p>Tapez le nom du nouveau pays à créer</p>\n\
    <input type=\"text\" name=\"nouveauPays\"></input>\n\
    <input type=\"submit\" value=\"Creer un nouveau pays\">\n\
    </form>";
}

function nouvelleCategorie(idPays){
    document.getElementById('idNouvelleRubrique').innerHTML = "";
    document.getElementById('idNouvelleRubrique').innerHTML += "\
    <form action=\"modifierPays\">\n\
    <input type=\"hidden\" name=\"action\" value=\"ajouterRubrique\">\n\
    <input type=\"hidden\" name=\"idPays\" value=\""+ idPays +"\">\n\
    <h2>Nouvelle Categorie</h2>\n\
    <table>\n\
        <tr>\n\
            <td>Titre</td>\n\
            <td><input type=\"text\" name=\"titreNouvelleRubrique\" /></td>\n\
        </tr>\n\
        <tr>\n\
            <td>Contenu</td>\n\
            <td><textarea name=\"contenuNouvelleRubrique\" style=\"width:600px; height:200px;\"> </textarea></td>\n\
        </tr>\n\
    </table>\n\
    <input type=\"submit\" value=\"Creer une nouvelle rubrique\">\n\
    <input type=\"button\" value=\"Annuler\" onClick=\"javascript:annulerNouvelleRubrique("+ idPays+ ");\">\n\
    </form>";
    window.location.href = "#idNouvelleRubrique";
}

function annulerNouvelleRubrique(idPays){
    document.getElementById('idNouvelleRubrique').innerHTML = "";
    document.getElementById('idNouvelleRubrique').innerHTML += "\
        <input type=\"button\" onClick=\"javascript:nouvelleCategorie('"+idPays+"')\" value=\"Ajouter une categorie\">";
}

function modifierRubrique(idPays, idRubrique, idPara, idDivContenu, nomRub){
    var contenu = document.getElementById(idPara).innerHTML;
    document.getElementById(idDivContenu).innerHTML = "";
    document.getElementById(idDivContenu).innerHTML += "\
    <form action=\"modifierPays\">\n\
    <input type=\"hidden\" name=\"action\" value=\"modifierRubrique\">\n\
    <input type=\"hidden\" name=\"idPays\" value=\""+ idPays +"\">\n\
    <input type=\"hidden\" name=\"idRubrique\" value=\""+ idRubrique +"\">\n\
    <textarea name=\"nouveauContenuRubrique\" style=\"width:600px; height:200px;\">" + contenu + "</textarea>\n\
    <br/><input type=\"submit\" value=\"Valider la modification\" />\n\
    <input type=\"button\" value=\"Annuler\" onClick=\"annulerModifierRubrique(\'"+ contenu +"\',\'"+ idDivContenu +"\',\'"+ idPara +"\');\"/>\n\
    </form>";
    window.location.href = "#" + nomRub;
}

function annulerModifierRubrique(contenu, idDivContenu, idPara){
    document.getElementById(idDivContenu).innerHTML = "";
    document.getElementById(idDivContenu).innerHTML += "<p id='"+ idPara+"'>"+contenu+"</p>";
}

function visibilite(idDiv){
    var divAModifier;
    divAModifier = document.getElementById(idDiv) ;

    if (divAModifier.style.display == "none"){
        divAModifier.style.display = "block" ;
    } else {
        divAModifier.style.display = "none" ;
    }
}

function nouveauFichier(idDiv, idPays){
    document.getElementById(idDiv).innerHTML = "";
    document.getElementById(idDiv).innerHTML += "\
    <p>Entrez le chemin vers le fichier :</p>\n\
    <form action=\"uploadFichier\" method=\"POST\" enctype=\"multipart/form-data\">\n\
    <input type=\"file\" name=\"fichier\"/>\n\
    <br/><input type=\"submit\" value=\"Envoyer\" />\n\
    <input type=\"button\" value=\"Annuler\" onClick=\"annulerEnvoiFichier(\'"+ idDiv +"\',\'"+ idPays +"\');\"/>\n\
    </form>\n\
";
    window.location.href = "#" + idDiv;
}

function annulerEnvoiFichier(idDiv, idRub){
    document.getElementById(idDiv).innerHTML = "";
    document.getElementById(idDiv).innerHTML += "\n\
    <input type=\"button\" onclick=\"javascript:nouveauFichier('"+ idDiv + "','"+ idRub + "')\" value=\"Uploader un fichier\">";
}

function goToSection(id){
    var section = "#" + id;
    window.location.href = section;
}

function addDestination(idDiv, idVille){
    var section = "#" + idDiv;
    document.getElementById(idDiv).innerHTML = "";
    document.getElementById(idDiv).innerHTML += "\
    <form action=\"nouvelleDestination\">\n\
    <input type=\"hidden\" name=\"action\" value=\"villeExistante\">\n\
    <input type=\"hidden\" name=\"idVille\" value=\""+ idVille +"\">\n\
    <table>\n\
        <tr>\n\
            <td width=\"25%\">Type</td>\n\
            <td><SELECT name=\"type\">\n\
                    <OPTION VALUE=\"stage\">Stage</OPTION>\n\
                    <OPTION VALUE=\"semestre\">Départ universitaire</OPTION>\n\
                    <OPTION VALUE=\"tourisme\">Tourisme</OPTION>\n\
                </SELECT>\n\
            </td>\n\
        </tr>\n\
        <tr>\n\
            <td width=\"25%\">Précision (Entreprise, université, lieu de visite...)</td>\n\
            <td><input type=\"text\" name=\"organisme\" /></td>\n\
        </tr>\n\
        <tr>\n\
            <td width=\"20%\">Commentaire</td>\n\
            <td><textarea name=\"commentaire\" style=\"width:600px; height:200px;\"> </textarea></td>\n\
        </tr>\n\
    </table>\n\
    <input type=\"submit\" value=\"Ajouter une destination\">\n\
    <input type=\"button\" value=\"Annuler\" onClick=\"javascript:annulerAjoutDestination('"+ idDiv+ "');\">\n\
    </form>";
    window.location.href = section;
}

function annulerAjoutDestination(idDiv){
    document.getElementById(idDiv).innerHTML = "";
}