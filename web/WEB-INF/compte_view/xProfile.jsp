<%-- 
    Document   : xProfile
    Created on : 15 janv. 2012, 16:26:23
    Author     : Pauline
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.lang.*,javax.ejb.EJB,session.ProfilFacade,entity.Profil"%>
<div class="divBody"">
   <% int id = Integer.parseInt(request.getParameter("id"));
      String nom = (String) getServletContext().getAttribute("nom");
      String prenom = (String) getServletContext().getAttribute("prenom");
      int promo = (Integer) getServletContext().getAttribute("promo");
      String pays = (String) getServletContext().getAttribute("pays");
      String ville = (String) getServletContext().getAttribute("ville");
      String email = (String) getServletContext().getAttribute("email");
   %>
   
   <h1>Profil de <%= nom%></h1>
    <div class="colonneGauche" id="cgauche">
        <table>
            <caption>Informations personelles</caption>
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
        </table>
    </div>
    <div class="colonneDroiteHaut" id="cdroitehaut">
        <table>
            <caption>Parcours Ecole</caption>
            <tr>
                <td>Promotion</td>
                <td><%= promo%></td>
            </tr>
        </table>
    </div>
   
    <div class="colonneDroiteBas" id="cdroitebas">
        <% if (ville!=null) {%>
         <table>
             <caption>Parcours International</caption>
            <tr>
                <td>Pays</td>
                <td><%= pays%></td>
            </tr>
            <tr>
                <td>Ville</td>
                <td><%= ville%></td>
            </tr>
         </table>
    <%}
           else {%>
           Pas de parcours à l'international spécifié
       <%}%> 
    </div>
</div>