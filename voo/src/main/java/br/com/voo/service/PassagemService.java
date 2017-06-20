package br.com.voo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.voo.bll.PassagemBS;
import br.com.voo.model.Voo;

@Path("/passagem")
public class PassagemService {

	PassagemBS passagemBS = new PassagemBS();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPassagens(@PathParam("id") String id){
		
		Long idVoo = Long.parseLong(id);
		
		try{
			return Response.status(200)
					.entity(passagemBS.listarPassagens(new Voo(idVoo)))
					.build();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Response.status(404).build();
	}
	
}
