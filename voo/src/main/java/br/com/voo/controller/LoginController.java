package br.com.voo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.Pessoa;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String PAGINA = "/index.jsp";
	private PessoaDAO dao;

	public LoginController() {
		super();
		dao = new PessoaDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = request.getParameter("usuario");
		String senha = request.getParameter("senha");

		Pessoa pessoa = null;
		try {
			pessoa = dao.validaLogin(email, senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Cookie ck = new Cookie("id", pessoa.getId().toString());
		response.addCookie(ck);

		if (pessoa == null) {
			session.invalidate();
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		} else {
			session.setAttribute("pessoa", pessoa);
			request.getRequestDispatcher("itinerario.jsp").forward(request, response);
		}
	}

}
