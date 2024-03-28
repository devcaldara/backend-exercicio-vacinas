package model.repository;

import java.sql.Connection;   
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.entity.Pessoa;

public class PessoaRepository implements BaseRepository<Pessoa> {

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		//	String sql = "insert into Pessoa (nome, cpf, dataNascimento, sexo, categoria) values (?,?,?,?,?)";
		String sql = "insert into Pessoa (nome, cpf, dataNascimento, sexo, categoria, id_pais) values (?,?,?,?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);
		
		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setString(4, novaPessoa.getSexo());
			pstmt.setInt(5, novaPessoa.getCategoria());
			pstmt.setInt(6, novaPessoa.getPais().getIdPais());
			
			pstmt.execute();
			ResultSet result = pstmt.getGeneratedKeys();
			
			if(result.next()) {
				novaPessoa.setIdPessoa(result.getInt(1));
			}
			
			
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar inserir uma Pessoa no banco de dados");
			System.out.println("Erro: " + erro.getMessage());
			
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
				
		return novaPessoa;
	}

	
	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		
		String sql = "delete from Pessoa where idPessoa = " + id;
		try {
			if(stmt.executeUpdate(sql) == 1) {
				excluiu = true;
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}
		

	@Override
	public boolean alterar(Pessoa pessoaEditada) {
		boolean alterou = false;
		
		//	String sql = "update Pessoa set nome = ?, cpf = ?, dataNascimento = ?, sexo = ?, categoria = ? where idPessoa = ?" ;
		String sql = "update Pessoa set nome = ?, cpf = ?, dataNascimento = ?, sexo = ?, categoria = ?, id_pais = ? where idPessoa = ?" ;

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			pstmt.setString(1, pessoaEditada.getNome());
			pstmt.setString(2, pessoaEditada.getCpf());
			pstmt.setDate(3, Date.valueOf(pessoaEditada.getDataNascimento()));
			pstmt.setString(4, pessoaEditada.getSexo());
			pstmt.setInt(5, pessoaEditada.getCategoria());
			pstmt.setInt(6, pessoaEditada.getPais().getIdPais());
			pstmt.setInt(7, pessoaEditada.getIdPessoa()); 
			
			alterou = pstmt.executeUpdate() > 0;
			
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar Pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
		

	@Override
	public Pessoa consultarPorId(int idPessoa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		
		Pessoa pessoaLocal = new Pessoa();
		String sql = "select * from Pessoa where idPessoa = " + idPessoa;
		
		try {
			result = stmt.executeQuery(sql);
			PaisRepository paisRepo = new PaisRepository();
			//	VacinacaoRepository vacinacaoRepo = new VacinacaoRepository();
			
			if(result.next()){
				pessoaLocal.setIdPessoa(result.getInt("idPessoa"));
				pessoaLocal.setNome(result.getString("nome"));
				pessoaLocal.setCpf(result.getString("cpf"));
				pessoaLocal.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
				pessoaLocal.setSexo(result.getString("sexo"));
				pessoaLocal.setCategoria(result.getInt("categoria"));
				pessoaLocal.setPais(paisRepo.consultarPorId(result.getInt("id_pais")));
				//	pessoaLocal.setVacinacoes(vacinacaoRepo.consultarPorPessoa(result.getInt("idPessoa")));
			}
			
		}  catch (SQLException erro){
			System.out.println("Erro ao executar consultar Pessoa com id (" + idPessoa + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return pessoaLocal;
	}

	
	@Override
	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet result = null;
		String sql = "select * from Pessoa";
		
		try {
			result = stmt.executeQuery(sql);
			PaisRepository paisRepo = new PaisRepository();
			//	VacinacaoRepository vacinacaoRepo = new VacinacaoRepository();
			
			while(result.next()) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setIdPessoa(result.getInt("idPessoa"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setCpf(result.getString("cpf"));
				pessoa.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
				pessoa.setSexo(result.getString("sexo"));
				pessoa.setCategoria(result.getInt("categoria"));
				pessoa.setPais(paisRepo.consultarPorId(result.getInt("id_pais")));
				//	pessoa.setVacinacoes(vacinacaoRepo.consultarPorPessoa(result.getInt("idPessoa")));

				
				pessoas.add(pessoa);
			}
			
		} catch (SQLException erro){
			System.out.println("Erro ao executar consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		
		return pessoas;
	}
	
	
	public boolean cpfJaCadastrado(String cpf) {
		boolean cpfJaUtilizado = false;	
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = " SELECT count(idPessoa) FROM pessoa WHERE cpf = " + cpf;
		
		try {
			ResultSet resultado = stmt.executeQuery(query);
			cpfJaUtilizado = (resultado.getInt(1) > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao consultar CPF. Causa: " + e.getMessage());
		}
		
		return cpfJaUtilizado;
	}
	

}









