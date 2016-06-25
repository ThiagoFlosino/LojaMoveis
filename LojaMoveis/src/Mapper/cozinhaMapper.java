package Mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objetos.Cozinha;

public class cozinhaMapper {
	private String username = "root";
	private String senha = "root";
	private Connection con = null;
	private PreparedStatement STM = null;
	private ResultSet result = null;
	private String driver = "com.mysql.jdbc.Driver";

	public cozinhaMapper(){
		
	}
	
	public void Connect() throws Exception {
			Class.forName(driver);		
		
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/comp3","root","root");
		} catch (SQLException e) {
			System.out.println("Erro " + e.getMessage() + "--------");
		}
	}
	
	public boolean estaConectado(){
		if(con != null){
			return true;
		}else{
			return false;
		}
	}
	public boolean close(){
		try {
			con.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean insert(Cozinha cozinha){
		
		String base_query = "insert into cozinha(descricao) values(?)";
		
		try {
			this.STM = this.con.prepareStatement(base_query);
			STM.setString(0, cozinha.getDescricao());
			STM.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		}
	
}
