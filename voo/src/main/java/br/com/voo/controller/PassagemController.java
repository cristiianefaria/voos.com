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

import br.com.voo.bll.PassagemBS;
import br.com.voo.bll.VooBS;
import br.com.voo.model.Passagem;
import br.com.voo.model.Voo;


@WebServlet(name = "/Passagem", urlPatterns = "/Passagem")
public class PassagemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private PassagemBS bs;
	private String acao;
	private String PAGINA = "/passagem.jsp";
	private VooBS vooBs;
	
	
	
    public PassagemController() {
        super();
        bs = new PassagemBS();
        vooBs = new VooBS();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		acao = request.getParameter("acao");
		List<Passagem> passagens = new ArrayList<Passagem>();
		
		switch (acao) {
		case "listarPassagens":
			
			Long id = Long.parseLong(request.getParameter("id"));
			Voo voo = vooBs.consultar(new Voo(id));	
			passagens = bs.listarPassagens(voo);
			request.setAttribute("isLista", true);
			break;

		case "comprarPassagem":
			
			Long idPassagem = Long.parseLong(request.getParameter("id"));
			
			PAGINA = "/passageiro.jsp";
			
			break;
		default:
			break;
		}
		
		request.setAttribute("passagens", passagens);
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
