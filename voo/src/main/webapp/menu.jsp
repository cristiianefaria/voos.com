<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">

			<div id="acesso" class="row">
				<ul class="nav nav-pills pull-right acc">


					<li><a href="login.jsp"><i class="fa fa-user-plus"
							aria-hidden="true"></i>&nbsp;Cadastrar</a></li>
					<li><a href="login.jsp"><i class="fa fa-user"
							aria-hidden="true"></i> Olá ${sessionScope.usuarioLogado.nome} |&nbsp;Acessar conta</a></li>
					
					<li><a href="login.jsp"><i class="fa fa-sign-out"></i>&nbsp;Sair</a></li>

				</ul>
			</div>


			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp"><img
					src="app/images/logomarca2.png" height="30px" alt="Voos.com"></a>
			</div>
			<ul class="nav navbar-nav navbar-right">

				<li><a href="index.jsp">Home</a></li>
				<li><a href="#">Efetuar Reserva</a></li>
				<li><a href="Voo?acao=comprarPassagem">Comprar Passagem</a></li>
				<li><a href="#">Fazer Checkin</a></li>
				<li><a href="Voo?acao=listar">Voo</a></li>
				<li><a href="Aeronave?acao=listar">Aeronave</a></li>
				<li><a href="Itinerario?acao=listar">Itinerário</a></li>
				<li><a href="Passageiro?acao=listarPassageiro">Passageiro</a></li>
				<li><a href="Passageiro?acao=listarCliente">Cliente</a></li>

			</ul>
		</div>
	</nav>

</body>
</html>