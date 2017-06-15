package br.com.voo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.voo.bll.PassageiroBS;

@Path("/passageiro")
public class PassageiroService {

	PassageiroBS passageiro = new PassageiroBS();
	
	@GET
	public Response getPassageiros(){
		
		passageiroBS.listar();
		
	}
	
}
