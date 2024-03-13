package model.service;

import java.util.ArrayList;

import exception.ControleVacinasException;
import model.repository.PessoaRepository;
import model.entity.Pessoa;

public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException {
		
		if (validarPessoa(novaPessoa)) {
			return repository.salvar(novaPessoa);			
		} else {
			throw new ControleVacinasException("Deu erro");
		}		
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
	
	public boolean validarPessoa(Pessoa pessoa) {
		boolean valido = false;	
		if(pessoa.getNome().equalsIgnoreCase(null) || pessoa.getNome().equalsIgnoreCase("")) {
		} else if(pessoa.getCategoria().equalsIgnoreCase(null) || pessoa.getCategoria().equalsIgnoreCase("")) {
		} else if(pessoa.getSexo().equalsIgnoreCase(null) || pessoa.getSexo().equalsIgnoreCase("")) {
		} else if(pessoa.getCpf().equalsIgnoreCase(null) || pessoa.getCpf().equalsIgnoreCase("")) {
		} else if(pessoa.getIdPessoa()<= 0) {
		} else if(pessoa.getDataNascimento().equals(null)) {
		} else {
			valido = true;
		}
		
		return valido;
	}
		
}

/*

	todos os campos são obrigatórios, caso algum não tenha sido preenchido 
	o serviço deve retornar uma ControleVacinasException, com uma mensagem amigável

*/