<%-- 
    Document   : rechercheResult
    Created on : 18 janv. 2012, 11:44:04
    Author     : Pauline
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.lang.*,javax.ejb.EJB,session.ProfilFacade,entity.Profil"%>
<div class="divBody">
   <% 
   String isValid = (String) getServletContext().getAttribute("valid");
   if (isValid.equals("true")){
   List<String> listNom = (List<String>) getServletContext().getAttribute("noms");
   List<String> listPrenom = (List<String>) getServletContext().getAttribute("prenoms");
   List<Integer> idList = (List<Integer>) getServletContext().getAttribute("idList");
    %><table> 
            <tr>
                <td>Profils trouvés</td>
            </tr><%
   for (int i=0; i<listNom.size() ; i++){
      %>  
            <tr>
                <td><a href='xProfile?id=<%= idList.get(i)%>'><%= listNom.get(i)%></a></td>
            </tr> <%
  }
   %></table> <%
  }
  else{
        %> Aucun profil ne correspond à votre recherche
        
        <a href="afficherRecherche">Effectuer une nouvelle recherche</a><%
    }
   %>
</div>
