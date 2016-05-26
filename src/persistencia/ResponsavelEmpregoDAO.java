package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import negocio.ResponsavelEmprego;

public class ResponsavelEmpregoDAO {
	private Banco banco;
	public ResponsavelEmpregoDAO(){
		banco = new Banco();
	}
	
	public int insereRespEmp(ResponsavelEmprego respEmp){
		try{
			Connection con = banco.getConexao();
			if(con != null){
				Statement stmt = con.createStatement();
				if(consultaRespEmp(respEmp.getID(), respEmp.getCpf()) == null){
					String comandoSQL = "INSERT INTO tb_responsavel_emprego(tb_responsavel_cpf, "
							+ "tb_emprego_ID)" +
					" VALUES ('"+ respEmp.getCpf() + "','"+ respEmp.getID() + "') ";
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
	
	public int deletaRespEmp(int id, int cpf){ 
		try{
			Connection con = banco.getConexao();
			if(con != null){
				if(consultaRespEmp(id,cpf) != null){
					Statement stat = con.createStatement();
					stat.executeUpdate("Delete FROM tb_responsavel_emprego"
						+ " WHERE tb_emprego_ID = " + id
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
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_emprego");
				
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
	
	public ArrayList<String> consultaResp(int id) {
		ArrayList<String> listaResps = new ArrayList<String>();
		try{
			Connection con = banco.getConexao();  
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_emprego"
						+ " WHERE tb_emprego_ID = " + id);
				
				while(res.next()) {
					int cpf = res.getInt("tb_responsavel_cpf");
					listaResps.add(Integer.toString(cpf));
				}
				
				stat.close(); 
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return listaResps;
	}
	
	public ArrayList<String> consultaEmprego() {
		ArrayList<String> listaEmps = new ArrayList<String>();
		try{
			Connection con = banco.getConexao();  
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_emprego");
				
				while(res.next()) {
					int id = res.getInt("tb_emprego_ID");
					listaEmps.add(Integer.toString(id));
				}
				
				stat.close(); 
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return listaEmps;
	}
	
	public ArrayList<String> consultaEmprego(int cpf) {
		ArrayList<String> listaEmps = new ArrayList<String>();
		try{
			Connection con = banco.getConexao();  
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_emprego"
						+ " WHERE tb_responsavel_cpf = " + cpf);
				
				while(res.next()) {
					int id = res.getInt("tb_emprego_ID");
					listaEmps.add(Integer.toString(id));
				}
				
				stat.close(); 
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return listaEmps;
	}
	
	public ResponsavelEmprego consultaRespEmp(int id, int cpf) { 
		try{
			Connection con = banco.getConexao();  
			ResponsavelEmprego respEmp = null;
			
			if(con != null){
				Statement stat = con.createStatement();
				
				ResultSet res = stat.executeQuery("SELECT * FROM tb_responsavel_emprego"
						+ " WHERE tb_emprego_ID = " + id
						+ " and tb_responsavel_cpf = " + cpf);
				
				if(res.next()) {
					
					respEmp = new ResponsavelEmprego(res.getInt("tb_emprego_ID"), 
							res.getInt("tb_responsavel_cpf"));
				}
					
				return respEmp;
			}
		} catch(SQLException e) {
			System.out.println("Erro = "+e.getMessage());
		}
		return null;
	}
}
