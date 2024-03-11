package model.service;

import java.util.ArrayList;

import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) {
		return repository.salvar(novaPessoa);
	}
	
	public boolean excluir(int idPessoa) {
		return repository.excluir(idPessoa);
	}
	
	public boolean alterar(Pessoa pessoaEditada) {
		return repository.alterar(pessoaEditada);
	}
	
	public Pessoa consultarPorId(int idPessoa) {
		return repository.consultarPorId(idPessoa);
	}
	
	public ArrayList<Pessoa> consultarTodas(){
		return repository.consultarTodos();
	}
	
}