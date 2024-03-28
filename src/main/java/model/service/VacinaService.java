package model.service;

import java.util.ArrayList; 

import exception.ControleVacinasException;
import model.entity.Vacina;
import model.entity.Vacinacao;
import model.repository.VacinaRepository;
import model.repository.VacinacaoRepository;

public class VacinaService {

	private VacinaRepository repository = new VacinaRepository();

	public Vacina salvar(Vacina novaVacina) {	
		if(novaVacina.getMediaAvaliacao() == 0.0) {
			novaVacina.setMediaAvaliacao(repository.calcularMediaVacina(novaVacina.getIdVacina()));
		}
		return repository.salvar(novaVacina);
	}

	public boolean excluir(int idVacina) throws ControleVacinasException {
		if(!vacinaJaUtilizada(idVacina)) {
			return repository.excluir(idVacina);			
		} else {
			throw new ControleVacinasException("Erro ao excluir pois já existe vacinação com essa vacina");
		}
	}

	private boolean vacinaJaUtilizada(int idVacina) {
		VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
		boolean utilizada = false;
		
		for(Vacinacao vacinacao : vacinacaoRepository.consultarTodos()) {
			if(vacinacao.getVacina().getIdVacina() == idVacina){
				utilizada = true;
			}
		}
		return utilizada;
	}

	public boolean alterar(Vacina vacinaEditada) {
		if(vacinaEditada.getMediaAvaliacao() == 0.0) {
			vacinaEditada.setMediaAvaliacao(repository.calcularMediaVacina(vacinaEditada.getIdVacina()));
		}
		return repository.alterar(vacinaEditada);
	}

	public Vacina consultarPorId(int idVacina) {
		Vacina vacLocal = repository.consultarPorId(idVacina);
		if(vacLocal.getMediaAvaliacao() == 0.0) {
			vacLocal.setMediaAvaliacao(repository.calcularMediaVacina(idVacina));
		}
		return vacLocal;
	}

	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinacoes = new ArrayList<Vacina>();
		
		for (Vacina vac : repository.consultarTodos()) {
			if(vac.getMediaAvaliacao() == 0.0) {
				vac.setMediaAvaliacao(repository.calcularMediaVacina(vac.getIdVacina()));
			}
			vacinacoes.add(vac);
		}
		
		return vacinacoes;
	}

	/*
	
	 FIZ ESSE ROLE TODO PRA CONSEGUIR TRAZER UMA MÉDIA ÀS VACINAS QUE FORAM 
	 INSERIDAS ANTERIORMENTE À LÓGICA DE CÁLCULO DE MÉDIA
	 APESAR DE NÃO PERSISTIR ESSES VALORES NO BANCO
	 
	 */
}