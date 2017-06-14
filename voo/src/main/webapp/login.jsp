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
	<jsp:include page="menu.jsp" />
	<div class="container-fluid">

		<div class="telaLogin">
			<div class="espacoTopo"></div>
			<div class="formularioLogin">
				<img src="app/images/logomarca2.png">
				<h4>Acesse sua conta</h4>
				<form method="POST" action="Login">
					<div class="form-group">
						<input class="hidden" type="text" name="id"> <input
							class="form-control" type="text" name="usuario"
							placeholder="USUÁRIO" value="${param.usuario}" /><br> <input
							class="form-control" type="password" name="senha"
							placeholder="SENHA">

						<p>
							<a href="cliente.jsp"> Faça seu cadastro </a><span>|</span> <a
								href="#"> Esqueceu a senha?</a>
						</p>

					</div>
					<div class="text-right">
						<input class="btn btn-primary" type="submit" value="LOGIN">
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
	<jsp:include page="footer.jsp" />
</body>
</html>