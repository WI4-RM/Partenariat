<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : recherche
    Created on : 15 déc. 2011, 16:26:06
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<div class="divBody">
    <form>
        <input type="hidden"name="type" value="avancee">
        <p>Rechercher un profil</p>
        <input type="text" name="profil"/>
        <p>Rechercher un pays</p>
        <input type="text" name="pays"/>
        <p>Rechercher une ville</p>
        <input type="text" name="ville"/>
        <input type="submit" value="Recherche">
    </form>
    <p>Poutou</p>
</div>

