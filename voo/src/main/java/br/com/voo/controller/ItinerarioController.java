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

@WebServlet("/Itinerario")
public class ItinerarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String FORMULARIO = "/itinerario.jsp";
	private ItinerarioBS bs;
	Itinerario itinerario = new Itinerario();

	public ItinerarioController() {
		super();
		bs = new ItinerarioBS();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String caminho = "";
		String acao = request.getParameter("acao");

		if (acao.equalsIgnoreCase("listar")) {
			caminho = FORMULARIO;
			request.setAttribute("lista", bs.listar());

		} else if (acao.equalsIgnoreCase("cancelar")) {
			caminho = FORMULARIO;
			request.setAttribute("salvar", bs.limpaCampos(itinerario));

		} else if (acao.equalsIgnoreCase("alterar")) {
			caminho = FORMULARIO;
			String idTela = request.getParameter("id");
			Long id = (long) 0;
			if (idTela != null && !idTela.isEmpty()) {
				id = Long.parseLong(idTela);
				Itinerario itinerario = bs.consultar(id);
				request.setAttribute("salvar", itinerario);
				request.setAttribute("lista", bs.listar());
			}

		} else if (acao.equalsIgnoreCase("remover")) {
			String idTela = request.getParameter("id");
			Long id = (long) 0;
			if (idTela != null && !idTela.isEmpty()) {
				id = Long.parseLong(idTela);
			}
			Itinerario itinerario = new Itinerario();
			itinerario.setId(id);
			bs.remover(itinerario);
			caminho = FORMULARIO;
			request.setAttribute("lista", bs.listar());
		}

		else {
			caminho = FORMULARIO;
		}
		RequestDispatcher view = request.getRequestDispatcher(caminho);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id.equals("")) {
			id = "0";
		}

		
		itinerario.setId(Long.parseLong(id));
		itinerario.setOrigem(request.getParameter("origem"));
		itinerario.setDestino(request.getParameter("destino"));
		itinerario.setValor(Double.parseDouble(request.getParameter("valor")));

		bs.salvar(itinerario);

		RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
		request.setAttribute("lista", bs.listar());
		view.forward(request, response);
	}
}
