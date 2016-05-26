package persistencia;
// Codigo reutilizado do que foi utilizado em sala de aula

import java.sql.*;
import java.util.*;

import negocio.Responsavel;


public class ResponsavelDAO {
	private Banco banco;
	
	public ResponsavelDAO(){
		banco = new Banco();
	}
	
	public int insereResponsavel (Responsavel responsavel){
		try{
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				if(consultaResponsavel(responsavel.getCpf()) == null){
					String comandoSQL = "INSERT INTO tb_responsavel(cpf, nome, "
							+ "telefone, genero, email, endereco, cep)" +
					" VALUES ('"+ responsavel.getCpf() + "','"+ responsavel.getNome() +
					"','"+responsavel.getTelefone() +"','"+responsavel.getGenero() +
					"','"+responsavel.getEmail() +"','"+responsavel.getEndereco() +
					"','"+responsavel.getCEP() +"') ";
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
	
	public Responsavel consultaResponsavel(int cpf) { 
		try{
			Connection con = banco.getConexao(); 
			//Responsavel responsavel = new Responsavel(); 
			Responsavel responsavel = null;
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel where cpf = "+cpf);
				
				if(res.next()) {
					responsavel = new Responsavel(res.getInt("cpf"), 
							res.getString("nome"),
							res.getString("genero"),
							res.getString("endereco"),
							res.getInt("cep"),
							res.getInt("telefone"),
							res.getString("EMAIL"));
				}
					
				return responsavel;
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Responsavel> consultaResponsavel(){ 
		ArrayList<Responsavel> listaResponsavel = new ArrayList<Responsavel>();
		Responsavel responsavel = null; 
		try {
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				String query = "SELECT * FROM tb_responsavel"; 
				ResultSet res = stmt.executeQuery(query);
				
				while (res.next() ){
					responsavel = new Responsavel(res.getInt("cpf"), 
							res.getString("nome"),
							res.getString("genero"),
							res.getString("endereco"),
							res.getInt("cep"),
							res.getInt("telefone"),
							res.getString("EMAIL"));
				listaResponsavel.add(responsavel);
				}
				stmt. close(); 
				con. close();
			}
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD"); 
		}
		return listaResponsavel; 
	}
	
	public int deletaResponsavel(int cpf) { 
		Responsavel existencia = null;
		try{
			Connection con = banco.getConexao();
			if(con != null){
				existencia = consultaResponsavel(cpf);
				if(existencia != null){
					Statement stat = con.createStatement();
					stat.executeUpdate("Delete FROM tb_responsavel WHERE cpf = "+cpf); 
					stat. close();
					return 0;
				}
			}
			return 1;
		}catch(SQLException e) {
			System.out.println("Problemas ao abrir a conexao com o BD"); 
			return 2; 
		}
	}
	
	public boolean editaResponsavel(int cpf, String n_nome, String n_genero, String n_endereco,
								int n_CEP, int n_telefone, String n_email) { 
		try{
			Connection con = banco.getConexao();
			if(con != null){
				Statement stat = con.createStatement();
				String comandoSQL = "UPDATE tb_responsavel SET "
						+ "nome='"+ n_nome + "', telefone='"+ n_telefone +
				"', genero='"+ n_genero +"', email='"+ n_email +
				"', endereco='"+ n_endereco +
				"', cep='"+ n_CEP +
				"' WHERE cpf= '"+ cpf +"'";
				stat.executeUpdate(comandoSQL); 
				stat. close();
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.out.println("Problemas ao abrir a conexao com o BD"); 
			return false; 
		}
	}
}
