<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap -->
<link rel="stylesheet" href="app/lib/bootstrap/bootstrap.min.css">

<!-- CSS -->
<link rel="stylesheet" href="app/css/style.css">

<!-- Font Awesome -->
<link rel="stylesheet" href="app/lib/font-awesome/font-awesome.min.css">

</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">Voos.Com</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="#">Efetuar Reserva</a></li>
				<li><a href="#">Comprar Passagem</a></li>
				<li><a href="#">Fazer Checkin</a></li>
				<li><a href="#">Aeronave</a></li>
				<li><a href="ItinerarioController?botao=listar">Itinerário</a></li>
			</ul>
		</div>
	</nav>





	<footer class="container-fluid text-center ">
		<p>&copy; TODOS OS DIREITOS RESERVADOS</p>
	</footer>
</body>
</html>