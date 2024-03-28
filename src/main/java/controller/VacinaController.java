package controller;

import java.util.ArrayList;

import exception.ControleVacinasException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Vacina;
import model.service.VacinaService;

@Path("/vacina")
public class VacinaController {
	
	private VacinaService service = new VacinaService();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina salvar(Vacina novaVacina) throws ControleVacinasException {
		return service.salvar(novaVacina);
	}

	@GET
	@Path("/todas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Vacina> consultarTodos() {
		return service.consultarTodos();
	}

	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int idVacina) throws ControleVacinasException  {
		return service.excluir(idVacina);
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Vacina vacinaEditada) {
		return service.alterar(vacinaEditada);
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina consultarPorId(@PathParam("id") int idVacina) {
		return service.consultarPorId(idVacina);
	}

}