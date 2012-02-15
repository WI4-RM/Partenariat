<%-- 
    Document   : myProfile
    Created on : 12 janv. 2012, 09:22:11
    Author     : Pauline
--%>

<%@page import="java.util.List"%>
<div class="divBody">
   <% //int setup = Integer.parseInt(request.getParameter("setup"));
      String nom = (String) getServletContext().getAttribute("nom");
      String prenom = (String) getServletContext().getAttribute("prenom");
      int promo = (Integer) getServletContext().getAttribute("promo");
      String email = (String) getServletContext().getAttribute("email");
      List<String> ville = (List<String>) getServletContext().getAttribute("ville");
      List<String> pays = (List<String>) getServletContext().getAttribute("pays");
   %>
   
   <h1>Mon Profil</h1>
  
    <div class="colonneGauche" id="cgauche">
        <table>
            <caption><h2>Informations personelles</h2></caption>
            <tr>
                <td>E-mail</td>
                <td><%= email%></td>
            </tr>

            <tr>
                <td>Nom</td>
                <td><%= nom%></td>
            </tr>

            <tr>
                <td>Prenom</td>
                <td><%= prenom%></td>
            </tr>
            <tr>
                <td>Promotion</td>
                <td><%= promo%></td>
            </tr>
        </table>
                <input type="button" onclick="javascript:ModifInfoPerso('<%= nom%>','<%= prenom%>','<%= promo%>');" value="Modifier" > 
   
   
    <div class="colonneDroite" id="cd">
         <%if ((ville==null)||(ville.size() == 0)||(ville.get(0) == null)||(pays==null)||(pays.size() == 0)||(pays.get(0) == null)) {%>
           Pas de parcours à l'international spécifié%>
           <input type="button" value="Ajouter une destination" onclick="javascript:addDestinationP();">  
        <%} else  {%>
         <table>
             <caption><h2>Parcours International</h2></caption>
             <% for (int i=0; (i<ville.size())&&(i<pays.size()); i++) %>
            <tr>
                <td>Pays</td>
                <td><%= pays%></td>
            </tr>
            <tr>
                <td>Ville</td>
                <td><%= ville%></td>
            </tr>
            <%}%>
         </table>
    <%}%>
           
       
    </div>
   
</div>
<!--
<div class="divBody">
    
	<h1>Profil</h1>
		
	<!--<span class="light"><div class="profil" id="ext-div">
		
	</div></span>-->
        <!--<div class="profil" id="ext-div">
		
	</div>-->
       <!-- <div class="colonneGauche" id="cg"></div>
        <div class="colonneDroiteHaut" id="cdh"></div>
	<div class="colonneDroiteBas" id="cdb"></div>	
        
    
</div>
<script type="text/javascript">
load();
</script>-->
<!--<script type="text/javascript">
refreshPI(true);
</script>
<script type="text/javascript">
refreshPE(true);
</script>
<script type="text/javascript">
refreshIP(true);
</script>-->
<!--
<script type="text/javascript">
function (){refreshPI(true);
refreshPE(true);
refreshIP(true);}
</script>-->
