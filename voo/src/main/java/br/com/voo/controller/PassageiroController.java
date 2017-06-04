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

import br.com.voo.bll.PassageiroBS;
import br.com.voo.dal.PassageiroDAO;
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

	private Long idPessoa;

	public PassageiroController() {
		super();
		passageiroBS = new PassageiroBS();
		idPessoa = new Long(0);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("comando");
		List<Passageiro> passageiros = new ArrayList<Passageiro>();

		switch (acao) {
		case "listar":

			passageiros = passageiroBS.listar("");
			break;

		case "inserir":

			break;
		case "editar":

			int codigoEdicao = Integer.parseInt(request.getParameter("codigo"));
			Passageiro passageiro = passageiroBS.consultar(new Long(codigoEdicao));
			idPessoa = passageiro.getPessoa().getId();
			request.setAttribute("passageiro", passageiro);

			break;
		case "excluir":
			int codigoExclusao = Integer.parseInt(request.getParameter("codigo"));
			passageiroBS.excluir(new Long(codigoExclusao));
			passageiros = passageiroBS.listar("");
		default:
			break;
		}

		request.setAttribute("passageiros", passageiros);
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String botao = request.getParameter("botao");
		PassageiroBS bs = new PassageiroBS();
		String nome = "";

		if (botao.equals("Cadastrar Passageiro")) {

			Long codigo = new Long(0);
			Passageiro passageiro = new Passageiro(ObterPessoa(request));
			String codigoParam = request.getParameter("codigo");

			if (codigoParam == null || !"".equals(codigoParam)) {
				codigo = Long.parseLong(codigoParam);
			}

			passageiro.setId(codigo);
			bs.salvar(passageiro);

		}
		if (botao.equals("Pesquisar")) {
			nome = request.getParameter("pesquisa");
		}

		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		request.setAttribute("passageiros", bs.listar(nome));
		view.forward(request, response);

	}

	private Pessoa ObterPessoa(HttpServletRequest request) {

		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf") != null ? request.getParameter("cpf") : "";
		String cnpj = request.getParameter("cnpj") != null ? request.getParameter("cnpj") : "";
		String endereco = request.getParameter("endereco");

		String data = request.getParameter("dataNascimento");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dataNacimento = format.parse(data);

			EstadoCivil estadoCivil = ValidarPessoa.estadoCivilDescricao(request.getParameter("estadoCivil"));
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");

			return new Pessoa(idPessoa, nome, cpf, cnpj, endereco, dataNacimento, estadoCivil, telefone, email);
		
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
	}

}
