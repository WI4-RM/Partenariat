<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%--
    Document   : index
    Created on : 3 déc. 2011, 18:45:03
    Author     : fingon
--%>
 
<div id="divBody">
    <span class="light"><div class="intro">
            <p>Cliquez sur la carte pour visiter une destination</p>
    </div></span>
    <span class="light"><div class="carte">
            <a href=""><img src="img/carte.jpg" height="350"/></a>
    </div></span>
    <span class="light"><div class="instructions">
            <p><a href="javascript:nouveauPays();">Ajouter un nouveau pays</a></p>
            <div id="idNouveauPays"></div>
            <p><a href="">Ajouter un lieu</a></p>
    </div>	</span>
    <span class="light"><div class="paysConnus">
        <p>Les pays déjà répertoriés sont :</p>
        <div id="idPaysConnus">
            <SCRIPT type="text/javascript">
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

                function nouveauPays(){
                    document.getElementById('idNouveauPays').innerHTML = "";
                    document.getElementById('idNouveauPays').innerHTML += "\
                    <form action=\"nouveauPays\">\n\
                    <p>Taper le nom du nouveau pays à créer</p>\n\
                    <input type=\"text\" name=\"nouveauPays\"></input>\n\
                    <input type=\"submit\" value=\"Creer un nouveau pays\">\n\
                    </form>";
                }
            </SCRIPT>
        </div>
    </div></span>
</div>
	