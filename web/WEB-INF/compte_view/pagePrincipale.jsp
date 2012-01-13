<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%--
    Document   : index
    Created on : 3 déc. 2011, 18:45:03
    Author     : fingon
--%>
 
<div id="divBody">
    <span class="light">
        <div class="carte" id="map">
            <p style="font-size: 1.6em;">Choisissez sur la carte un lieu à visiter :</p>
            <a href=""><img alt="carte du monde" src="img/carte.jpg" height="350"/></a>
        </div>
    </span>
    <span class="light">
        <div class="paysConnus">
            <p>Les pays déjà répertoriés sont :</p>
            <div id="idPaysConnus">
            </div>
        </div>
    </span>
    <%
    if (request.getSession(false) != null){// && !request.getSession(false).isNew() ){
    %>
    <span class="light">
        <div class="instructions">
            <div id="idNouveauPays">
                <%
                String erreur = (String)request.getAttribute("erreurCreationPays");
                if ((erreur != null) && (!erreur.equals(""))){
                %>
                <p style="color: red;"><%= erreur%></p>
                <%
                }
                %>
                <p><a href="javascript:nouveauPays();">Ajouter un nouveau pays</a></p>
            </div>
            <p><a href="">Ajouter un lieu</a></p>
        </div>
    </span>
    <%
    }
    %>
</div>
	