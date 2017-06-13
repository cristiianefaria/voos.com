<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprar Passagem | Voos.Com</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="altura">
		<div class="container">
			<div class="espacoTopo"></div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="bloco">
						<div class="row"></div>
						<div class="col-md-12">
							<h1>Obrigado por efetuar a compra, seu ticket foi enviado
								por email!</h1>
							<br> <a href="paginaPrincipal.jsp" class="btn btn-primary">Voltar
								para página principal</a>
						</div>
					</div>
				</div>
			</div>
			<div class="espacoTopo"></div>
		</div>
	</div>



	<jsp:include page="footer.jsp" />
</body>
</html>