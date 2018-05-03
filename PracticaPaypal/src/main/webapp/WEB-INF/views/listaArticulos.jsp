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
      
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="home">Home</a></li>
        <li><a href="#">Products</a></li>
        <li><a href="Modificar">Modificar Datos Usuario</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Carrito"><span class="glyphicon glyphicon-shopping-cart"></span> Carrito</a></li>
      </ul>
    </div>
  </div>
</nav>

 
<div class="container">    
<c:forEach items= "${listaArticulos}" var="lista">
  <c:if test= "${lista.codigo == 0}" >
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">Pantalones deportivos</div>
        <div class="panel-body"><img src="/practicaPaypal/resources/images/pantalones.jpg" class="img-responsive" style="width:100%"></div>
        <div class="panel-footer">Articulo: ${lista.nombre}<br>Precio: ${lista.precio }
       <!-- Botones para el carrito  -->
	 <form action="Add" method=post>
	 <input type="hidden" name="accion_servlet" value="accion1">
	<input type=submit value=Añadir></form>
      </div>
      </div>
    </div>
    </c:if>
    <c:if test= "${lista.codigo == 1}" >
    <div class="col-sm-4"> 
      <div class="panel panel-danger">
        <div class="panel-heading">Camisetas de Fútbol</div>
        <div class="panel-body"><img src="/practicaPaypal/resources/images/camiseta.jpg" class="img-responsive" style="width:100%"></div>
		<div class="panel-footer">Articulo: ${lista.nombre}<br>Precio: ${lista.precio }
		 <!-- Botones para el carrito  -->
		 <form action="Add" method=post>
		 <input type="hidden" name="accion_servlet" value="accion2">
		<input type=submit value=Añadir></form>
		
		</div>
      </div>
    </div>
    </c:if>
    <c:if test= "${lista.codigo == 2}" >
    <div class="col-sm-4"> 
      <div class="panel panel-success">
        <div class="panel-heading">Zapatillas Deportivas</div>
        <div class="panel-body"><img src="/practicaPaypal/resources/images/BotasMercurial.jpg" class="img-responsive" style="width:100%">
     <div class="panel-footer">Articulo: ${lista.nombre}<br>Precio: ${lista.precio }
       <!-- Botones para el carrito  -->
	 <form action="Add" method=post>
	 <input type="hidden" name="accion_servlet" value="accion2">
	<input type=submit value=Añadir></form>
      </div>
      </div>
    </div>
  </div>
  </c:if>
  </c:forEach>
</div>

<br><br>



<footer class="container-fluid text-center">
  <p>Tienda Online Copyright</p>  
  <form class="form-inline">Get deals:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Sign Up</button>
  </form>
</footer>

</body>
</html>