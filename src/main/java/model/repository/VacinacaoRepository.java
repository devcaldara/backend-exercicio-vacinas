package model.repository;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.entity.Vacina;
import model.entity.Vacinacao;
import model.entity.Vacinacao;

public class VacinacaoRepository implements BaseRepository<Vacinacao>{

	@Override
	public Vacinacao salvar(Vacinacao novaVacinacao) {
		
		String sql = "insert into vacinacao (id_pessoa, id_vacina, dataAplicacao, avaliacao) values (?,?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);
		
		try {
			pstmt.setInt(1, novaVacinacao.getId_pessoa());
			pstmt.setInt(2, novaVacinacao.getVacina().getIdVacina());
			// pstmt.setDate(3,Date.valueOf(LocalDate.now()));
			pstmt.setDate(3, Date.valueOf(novaVacinacao.getDataVacinacao()));
			pstmt.setInt(4, novaVacinacao.getAvaliacao());
			
			pstmt.execute();
			ResultSet result = pstmt.getGeneratedKeys();
			
			if(result.next()) {
				novaVacinacao.setIdVacinacao(result.getInt(1));
			}
			
		}catch (SQLException erro) {
			System.out.println("Erro ao tentar inserir uma Vacinação no banco de dados");
			System.out.println("Erro: " + erro.getMessage());
			
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return novaVacinacao;
	}

	@Override
	public boolean excluir(int idVacinacao) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		
		String sql = "delete from vacinacao where idVacinacao = " + idVacinacao;
		
		try {
			if(stmt.executeUpdate(sql) == 1) {
				excluiu = true;
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Vacinação");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return excluiu;
	}

	@Override
	public boolean alterar(Vacinacao vacinacaoEditada) {
		
		boolean alterou = false;
		
		String sql = "update Vacinacao set id_pessoa = ?, id_vacina = ?, dataAplicacao = ?, avaliacao = ? where idVacinacao = ?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);
		
		try {
			pstmt.setInt(1, vacinacaoEditada.getId_pessoa());
			pstmt.setInt(2, vacinacaoEditada.getVacina().getIdVacina());
			pstmt.setDate(3, Date.valueOf(vacinacaoEditada.getDataVacinacao()));
			pstmt.setInt(4, vacinacaoEditada.getAvaliacao());
			pstmt.setInt(5, vacinacaoEditada.getIdVacinacao());
			
			alterou = pstmt.executeUpdate() > 0;			
			
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar Vacinação");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return alterou;
	}

	@Override
	public Vacinacao consultarPorId(int idVacinacao) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		
		Vacinacao vacinacaoLocal = new Vacinacao();
		String sql = "select * from vacinacao where id_vacina = " + idVacinacao;
		
		try {
			result = stmt.executeQuery(sql);
			VacinaRepository vacinaRepo = new VacinaRepository();
			
			if(result.next()) {
				
				vacinacaoLocal.setIdVacinacao(result.getInt("idVacinacao"));
				vacinacaoLocal.setId_pessoa(result.getInt("id_pessoa"));				
				vacinacaoLocal.setVacina(vacinaRepo.consultarPorId(result.getInt("id_vacina")));
				vacinacaoLocal.setDataVacinacao(result.getDate("dataVacinacao").toLocalDate());
				vacinacaoLocal.setAvaliacao(result.getInt("avaliacao"));	
			}
			
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar Vacinação com id (" + idVacinacao + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return vacinacaoLocal;
	}

	@Override
	public ArrayList<Vacinacao> consultarTodos() {
		
		ArrayList<Vacinacao> vacinacoes = new ArrayList<Vacinacao>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		
		String sql = "select * from Vacinacao";
		
		try {
			result = stmt.executeQuery(sql);
			VacinaRepository vacinaRepo = new VacinaRepository();
			
			while(result.next()) {
				Vacinacao vacinacaoLocal = new Vacinacao();
				
				vacinacaoLocal.setIdVacinacao(result.getInt("idVacinacao"));
				vacinacaoLocal.setId_pessoa(result.getInt("id_pessoa"));
				vacinacaoLocal.setVacina(vacinaRepo.consultarPorId(result.getInt("id_vacina")));
				vacinacaoLocal.setDataVacinacao(result.getDate("dataAplicacao").toLocalDate());
				vacinacaoLocal.setAvaliacao(result.getInt("avaliacao"));
				
				vacinacoes.add(vacinacaoLocal);
				
			}
						
		}catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todas as Vacinações");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return vacinacoes;
	}
	
	public ArrayList<Vacinacao> consultarPorPessoa(int idPessoa) {
		
		ArrayList<Vacinacao> vacinacoes = new ArrayList<Vacinacao>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		
		String sql = "select * from Vacinacao where id_pessoa = " + idPessoa;
		
		try {
			result = stmt.executeQuery(sql);
			VacinaRepository vacinaRepo = new VacinaRepository();
			
			while(result.next()) {
				Vacinacao vacinacaoLocal = new Vacinacao();
				
				vacinacaoLocal.setIdVacinacao(result.getInt("idVacinacao"));
				vacinacaoLocal.setId_pessoa(idPessoa);
				vacinacaoLocal.setVacina(vacinaRepo.consultarPorId(result.getInt("id_vacina")));
				vacinacaoLocal.setDataVacinacao(result.getDate("dataAplicacao").toLocalDate());
				vacinacaoLocal.setAvaliacao(result.getInt("avaliacao"));
				
				vacinacoes.add(vacinacaoLocal);
				
			}			
			
		}catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todas as Vacinações por pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return vacinacoes;
	}

	
}


















