<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>

<title>Cadastro Itinerário</title>

</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="page-header">
		<h1>ITINERÁRIO</h1>
		<ul class="breadcrumb">
			<li><a href="index.jsp">home</a></li>
			<li><a href="itinerario.jsp">itininerário</a></li>
		</ul>
	</div>

	<div class="container">
		<div class="bloco">
			<div class="row">
				<div class="col-md-6"></div>
				<div class="col-md-6">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Digite a cidade de origem ou destino..."> <span
							class="input-group-btn">
							<button class="btn btn-primary" type="button">Pesquisar</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="bloco">
			<button type="button" class="btn btn-primary" data-toggle="collapse"
				data-target="#adicionarItinerario">Novo Itinerário</button>
			<div id="adicionarItinerario" class="collapse">
				
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-body">
							<form method="POST" action="Itinerario" name="inserirIntinerario">
								<div class="hide">
									<div class="form-group">
										<label>Código:</label> <input type="text" id="id"
											readonly="readonly" name="id" class="form-control"
											value="<c:out value="${salvar.id}"/>" />
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Origem:</label> <input type="text" id="origem"
											name="origem" class="form-control"
											value="<c:out value="${salvar.origem}"/>" />
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Destino:</label> <input type="text"
											class="form-control" id="destino" name="destino"
											value="<c:out value="${salvar.destino}"/>" />
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Valor:</label> <input type="text" class="form-control"
											id="valor" name="valor"
											value="<c:out value="${salvar.valor}"/>" />
									</div>
								</div>


								<div class="text-right">
									<input type="reset" class="btn btn-DEFAULT" value="CANCELAR" />
									<input type="submit" class="btn btn-success" value="SALVAR" />
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class=""></div>
				<div class="panel-heading">Lista de Itinerário</div>
				<div class="col-md-8 panel-body">
					<table class="table table-striped">
						<thead>
							<tr>
								<th class="col-md-1">Código</th>
								<th class="col-md-4">Origem</th>
								<th class="col-md-4">Destino</th>
								<th class="col-md-1">Valor</th>
								<th class="col-md-2" colspan="2"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${lista}" var="listagem">
								<tr>
									<td><c:out value="${listagem.id}" /></td>
									<td><c:out value="${listagem.origem}" /></td>
									<td><c:out value="${listagem.destino}" /></td>
									<td><c:out value="${listagem.valor}" /></td>
									<td><a class="btn btn-warning"
										href="Itinerario?botao=alterar&id=<c:out value="${listagem.id}"/>">
											Alterar</a></td>
									<td><a class="btn btn-danger"
										href="Itinerario?botao=remover&id=<c:out value="${listagem.id}"/>">
											Remover</a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>