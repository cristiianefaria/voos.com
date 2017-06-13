<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cadastro de passageiro</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="menu.jsp" />

	<div class="page-header">
		<c:if test="${isPassageiro == true}">
			<h1>Passageiro</h1>
		</c:if>
		<c:if test="${isPassageiro == false}">
			<h1>Cliente</h1>
		</c:if>
		<ul class="breadcrumb">
			<li><a href="index.jsp">home</a></li>
			<c:if test="${isPassageiro == true}">
				<li>passageiro</li>
			</c:if>
			<c:if test="${isPassageiro == false}">
				<li>cliente</li>
			</c:if>
		</ul>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger">
					<c:out value="${mensagem}" />
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="bloco">
					<form action="Passageiro" method="POST">
							<div class="row">
								<div class="col-md-6"></div>
								<div class="col-md-6">
									<div class="input-group">
										<input type="text" id="pesquisar" name="pesquisar"
											class="form-control" placeholder="Digite o nome...">
										<span class="input-group-btn"> <c:if
												test="${isPassageiro == false}">
												<button class="btn btn-primary" value="Pesquisar Cliente"
													name="botao" type="submit">Pesquisar</button>
											</c:if> 
											<c:if test="${isPassageiro == true}">
												<button class="btn btn-primary" value="Pesquisar Passageiro"
													name="botao" type="submit">Pesquisar</button>
											</c:if>
										</span>
									</div>
								</div>
							</div>
				

						
					</form>
				</div>
				<c:if test="${isPassageiro == true}">
					<h3>Cadastro de Passageiro</h3>
				</c:if>
				<c:if test="${isPassageiro == false}">
					<h3>Cadastro de Cliente</h3>
				</c:if>
				<hr>
				<div class="bloco">
					<form action="Passageiro" method="POST">

						<div class="form-group hide">
							<label for="codigo">Código</label> <input type="text"
								class="form-control" id="codigo" readonly="readonly"
								placeholder="código" name="codigo"
								value="<c:out value="${passageiroCliente.id}"/>">
						</div>

						<div class="row">
							<div class="col-md-9">
								<div class="form-group">
									<label for="nome">Nome</label> <input type="text"
										class="form-control" id="nome" placeholder="nome" name="nome"
										value="<c:out value="${passageiroCliente.pessoa.nome}"/>">
								</div>
							</div>

							<div class="col-md-3">
								<div class="form-group">
									<label for="horario">Data Nascimento</label>
									<div class='input-group date' id='dataNascimento'>
										<input name="horario" type='text' class="form-control"
											value='<time:format pattern="dd/MM/yyyy HH:mm" value="${voo.horario}" />' />
										<span class="input-group-addon"> <span><i
												class="fa fa-calendar-plus-o" aria-hidden="true"></i></span>
										</span>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="cpf">CPF</label> <input type="text"
										class="form-control" id="cpf" placeholder="cpf" name="cpf"
										value="<c:out value="${passageiroCliente.pessoa.cpf.numero}"/>">
								</div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label for="cpf">CNPJ</label> <input type="text"
										class="form-control" id="cnpj" placeholder="CNPJ" name="cnpj"
										value="<c:out value="${passageiroCliente.pessoa.cnpj.numero}"/>">
								</div>
							</div>

							<div class="col-md-4">
								<div class="form-group ">
									<label>Estado Civil</label> <select class="form-control"
										id="estadoCivil" name="estadoCivil"
										value="<c:out value="${passageiroCliente.pessoa.estadoCivil}"/>">
										<option>Casado</option>
										<option>Solteiro</option>
										<option>Divorciado</option>
										<option>Viuvo</option>
									</select>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="telefone">Telefone</label> <input type="text"
										class="form-control" id="telefone" placeholder="telefone"
										name="telefone"
										value="<c:out value="${passageiroCliente.pessoa.telefone}"/>">
								</div>
							</div>
							<div class="col-md-8">
								<div class="form-group">
									<label for="endereco">Endereco</label> <input type="text"
										class="form-control" id="endereco" placeholder="endereço"
										name="endereco"
										value="<c:out value="${passageiroCliente.pessoa.endereco}"/>">
								</div>
							</div>
						</div>


						<c:if test="${isPassageiro == false}">

							<div class="row">
								<div class="form-group col-md-4">
									<label for="percentDesconto">Desconto</label> <input
										type="text" class="form-control" id="percentDesconto"
										placeholder="desconto" name="percentDesconto"
										value="<c:out value="${passageiroCliente.percentDesconto}"/>">
								</div>

								<div class="form-group col-md-4">
									<label>Tipo Cliente</label> <select class="form-control"
										id="tipoCliente" name="estadoCivil"
										value="<c:out value="${passageiroCliente.tipoCliente}"/>">
										<option>Cliente Final</option>
										<option>Parceiro</option>
									</select>
								</div>
							</div>
						</c:if>



						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="email">Email</label> <input type="email"
										class="form-control" id="email" placeholder="email"
										name="email"
										value="<c:out value="${passageiroCliente.pessoa.email}"/>">
								</div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label for="senha">Senha</label> <input type="password"
										class="form-control" id="senha" placeholder="senha"
										name="senha"
										value="<c:out value="${passageiroCliente.senha}"/>">
								</div>
							</div>
						</div>





						<c:if test="${isPassageiro == true}">
							<div class="row">
								<div class="text-right">
									<input class="btn btn-success" type="submit"
										value="Cadastrar Passageiro" name="botao">
								</div>
							</div>
						</c:if>


						<c:if test="${isPassageiro == false}">
							<div class="row">
								<div class="text-right">
									<input class="btn btn-success" type="submit"
										value="Cadastrar Cliente" name="botao">
								</div>
							</div>
						</c:if>

					</form>
				</div>




				<c:if test="${isCadastro == false}">
					<div class="row">
						<c:if test="${isPassageiro == true}">
							<h3>Lista de Passageiros</h3>
						</c:if>
						<c:if test="${isPassageiro == false}">
							<h3>Lista de Clientes</h3>
						</c:if>
					</div>
					<hr>
					<div class="bloco">
						<table class="table table-striped">
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
								<c:forEach items="${passageirosClientes}"
									var="passageiroCliente">
									<tr>
										<td><c:out value="${passageiroCliente.id}" /></td>
										<td><c:out value="${passageiroCliente.pessoa.nome}" /></td>
										<td><c:out value="${passageiroCliente.pessoa.endereco}" /></td>
										<td><c:out value="${passageiroCliente.pessoa.cpf.numero}" /></td>
										<td><c:out
												value="${passageiroCliente.pessoa.cnpj.numero}" /></td>
										<td><c:out value="${passageiroCliente.pessoa.email}" /></td>
										<td><c:out value="${passageiroCliente.pessoa.telefone}" /></td>

										<c:if test="${isPassageiro == true}">
											<td><a class="btn btn-warning"
												href="Passageiro?acao=editarPassageiro&codigo=<c:out value="${passageiroCliente.id}"/>">Alterar</a>
											</td>
											<td><a class="btn btn-success"
												href="Passageiro?acao=excluirPassageiro&codigo=<c:out value="${passageiroCliente.id}"/>">Remover</a>
											</td>
										</c:if>

										<c:if test="${isPassageiro == false}">
											<td><a class="btn btn-warning"
												href="Passageiro?acao=editarCliente&codigo=<c:out value="${passageiroCliente.id}"/>">Alterar</a>
											</td>
											<td><a class="btn btn-success"
												href="Passageiro?acao=excluirCliente&codigo=<c:out value="${passageiroCliente.id}"/>">Remover</a>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="app/js/passageiro.js"></script>
</body>
</html>