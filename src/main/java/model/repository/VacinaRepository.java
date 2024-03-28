package model.repository;

import java.sql.Connection;  
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;
import model.entity.Vacina;
import model.entity.Vacinacao;

public class VacinaRepository implements BaseRepository<Vacina> {

	@Override
	public Vacina salvar(Vacina novaVacina) {

		String sql = "insert into Vacina (nome, pais_origem, id_pesquisador, dataInicioPesquisa, estagio, mediaAvaliacao) values (?,?,?,?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);

		try {
			pstmt.setString(1, novaVacina.getNome());
			pstmt.setInt(2, novaVacina.getPais().getIdPais());			
			pstmt.setInt(3, novaVacina.getPesquisadorResponsavel().getIdPessoa());
			pstmt.setDate(4, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			pstmt.setInt(5, novaVacina.getEstagio());
			pstmt.setDouble(6, novaVacina.getMediaAvaliacao());

			pstmt.execute();
			ResultSet result = pstmt.getGeneratedKeys();

			if (result.next()) {
				novaVacina.setIdVacina(result.getInt(1));
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao tentar inserir uma Vacina no banco de dados");
			System.out.println("Erro: " + erro.getMessage());

		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaVacina;
	}

	@Override
	public boolean excluir(int idVacina) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;

		String sql = "delete from Vacina where idVacina = " + idVacina;
		try {
			if (stmt.executeUpdate(sql) == 1) {
				excluiu = true;
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Vacina VacinaEditada) {
		boolean alterou = false;
		String sql = "update Vacina set nome =?, pais_origem =?, id_pesquisador =?, dataInicioPesquisa =?, estagio =?, mediaAvaliacao = ? where idVacina = ?";
		
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);
		
		try {
			pstmt.setString(1, VacinaEditada.getNome());
			pstmt.setInt(2, VacinaEditada.getPais().getIdPais());
			pstmt.setInt(3, VacinaEditada.getPesquisadorResponsavel().getIdPessoa());
			pstmt.setDate(4, Date.valueOf(VacinaEditada.getDataInicioPesquisa()));
			pstmt.setInt(5, VacinaEditada.getEstagio());
			pstmt.setDouble(6, VacinaEditada.getMediaAvaliacao());
			pstmt.setInt(7, VacinaEditada.getIdVacina());

			alterou = pstmt.executeUpdate(sql) == 1;

		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar Vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Vacina consultarPorId(int idVacina) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet result = null;
		Vacina vacinaLocal = new Vacina();
		String sql = "select * from Vacina where idVacina = " + idVacina;
		

		try {
			result = stmt.executeQuery(sql);
			PessoaRepository pessoaRepo = new PessoaRepository();
			PaisRepository paisRepo = new PaisRepository();

			if (result.next()) {

				vacinaLocal.setIdVacina(Integer.parseInt(result.getString("idVacina")));
				vacinaLocal.setNome(result.getString("nome"));
				vacinaLocal.setPais(paisRepo.consultarPorId(result.getInt("pais_origem")));
				vacinaLocal.setDataInicioPesquisa(result.getDate("dataInicioPesquisa").toLocalDate());
				vacinaLocal.setEstagio(result.getInt("estagio"));
				vacinaLocal.setMediaAvaliacao(result.getDouble("mediaAvaliacao"));
				
				Pessoa pessoaLocal = pessoaRepo.consultarPorId(result.getInt("id_pesquisador"));
				vacinaLocal.setPesquisadorResponsavel(pessoaLocal);
				
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar Vacina com id (" + idVacina + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return vacinaLocal;
	}


	@Override
	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<Vacina>();
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet result = null;
		String sql = "select * from Vacina";

		try {
			result = stmt.executeQuery(sql);
			PessoaRepository pessoaRepository = new PessoaRepository();
			PaisRepository paisRepo = new PaisRepository();

			while (result.next()) {
				Vacina vacinaLocal = new Vacina();

				vacinaLocal.setIdVacina(Integer.parseInt(result.getString("idVacina")));
				vacinaLocal.setNome(result.getString("nome"));
				vacinaLocal.setPais(paisRepo.consultarPorId(result.getInt("pais_origem")));
				vacinaLocal.setDataInicioPesquisa(result.getDate("dataInicioPesquisa").toLocalDate());
				vacinaLocal.setEstagio(result.getInt("estagio"));
				vacinaLocal.setMediaAvaliacao(result.getDouble("mediaAvaliacao"));

				
				Pessoa pesquisador = pessoaRepository.consultarPorId(result.getInt("id_pesquisador"));
				vacinaLocal.setPesquisadorResponsavel(pesquisador);
				
				vacinas.add(vacinaLocal);
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todas as Vacinas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return vacinas;
	}
	
	
	public double calcularMediaVacina(int idVacina) {
		VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
		double somatorio = 0.0;
		int contAplicacoes = 0;
		
		for(Vacinacao vac : vacinacaoRepository.consultarTodos()) {
			if(vac.getVacina().getIdVacina() == idVacina) {
				somatorio += vac.getAvaliacao();
				contAplicacoes++;
			}
		}
		
		if(contAplicacoes == 0) {
			contAplicacoes = 1;
		}
		
		return (somatorio/contAplicacoes);
	}
	


}










