<%-- 
    Document   : historique
    Created on : 11 janv. 2012, 01:14:18
    Author     : lolo
--%>

<%@page import="controller.ControllerServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.SimpleDateFormat,entity.Rubrique"%>
<div class="divBody">
    <%
    String nomPays = (String)request.getAttribute("nom");
    String idPays = (String)request.getAttribute("idPays");
    List<Rubrique> listeHist = (List<Rubrique>)getServletContext().getAttribute("historique");
    %>
    <h1>Historique - <a href="pays?idPays=<%= idPays%>"><%= nomPays%></a></h1>
    <br/>
    <br/>
    <%
    String titreRub = "--init--";
    for (int i = 0; i < listeHist.size(); i++){
        Rubrique curRub = listeHist.get(i);
        int idRub = curRub.getIdrubrique();
        String curTitreRub = curRub.getNom();
        String contenu = curRub.getTexte();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yy" );
        String date = dateFormat.format(curRub.getDate());
        SimpleDateFormat heureFormat= new SimpleDateFormat("hh:mm:ss" );
        String heure = heureFormat.format(curRub.getDate());
        String nom = curRub.getProfilIdprofil().getNom();
        String prenom = curRub.getProfilIdprofil().getPrenom();
        String idTabRub = "idTab" + curTitreRub;
        if (!curTitreRub.equals(titreRub)){
            if (!titreRub.equals("--init--")){
                %>
    </table>
                <%
            }
            titreRub = curTitreRub;
            %>
    <p><a href="javascript:visibilite('<%= idTabRub%>');"><%= curTitreRub%></a></p>
    <table id='<%= idTabRub%>' width="100%" style="display: none;">
            <%
            out.print("");
        }
        %>
        <tr>
            <td width="15%">Modifié le <%= date%><br/>à <%= heure%></td>
            <%
            if (contenu.equals("--null--")){
            %>
            <td width="55%">Catégorie supprimée</td>
            <%
            }
            else {
            %>
            <td><%= contenu%></td>
            <%
            }
            if (ControllerServlet.isConnected(request)){
            %>
            <td width="15%">Par <a href="" ><%= prenom%> <%= nom%></a></td>
            <td width="15%"><a href="modifierPays?action=modifierRubrique&idPays=<%= idPays%>&idRubrique=<%= idRub%>&nouveauContenuRubrique=<%= contenu%>">Restaurer</a></td>
            <%
            }
            %>
        </tr>
        <%
    }
    %>
    </table>
    <br/>
    <br/>
    <a href="pays?idPays=<%= idPays%>">Retour à la page "<%= nomPays%>"</a>
</div>