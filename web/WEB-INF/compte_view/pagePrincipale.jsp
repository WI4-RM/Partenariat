<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%--
    Document   : index
    Created on : 3 d�c. 2011, 18:45:03
    Author     : fingon
--%>
 
<div id="divBody">
    <span class="light"><div class="intro">
            <p>Cliquez sur la carte pour visiter une destination</p>
    </div></span>
    <span class="light"><div class="carte" id="map">
            <a href=""><img alt="carte du monde" src="img/carte.jpg" height="350"/></a>
    </div></span>
    <span class="light"><div class="instructions">
            <div id="idNouveauPays"><p><a href="javascript:nouveauPays();">Ajouter un nouveau pays</a></p></div>
            <p><a href="">Ajouter un lieu</a></p>
    </div>	</span>
    <span class="light"><div class="paysConnus">
        <p>Les pays d�j� r�pertori�s sont :</p>
        <div id="idPaysConnus">
        </div>
    </div></span>
</div>
	