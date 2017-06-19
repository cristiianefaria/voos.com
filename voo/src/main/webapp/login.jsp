<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tela de Login</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<div class="container-fluid">

		<div class="telaLogin">
			<div class="espacoTopo"></div>
			<div class="formularioLogin">
				<img src="app/images/logomarca2.png">
				<h4>Acesse seu cadastro!</h4>
				<form method="POST" action="Login">
					<div class="form-group">
						<input class="hidden" type="text" name="id"> <input
							class="form-control" type="text" name="usuario"
							placeholder="USUÁRIO" value="${param.usuario}" /><br> <input
							class="form-control" type="password" name="senha"
							placeholder="SENHA">

						<p class="text-left">
							<a href="passageiro.jsp"> Faça seu cadastro </a>
						</p>

					</div>
					<div class="text-right">
						<a href="paginaInicial.jsp" class="btn btn-default">Voltar</a> <input
							class="btn btn-primary" name="botao" type="submit" value="Login">
					</div>
					<input type="hidden" name="acessar" value="login" />
				</form>
				<br>
				<c:if test="${mensagens.existeErros}">
					<div id="erro">
						<ul>
							<c:forEach var="erro" items="${mensagens.erros}">
								<li>${erro}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>