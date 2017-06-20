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
					<form action="Checkin" method="POST">
					<h3 style="text-align: center;">Realizar Checkin</h3>
						<div class="row">
							<div class="col-md-10">
								<label>Identificador</label>
								<input class="form-control" placeholder="Informe o Identificador" type="text" name="hash"> 
							</div>
							<div class ="col-md-2">
								<div class="form-group">
									<label for="teste" style="color: white;">gfhf</label><br/>
									<input id="teste" name="teste" type="submit" class="btn btn-primary" style="text-align: right;">	
								</div>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
			<div class="espacoTopo"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>