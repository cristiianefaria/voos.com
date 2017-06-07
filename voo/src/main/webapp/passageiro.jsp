<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cadastro de passageiro</title>
</head>
<body>
	<jsp:include page="header.jsp" />

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
										class="form-control" placeholder="Digite o nome..."> <span
										class="input-group-btn">
										<c:if test="${isPassageiro == false}">
											<button class="btn btn-primary" 
												value="Pesquisar Cliente" 
												name="botao"
												type="submit">Pesquisar
											</button>
									     </c:if>
									     <c:if test="${isPassageiro == true}">
											<button class="btn btn-primary" 
												value="Pesquisar Passageiro" 
												name="botao"
											 	type="submit">Pesquisar
											</button>
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

						<div class="form-group col-md-9">
							<label for="nome">Nome</label> <input type="text"
								class="form-control" id="nome" placeholder="nome" name="nome"
								value="<c:out value="${passageiroCliente.pessoa.nome}"/>">
						</div>
						
						
						<div class="form-group col-md-3">
							<label for="datepicker">Data de Nascimento</label>
							<p>
								<input type="date" id="dataNascimento" name="dataNascimento"
									class="form-control"
									value="<fmt:formatDate pattern="MM/dd/yyyy" value="${passageiroCliente.pessoa.dataNascimento}" />">
							</p>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label for="datepicker">Data de Nascimento</label>
								<div class='input-group date' id='dataNascimento'>
									<input  type='text' class="form-control" />
									<span class="input-group-addon">
										<span><i class="fa fa-calendar-plus-o" aria-hidden="true"></i></span>
									</span>
								</div>
							</div>
						</div>

						<div class="form-group col-md-3">
							<label for="cpf">CPF</label> <input type="text"
								class="form-control" id="cpf" placeholder="cpf" name="cpf"
								value="<c:out value="${passageiroCliente.pessoa.cpf.numero}"/>">
						</div>

						<div class="form-group col-md-3">
							<label for="cpf">CNPJ</label> <input type="text"
								class="form-control" id="cnpj" placeholder="CNPJ" name="cnpj"
								value="<c:out value="${passageiroCliente.pessoa.cnpj.numero}"/>">
						</div>

						<div class="form-group col-md-3">
							<label>Estado Civil</label> <select class="form-control"
								id="estadoCivil" name="estadoCivil"
								value="<c:out value="${passageiroCliente.pessoa.estadoCivil}"/>">
								<option>Casado</option>
								<option>Solteiro</option>
								<option>Divorciado</option>
								<option>Viuvo</option>
							</select>
						</div>

						<div class="form-group col-md-3">
							<label for="email">Email</label> <input type="email"
								class="form-control" id="email" placeholder="email" name="email"
								value="<c:out value="${passageiroCliente.pessoa.email}"/>">
						</div>
						<div class="form-group col-md-3">
							<label for="telefone">Telefone</label> <input type="text"
								class="form-control" id="telefone" placeholder="telefone"
								name="telefone"
								value="<c:out value="${passageiroCliente.pessoa.telefone}"/>">
						</div>
						<div class="form-group col-md-9">
							<label for="endereco">Endereco</label> <input type="text"
								class="form-control" id="endereco" placeholder="endereço"
								name="endereco"
								value="<c:out value="${passageiroCliente.pessoa.endereco}"/>">
						</div>
						
						<c:if test="${isPassageiro == false}">
						
							<div class="form-group col-md-4">
								<label for="percentDesconto">Desconto</label> <input type="text"
									class="form-control" id="percentDesconto"
									placeholder="desconto" name="percentDesconto"
									value="<c:out value="${passageiroCliente.percentDesconto}"/>">
							</div>

							<div class="form-group col-md-4">
								<label for="senha">Senha</label> <input type="password"
									class="form-control" id="senha" placeholder="senha"
									name="senha" value="<c:out value="${passageiroCliente.senha}"/>">
							</div>

							<div class="form-group col-md-4">
								<label>Tipo Cliente</label> <select class="form-control"
									id="tipoCliente" name="estadoCivil"
									value="<c:out value="${passageiroCliente.tipoCliente}"/>">
									<option>Cliente Final</option>
									<option>Parceiro</option>
								</select>
							</div>
						</c:if>
						
                        
                       
                        <c:if test="${isPassageiro == true}">
						<div class="text-right">
							<input class="btn btn-success" type="submit"
								value="Cadastrar Passageiro" name="botao">
						</div>
						</c:if>
						
						
						<c:if test="${isPassageiro == false}">
						<div class="text-right">
							<input class="btn btn-success" type="submit"
								value="Cadastrar Cliente" name="botao">
						</div>
						</c:if>

					</form>
				</div>
                <c:if test="${isPassageiro == true}">
				<h3>Lista de Passageiros</h3>
				</c:if>
				<c:if test="${isPassageiro == false}">
				<h3>Lista de Cientes</h3>
				</c:if>
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
							<c:forEach items="${passageirosClientes}" var="passageiroCliente">
								<tr>
									<td><c:out value="${passageiroCliente.id}" /></td>
									<td><c:out value="${passageiroCliente.pessoa.nome}" /></td>
									<td><c:out value="${passageiroCliente.pessoa.endereco}" /></td>
									<td><c:out value="${passageiroCliente.pessoa.cpf.numero}" /></td>
									<td><c:out value="${passageiroCliente.pessoa.cnpj.numero}" /></td>
									<td><c:out value="${passageiroCliente.pessoa.email}" /></td>
									<td><c:out value="${passageiroCliente.pessoa.telefone}" /></td>

									<c:if test="${isPassageiro == true}">
										<td>
											<a class="btn btn-warning"
											href="Passageiro?acao=editarPassageiro&codigo=<c:out value="${passageiroCliente.id}"/>">Alterar</a>
										</td>
										<td>
											<a class="btn btn-success"
											href="Passageiro?acao=excluirPassageiro&codigo=<c:out value="${passageiroCliente.id}"/>">Remover</a>
										</td>
								    </c:if>
								    
								    <c:if test="${isPassageiro == false}">
										<td>
											<a class="btn btn-warning"
											href="Passageiro?acao=editarCliente&codigo=<c:out value="${passageiroCliente.id}"/>">Alterar</a>
										</td>
										<td>
											<a class="btn btn-success"
											href="Passageiro?acao=excluirCliente&codigo=<c:out value="${passageiroCliente.id}"/>">Remover</a>
										</td>

								    </c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>