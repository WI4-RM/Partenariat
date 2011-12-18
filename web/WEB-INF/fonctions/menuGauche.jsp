<span class="dark">
    <div class="menuGauche" id='menuGauche' class='menuGauche'>
        <ul>
            <li><a href="index.html">Accueil</a></li>
            <li><a href="">Liste des pays</a></li>
            <li><a href="">Dernières destinations ajoutées</a></li>
            <ul>
                    <li><a href="">Cambodge</a></li>
                    <li><a href="">Mexique</a></li>
            </ul>
            <li><a href="afficherRecherche">Recherche</a></li>
            <%
            if (request.getAttribute("connecte").equals("true")){
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