package br.com.voo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

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
import br.com.voo.bll.VendaBS;
import br.com.voo.model.Cliente;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Passagem;
import br.com.voo.model.Venda;

@WebServlet("/Venda")
public class VendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String PAGINA = "/venda.jsp";
	PassageiroBS passageiroBs;
	PassagemBS passagemBs;
	ClienteBS clienteBS;
	Cliente cliente;
	Passagem passagem;
	VendaBS vendaBs;
	Venda venda;
	
	public VendaController() {
        super();
        passageiroBs = new PassageiroBS();
        passagemBs = new PassagemBS();
        clienteBS = new ClienteBS();
        Passagem passagem = new Passagem();
        vendaBs  = new VendaBS();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String acao =  request.getParameter("acao");
		String cpfCliente = request.getParameter("cpfPassageiro");
		Long idPassagem = Long.parseLong(request.getParameter("idPassagem"));
	    
		switch (acao) {
		case "comprarPassagem":
			try {
				Passageiro passageiro = passageiroBs.consultarPorCpf(cpfCliente);
			    passagem = passagemBs.consultaPassagem(idPassagem);
				Cookie[] cookie = request.getCookies();
				
				Long idCliente = new Long(0);
				for (Cookie cookie2 : cookie) {
					if(cookie2.getName().equals("idCliente"))
					    idCliente = Long.parseLong(cookie2.getValue());
				}
				
				cliente = clienteBS.consultar(idCliente);
				
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
	
		try {
			
			String tipoPgto = request.getParameter("formaPagamento");
			
			passagem.setHashCheckIn(passagemBs.obterHash());
			passagemBs.AlterarPassagem(passagem);
			
			venda = new Venda(LocalDate.now(), 
								cliente.getPercentDesconto(), 
					             	tipoPgto, "Vendido", passagem, cliente);

			vendaBs.salvar(venda);
			
			PAGINA = "/comprarPassagem.jsp";
			request.setAttribute("hash", passagem.getHashCheckIn());
			vendaBs.enviarEmail(passagem);
			
		} catch (Exception e) {
			request.setAttribute("isErro", true);
			request.setAttribute("mensagem", e.getMessage());
			
			request.setAttribute("passagem", passagem);
			request.setAttribute("cliente", cliente);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(PAGINA);
		view.forward(request, response);
	
	}

	
}
