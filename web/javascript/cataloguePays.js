/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var idDivPaysConnus = 'idPaysConnus';

function cataloguePays(lettre){
    var url = "paysAlphabet?lettre=" + lettre;
    var hreq=null;
    if(window.XMLHttpRequest){//firefox, chrome,...
            hreq=new XMLHttpRequest();
    }else{
            hreq=new ActiveXObject("Microsoft.XMLHTTP");//IE
    }
    hreq.onreadystatechange = function(){afficherCataloguePays(hreq);};//Que faire lorsque le serveur repond ?
    hreq.open("GET", url, true); //true=asynchronous
    hreq.send(null);
}

 function afficherCataloguePays(hreq){
    if (hreq.readyState == 4){ //verifier que la requete soit terminee
        if (hreq.responseText != null) {
            if(hreq.status == 200){ //pas d'erreur
                var dochtml = hreq.responseText;
                ecrireCatalogue(dochtml);
            }else{
                alert("Erreur HTTP code :"+hreq.status);
            }
        }
    }
}

function ecrireCatalogue(txt){
    document.getElementById(idDivPaysConnus).innerHTML = "";
    document.getElementById(idDivPaysConnus).innerHTML += txt;
}