package br.com.voo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.voo.bll.PassageiroBS;

@Path("/passageiro")
public class PassageiroService {

	PassageiroBS passageiroBS = new PassageiroBS();
	
	@GET
	public String getPassageiros(){
		
		passageiroBS.listar("");
		
		return "";
		
	}
	
}
