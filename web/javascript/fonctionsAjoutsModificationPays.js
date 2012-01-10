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
    </form>";
}

function modifierRubrique(idPays, idRubrique, idPara, idDivContenu){
    var contenu = document.getElementById(idPara).innerHTML;
    document.getElementById(idDivContenu).innerHTML = "";
    document.getElementById(idDivContenu).innerHTML += "\
    <form action=\"modifierPays\">\n\
    <input type=\"hidden\" name=\"action\" value=\"modifierRubrique\">\n\
    <input type=\"hidden\" name=\"idPays\" value=\""+ idPays +"\">\n\
    <input type=\"hidden\" name=\"idPays\" value=\"\">\n\
    <input type=\"hidden\" name=\"idRubrique\" value=\""+ idRubrique +"\">\n\
    <input type=\"text\" name=\"nouveauContenuRubrique\" style=\"width:600px; height:200px;\" value=\""+ contenu +"\"/>\n\
    <br/><input type=\"submit\" value=\"Valider la modification\" />\n\
    </form>\n\
    <input type=\"button\" value=\"Annuler\" onClick=\"annulerModifierRubrique(\'"+ contenu +"\',\'"+ idDivContenu +"\');\"/>";
    alert();
}

function annulerModifierRubrique(contenu, idDivContenu){
    document.getElementById(idDivContenu).innerHTML = "";
    document.getElementById(idDivContenu).innerHTML += "<p>"+contenu+"</p>";
}
