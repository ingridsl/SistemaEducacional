package persistencia;
// Codigo reutilizado do que foi utilizado em sala de aula

import java.sql.*;
import java.util.*;

import negocio.Emprego;


public class EmpregoDAO {
	private Banco banco;
	
	public EmpregoDAO(){
		banco = new Banco();
	}
	
	public int insereEmprego (Emprego emprego){
		try{
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				if(consultaEmprego(emprego.getID()) == null){
					String comandoSQL = "INSERT INTO tb_emprego(ID, categoria, "
							+ "faixa_salarial)" +
					" VALUES ('"+ emprego.getID() + "','"+ emprego.getCategoria() +
					"','"+emprego.getFaixaSalarial() +"') ";
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
	
	public Emprego consultaEmprego(int ID) { 
		try{
			Connection con = banco.getConexao(); 
			//Emprego emprego = new Emprego(); 
			Emprego emprego = null;
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_emprego where ID = "+ID);
				
				if(res.next()) {
					emprego = new Emprego(res.getInt("ID"), 
							res.getString("categoria"),
							res.getFloat("faixa_salarial"));
				}
					
				return emprego;
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage()); 
		}
		return null;
	}
	
	public ArrayList<Emprego> consultaEmprego(){ 
		ArrayList<Emprego> listaEmprego = new ArrayList<Emprego>();
		Emprego emprego = null; 
		try {
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				String query = "SELECT * FROM tb_emprego"; 
				ResultSet res = stmt.executeQuery(query);
				
				while (res.next() ){
					emprego = new Emprego(res.getInt("ID"), 
							res.getString("categoria"),
							res.getFloat("faixa_salarial"));
					listaEmprego.add(emprego);
				}
				stmt. close(); 
				con. close();
			}
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD"); 
		}
		return listaEmprego; 
	}
	
	public int deletaEmprego(int ID) {
		Emprego existencia = null;
		try{
			Connection con = banco.getConexao(); 
			if(con != null){
				existencia = consultaEmprego(ID);
				if(existencia != null){
					Statement stat = con.createStatement();
					stat.executeUpdate("Delete FROM tb_emprego WHERE ID = "+ID); 
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
	
	public boolean editaEmprego(int ID, String n_categoria, float n_faixaSalarial) { 
		try{
			Connection con = banco.getConexao(); 
			if(con != null){
				Statement stat = con.createStatement();
				String comandoSQL = "UPDATE tb_emprego SET categoria='"+ 
				n_categoria + "', faixa_salarial='"+ n_faixaSalarial +
				"' WHERE ID= "+ ID;
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
