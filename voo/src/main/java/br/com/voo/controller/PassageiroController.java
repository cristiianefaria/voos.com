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

import br.com.voo.bll.PassageiroBS;
import br.com.voo.dal.PassageiroDAO;
import br.com.voo.model.Passageiro;

@WebServlet("/Passageiro")
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
		String acao = request.getParameter("acao");
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

	}

}
