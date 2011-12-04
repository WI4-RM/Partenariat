<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Bienvenue sur le site du projet partenariat</title>
        <meta name="description" content="Partage d'informations sur les départs Ã  l'étranger" />

        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <link rel="shortcut icon" type="image/ico" href="/favicon.ico" />

        <link rel="stylesheet" type="text/css" media="screen, projection, print" href="partenariat.css" />
        <!--<script src="ext-base.js" type="text/javascript" charset="utf-8"></script>
        <script src="ext-all.js" type="text/javascript" charset="utf-8"></script>-->
    </head>
    <body class="body">
        <span class="dark"><div class="header" id="header">
                <div class="logoEMSE"><img class="logo" src="logoENSMSE.gif"/></div>
                <div class = "titreBienvenue"><h1>Bienvenue sur le projet MineWorld !</h1></div>
            </div></span>
        <span class="dark"><div class="menuGauche" name='menuGauche' id='menuGauche' class='menuGauche'>
                <ul>
                    <li><a href="">Accueil</a></li>
                    <li><a href="">Liste des pays</a></li>	
                    <li><a href="">Dernières destinations ajoutées</a></li>
                    <ul>
                        <li><a href="">Cambodge</a></li>
                        <li><a href="">Mexique</a></li>
                    </ul>
                    <li><a href="">Recherche</a></li>
                </ul>
            </div></span>
        <span class="light"><div class="formInscription" name='bodyDiv' id='bodyDiv'>
                <span class="titreInscription" >Inscription</span>
                <br/><br/>
                <form method="POST" action="inscriptionValidation">
                    <table>
                        <tr>
                            <td>E-mail</td>
                            <td><input type="text" name="email" id="email" /></td>
                        </tr>

                        <tr>
                            <td>Mot de passe</td>
                            <td><input type="text" name="password" id="email" /></td>
                        </tr>

                        <tr>
                            <td>Vérification du mot de passe</td>
                            <td><input type="text" name="pasword2" id="email" /></td>
                        </tr>
                        <tr>
                            <td>Nom</td>
                            <td><input type="text" name="name" id="nom" /></td>
                        </tr>
                        <tr>
                            <td>Prénom</td>
                            <td><input type="text" name="username" id="prenom" /></td>
                        </tr>
                        <td>Sexe</td>
                        <td>
                            Homme : <INPUT type=radio name="sexe" value=\"M\">
                            Femme : <INPUT type=radio name="sexe" value=\"F\">
                        </td>
                        <tr>
                            <td>Date de naissance</td>
                            <td>
                                <input type="text" name="day" id="jour" size="2" maxlength="2"/>
                                /<input type="text" name="month" id="mois" size="2" maxlength="2"/>
                                /<input class="annee" type="text" name="years" id="annee" size="4" maxlength="4"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Téléphone</td>
                            <td><input type="text" name="phone" id="telephone" size="10" maxlength="10"/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="valider"></td>
                        </tr>
                    </table>


                </form>

            </div></span>
        <span class="dark"><div class="footer" name='footer' id='footer'>
                <table><tr>
                        <td><a href="www.emse.fr">Site de l'école des Mines</a></td>
                        <td><blockquote><a href="http://webeleves.emse.fr">Site des élèves</a></blockquote></td>
                        <td></td>
                    </tr></table>
            </div></span>
    </body>
</html>
