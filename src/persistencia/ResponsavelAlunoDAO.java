package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import negocio.ResponsavelAluno;

public class ResponsavelAlunoDAO {
	private Banco banco;
	public ResponsavelAlunoDAO(){
		banco = new Banco();
	}
	
	public int insereRespAluno(ResponsavelAluno respAluno){
		try{
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				if(consultaRespAluno(respAluno.getMatricula(), respAluno.getCpf()) == null){
					String comandoSQL = "INSERT INTO tb_responsavel_aluno(tb_aluno_matricula, "
							+ "tb_responsavel_cpf)" +
					" VALUES ('"+ respAluno.getMatricula() + "','"+ respAluno.getCpf() + "') ";
					stmt.executeUpdate(comandoSQL);
					stmt.close(); 
					con.commit(); 
					con.close();
					return 0;
				}
				return 1;
			}
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD");
			e.printStackTrace();
		}
		return 2;
	}
	
	public int deletaRespAluno(int matricula, int cpf){ 
		try{
			Connection con = banco.getConexao();
			if(con != null){
				if(consultaRespAluno(matricula,cpf) != null){
					Statement stat = con.createStatement();
					stat.executeUpdate("Delete FROM tb_responsavel_aluno"
						+ " WHERE tb_aluno_matricula = " + matricula
						+ " and tb_responsavel_cpf = " + cpf);
					stat.close();
					return 0;
				}
			}
			return 1;
		}catch(SQLException e) {
			System.out.println("Problemas ao abrir a conexao com o BD"); 
			return 2; 
		}
	}
	
	public ArrayList<String> consultaResp() {
		ArrayList<String> listaResps = new ArrayList<String>();
		try{
			Connection con = banco.getConexao();  
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_aluno");
				
				while(res.next()) {
					int id = res.getInt("tb_responsavel_cpf");
					listaResps.add(Integer.toString(id));
				}
				
				stat.close(); 
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return listaResps;
	}
	
	public ArrayList<String> consultaResp(int matricula) {
		ArrayList<String> listaResps = new ArrayList<String>();
		try{
			Connection con = banco.getConexao();  
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_aluno"
						+ " WHERE tb_aluno_matricula = " + matricula);
				
				while(res.next()) {
					int id = res.getInt("tb_responsavel_cpf");
					listaResps.add(Integer.toString(id));
				}
				
				stat.close(); 
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return listaResps;
	}
	
	public ArrayList<String> consultaAluno() {
		ArrayList<String> listaAlunos = new ArrayList<String>();
		try{
			Connection con = banco.getConexao();  
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_aluno");
				
				while(res.next()) {
					int id = res.getInt("tb_aluno_matricula");
					listaAlunos.add(Integer.toString(id));
				}
				
				stat.close(); 
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return listaAlunos;
	}
	
	public ArrayList<String> consultaAluno(int cpf) {
		ArrayList<String> listaAlunos = new ArrayList<String>();
		try{
			Connection con = banco.getConexao();  
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_aluno"
						+ " WHERE tb_responsavel_cpf = " + cpf);
				
				while(res.next()) {
					int id = res.getInt("tb_aluno_matricula");
					listaAlunos.add(Integer.toString(id));
				}
				
				stat.close(); 
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return listaAlunos;
	}
	
	public ResponsavelAluno consultaRespAluno(int matricula, int cpf) { 
		try{
			Connection con = banco.getConexao();  
			ResponsavelAluno respAluno = null;
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_aluno"
						+ " WHERE tb_aluno_matricula = " + matricula
						+ " and tb_responsavel_cpf = " + cpf);
				
				if(res.next()) {
					
					respAluno = new ResponsavelAluno(res.getInt("tb_aluno_matricula"), 
							res.getInt("tb_responsavel_cpf"));
				}
					
				return respAluno;
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return null;
	}
	
}
