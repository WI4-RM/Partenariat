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
    <span class="light"><div class="carte" id="map">
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
	