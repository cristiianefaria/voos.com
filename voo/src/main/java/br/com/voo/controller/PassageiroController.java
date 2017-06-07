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
import br.com.voo.model.BuilderPessoaCliente;
import br.com.voo.model.Cliente;
import br.com.voo.model.Entidade;
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
	private Long idCliente;
	private String acao;
	
	public PassageiroController() {
		super();
		passageiroBS = new PassageiroBS();
		clienteBS = new ClienteBS();
		idPessoa = new Long(0);
		idCliente = new Long(0);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		acao = request.getParameter("acao");
		List<Entidade> passageirosClientes = new ArrayList<Entidade>();
        
		
		
		switch (acao) {
		case "listarPassageiro":
			request.setAttribute("isPassageiro", true);
			passageiroBS.listar("").forEach(e -> passageirosClientes.add(e));
			break;

		case "listarCliente":
			request.setAttribute("isPassageiro", false);
			//clientes = clienteBS.listar("");
			
			break;
			
		case "editarPassageiro":
			int codigoEdicaoPassageiro = Integer.parseInt(request.getParameter("codigoPassageiro"));
			Passageiro passageiro = passageiroBS.consultar(new Long(codigoEdicaoPassageiro));
			idPessoa = passageiro.getPessoa().getId();
			request.setAttribute("passageiroCliente", passageiro);
			break;

		case "editarCliente":
			int codigoEdicaoCliente = Integer.parseInt(request.getParameter("codigoCliente"));
			Cliente cliente = clienteBS.consultar(new Long(codigoEdicaoCliente));
			idCliente = cliente.getPessoa().getId();
			request.setAttribute("passageiroCliente", cliente);
			break;

		case "excluirPassageiro":
			int codigoExclusao = Integer.parseInt(request.getParameter("codigo"));
			passageiroBS.excluir(new Long(codigoExclusao));
			//passageiros = passageiroBS.listar("");
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
		String nome = "";
		PessoaBS pessoaBs = new PessoaBS(request, idPessoa);
		String desconto = validaCampos(request.getParameter("percentDesconto"));
		String codigoParam = validaCampos(request.getParameter("codigo"));
		String senha = validaCampos(request.getParameter("senha"));
		String tipocliente = validaCampos(request.getParameter("tipoCliente"));

		BuilderPessoaCliente build = new BuilderPessoaCliente(obterPessoa(request))
										 .setPercentDesconto(desconto)
				                         .setSenha(senha)
				                         .setTipoCliente(tipocliente)
				                         .setIdBuilder(codigoParam);

		if (botao.equals("Cadastrar Passageiro")) {
			Passageiro passageiro = build.buildPassageiro();
			passageiroBS.salvar(passageiro);
			request.setAttribute("passageirosClientes", passageiroBS.listar(nome));
			request.setAttribute("isPassageiro", true);
		}

		if (botao.equals("Cadastrar Cliente")) {
			Cliente cliente = build.buildCliente();
			clienteBS.salvar(cliente);
			request.setAttribute("passageirosClientes", clienteBS.listar(nome));
			request.setAttribute("isPassageiro", false);
		}

		nome = request.getParameter("pesquisar");

		if (botao.equals("Pesquisar Passageiro")) {
			List<Passageiro> list = passageiroBS.listar(nome);
			request.setAttribute("passageirosClientes", passageiroBS.listar(nome));
			request.setAttribute("isPassageiro", true);
		}

		if (botao.equals("Pesquisar Cliente")) {
			request.setAttribute("passageirosClientes", clienteBS.listar(nome));
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

			return new Pessoa(idPessoa, nome, cpf, cnpj, endereco, dataNacimento, estadoCivil, telefone, email);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
