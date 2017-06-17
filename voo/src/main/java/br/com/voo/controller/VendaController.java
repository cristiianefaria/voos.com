package br.com.voo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
	Cliente cliente;
	public VendaController() {
        super();
        passageiroBs = new PassageiroBS();
        passagemBs = new PassagemBS();
        clienteBS = new ClienteBS();
        cliente = new Cliente();
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
				
				Long idCliente = new Long(0);
				for (Cookie cookie2 : cookie) {
					if(cookie2.getName().equals("idCliente"))
					    idCliente = Long.parseLong(cookie2.getValue());
				}
				
				Cliente cliente = clienteBS.consultar(idCliente);
				
				passagem.setPassageiro(passageiro);
				passagem.setSituacao(Passagem.SituacaoAlocado);
				
				request.setAttribute("passagem", passagem);
				request.setAttribute("cliente", cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;

		default:
			break;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);
	   	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
