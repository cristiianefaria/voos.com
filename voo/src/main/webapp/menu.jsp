

<%@page import="br.com.voo.util.Utilitaria"%>
<%@page import="br.com.voo.model.Cliente"%>
<%@page import="br.com.voo.model.Pessoa"%>

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
				<div class="navbar-header">
					<a class="navbar-brand" href="index.jsp"><img
						src="app/images/logomarca2.png" height="30px" alt="Voos.com"></a>
				</div>
				<ul class="nav nav-pills pull-right acc">

					<c:if test="${not empty usuarioLogado}">
						<li><a href="#"> <i class="fa fa-user"></i> <%=(session.getAttribute("usuarioLogado") == null ? ""
						: Utilitaria
								.getSaudacao(((Cliente) session.getAttribute("usuarioLogado")).getPessoa().getNome()))%>
								&nbsp;
						</a></li>
						<li><a href="Login?botao=sair"><i class="fa fa-sign-out"></i>&nbsp;Sair</a></li>
					</c:if>
					<c:if test="${empty usuarioLogado}">
						<li><a href="login.jsp"><i class="fa fa-user"></i>&nbsp;Acessar
								Conta</a></li>
						<li><a href="passageiro.jsp"><i class="fa fa-user-plus"></i>&nbsp;Cadastrar</a></li>
					</c:if>

				</ul>
			</div>

			<c:if test="${not empty usuarioLogado}">

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
			</c:if>
		</div>
	</nav>

</body>
</html>