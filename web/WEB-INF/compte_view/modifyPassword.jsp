<%@page contentType="text/html" pageEncoding="UTF-8"%>
<span class="light"><div class="formInscription" name='bodyDiv' id='divBody'>
    <span class="titreInscription" >Modification mot de passe</span>
    <br/><br/>
    <form method="POST" action="newPasswordSubmission">
        <table>
            <tr>
                <td>Ancien mot de passe</td>
                <td><input type="password" name="old_password" id="email" /></td>
            </tr>            
            <tr>
                <td>Nouveau mot de passe</td>
                <td><input type="password" name="new_password" id="email" /></td>
            </tr>

            <tr>
                <td>Vérification du nouveau mot de passe</td>
                <td><input type="password" name="new_password2" id="email" /></td>
            </tr>

            <tr>
                <td><input type="submit" value="valider"></td>
            </tr>
        </table>


    </form>

</div></span>