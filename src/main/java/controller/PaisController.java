/*package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Pais;
import model.service.PaisService;

@Path("/pais")
public class PaisController {
	
	PaisService service = new PaisService();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pais salvar(Pais novoPais) {
		return service.salvar(novoPais);
	}

}*/
