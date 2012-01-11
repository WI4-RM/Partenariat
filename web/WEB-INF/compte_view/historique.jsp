<%-- 
    Document   : historique
    Created on : 11 janv. 2012, 01:14:18
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,partenariat.Historique,entity.Rubrique"%>
<div class="divBody">
    <%
    String nomPays = (String)request.getAttribute("nom");
    String idPays = (String)request.getAttribute("idPays");
    ArrayList<Historique> listeHist = (ArrayList<Historique>)getServletContext().getAttribute("historique");
    %>
    <h1>Historique - <a href="pays?idPays=<%= idPays%>"><%= nomPays%></a></h1>
    <%
    String titreRub = "--init--";
    for (int i = 0; i < listeHist.size(); i++){
        Historique hist = listeHist.get(i);
        Rubrique curRub = hist.getRubrique();
        int idRub = curRub.getIdrubrique();
        String curTitreRub = curRub.getNom();
        String contenu = curRub.getTexte();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(curRub.getDate());
        String date = String.valueOf(cal.DAY_OF_MONTH) + "/" +  String.valueOf(cal.MONTH) + "/" + String.valueOf(cal.YEAR);
        String heure = String.valueOf(cal.HOUR) + ":" + String.valueOf(cal.MINUTE) + ":" + String.valueOf(cal.SECOND);
        String nom = hist.getNomProfil();
        String prenom = hist.getPrenomProfil();
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
            %>
            <td width="15%">Par <a href="" ><%= nom%> <%= prenom%></a></td>
            <td width="15%"><a href="modifierPays?action=modifierRubrique&idPays=<%= idPays%>&idRubrique=<%= idRub%>&nouveauContenuRubrique=<%= contenu%>">Restaurer</a></td>
        </tr>
        <%
    }
    %>
    </table>
</div>