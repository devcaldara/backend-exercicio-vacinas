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
import model.entity.Vacinacao;
import model.service.VacinacaoService;

@Path("/vacinacao")
public class VacinacaoController {
	
	private VacinacaoService service = new VacinacaoService();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacinacao salvar(Vacinacao novaVacinacao) throws ControleVacinasException {
		return service.salvar(novaVacinacao);
	}

	@GET
	@Path("/todas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Vacinacao> consultarTodos() {
		return service.consultarTodos();
	}

	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int idVacinacao) {
		return service.excluir(idVacinacao);
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Vacinacao vacinacaoEditada) throws ControleVacinasException {
		return service.alterar(vacinacaoEditada);
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacinacao consultarPorId(@PathParam("id") int idVacinacao) {
		return service.consultarPorId(idVacinacao);
	}
	
	@GET
	@Path("/pessoa/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Vacinacao> consultarPorPessoa(@PathParam("id") int idPessoa) {
		return service.consultarPorPessoa(idPessoa);
	}
	
}
