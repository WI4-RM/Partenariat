<%-- 
    Document   : accounts
    Created on : 25 janv. 2012, 19:52:20
    Author     : fingon
--%>

<%@page import="session.ProfilFacade"%>
<%@page import="entity.Profil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comptes</title>
    </head>
    <body>
        <div>
            <table>
                <tr>
                <td>nom</td>
                
                <td>prenom</td>
                
                <td>promo</td>
               
                <td>
                    email
                </td>
                <td>administrateur</td>
                <td> nouveau statut </td>
                <td> suppression </td>

                </tr>
                <%
                   // List<entity.Compte> listeComptes = (List<entity.Compte>) request.getSession(false).getAttribute("comptes");
                    //if (listeComptes != null) {
                      //  for (int i = 0; i < listeComptes.size(); i++) {
                        //    entity.Compte c = listeComptes.get(i);
                          //  String nom = c.getEmail();
                            //boolean isAdmin = c.getIsAdministrator();
                List<entity.Profil> listProfils = (List<entity.Profil>) request.getSession().getAttribute("profils");
                for (int p=0;p<listProfils.size();p++){
                    Profil profil = listProfils.get(p);
                    String nom = profil.getNom();
                    String prenom = profil.getPrenom();
                    int promo = profil.getPromo();
                    String email = "";
                    boolean isAdmin = false;
                    int idCompte = -1;
                    if (profil.getCompteList().size() != 0){
                        email= profil.getCompteList().get(0).getEmail();
                        isAdmin = profil.getCompteList().get(0).getIsAdministrator();
                        idCompte = profil.getCompteList().get(0).getIdcompte();
                                               }
                %>
                <tr>
                <td>
                    <%=nom%>
                </td>
                <td><%= prenom%> </td> 
                <td><%= promo%> </td>
                <td><%= email%> </td>                
                <td><%= isAdmin%> </td>
                <td><a href="setNewAdmin?idCompte=<%=idCompte%>">definir comme administrateur </a> </td>
                <td><a href="deleteAccount?idCompte=<%=idCompte%>">supprimer le compte </a> </td>                
                </tr>
                <%
                        
                    }
                %>
            </table>
        </div>
    </body>
</html>
