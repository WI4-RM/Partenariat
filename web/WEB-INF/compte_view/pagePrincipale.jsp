<%@page import="service.PaysFacadeREST"%>
<%@page import="controller.ControllerServlet"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%--
    Document   : index
    Created on : 3 déc. 2011, 18:45:03
    Author     : fingon
--%>
<style type="text/css">
    @import "http://serverapi.arcgisonline.com/jsapi/arcgis/2.6/js/dojo/dijit/themes/claro/claro.css";
    @import "http://serverapi.arcgisonline.com/jsapi/arcgis/2.6/js/dojo/dojox/grid/resources/Grid.css";
    html, body { height: 100%; width: 100%; margin: 0; padding: 0; overflow:hidden; }
</style>

<script type="text/javascript">
    djConfig = {
    parseOnLoad: true
    };
</script>
<script type="text/javascript" src="http://serverapi.arcgisonline.com/jsapi/arcgis/?v=2.6"></script>
<script type="text/javascript" src="javascript/map.js"></script>

<div id="divBody">
    <span class="light" style="position:absolute; left:300px; right:25%; top:120px;">
        <p style="font-size: 1.6em;">Choisissez sur la carte un lieu à visiter :</p>
        <div id ="map"></div>
    </span>
    <span class="light" style="position:absolute; left:75%; right:0; top:0">
        <div class="paysConnus">
            <p>Les pays déjà répertoriés sont :</p>
            <div id="idPaysConnus">
            </div>
        </div>
    </span>
    <%
    if (ControllerServlet.isConnected(request)){
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
	