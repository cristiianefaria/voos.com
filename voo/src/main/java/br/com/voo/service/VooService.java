package br.com.voo.service;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.voo.bll.VooBS;

@Path("/voo")
public class VooService {

	VooBS vooBS = new VooBS();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVoos(){
		
		try {
		
			return Response.status(200).entity(vooBS.listar()).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(404).build();
	}
	
}
