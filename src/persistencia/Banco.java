package persistencia;
// Codigo obtido do que foi utilizado em sala de aula

import java.sql.*;
public class Banco {
	// Access
	private static String DRIVER_BD = "com.mysql.jdbc.Driver";
	private static String DATABASE = "educacao"; /*Modificar de acordo com a sua database*/
	private static String URL_BD = "jdbc:mysql://localhost:3306/"+DATABASE+"?autoReconnect=true&relaxAutoCommit=true";
	private static String usuario = "root"; /*Modificar de acordo com o seu usu�rio*/
	private static String senha = "january19@"; /*Modificar de acordo com sua senha*/
	private static Connection con;
	
	public Banco(){
		try{
			Class.forName(DRIVER_BD);
		} catch (ClassNotFoundException e){
			System.out.println("Problemas ao carregar o driver"); 
		}
	}
	
	public Connection getConexao(){
		try {
			if ((con == null) || (con.isClosed()))
				con = DriverManager.getConnection(URL_BD,usuario,senha);
		} catch (SQLException e){
			System.out.println("Problemas ao abrir a conexao com o BD");
			e.printStackTrace();
		}
		return con;
	}
}
