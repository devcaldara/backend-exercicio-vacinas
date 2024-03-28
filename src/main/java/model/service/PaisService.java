package model.service;

import model.entity.Pais;
import model.repository.PaisRepository;

public class PaisService {

	PaisRepository repository = new PaisRepository();
	
	public Pais salvar(Pais novoPais) {
		return repository.salvar(novoPais);
	}
	
}
