package br.com.voo.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.voo.bll.PassageiroBS;
import br.com.voo.model.Passageiro;

@Path("/passageiro")
public class PassageiroService {

 	PassageiroBS passageiroBS = new PassageiroBS();
 	Gson gson = new Gson();
 	
	@GET
	@Path("/{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPassageiro(@PathParam("cpf") String nome){
		
		return Response.status(200).entity(passageiroBS.listar(nome)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPassageiro(){
		
		return Response.status(200).entity(passageiroBS.listar("")).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response setPassageiro(Passageiro passageiro){
		try {
			passageiroBS.salvar(passageiro);
			return  Response.status(200)
					.entity(passageiroBS.consultarPorCpf(
							passageiro.getPessoa().getCpf().getNumero()))
						.build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
		
	}
}
