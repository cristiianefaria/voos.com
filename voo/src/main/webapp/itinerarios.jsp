<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Consulta Itinerario</title>
<jsp:include page="importacao.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr>
	<div class="panel-body">

		<div class="container">
			

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
</body>
</html>