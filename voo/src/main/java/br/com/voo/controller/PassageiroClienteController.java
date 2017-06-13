package br.com.voo.controller;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.bll.ClienteBS;
import br.com.voo.bll.PassageiroBS;
import br.com.voo.bll.PessoaBS;
import br.com.voo.model.BuilderPessoaCliente;
import br.com.voo.model.Cliente;
import br.com.voo.model.Entidade;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;
import br.com.voo.util.ValidarPessoa;

@WebServlet(name = "/Passageiro", urlPatterns = "/Passageiro")
public class PassageiroClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String PAGINA = "/passageiro.jsp";
	private PassageiroBS passageiroBS;
	private ClienteBS clienteBS;

	private Long idPassageiroCliente;
	private Long idPessoa;
	private String acao;
	private boolean isErro;
	private boolean isCadastro;
	private Long idPassagem;

	public PassageiroClienteController() {
		super();
		passageiroBS = new PassageiroBS();
		clienteBS = new ClienteBS();
		idPassageiroCliente = new Long(0);
		idPessoa = new Long(0);
		isCadastro = false;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		acao = request.getParameter("acao");
		List<Entidade> passageirosClientes = new ArrayList<Entidade>();
		request.setAttribute("isErro", false);
		request.setAttribute("isEditar", false);
		request.setAttribute("isCadastro", false);

		switch (acao) {
		case "listarPassageiro":
			request.setAttribute("isPassageiro", true);
			passageiroBS.listar("").forEach(e -> passageirosClientes.add(e));
			break;

		case "listarCliente":
			request.setAttribute("isPassageiro", false);
			clienteBS.listar("").forEach(c -> passageirosClientes.add(c));

			break;

		case "editarPassageiro":
			int codigoEdicaoPassageiro = Integer.parseInt(request.getParameter("codigo"));
			Passageiro passageiro = passageiroBS.consultar(new Long(codigoEdicaoPassageiro));
			idPassageiroCliente = passageiro.getId();
			idPessoa = passageiro.getPessoa().getId();
			request.setAttribute("isPassageiro", true);
			request.setAttribute("passageiroCliente", passageiro);
			break;

		case "editarCliente":

			int codigoEdicaoCliente = Integer.parseInt(request.getParameter("codigo"));
			Cliente cliente = clienteBS.consultar(new Long(codigoEdicaoCliente));
			idPassageiroCliente = cliente.getId();
			idPessoa = cliente.getPessoa().getId();
			request.setAttribute("isPassageiro", false);
			request.setAttribute("passageiroCliente", cliente);
			break;

		case "meusDados":

			int codigo = Integer.parseInt(request.getParameter("codigo"));
			Cliente dadosDoCliente = clienteBS.consultar(new Long(codigo));
			idPassageiroCliente = dadosDoCliente.getId();
			idPessoa = dadosDoCliente.getPessoa().getId();
			request.setAttribute("isPassageiro", false);
			request.setAttribute("passageiroCliente", dadosDoCliente);
			break;

		case "excluirPassageiro":
			int codigoExclusao = Integer.parseInt(request.getParameter("codigo"));
			passageiroBS.excluir(new Long(codigoExclusao));
			request.setAttribute("isPassageiro", true);
			passageiroBS.listar("").forEach(c -> passageirosClientes.add(c));

			break;

		case "excluirCliente":
			int codigoExclusaoCliente = Integer.parseInt(request.getParameter("codigo"));
			clienteBS.excluir(new Long(codigoExclusaoCliente));
			request.setAttribute("isPassageiro", false);
			clienteBS.listar("").forEach(c -> passageirosClientes.add(c));
			break;
		case "cadastrarPassageiro":
			idPassagem = Long.parseLong(request.getParameter("idPassagem"));
			request.setAttribute("isPassageiro", true);
			request.setAttribute("isCadastro", true);
			isCadastro = true;

			break;
		default:
			break;
		}

		request.setAttribute("passageirosClientes", passageirosClientes);
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String botao = request.getParameter("botao");
		String busca = "";
		String desconto = validaCampos(request.getParameter("percentDesconto"));
		String codigoParam = validaCampos(request.getParameter("codigo"));
		String senha = validaCampos(request.getParameter("senha"));
		String tipocliente = validaCampos(request.getParameter("tipoCliente"));

		BuilderPessoaCliente build = new BuilderPessoaCliente(obterPessoa(request)).setPercentDesconto(desconto)
				.setTipoCliente(tipocliente).setIdBuilder(idPassageiroCliente);

		if (botao.equals("Cadastrar Passageiro")) {

			Passageiro passageiro = build.buildPassageiro();
			try {
				passageiroBS.salvar(passageiro);
			} catch (Exception e) {

				request.setAttribute("isErro", true);
				request.setAttribute("mensagem", e.getMessage());

			}

			request.setAttribute("passageirosClientes", passageiroBS.listar(busca));
			request.setAttribute("isPassageiro", true);
		}

		if (botao.equals("Cadastrar Cliente")) {
			Cliente cliente = build.buildCliente();
			try {
				clienteBS.salvar(cliente);
			} catch (Exception e) {
				request.setAttribute("isErro", true);
				request.setAttribute("mensagem", e.getMessage());
			}

			request.setAttribute("passageirosClientes", clienteBS.listar(busca));
			request.setAttribute("isPassageiro", false);
		}

		busca = request.getParameter("pesquisar");

		if (botao.equals("Pesquisar Passageiro")) {

			if (isCadastro) {
				try {
					
					Passageiro consultarPorCpf = passageiroBS.consultarPorCpf(busca);
					idPassageiroCliente = consultarPorCpf.getId();
					request.setAttribute("passageiroCliente", consultarPorCpf);
					
				} catch (Exception e) {
					
					request.setAttribute("isErro", true);
					request.setAttribute("mensagem", e.getMessage());
				}

			}

			else {
				List<Passageiro> list = passageiroBS.listar(busca);
				request.setAttribute("passageirosClientes", passageiroBS.listar(busca));
			}

			request.setAttribute("isPassageiro", true);
		}

		if (botao.equals("Pesquisar Cliente")) {
			request.setAttribute("passageirosClientes", clienteBS.listar(busca));
			request.setAttribute("isPassageiro", false);
		}

		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);

	}

	private String validaCampos(String parametro) {
		return parametro != null ? parametro : "";
	}

	public Pessoa obterPessoa(HttpServletRequest request) {

		String nome = validaCampos(request.getParameter("nome"));
		String cpf = validaCampos(request.getParameter("cpf"));
		String cnpj = validaCampos(request.getParameter("cnpj"));
		String endereco = validaCampos(request.getParameter("endereco"));

		String data = validaCampos(request.getParameter("dataNascimento"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dataNacimento = new Date();
			if (!data.equals("")) {
				dataNacimento = format.parse(data);
			}

			String estadoCivilParam = validaCampos(request.getParameter("estadoCivil"));

			EstadoCivil estadoCivil = EstadoCivil.Solteiro;
			if (!estadoCivilParam.equals("") || estadoCivilParam != null)
				estadoCivil = ValidarPessoa.estadoCivilDescricao(estadoCivilParam);
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			return new Pessoa(idPessoa, nome, cpf, cnpj, endereco, dataNacimento, estadoCivil, telefone, email, senha);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
