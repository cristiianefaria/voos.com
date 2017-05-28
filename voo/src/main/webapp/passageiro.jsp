<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de passageiro</title>
</head>
<body>
	<div>
		<form action="Passageiro" method="POST">

			<div class="form-group col-md-3">
				<label for="codigo">Código</label> <input type="text"
					class="form-control" id="codigo" readonly="readonly"
					placeholder="código" name="codigo"
					value="<c:out value="${passageiro.id}"/>">
			</div>

			<div class="form-group col-md-9">
				<label for="nome">Nome</label> <input type="text"
					class="form-control" id="nome" placeholder="nome" name="nome"
					value="<c:out value="${passageiro.pessoa.nome}"/>">
			</div>

			<div class="form-group col-md-9">
				<label for="nome">Endereço</label> <input type="text"
					class="form-control" id="nome" placeholder="nome" name="nome"
					value="<c:out value="${passageiro.pessoa.endereco}"/>">
			</div>

			<div class="form-group col-md-3">
				<label for="datepicker">Data de Nascimento</label>
				<p>
					<input type="date" id="dataNascimento" name="dataNascimento"
						class="form-control"
						value="<fmt:formatDate pattern="MM/dd/yyyy" value="${passageiro.pessoa.dataNascimento}" />">
				</p>
			</div>

			<div class="form-group col-md-3">
				<label>Estado Civil</label> <select class="form-control" id="estadoCivil"
					name="estadoCivil" value="<c:out value="${passageiro.pessoa.estadoCivil}"/>">
					<option>Casado</option>
					<option>Solteiro</option>
					<option>Divorciado</option>
					<option>Viuvo</option>
				</select>
			</div>

			<div class="form-group col-md-12">
				<label for="email">Email</label> <input type="email"
					class="form-control" id="email" placeholder="email" name="email"
					value="<c:out value="${passageiro.pessoa.email}"/>">
			</div>

			<div class="form-group col-md-3">
				<label for="cpf">CPF</label> <input type="text" class="form-control"
					id="cpf" placeholder="cpf" name="cpf"
					value="<c:out value="${passageiro.pessoa.cpf.numero}"/>">
			</div>

			<div class="form-group col-md-3">
				<label for="cpf">CNPJ</label> <input type="text" class="form-control"
					id="cpf" placeholder="cpf" name="cpf"
					value="<c:out value="${passageiro.pessoa.cnpj.numero}"/>">
			</div>

			<div class="form-group col-md-3">
				<label for="telefone">Telefone</label> <input type="text"
					class="form-control" id="telefone" placeholder="telefone"
					name="telefone" value="<c:out value="${passageiro.pessoa.telefone}"/>">
			</div>
			
			<div class="form-group col-md-3">
				<label for="endereco">endereco</label> <input type="text"
					class="form-control" id="telefone" placeholder="telefone"
					name="telefone" value="<c:out value="${passageiro.pessoa.endereco}"/>">
			</div>

			<input class="btn btn-success" type="submit"
				value="Cadastrar Passageiro" name="botao">

		</form>
		<div>
			<h2>Passageiros</h2>
			<table>
				<thead>
					<tr>
						<th>Código</th>
						<th>Nome</th>
						<th>Endereço</th>
						<th>CPF</th>
						<th>CNPJ</th>
						<th>E-Miail</th>
						<th>Telefone</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${passageiros}" var="passageiro">
						<tr>
							<td><c:out value="${passageiro.id}" /></td>
							<td><c:out value="${passageiro.pessoa.nome}" /></td>
							<td><c:out value="${passageiro.pessoa.endereco}" /></td>
							<td><c:out value="${passageiro.pessoa.cpf.numero}" /></td>
							<td><c:out value="${passageiro.pessoa.cnpj.numero}" /></td>
							<td><c:out value="${passageiro.pessoa.email}" /></td>
							<td><c:out value="${passageiro.pessoa.telefone}" /></td>
							<td><a
								href="Passageiro?comando=editar&codigo=<c:out value="${passageiro.id}"/>">Atualizar</a></td>
							<td><a
								href="Passageiro?comando=excluir&codigo=<c:out value="${passageiro.id}"/>">Excluir</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>