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
				
				<h3>Voos</h3>
				<hr>
				
				<c:forEach items="${voos}" var="voo">
					<div class="bloco">
							<form>
								<div class="row passagem">
									<div class="col-md-10 dados">
										<input style="display: none;" type="text" value="<c:out value="${voo.id}"></c:out>" name="idPAssagem"/>
										<div class="row" style="heigth: 20px;">
											<div class="col-xs-2">
												<div class="icone-aviao">
													<i class="fa fa-plane fa-3x" aria-hidden="true"></i>
												</div>
											</div>
											<div class="col-xs-10 horario" >
												<h4><span class="horarioVoo"><time:format pattern="dd/MM/yyyy HH:mm" value="${voo.horario}" /></span></h4>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-4 col-xs-offset-2">
												<h4 class="cidade"><c:out value="${voo.itinerario.origem}"></c:out></h4>
											</div>
											<div class="col-xs-2" >
												<div class="icone-flecha ">
													<i class="fa fa-long-arrow-right fa-3x" aria-hidden="true"></i>
												</div>
											</div>
											<div class="col-xs-4">
												<h4 class="cidade"><c:out value="${voo.itinerario.destino}"></c:out></h4>
											</div>
										</div>
										
									</div>
									<div class="col-md-2 valor hvr-bounce-to-right">
										<div class="row horario-voo">
											<h4><strong>Valor</strong>	</h4>
										</div>
										<div class="row custo">
											<h4>Apartir de <strong><span>R$<c:out value="${voo.itinerario.valor + voo.aeronave.poltronas[0].valor}"></c:out></span></strong></h4>
										</div>
										<div class="row botao-comprar">
											<a class="btn btn-success" href="/Passagem?acao=alterar&id=<c:out value="${voo.id}"></c:out>">Comprar</a>
										</div>
									</div>
								</div>
							</form>
					</div>
				</c:forEach>
				
			</div>
		</div>
	</div>

	<link rel="stylesheet" type="text/css" href="app/css/ListagemVoo.css">
	<link rel="stylesheet" type="text/css" href="app/css/hover.css">
	<script src="app/js/ListagemVoo.js"></script>
	<jsp:include page="footer.jsp" />
</body>
</html>