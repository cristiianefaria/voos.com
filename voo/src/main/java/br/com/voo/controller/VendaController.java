package br.com.voo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.bll.ClienteBS;
import br.com.voo.bll.PassageiroBS;
import br.com.voo.bll.PassagemBS;
import br.com.voo.model.Cliente;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Passagem;

@WebServlet("/Venda")
public class VendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static String PAGINA = "/venda.jsp";
	PassageiroBS passageiroBs;
	PassagemBS passagemBs;
	ClienteBS clienteBS;
	public VendaController() {
        super();
        passageiroBs = new PassageiroBS();
        passagemBs = new PassagemBS();
        clienteBS = new ClienteBS(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String acao =  request.getParameter("acao");
		String cpfCliente = request.getParameter("cpfPassageiro");
		Long idPassagem = Long.parseLong(request.getParameter("idPassagem"));
	    
		switch (acao) {
		case "comprarPassagem":
			try {
				Passageiro passageiro = passageiroBs.consultarPorCpf(cpfCliente);
				Passagem passagem = passagemBs.consultaPassagem(idPassagem);
				Cookie[] cookie = request.getCookies();
				
				Long idClienete = new Long(0);
				for (Cookie cookie2 : cookie) {
					if(cookie2.getValue().equals("idCliente"))
					    idClienete = Long.parseLong(cookie2.getValue());
				}
				
				Cliente cliente = clienteBS.consultar(idClienete);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;

		default:
			break;
		}
	   	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
