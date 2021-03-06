package br.com.voo.bll;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.omg.CORBA.DATA_CONVERSION;

import br.com.voo.dal.VendaDAO;
import br.com.voo.model.Passagem;
import br.com.voo.model.Pessoa;
import br.com.voo.model.Venda;
import br.com.voo.util.Data;

public class VendaBS {

	VendaDAO dao;
	PassagemBS bsPassagem;
	Map<String, String> erros;
	private final int idadeMinima = 12;// idade minima;

	public VendaBS(VendaDAO dao) {
		super();
		this.dao = dao;
		erros = new HashMap<>();
		bsPassagem = new PassagemBS();
	}

	public VendaBS() {
		this.dao = new VendaDAO();
		erros = new HashMap<>();
		bsPassagem = new PassagemBS();
	}

	public Venda salvar(Venda venda) throws Exception {
		
		Venda retorno = new Venda();
		if (venda != null) {
			if (venda.getId().intValue() == 0) {
				//validaVenda(venda);
				retorno = dao.incluir(venda);
			} else {
				dao.alterar(venda);
				return new Venda();
			}
		}
		return retorno;
	}

	private void validaVenda(Venda venda) throws Exception {

		Passagem passagem = bsPassagem.consultaPassagem(venda.getCodigoPassagem());
		Data data = new Data(passagem.getPassageiro().getPessoa().getDataNascimento());

		if (passagem.getPassageiro().getId() == 0)
			erros.put("Passageiro", "É nescessário informar um passageiro!");

		if (data.calcularIdade() < idadeMinima && passagem.getResponsavel().getId() == 0)
			erros.put("Responsavel", "É Nescessário informar o responsável!");

		if(!erros.isEmpty()){
			
		}

	}

	public boolean excluir(Venda venda) throws SQLException {
		venda.setRemovida(true);
		return dao.remover(venda);
	}

	public Venda consultar(Venda venda) throws Exception {
		return dao.consultar(venda);
	}

	public List<Venda> listar(Long idUsuario) throws SQLException, Exception {
		return dao.listar(idUsuario);
	}

	public void enviarEmail(Passagem passagem) {
		try {
			
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("thiagorodriguescamara@gmail.com", "aminorejgafay261188"));
			email.setSSLOnConnect(true);
			email.setFrom("thiagorodriguescamara@gmail.com");
			email.addTo("thiago.rodrigues@inventsoftware.com.br");
			email.setSubject("voo.com");
			email.setMsg("Obrigado por camprar na voo.com seu identificador é:"+ passagem.getHashCheckIn());
			email.send(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
