package br.com.voo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.bll.ItinerarioBS;

@WebServlet("/Index")
public class IndexController extends HttpServlet {

	private final String PAGINA = "/paginaInicial.jsp";
	ItinerarioBS itinerarioBS;
	
	public IndexController(){
		itinerarioBS = new ItinerarioBS();
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("itinerarios", itinerarioBS.listar());
		RequestDispatcher view = req.getRequestDispatcher(PAGINA);
		view.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	
	
	
	
}
