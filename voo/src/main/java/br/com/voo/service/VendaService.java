package br.com.voo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.voo.bll.ClienteBS;
import br.com.voo.bll.PassageiroBS;
import br.com.voo.bll.PassagemBS;
import br.com.voo.bll.PoltronaBS;
import br.com.voo.bll.VendaBS;
import br.com.voo.bll.VooBS;
import br.com.voo.model.Cliente;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Passagem;
import br.com.voo.model.Venda;
import br.com.voo.model.Voo;

@Path("/venda")
public class VendaService {

	ClienteBS clienteBS  = new ClienteBS();
	PassageiroBS passageiroBS = new PassageiroBS();
	VooBS vooBS = new VooBS();
	PoltronaBS poltronaBS = new PoltronaBS();
	VendaBS vendaBS = new VendaBS();
	PassagemBS passagemBS = new PassagemBS();
	
	@GET
	@Path("/{idpassagem}/{idusuario}")
	@Produces("application/json")
	public Response venderPassagem(@PathParam("idpassagem") String idPassagem,
			@PathParam("idusuario") String idUsuario){
		
		Long id_usuario = Long.parseLong(idUsuario);
		Long id_passagem = Long.parseLong(idPassagem);
		Venda resposta = new Venda();
		try{
			
			Passageiro passageiro = passageiroBS.consultar(id_usuario);
			Cliente cliente = clienteBS.consultar(id_usuario);
			Passagem passagem = passagemBS.consultaPassagem(id_usuario);
			passagem.setSituacao(Passagem.SituacaoAlocado);
			passagem.setPassageiro(passageiro);
			
			Venda venda = new Venda();
			venda.setSituacao("Comprada");
			venda.setPassagem(passagem);
			venda.setCliente(cliente);

			resposta = vendaBS.salvar(venda);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Response.status(200).entity(resposta).build();
	}
	
	
	@GET
	@Path("/{idusuario}")
	@Produces("application/json")
	public Response venderPassagem(@PathParam("idusuario") String idUsuario){
		List<Venda> vendas = new ArrayList<Venda>();
		Long id_usuario = Long.parseLong(idUsuario);
		List<Voo> resposta = new ArrayList<Voo>();
		try{
			vendas = vendaBS.listar(id_usuario);
			vendas.forEach(e -> resposta.add(e.getPassagem().getVoo()));

			return Response.status(200).entity(resposta).build();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Response.status(404).build();
		
		
	}
	

	
}
