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
			}else if(acao.equals("removerPoltrona")){
				Long idPoltrona = Long.parseLong(
				request.getParameter("id"));
				Long codigoAeronave = Long.parseLong(
						request.getParameter("poltrona_aeronave_id"));
						
				try {
					poltronaBS.excluir(new Poltrona(idPoltrona));
					aeronaves = aeronaveBS.listar();
					Aeronave aeronave = aeronaveBS.consultar(new Aeronave(codigoAeronave));
					request.setAttribute("aeronave", aeronave);
					
				} catch (Exception e) {
					// TODO Tratar exception
					e.printStackTrace();
				}
			}else if(acao.equals("alterarPoltrona")){
				Long idPoltrona = Long.parseLong(
						request.getParameter("id"));
				
				try {
					Poltrona poltrona = poltronaBS.consultar(new Poltrona(idPoltrona));
					request.setAttribute("poltrona", poltrona);
					request.setAttribute("aeronave", 
							aeronaveBS.consultar(new Aeronave(poltrona.getCodigoAeronave())));
					aeronaves = aeronaveBS.listar();
				} catch (SQLException e) {
					// TODO Tratar exception
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
		String codigoAeronave = request.getParameter("poltrona_aeronave_id");
		String codigoAeronaveForm = request.getParameter("aeronave_id_form");
		String botao = request.getParameter("botao");
		List<Poltrona> poltronas = new ArrayList<Poltrona>();	
		Aeronave aeronave = new Aeronave();
		Long idDaAeronave = new Long(0);
		
		
		if(codigoAeronaveForm != null && !"".equals(codigoAeronaveForm)){
			idDaAeronave = Long.parseLong(codigoAeronaveForm);
		}
		
		if(codigoAeronave != null && !"".equals(codigoAeronave)){
			idDaAeronave = Long.parseLong(codigoAeronave);
		}
		
		if(botao.equals("Cadastrar Aeronave")){
			aeronave = new Aeronave(idDaAeronave,descricao, poltronas);

			try {
				aeronaveBS.salvar(aeronave);
				aeronaves = aeronaveBS.listar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else if(botao.equals("Cadastrar Poltronas")){
			
			aeronave = new Aeronave(idDaAeronave);
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
				request.setAttribute("aeronave", aeronave);
			}catch(SQLException e){
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
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
}
