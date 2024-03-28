package model.service;

import java.util.ArrayList;

import exception.ControleVacinasException;
import model.entity.Pessoa;
import model.entity.Vacinacao;
import model.repository.PessoaRepository;
import model.repository.VacinacaoRepository;

public class VacinacaoService {

	VacinacaoRepository repository = new VacinacaoRepository();

	public Vacinacao salvar(Vacinacao novaVacinacao) throws ControleVacinasException {
		PessoaRepository pessoaRepository = new PessoaRepository();
		Pessoa pessoa = pessoaRepository.consultarPorId(novaVacinacao.getId_pessoa());

		if (pessoa.getCategoria() > novaVacinacao.getVacina().getEstagio()) {
			throw new ControleVacinasException("Erro ao inserir vacinação pois a categoria e o estágio não coincidem");
		} else {
			return repository.salvar(novaVacinacao);
		}
	}


	public boolean excluir(int idVacina) {
		return repository.excluir(idVacina);
	}

	public boolean alterar(Vacinacao vacinacaoEditada) throws ControleVacinasException {
		PessoaRepository pessoaRepository = new PessoaRepository();
		Pessoa pessoa = pessoaRepository.consultarPorId(vacinacaoEditada.getId_pessoa());

		if (pessoa.getCategoria() > vacinacaoEditada.getVacina().getEstagio()) {
			throw new ControleVacinasException("Erro ao inserir vacinação pois a categoria e o estágio não coincidem");
		} else {
			return repository.alterar(vacinacaoEditada);
		}
	}

	public Vacinacao consultarPorId(int idVacinacao) {
		return repository.consultarPorId(idVacinacao);
	}

	public ArrayList<Vacinacao> consultarTodos() {
		return repository.consultarTodos();
	}

	public ArrayList<Vacinacao> consultarPorPessoa(int idPessoa) {
		return repository.consultarPorPessoa(idPessoa);
	}

}