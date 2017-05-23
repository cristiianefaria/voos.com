package br.com.voo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.voo.bll.AeronaveBS;
import br.com.voo.bll.PoltronaBS;
import br.com.voo.model.Aeronave;
import br.com.voo.model.Poltrona;

@WebServlet("/Aeronave")
public class AeronaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String TELA = "aeronave.jsp";
    private AeronaveBS aeronaveBS;
	private PoltronaBS poltronaBS;
	
	public AeronaveController() {
		super();
		this.aeronaveBS = new AeronaveBS();
		this.poltronaBS = new PoltronaBS();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String caminho = TELA;
		List<Aeronave> aeronaves = new ArrayList<Aeronave>();
		
		if(acao != null && !"".equals(acao)){
			if(acao.equals("listar")){
				try {
					aeronaves = aeronaveBS.listar();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if(acao.equals("remover")){
				try{
					String id = request.getParameter("id");
					if(!"".equals(id) && id != null){
						aeronaveBS.excluir(
								new Aeronave(Long.parseLong(id)));
					}
					aeronaves = aeronaveBS.listar();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}else if(acao.equals("alterar")){
				try{
					Aeronave aeronave = new Aeronave();
					String id = request.getParameter("id");
					if(!"".equals(id) && id != null){
						aeronave = aeronaveBS.consultar(
								new Aeronave(Long.parseLong(id)));
					}
					aeronaves = aeronaveBS.listar();
					request.setAttribute("aeronave", aeronave);
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}else{
			try {
				aeronaves = aeronaveBS.listar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("aeronaves", aeronaves);
		RequestDispatcher view = request.getRequestDispatcher(TELA);
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Aeronave> aeronaves = new ArrayList<Aeronave>();
		String descricao = request.getParameter("aeronave_descricao");
		String codigoAeronave = request.getParameter("aeronave_id");
		String botao = request.getParameter("botao");
		List<Poltrona> poltronas = new ArrayList<Poltrona>();	
		Aeronave aeronave = new Aeronave();
		
		if(codigoAeronave != null && !"".equals(codigoAeronave)){
			aeronave.setId(Long.parseLong(codigoAeronave));
		}
		
		if(botao.equals("Cadastrar Aeronave")){
			aeronave = new Aeronave(descricao, poltronas);

			try {
				aeronaveBS.salvar(aeronave);
				request.setAttribute("aeronaves", aeronaveBS.listar());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else{
			String classePoltrona = request.getParameter("poltrona_classe");
			String detalhesPoltrona = request.getParameter("poltrona_detalhes");
			String valorPoltrona = request.getParameter("poltrona_valor");
			String qtdPoltrona = request.getParameter("poltrona_quantidade");
			int qtdDePoltronas = 1;
			
			Double valorDaPoltrona = 0.0;
			
			if(qtdPoltrona != null && !"".equals(qtdPoltrona)){
				qtdDePoltronas = Integer.parseInt(qtdPoltrona);
			}
			if(valorPoltrona != null && !"".equals(valorPoltrona)){
				valorDaPoltrona = Double.parseDouble(valorPoltrona);
			}
			
			
			try{
				aeronave = aeronaveBS.consultar(aeronave);
				Poltrona poltrona = new Poltrona("", valorDaPoltrona, classePoltrona, detalhesPoltrona, aeronave);
				poltronaBS.cadastrarPoltronasNaAeronave(poltrona,qtdDePoltronas);
				aeronaves = aeronaveBS.listar();
				aeronave = aeronaveBS.consultar(aeronave);
			}catch(SQLException e){
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		request.setAttribute("aeronaves", aeronaves);
		request.setAttribute("aeronave", aeronave);
		RequestDispatcher view = request.getRequestDispatcher(TELA);
		view.forward(request, response);
	}
}
