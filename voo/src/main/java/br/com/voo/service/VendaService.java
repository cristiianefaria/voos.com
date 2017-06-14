package br.com.voo.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.voo.bll.ClienteBS;
import br.com.voo.bll.PassageiroBS;
import br.com.voo.bll.PoltronaBS;
import br.com.voo.bll.VendaBS;
import br.com.voo.bll.VooBS;
import br.com.voo.model.BuilderPessoaCliente;
import br.com.voo.model.Cliente;
import br.com.voo.model.Compra;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Passagem;
import br.com.voo.model.Pessoa;
import br.com.voo.model.Poltrona;
import br.com.voo.model.TipoCliente;
import br.com.voo.model.Venda;
import br.com.voo.model.Voo;

@Path("/venda")
public class VendaService {

	ClienteBS clienteBS  = new ClienteBS();
	PassageiroBS passageiroBS = new PassageiroBS();
	VooBS vooBS = new VooBS();
	PoltronaBS poltronaBS = new PoltronaBS();
	VendaBS vendaBS = new VendaBS();
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public String venderPassagem(Compra compra){
		
		Gson gson = new Gson();
		
		Venda resposta = new Venda();
		try{
			
			Passageiro passageiro = passageiroBS.consultar(compra.getPassagem().getPassageiro().getId());
			Cliente cliente = clienteBS.consultar(compra.getCliente().getId());
			Voo voo = vooBS.consultar(compra.getPassagem().getVoo());
			Poltrona poltrona = poltronaBS.consultar(compra.getPassagem().getPoltrona());
			
			Passagem passagem = new Passagem();
			
			passagem.setPassageiro(passageiro);
			passagem.setPoltrona(poltrona);
			passagem.setVoo(voo);
			
			Venda venda = new Venda();
			venda.setPassagem(passagem);
			venda.setCliente(cliente);
			
			resposta = vendaBS.salvar(venda);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return gson.toJson(resposta);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String moodeloDeCompra(){
		
		Gson gson = new Gson();
		Compra compra = new Compra();
		
		BuilderPessoaCliente builder = new BuilderPessoaCliente(new Pessoa("Bruno Rodrigues",
					"70019813112",
					 "",
					 "Rua sr.07",
					 new Date(),
					 EstadoCivil.Solteiro,
					 "62972224223",
					 "brunocroh@gmail.com",
					 "senha"))
				.setMilhagem(500)
				.setTipoCliente(TipoCliente.clienteFinal);
		
		compra.setCliente(builder.buildCliente());
		
		
		compra.setPassagem(new Passagem(new Long(10),
				builder.buildPassageiro(),
				builder.buildPassageiro(),
				"Em Aberto",
				"###SADASD",
				false,
				50.0,
				new Voo(),
				new Poltrona()
				));

		return gson.toJson(compra);
	}
	
}
