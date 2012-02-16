<%@page import="partenariat.Dest"%>
<%@page import="java.util.List"%>
<%@page import="controller.ControllerServlet"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%--
    Document   : index
    Created on : 3 déc. 2011, 18:45:03
    Author     : fingon
--%>


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
        List<Dest> destinations = (List<Dest>) request.getAttribute("destinations");
        int size = destinations == null ? 0 : destinations.size();
    %>
    <script type="text/javascript">
        var marker = new esri.symbol.PictureMarkerSymbol("../images/emse.png",25,70);
        graphics = [];
        <% 
        for(int i = 0; i < size; i++) {
            float x = destinations.get(i).getX();
            float y = destinations.get(i).getY();
        %>
            
	// displaying the location icon on the middle of the screen
	graphic = new esri.Graphic({"geometry":{"x":<%= x%>,"y":<%= y%>,"spatialReference":{"wkid":102100}}});
	graphic.setSymbol(marker);
        graphis.add(graphic);
        <% } %>
    </script>
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
                <p><a href="javascript:nouveauPays(); alert('attention, veuillez zoomer sur le pays avant de valider la creation');">Ajouter un nouveau pays</a></p>
            </div>
            <p><a href="">Ajouter un lieu</a></p>
        </div>
    </span>
    <%
    }
    %>
</div>
	
