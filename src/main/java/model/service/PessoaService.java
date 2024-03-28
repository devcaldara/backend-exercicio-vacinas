package model.service;

import java.util.ArrayList;  

import exception.ControleVacinasException;
import model.repository.PessoaRepository;
import model.repository.VacinacaoRepository;
import model.entity.Pessoa;
import model.entity.Vacinacao;

public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException {
		
		return repository.salvar(novaPessoa);
		
		/* if (validarPessoa(novaPessoa) && repository.cpfJaCadastrado(novaPessoa.getCpf()) == false) {
		return repository.salvar(novaPessoa);			
	} else {
		throw new ControleVacinasException("Erro ao tentar criar um novo registro de Pessoa :(");
	}	*/	
	}
	
	public boolean excluir(int idPessoa)throws ControleVacinasException {
		
		if(!pessoaRecebeuVacina(idPessoa)) {
			return repository.excluir(idPessoa);			
		} else {
			throw new ControleVacinasException("Erro ao excluir pois já existe vacinação com essa pessoa");
		}
	}
	
	private boolean pessoaRecebeuVacina(int idPessoa) {
		VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
		
		if(vacinacaoRepository.consultarPorPessoa(idPessoa).isEmpty()) {
			return false;
		} else {
			return true;
		}
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
	
	public boolean validarPessoa(Pessoa pessoa) throws ControleVacinasException{
		boolean valido = false;	
		
		if(pessoa.getNome().equalsIgnoreCase(null) || pessoa.getNome().equalsIgnoreCase("")) {
		} else if(pessoa.getCategoria() == 0 || pessoa.getCategoria() <= 0){
		} else if(pessoa.getSexo().equalsIgnoreCase(null) || pessoa.getSexo().equalsIgnoreCase("")) {
		} else if(pessoa.getCpf().equalsIgnoreCase(null) || pessoa.getCpf().equalsIgnoreCase("")) {
		//} else if(pessoa.getIdPessoa()<= 0) {
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