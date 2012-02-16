<%-- 
    Document   : forgottenPassword
    Created on : 25 janv. 2012, 17:16:37
    Author     : fingon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
<span class="light"><div class="formInscription" name='bodyDiv' id='divBody'>
        <span class="passwordReset" >mot de passe oubli&eacute;</span>
    <br/><br/>
    <form method="POST" action="resetPassword">
        <table>
            <tr>
                <td>E-mail</td>
                <td><input type="text" name="email" id="email" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="valider"></td>
            </tr>
        </table>
    </form>
    </div>
   
</html>
