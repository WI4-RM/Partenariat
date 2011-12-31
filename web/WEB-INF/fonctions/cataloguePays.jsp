<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" import="java.util.*"%>
<table>
    <tr class="alphabet">
        <%
            String attrInit = (String)request.getParameter("lettre");
            String attrDemande = (String)request.getAttribute("lettre");
            String lettreOpen = "";
            if (attrInit == null || attrInit.equals("")){
                if (attrDemande != null && (!attrDemande.equals(""))){
                    lettreOpen = attrDemande;
                }
            }
            else {
                lettreOpen = attrInit;
            }
        %>
    <%
        ArrayList<String> listeLettres = new ArrayList<String>();
        listeLettres.add("A");
        listeLettres.add("B");
        listeLettres.add("C");
        listeLettres.add("D");
        listeLettres.add("E");
        listeLettres.add("F-G");
        listeLettres.add("H-I");
        listeLettres.add("J-K");
        listeLettres.add("L");
        listeLettres.add("M");
        listeLettres.add("N");
        listeLettres.add("O-P");
        listeLettres.add("Q-R");
        listeLettres.add("S");
        listeLettres.add("T");
        listeLettres.add("U-V");
        listeLettres.add("W-X-Y-Z");

        
        for (int i = 0; i < listeLettres.size(); i++){
            String curLettre = listeLettres.get(i);
            if ((curLettre.contains(lettreOpen)) & (!lettreOpen.equals(""))){
                %>
                <td class='ouvert'> <%= curLettre%> </td>
                <%
            }
            else {%>
                <td><a href="javascript:cataloguePays('<%= curLettre%>')"><%= curLettre%></a></td>
                <%
            }
        }
    %>
    </tr>
    <tr>
        <td colspan="17">
            <%
                List<entity.Pays> listePays = (List<entity.Pays>)getServletContext().getAttribute("pays");
                if (listePays != null){
                    for (int i = 0; i < listePays.size(); i++){
                        entity.Pays p = listePays.get(i);
                        String nom = p.getNom();
                        int id = p.getIdpays();
                        %>
                        <a href="pays?id=<%= id%>"><%= nom%></a>

                        <%
                    }
                }
            %>
            
        </td>
    </tr>
</table>