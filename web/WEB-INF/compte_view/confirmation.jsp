<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : confirmation
    Created on : 5 déc. 2011, 00:47:11
    Author     : fingon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>confirmation Page</title>
    </head>
    <body>
        <h1>+1!</h1>
        
    <sql:query var="result" dataSource="jdbc/mineworld">
        SELECT * FROM compte
    </sql:query>
    
    <table border="1">
        <!-- column headers -->
        <tr>
        <c:forEach var="columnName" items="${result.columnNames}">
            <th><c:out value="${columnName}"/></th>
        </c:forEach>
    </tr>
    <!-- column data -->
    <c:forEach var="row" items="${result.rowsByIndex}">
        <tr>
        <c:forEach var="column" items="${row}">
            <td><c:out value="${column}"/></td>
        </c:forEach>
        </tr>
    </c:forEach>
</table>
    </body>
</html>
