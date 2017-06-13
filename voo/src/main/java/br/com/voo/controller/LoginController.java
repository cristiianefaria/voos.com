package br.com.voo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.Pessoa;
import br.com.voo.util.Erro;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PessoaDAO dao;
	private Pessoa user = null;

	public LoginController() {
		super();
		dao = new PessoaDAO();
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
				user = dao.validaLogin(email, senha);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (user != null) {
				request.getSession().setAttribute("usuarioLogado", user);
				response.sendRedirect("itinerario.jsp");
				return;
			} else {
				erros.add("Usuário ou senha incorreto!");
			}
		}
		Cookie ck = new Cookie("id", user.getId().toString());
		response.addCookie(ck);

		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		String URL = "login.jsp";
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
