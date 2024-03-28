package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.Pais;

public class PaisRepository {

	public Pais salvar(Pais novoPais) {
		
		String sql = "insert into Pais (nome, sigla) values (?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);
		
		try {
			pstmt.setString(1, novoPais.getNomePais());
			pstmt.setString(2, novoPais.getSiglaPais());
			
			pstmt.execute();
			ResultSet result = pstmt.getGeneratedKeys();
			
			if(result.next()) {
				novoPais.setIdPais(result.getInt(1));
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao tentar inserir um País no banco de dados");
			System.out.println("Erro: " + erro.getMessage());
			
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return novoPais;
	}
	
	
	public Pais consultarPorId(int idPais) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		String sql = "select * from pais where idPais = " + idPais;
		Pais paisLocal = new Pais();
				
		try {
			ResultSet result = stmt.executeQuery(sql);
			
			if(result.next()) {
				
				paisLocal.setIdPais(result.getInt("idPais"));
				paisLocal.setNomePais(result.getString("nome"));
				paisLocal.setSiglaPais(result.getString("sigla"));
				
			}						
			
		}catch (SQLException erro) {
			System.out.println("Erro ao tentar consultar um País no banco de dados");
			System.out.println("Erro: " + erro.getMessage());
			
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return paisLocal;
	}
	
}



















