package br.com.voo.controller;

import java.io.IOException;
import java.text.ParseException;
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
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import br.com.voo.bll.PassageiroBS;
import br.com.voo.dal.PassageiroDAO;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;
import br.com.voo.util.ValidarPessoa;
import net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator;

@WebServlet(name = "/Passageiro", urlPatterns = "/Passageiro")
public class PassageiroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String PAGINA = "/passageiro.jsp";
	private PassageiroBS bs;

	public PassageiroController() {
		super();
		bs = new PassageiroBS();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("comando");
		List<Passageiro> passageiros = new ArrayList<Passageiro>();

		switch (acao) {
		case "listar":

			passageiros = bs.listar("");
			break;

		case "inserir":

			
			break;
		case "editar":

			int codigoEdicao = Integer.parseInt(request.getParameter("codigo"));
			Passageiro passageiro = bs.consultar(new Long(codigoEdicao));
			request.setAttribute("passageiro", passageiro);

			break;
		case "excluir":
			int codigoExclusao = Integer.parseInt(request.getParameter("codigo"));
			bs.excluir(new Long(codigoExclusao));
		default:
			break;
		}

		request.setAttribute("passageiros", passageiros);
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PassageiroBS bs = new PassageiroBS();
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf") != null ? request.getParameter("cpf") : "";
		String cnpj = request.getParameter("cnpj") != null ? request.getParameter("cnpj"): "";
		String endereco = request.getParameter("endereco");
		
		
		SimpleDateFormat dataS = new SimpleDateFormat("dd-MM-yyyy");
		Date dataNascimento = new Date();
		try {
			dataNascimento = dataS.parse(request.getParameter("dataNascimento"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		EstadoCivil estadoCivil = ValidarPessoa.estadoCivilDescricao(request.getParameter("estadoCivil"));
		String telefone = request.getParameter("telefone");
		String email = request.getParameter("email");
		
		Pessoa pessoa = new Pessoa(nome, cpf, cnpj, 
				                         endereco, dataNascimento, estadoCivil, 
				                                                     telefone, email);
		
		long codigo = request.getParameter("codigo") != null ?Long.parseLong(request.getParameter("codigo")) : 0;
		
		Passageiro passageiro = new Passageiro(pessoa);
		passageiro.setId(codigo);
		bs.salvar(passageiro);
		
		
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		request.setAttribute("passageiros", bs.listar(""));
		view.forward(request, response);

	}

}
