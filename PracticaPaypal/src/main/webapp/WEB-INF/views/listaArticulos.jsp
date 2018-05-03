<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Tienda Online</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body>

<div class="jumbotron">
  <div class="container text-center">
    <h1>Tienda Online</h1>      
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Products</a></li>
        <li><a href="#">Deals</a></li>
        <li><a href="#">Stores</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Carrito</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Nuestros Productos</div>
        <div class="panel-body">
        <c:forEach items= "${listaArticulos}" var="lista">
<c:out value="${lista.nombre}" />
<c:out value="${lista.precio}" />
<c:out value="${lista.codigo}" />
<c:if test= "${lista.codigo==0}" >
<img src="<c:url value="/resources/images/pc.jpg"/>"/>
  <!-- Botones para el carrito  -->
 <form action="Add" method=post>
 <input type="hidden" name="accion_servlet" value="accion1">
<input type=submit value=Añadir></form>
</c:if>
<c:if test= "${lista.codigo==1}" >
<img src="<c:url value="/resources/images/movil.jpg"/>"/>
<form action="Add" method=post>
 <input type="hidden" name="accion_servlet" value="accion2">
<input type=submit value=Añadir></form>
</c:if>
</c:forEach>
<br></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
     

      </div>
      
    </div>
  

    <div class="col-sm-4">
        <div class="panel-heading">Opciones usuario</div>
        <div class="panel-body">
 <!-- Boton para modificar usuario -->
<form action="Modificar" method=post>
<input type=submit value=Modificar>
</form>

<!-- Boton pago total para llevarnos al carrito -->
<form action="Carrito" method=post>
<input type=submit value=Pago></form>
        </div>
        <div class="panel-footer"></div>
     

      </div>
      
    </div>
</div>

<footer class="container-fluid text-center">
  <p>Tienda Online Copyright</p>  
  <form class="form-inline">Get deals:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Sign Up</button>
  </form>
</footer>

</body>
</html>
