<span class="dark">
    <div class="menuGauche" id='menuGauche' class='menuGauche'>
        <ul>
            <li><a href="index.html">Accueil</a></li>
            <li><a href="listePays">Liste des pays</a></li>
            <%--<%@ include file="dernieresDestinations.jsp"%>--%>
            <%@ include file="derniersPays.jsp"%>
            <li><a href="afficherRecherche">Recherche</a></li>
            <%
            if (request.getSession(false) != null){// && !request.getSession(false).isNew()){
            %>
                <li><a href="">Liste des profils</a></li>
            <%
            }
            %>
            <br/><br/>
            <p>Recherche rapide</p>
            <form action="recherche" >
                <input type="hidden" name="type" value="rapide">
                <tr>
                    <td>
                        <input type="text" name="champRecherche"/>
                    </td>
                    <td>
                        <input type="submit" value="OK" id="rechercheRapide" />
                    </td>
                </tr>
            </form>
        </ul>
    </div>
</span>