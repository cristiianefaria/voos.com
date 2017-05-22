<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<<<<<<< HEAD
<title>Consulta Itinerario</title>
<jsp:include page="importacao.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr>
	<div class="panel-body">

<<<<<<< HEAD
=======
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="2">
		<thead>
			<tr>
				<th>Código</th>
				<th>Origem</th>
				<th>Destino</th>
				<th colspan="2"></th>
			</tr>
		</thead>
		<tbody>

>>>>>>> crud itinerario
			<c:forEach items="${lista}" var="listagem">
				<tr>
					<td><c:out value="${listagem.id}" /></td>
					<td><c:out value="${listagem.origem}" /></td>
					<td><c:out value="${listagem.destino}" /></td>
					<td><a
						href="ItinerarioController?acao=alterar&id=<c:out value="${listagem.id}"/>">
							Alterar</a></td>
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

					<td><a href="ItinerarioController?acao=remover&id=<c:out value="${listagem.id}"/>">
							Remover</a></td>

					<td><a href="ItinerarioController?acao=remover&id=<c:out value="${listagem.id}"/>">
=======
					<td><a
						href="ItinerarioController?acao=remover&id=<c:out value="${listagem.id}"/>">
>>>>>>> crud itinerario
=======
					<td><a href="ItinerarioController?acao=remover&id=<c:out value="${listagem.id}"/>">
>>>>>>> crud itinerario
							Remover</a></td>
=======
					<td><a
						href="ItinerarioController?acao=remover&id=<c:out value="${listagem.id}"/>">
							Remover</a></td>

>>>>>>> crud itinerario
=======
					<td><a href="ItinerarioController?acao=remover&id=<c:out value="${listagem.id}"/>">
							Remover</a></td>
					
>>>>>>> crud itinerario
				</tr>
			</c:forEach>
		</tbody>
	</table>
<<<<<<< HEAD
=======
		<div class="container">
			
>>>>>>> Adicionado frameworks html no projeto, estilização da página itinerario

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Código</th>
						<th>Origem</th>
						<th>Destino</th>
						<th colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista}" var="listagem">
						<tr>
							<td><c:out value="${listagem.id}" /></td>
							<td><c:out value="${listagem.origem}" /></td>
							<td><c:out value="${listagem.destino}" /></td>
							<td><a class="btn btn-warning"
								href="ItinerarioController?acao=alterar&id=<c:out value="${listagem.id}"/>">
									Alterar</a></td>
							<td><a class="btn btn-danger"
								href="ItinerarioController?acao=remover&id=<c:out value="${listagem.id}"/>">
									Remover</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
=======


>>>>>>> crud itinerario
</body>
</html>