<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro Itinerário</title>
</head>
<body>
					<form method="POST" action="ItinerarioController"
						name="adicionarCliente">
						
						id:
						<input type="text" id="id" name="id" readonly="readonly"
										value="<c:out value="${salvar.id}"/>" />
						Origem:
						<input type="text" id="origem" name="origem"
										value="<c:out value="${salvar.origem}"/>" />
						
						
						Destino:
						<input type="text"
										id="destino" name="destino"
										value="<c:out value="${salvar.destino}"/>" />
						 <input type="submit" class="btn btn-primary" value="SALVAR" />
						</form>

</body>
</html>