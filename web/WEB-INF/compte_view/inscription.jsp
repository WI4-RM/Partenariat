<%@page contentType="text/html" pageEncoding="UTF-8"%>
<span class="light"><div class="formInscription" name='bodyDiv' id='divBody'>
    <span class="titreInscription" >Inscription</span>
    <br/><br/>
    <form method="POST" action="inscriptionValidation" >
        <table>
            <tr>
                <td>E-mail</td>
                <td><input type="text" name="email" id="email" onblur="checkEmail(this);"/></td>
            </tr>

            <tr>
                <td>Mot de passe</td>
                <td><input type="password" name="password" id="email" onblur="checkPassword(this);" /></td>
            </tr>

            <tr>
                <td>Vérification du mot de passe</td>
                <td><input type="password" name="password2" id="email" onblur="checkPassword(this);" /></td>
            </tr>
            <tr>
                <td>Nom</td>
                <td><input type="text" name="name" id="nom" onblur="checkName(this);"/></td>
            </tr>
            <tr>
                <td>Prénom</td>
                <td><input type="text" name="username" id="prenom" onblur="checkName(this);"/></td>
            </tr>
            <tr>
                <td>Promo</td>
                <td>
                    <input class="annee" type="text" name="year" id="annee" size="4" maxlength="4" onblur="checkYear(this);"/>
                </td>
            </tr>
            <tr>
                <td>Téléphone</td>
                <td><input type="text" name="phone" id="telephone" size="10" maxlength="10" onblur="checkPhone(this);"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="valider" ></td>
            </tr>
        </table>


    </form>

</div></span>
