<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tela de Login</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="conteiner-fluid">

		<div class="telaLogin">
			<div class="espacoTopo"></div>
			<div class="formularioLogin">
				<img src="app/images/logomarca2.png">
				<h4>Acesse sua conta</h4>
				<form action="#" method="post">
					<div class="form-group">
						<input class="form-control" type="text" Name="Username"
							placeholder="USUÁRIO" required="required"><br> <input
							class="form-control" type="password" Name="Password"
							placeholder="SENHA" required="required">

						<p>
							<a href="#"> Faça seu cadastro </a><span>|</span> <a href="#">
								Esqueceu a senha?</a>
						</p>

					</div>
					<div class="text-right">
						<input class="btn btn-primary" type="submit" value="LOGIN">
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>