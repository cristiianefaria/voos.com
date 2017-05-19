package br.com.voo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.bll.ItinerarioBS;
import br.com.voo.model.Itinerario;

@WebServlet("/ItinerarioControleler")
public class ItinerarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INCLUIR_OU_ALTERAR = "/cadastro.jsp";
	private static String LISTAR = "/listaitinerario.jsp";
	private ItinerarioBS bs;

	public ItinerarioController() {
		super();
		bs = new ItinerarioBS();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String caminho = "";
		String acao = request.getParameter("acao");

	if (acao.equalsIgnoreCase("alterar")){
		caminho = INCLUIR_OU_ALTERAR;
		String idTela = request.getParameter("id");
		Long id = (long)0;
		if(idTela != null && idTela.isEmpty()){
			id = Long.parseLong(idTela);
			Itinerario itinerario = bs.consultar(id);
			request.setAttribute("salvar", itinerario);
			request.setAttribute("lista", bs.listar());
		}
		
	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		Itinerario itinerario = new Itinerario();
		itinerario.setOrigem(request.getParameter("origem"));
		itinerario.setDestino(request.getParameter("destino"));
		itinerario.setId(Long.parseLong(request.getParameter("id")));
		
		bs.salvar(itinerario);
		
		RequestDispatcher view = request.getRequestDispatcher(INCLUIR_OU_ALTERAR);
		request.setAttribute("lista", bs.listar());
		view.forward(request, response);
	}
}
