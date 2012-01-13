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
            <td><input type=\"text\" name=\"contenuNouvelleRubrique\" style=\"width:600px; height:200px;\" /></td>\n\
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

function modifierRubrique(idPays, idRubrique, idPara, idDivContenu){
    var contenu = document.getElementById(idPara).innerHTML;
    document.getElementById(idDivContenu).innerHTML = "";
    document.getElementById(idDivContenu).innerHTML += "\
    <form action=\"modifierPays\">\n\
    <input type=\"hidden\" name=\"action\" value=\"modifierRubrique\">\n\
    <input type=\"hidden\" name=\"idPays\" value=\""+ idPays +"\">\n\
    <input type=\"hidden\" name=\"idRubrique\" value=\""+ idRubrique +"\">\n\
    <input type=\"text\" name=\"nouveauContenuRubrique\" style=\"width:600px; height:200px;\" value=\""+ contenu +"\"/>\n\
    <br/><input type=\"submit\" value=\"Valider la modification\" />\n\
    <input type=\"button\" value=\"Annuler\" onClick=\"annulerModifierRubrique(\'"+ contenu +"\',\'"+ idDivContenu +"\',\'"+ idPara +"\');\"/>\n\
    </form>";
    window.location.href = "#" + idRubrique;
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