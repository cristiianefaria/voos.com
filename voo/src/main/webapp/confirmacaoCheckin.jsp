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
		<div class="container"></div>
		<div class="espacoTopo"></div>
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="bloco">
						<h3>Checkin Confirmado</h3>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Nome do Passageiro:</label> <input class="form-control"
										type="text" name="passageiro"
										value="<c:out value="${passagem.passageiro.pessoa.nome}"/>" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="horario">Horário</label>
									<div class='input-group date' id='horario'>
										<input name="horario" type='text' class="form-control"
											value='<time:format pattern="dd/MM/yyyy HH:mm" value="${passagem.voo.horario}" />' />
										<span class="input-group-addon"> <span><i
												class="fa fa-calendar-plus-o" aria-hidden="true"></i></span>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="espacoTopo"></div>
	</div>
	<jsp:include page="footer.jsp" />
	<script src="app/js/voo.js"></script>
</body>
</html>