package br.com.voo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.bll.PassagemBS;
import br.com.voo.bll.VooBS;
import br.com.voo.model.Passagem;
import br.com.voo.model.Voo;


public class PassagemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private PassagemBS bs;
	private String acao;
	VooBS vooBs;
	
	
	
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
			
			request.setAttribute("passagens", passagens);
			
			
			break;

		default:
			break;
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
