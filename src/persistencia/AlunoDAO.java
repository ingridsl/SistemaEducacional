package persistencia;
// Codigo reutilizado do que foi utilizado em sala de aula

import java.sql.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

import negocio.Aluno;


public class AlunoDAO {
	private Banco banco;
	public AlunoDAO(){
		banco = new Banco();
	}
	
	public int insereAluno (Aluno aluno){
		try{
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				if(consultaAluno(aluno.getMatricula()) == null){
					String comandoSQL = "INSERT INTO tb_aluno(matricula, "
							+ "endereco, cep, data_nascimento, nome, telefone, "
							+ "email, genero)"//, tb_escola_ID, tb_tipo_aluno_ID, "
							+// "tb_turma_letra, tb_turma_tb_serie_ID)" +
					" VALUES ('"+ aluno.getMatricula() + "','"+ aluno.getEndereco() +
					"','"+aluno.getCEP() +"','"+aluno.getDataNascimento() +
					"','"+aluno.getNome() +"','"+aluno.getTelefone() +
					"','"+aluno.getEmail() +//"','"+aluno.getEscolaID() +
					"','"+aluno.getGenero()// +"','"+aluno.getTipoAlunoID() +
					//"','"+aluno.getTurmaLetra() +"','"+aluno.getTurmaSerieID() 
					+"') ";
					stmt.executeUpdate(comandoSQL );
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
	
	public Aluno consultaAluno(int matricula) { 
		try{
			Connection con = banco.getConexao();  
			Aluno aluno = null;
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_aluno where matricula = "+matricula);
				
				if(res.next()) {
					Date orig = res.getDate("data_nascimento");
					String nasc = new SimpleDateFormat("yyyy-MM-dd").format(orig);
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
					LocalDate dataNasc = LocalDate.parse(nasc, format);
					aluno = new Aluno(res.getInt("matricula"), 
							res.getString("nome"),
							dataNasc,
							res.getString("genero"),
							res.getString("endereco"),
							res.getInt("cep"),
							res.getInt("telefone"),
							res.getString("EMAIL"),
							res.getInt("tb_escola_ID"),
							res.getInt("tb_tipo_aluno_ID"),
							res.getString("tb_turma_letra"),
							res.getInt("tb_turma_tb_serie_ID"));
				}
					
				return aluno;
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Aluno> consultaAluno(){ 
		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
		Aluno aluno = null; 
		try {
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				String query = "SELECT * FROM tb_aluno"; 
				ResultSet res = stmt.executeQuery(query);
				
				while (res.next() ){
					Date orig = res.getDate("data_nascimento");
					String nasc = new SimpleDateFormat("yyyy-MM-dd").format(orig);
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
					LocalDate dataNasc = LocalDate.parse(nasc, format);
					aluno = new Aluno(res.getInt("matricula"), 
							res.getString("nome"),
							dataNasc,
							res.getString("genero"),
							res.getString("endereco"),
							res.getInt("cep"),
							res.getInt("telefone"),
							res.getString("EMAIL"),
							res.getInt("tb_escola_ID"),
							res.getInt("tb_tipo_aluno_ID"),
							res.getString("tb_turma_letra"),
							res.getInt("tb_turma_tb_serie_ID"));
				listaAluno.add(aluno);
				}
				stmt.close(); 
				con.close();
			}
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD"); 
		}
		return listaAluno; 
	}
	
	public int deletaAluno(int matricula) { 
		Aluno existencia = null;
		try{
			Connection con = banco.getConexao();
			if(con != null){
				existencia = consultaAluno(matricula);
				if(existencia != null){
					Statement stat = con.createStatement();
					stat.executeUpdate("Delete FROM tb_aluno WHERE matricula = "+matricula); 
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
	
	public boolean editaAluno(int matricula, String n_nome, LocalDate n_dataNascimento,
			  String n_genero, String n_endereco, int n_CEP, int n_telefone, String n_email) { 
		try{
		Connection con = banco.getConexao();
		if(con != null){
			Statement stat = con.createStatement();
			String comandoSQL = "UPDATE tb_aluno SET "
					+ "endereco='" + n_endereco + "', cep='"+ n_CEP +
			"', data_nascimento='" + n_dataNascimento +
			"', nome='"+ n_nome + "', telefone='" + n_telefone +
			"', email='"+ n_email +	"', genero='" + n_genero +
			"' WHERE matricula= "+ matricula;
			stat.executeUpdate(comandoSQL); 
			stat.close();
			return true;
		}
			return false;
		}catch(SQLException e) {
			System.out.println("Problemas ao abrir a conexao com o BD");
			e.printStackTrace();
			return false; 
		}
	}
	
	public boolean editaAluno(int matricula, String n_nome, LocalDate n_dataNascimento,
						  String n_genero, String n_endereco, int n_CEP, int n_telefone,
						  String n_email, int n_escolaID, int n_tipoAlunoID, 
						  String n_turmaLetra) { 
		try{
			Connection con = banco.getConexao();
			if(con != null){
				Statement stat = con.createStatement();
				String comandoSQL = "UPDATE tb_aluno SET "
						+ "endereco='"+ n_endereco + "', cep='"+ n_CEP +
				"', data_nascimento='"+ n_dataNascimento +
				"', nome='"+ n_nome +"', telefone='"+ n_telefone +
				"', email='"+ n_email +"', tb_escola_ID='"+ n_escolaID +
				"', genero='"+ n_genero +"', tb_tipo_aluno_ID='"+ n_tipoAlunoID +
				"', tb_turma_letra='"+ n_turmaLetra +
				//"', tb_turma_tb_serie_ID='"+ n_turmaSerieID +
				"' WHERE matricula= "+ matricula;
				stat.executeUpdate(comandoSQL); 
				stat.close();
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.out.println("Problemas ao abrir a conexao com o BD"); 
			return false; 
		}
	}
	
	public ArrayList<String> consultaEscola(){ 
		ArrayList<String> listaIDEsc = new ArrayList<String>();
		try {
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				String query = "SELECT * FROM tb_escola"; 
				ResultSet res = stmt.executeQuery(query);
				
				while (res.next() ){
					int id = res.getInt("ID");
					listaIDEsc.add(Integer.toString(id));
				}
				
				stmt.close(); 
				con.close();
			}
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD"); 
		}
		return listaIDEsc; 
	}
	
	public ArrayList<String> consultaTipo(){ 
		ArrayList<String> listaIDTipo = new ArrayList<String>();
		try {
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				String query = "SELECT * FROM tb_tipo_aluno"; 
				ResultSet res = stmt.executeQuery(query);
				
				while (res.next() ){
					int id = res.getInt("ID");
					listaIDTipo.add(Integer.toString(id));
				}
				
				stmt.close(); 
				con.close();
			}
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD"); 
		}
		return listaIDTipo; 
	}
	
	public ArrayList<String> consultaTurma(){ 
		ArrayList<String> listaIDTipo = new ArrayList<String>();
		try {
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				String query = "SELECT * FROM tb_turma"; 
				ResultSet res = stmt.executeQuery(query);
				
				while (res.next() ){
					listaIDTipo.add(res.getString("letra"));
				}
				
				stmt.close(); 
				con.close();
			}
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD"); 
		}
		return listaIDTipo; 
	}
}
