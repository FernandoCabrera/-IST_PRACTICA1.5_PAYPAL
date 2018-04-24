<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>
<center>Articulos</center>
</h1>

<table border = "1">
<tr>
<th> Codigo </th>
<th> Nombre </th>
<th> Precio </th>
</tr>
<tr>
<c:forEach items= "${list}" var="li">
<th><c:out value="${li.codigo}" /></th>
<th><c:out value="${li.nombre}" /></th>
<th><c:out value="${li.precio}" /></th>
</c:forEach> 
</tr>

</table>


</body>
</html>