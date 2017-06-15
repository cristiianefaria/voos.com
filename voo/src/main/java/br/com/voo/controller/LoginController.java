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
import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.Cliente;
import br.com.voo.util.Erro;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PessoaDAO dao;
	private Cliente cliente;
	ClienteBS clienteBs;

	public LoginController() {
		super();
		dao = new PessoaDAO();
		clienteBs = new ClienteBS();
		cliente  =  new Cliente();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Erro erros = new Erro();
		
		String email = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		if (email == null || email.isEmpty()) {
            erros.add("Email não informado!");
        }
        if (senha == null || senha.isEmpty()) {
            erros.add("Senha não informada!");
        }
		
		if (!erros.isExisteErros()) {
			try {
				cliente = clienteBs.consultar(email, senha);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (cliente == null) {
				//session.invalidate();
				 erros.add("Usuário ou senha, incorreto!");
				return;
			} else {
				
				request.getSession().setAttribute("usuarioLogado", cliente);
				request.getRequestDispatcher("itinerario.jsp").forward(request, response);
				
				Cookie ck = new Cookie("idCliente", cliente.getId().toString());
				response.addCookie(ck);
			}
		}
		request.getSession().invalidate();
		request.setAttribute("mensagens", erros);

		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
