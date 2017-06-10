<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://sargue.net/jsptags/time" prefix="time"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gerenciamento de voos</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="page-header">
		<h1>Voo</h1>
		<ul class="breadcrumb">
			<li><a href="index.jsp">home</a></li>
			<li>voo</li>
		</ul>
	</div>

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="bloco">
					<div class="row">
						<div class="col-md-6"></div>
						<div class="col-md-6">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="Digite o código do voo...">
								<span class="input-group-btn">
									<button class="btn btn-primary" type="button">Pesquisar</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<h3>Cadastro de Voo</h3>
				<hr>
				<div class="bloco">
					<form method="POST" action="/Voo">
						<div class="row hide">
							<div class="form-group col-md-12">
								<label for="id"></label> <input class="form-control" type="text"
									name="id" id="id">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="horario">Horário</label>
								<div class='input-group date' id='horario'>
									<input name="horario" type='text' class="form-control" value='<time:format pattern="dd/MM/yyyy HH:mm" value="${voo.horario}" />' />
									<span class="input-group-addon">
										<span><i class="fa fa-calendar-plus-o" aria-hidden="true"></i></span>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sel1">Selecione o itinerário:</label> <select
									name="itinerario" id="itinerario" class="form-control">
									<c:forEach items="${itinerarios}" var="itinerario">
										<option value="<c:out value="${itinerario.id}"></c:out>" 
											${itinerario.id == voo.itinerario.id ? 'selected="selected"' : ''}>
											<c:out value="${itinerario.origem}"></c:out> -
											<c:out value="${itinerario.destino}"></c:out>
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sel1">Selecione a aeronave:</label> <select
									class="form-control" name="aeronave" id="aeronave">
									<c:forEach items="${aeronaves}" var="aeronave">
										<option value="<c:out value="${aeronave.id}"></c:out>"
										${aeronave.id == voo.aeronave.id ? 'selected="selected"' : ''}>
										<c:out value="${aeronave.descricao}"></c:out></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="text-right">
							<input type="submit" class="btn btn-success" value="SALVAR" />
						</div>
					</form>
				</div>
				<h3>Lista de Voos</h3>
				<hr>
				<div id="listagem">
					<div class="bloco">
						<table class="table table-striped">
							<thead>
								<tr>
									<th class="col-md-1">Código</th>
									<th class="col-md-3">Horário</th>
									<th class="col-md-2">Origem</th>
									<th class="col-md-2">Destino</th>
									<th class="col-md-3">Aeronave</th>
									<th class="col-md-2" colspan="2"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${voos}" var="voo">
									<tr>
										<td><c:out value="${voo.id}"></c:out></td>
										<td>
											<time:format pattern="dd/MM/yyyy HH:mm" value="${voo.horario}" />
										</td>
										<td><c:out value="${voo.itinerario.origem}"></c:out></td>
										<td><c:out value="${voo.itinerario.destino}"></c:out></td>
										<td><c:out value="${voo.aeronave.descricao}"></c:out></td>
										<td><a class="btn btn-warning"
											href="/Voo?acao=alterar&id=<c:out value="${voo.id}"></c:out>">Alterar</a>
										</td>
										<td><a class="btn btn-danger"
											href="/Voo?acao=remover&id=<c:out value="${voo.id}"></c:out>">Remover</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div></div>
			</div>
		</div>


	<script src="app/js/voo.js"></script>
	<jsp:include page="footer.jsp" />
</body>
</html>