package br.com.voo.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import br.com.voo.bll.ClienteBS;
import br.com.voo.bll.PassageiroBS;
import br.com.voo.bll.PessoaBS;
import br.com.voo.dal.PassageiroDAO;
import br.com.voo.model.Builder;
import br.com.voo.model.Cliente;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;
import br.com.voo.util.Data;
import br.com.voo.util.ValidarPessoa;
import net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator;

@WebServlet(name = "/Passageiro", urlPatterns = "/Passageiro")
public class PassageiroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String PAGINA = "/passageiro.jsp";
	private PassageiroBS passageiroBS;
	private ClienteBS clienteBS;

	private Long idPessoa;
	private String acao;

	public PassageiroController() {
		super();
		passageiroBS = new PassageiroBS();
		clienteBS = new ClienteBS();
		idPessoa = new Long(0);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		acao = request.getParameter("acao");
		List<Passageiro> passageiros = new ArrayList<Passageiro>();
		List<Cliente> clientes = new ArrayList<Cliente>();

		switch (acao) {
		case "listarPassageiro":
			request.setAttribute("isPassageiro", true);
			passageiros = passageiroBS.listar("");
			request.setAttribute("passageirosClientes", passageiros);
			break;
		case "listarCliente":
			request.setAttribute("isCliente", true);
			request.setAttribute("isPassageiro", false);
			clientes = clienteBS.listar("");
			break;

		case "inserir":

			break;
		case "editarPassageiro":

			int codigoEdicao = Integer.parseInt(request.getParameter("codigo"));
			Passageiro passageiro = passageiroBS.consultar(new Long(codigoEdicao));
			idPessoa = passageiro.getPessoa().getId();
			request.setAttribute("passageiroCliente", passageiro);

			break;
		case "excluirPassageiro":
			int codigoExclusao = Integer.parseInt(request.getParameter("codigo"));
			passageiroBS.excluir(new Long(codigoExclusao));
			passageiros = passageiroBS.listar("");
		default:
			break;
		}

		
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String botao = request.getParameter("botao");

		String nome = "";
		PessoaBS pessoaBs = new PessoaBS(request, idPessoa);
		String desconto = request.getParameter("percentDesconto");

		String codigoParam = request.getParameter("codigo");

		Long codigo = new Long(0);
		if (codigoParam == null || !"".equals(codigoParam)) {
			codigo = Long.parseLong(codigoParam);
		}

		Builder build = new Builder();
		build.setPessoa(pessoaBs.obterPessoa());

		if (botao.equals("Cadastrar Passageiro")) {

			Passageiro passageiro = build.buildPassageiro();
			passageiroBS.salvar(passageiro);

		}
		if (botao.equals("Cadastrar Cliente")) {

			String senha = request.getParameter("senha");
			String tipocliente = request.getParameter("tipoCliente");
			Double percentDesconto = new Double(0);
			if (desconto == null || !"".equals(desconto)) {
				percentDesconto = Double.parseDouble(desconto);
			}

			build.setPercentDesconto(percentDesconto);
			build.setSenha(senha);
			build.setTipoCliente(tipocliente);

			Cliente cliente = build.buildCliente();
			clienteBS.salvar(cliente);

		}

		if (botao.equals("Pesquisar")) {
			nome = request.getParameter("pesquisa");
		}

		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		request.setAttribute("passageiros", passageiroBS.listar(nome));
		view.forward(request, response);

	}

}
