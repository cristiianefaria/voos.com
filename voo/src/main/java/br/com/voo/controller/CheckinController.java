package br.com.voo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.research.ws.wadl.Request;

import br.com.voo.bll.PassagemBS;
import br.com.voo.model.Passagem;

@WebServlet(name = "/Checkin", urlPatterns = "/Checkin")
public class CheckinController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	PassagemBS passagemBs;

	private final static String PAGINA = "/checkin.jsp"; 
	
	 public CheckinController() {
		 passagemBs = new PassagemBS();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String acao = req.getParameter("acao");
		
		if(acao.equalsIgnoreCase("checkin")){
			RequestDispatcher view = req.getRequestDispatcher(PAGINA);
			view.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String hashCode = req.getParameter("hash");
		try {

			Passagem pasagem = passagemBs.consultaPassagemPeloHash(hashCode);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
