<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : index
    Created on : 3 déc. 2011, 18:45:03
    Author     : fingon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Bienvenue sur le site du projet partenariat</title>
        <meta name="description" content="Partage d'informations sur les départs à l'étranger" />

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
                <div class="inscription">
                     <form method="POST" action="connect">
                    <table classe="inscriptionTable">
                        <tr>
                            <td colspan="3">Déjà inscrit ? Identifiez-vous !</td>
                        </tr>
                        <tr>
                            <td>Identifiant</td>
                            <td><input type="text" name="login" id="login"/></td>
                            <td rowspan="2"><input type="submit" name="send" value="Connexion"/></td>
                        </tr>
                        <tr>
                            <td>Mot de passe</td>
                            <td><input type="password" name="password" id="password" onkeydown="if (event.keyCode == 13)"/></td>
                        </tr>
                        <tr>
                            <td colspan="3"><a href="inscription">Cliquez ici pour vous inscrire</a></td>
                        </tr>
                    </table>
                     </form></div>
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
        <span class="light"><div class="intro">
                <p>Cliquez sur la carte pour visiter une destination</p>
            </div></span>
        <span class="light"><div class="carte">
                <a href=""><img src="carte.jpg" height="350"/></a>
            </div></span>
        <span class="light"><div class="instructions">
                <p><a href="">Ajouter un nouveau pays</a></p>
                <p><a href="">Ajouter un lieu</a></p>
            </div>	</span>
        <span class="light"><div class="paysConnus">
                <p>Les pays déjà répertoriés sont :
                <table>
                    <tr class="alphabet">
                        <td><a href="">A</a></td>
                        <td><a href="">B</a></td>
                        <td class="ouvert">C</a></td>
                        <td><a href="">D</a></td>
                        <td><a href="">E-F</a></td>
                        <td><a href="">G</a></td>
                        <td><a href="">H-I</a></td>
                        <td><a href="">J-K</a></td>
                        <td><a href="">L</a></td>
                        <td><a href="">M</a></td>
                        <td><a href="">N</a></td>
                        <td><a href="">O</a></td>
                        <td><a href="">P-Q</a></td>
                        <td><a href="">R</a></td>
                        <td><a href="">S</a></td>
                        <td><a href="">T</a></td>
                        <td><a href="">U-V</a></td>
                        <td><a href="">W-X-Y-Z</a></td>
                    </tr>
                    <tr><td colspan="18">
                            <p><a href="">Canada</a></p>
                            <p><a href="">Chine</a></p>
                            <p><a href="">Corée du Sud</a></p>
                        </td></tr>
                </table>
            </div>	</span>
        <span class="dark"><div class="footer" name='footer' id='footer'>
                <table><tr>
                        <td><a href="http://www.emse.fr">Site de l'école des Mines</a></td>
                        <td><blockquote><a href="http://webeleves.emse.fr">Site des élèves</a></blockquote></td>
                        <td></td>
                    </tr></table>
            </div></span>
    </body>
</html>
