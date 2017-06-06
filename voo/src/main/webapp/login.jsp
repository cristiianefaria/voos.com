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

			<div class="espacoTopo">
				<div class="formularioLogin">
					<h3>Acesse sua conta</h3>
					<form action="#" method="post">
						<div class="form-group">
							<input class="form-control" type="email" Name="Username"
								placeholder="USUÁRIO" required="required"> <input
								class="form-control" type="password" Name="Password"
								placeholder="SENHA" required="required">
							<div>
								<input class="btn btn-primary" type="submit" value="LOGIN">
								<p>
									<a class="btn btn-default" href="#"> Faça seu cadastro</a> <a
										class="btn btn-warning" href="#">Esqueceu a senha?</a>

								</p>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>