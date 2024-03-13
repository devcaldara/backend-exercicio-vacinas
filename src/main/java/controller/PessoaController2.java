package controller;

import java.util.ArrayList;

//import exception.VemNoX1Exception;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Pessoa;
import model.service.PessoaService;

@Path("/pessoa")
public class PessoaController {
	
	private PessoaService service = new PessoaService();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa salvar(Pessoa novaPessoa) { //throws VemNoX1Exception {
		 return service.salvar(novaPessoa);
	}	
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Pessoa> consultarTodos(){
		return service.consultarTodas();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean excluir(@PathParam("id") int idPessoa) {
		return service.excluir(idPessoa);
	}
	
	/*
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean alterar(Pessoa PessoaEditada) {
		return service.alterar(PessoaEditada);
	}*/
	
	/*
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa consultarPorId(int idPessoa) {
		return service.consultarPorId(idPessoa);
	}*/	
		

}





//	DÚVIDAS:
//	-	onde coloco exception?
//	-	getInt funciona pra ID?