package br.com.voo.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.bll.ClienteBS;
import br.com.voo.bll.PessoaBS;
import br.com.voo.model.Cliente;
import br.com.voo.model.Pessoa;


@WebServlet(name = "/Cliente",  urlPatterns ="/Cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String PAGINA = "/cliente.jsp";
	private ClienteBS clienteBs;
	private Long idPessoa;
	
    public ClienteController() {
        super();
        clienteBs = new ClienteBS();
        idPessoa = new Long(0);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		List<Cliente> clientes  = new ArrayList<Cliente>();
		
		switch (acao) {
		case "listar":
			clientes = clienteBs.listar("");
			break;
		case "ediatar":
			int codigoEdicao = Integer.parseInt(request.getParameter("codigo"));
			Cliente cliente =  clienteBs.consultar(new Long(codigoEdicao));
			idPessoa = cliente.getPessoa().getId();
			request.setAttribute("cliente", cliente);
		default:
			break;
		}
		
		request.setAttribute("clientes", clientes);
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String botao = request.getParameter("botao");
		String nome = "";
		
		
		if(botao.equals("Novo")){
			
		}
		if(botao.equals("Cadastrar Clientes")){
			Long codigo = new Long(0);
			
			PessoaBS pessoaBs = new PessoaBS(request, idPessoa);
			Cliente cliente = new Cliente(pessoaBs.obterPessoa());
			String codigoParam = request.getParameter("codigo");
			
			if (codigoParam == null || !"".equals(codigoParam)) {
				codigo = Long.parseLong(codigoParam);
			}
			if (botao.equals("Pesquisar")) {
				nome = request.getParameter("pesquisa");
			}
			
			
			cliente.setId(codigo);
			String desconto = request.getParameter("percentDesconto");
			
			Double percentDesconto = new Double(0);
			if (desconto == null || !"".equals(desconto)) {
				percentDesconto = Double.parseDouble(desconto);
			}
			cliente.setPercentDesconto(percentDesconto);
			cliente.setSenha(request.getParameter("senha"));
			cliente.setTipoCliente(request.getParameter("tipoCliente"));
			
			clienteBs.salvar(cliente);
			
			RequestDispatcher view = request.getRequestDispatcher(PAGINA);
			request.setAttribute("clientes", clienteBs.listar(nome));
			view.forward(request, response);
		}
		
	}

	

}
