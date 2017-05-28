<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gerenciamento de voos</title>
</head>
<body>
	<jsp:include page="header.jsp" />
    <div class="container">
         <h1>Voos</h1>
            <div id="listagem">
                <h2>Listagem dos Voos</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Horario</th>
                            <th>Origem</th>
                            <th>Destino</th>
                            <th>Aeronave</th>
                            <th colspan="2"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${voos}" var="voo">
                        	<tr>
                                <td>
                                    <c:out value="${voo.id}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${voo.horario}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${voo.itinerario.origem}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${voo.itinerario.destino}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${voo.aeronave.descricao}"></c:out>
                                </td>
                                <td>
                                    <a class="btn btn-primary" href="/Voo?acao=alterar&id=<c:out value="${voo.id}"></c:out>">Alterar</a>
                                </td>
                                <td>
                                    <a class="btn btn-danger" href="/Voo?acao=remover&id=<c:out value="${voo.id}"></c:out>">Remover</a>
                                </td>
                        	</tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="cadastro">
                <h2>Cadastro de Voo</h2>
                <form method="POST" action="/Voo">
                    <div class="row hide">
                        <div class="form-group col-md-12">
                            <label for="id"></label>
                            <input class="form-control" type="text" name="id" id="id">
                        </div>    
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label for="horario"></label>
                            <input class="form-control" type="date" name="horario" id="horario">
                        </div>    
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <select name="itinerario" id="itinerario" class="form-group">
                                <c:forEach items="${itinerarios}" var="itinerario">
                                    <option value="<c:out value="${itinerario.id}"></c:out>"><c:out value="${itinerario.origem}"></c:out> - <c:out value="${itinerario.destino}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <select name="aeronave" id="aeronave" class="form-group">
                                 <c:forEach items="${aeronaves}" var="aeronave">
                                    <option value="<c:out value="${aeronave.id}"></c:out>"><c:out value="${aeronave.descricao}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="submit" value="Salvar" class="btn btn-success">
                </form>
            </div>
    </div>
</body>
</html>