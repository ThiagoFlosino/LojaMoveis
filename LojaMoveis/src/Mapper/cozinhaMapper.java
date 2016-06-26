package Mapper;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import objetos.Cozinha;


public class cozinhaMapper {
	private static String username = "root";
	private static String senha = "root";
	private static Connection con = null;
	private static PreparedStatement STM = null;
	//private ResultSet result = null;
	private static String driver = "com.mysql.jdbc.Driver";

	public cozinhaMapper(){
		
	}
	
	public static void Connect() throws Exception {
			Class.forName(driver);		
		
		try {
			cozinhaMapper.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/comp3",username,senha);
		} catch (SQLException e) {
			System.out.println("Erro " + e.getMessage() + "--------");
		}
	}
	
	public static boolean estaConectado(){
		if(con != null){
			return true;
		}else{
			return false;
		}
	}
	public static boolean close(){
		try {
			con.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	public static boolean insert(HttpSession session, String descricao) throws Exception{
		cozinhaMapper.Connect();
		
		String base_query = "insert into cozinha(descricao) values(?)";
		
		try {
			STM = con.prepareStatement(base_query);
			STM.setString(1, descricao);
			STM.execute();
			cozinhaMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			cozinhaMapper.close();
			return false;
		}

	}
	public static List<Cozinha> listar(HttpSession session, String descricao) throws Exception{
		cozinhaMapper.Connect();
		List<Cozinha> retorno = new ArrayList<Cozinha>();
		String base_query = "SELECT * FROM COZINHA";
		try{
			if(descricao != null){
				String q = " WHERE DESCRICAO LIKE '%?%'";
				base_query += q;
				STM.setString(1, descricao);
			}
			STM = con.prepareStatement(base_query);
			ResultSet RS = STM.executeQuery();
			while(RS.next()){
				Cozinha novo = new Cozinha();
				novo.setId(RS.getLong("id"));
				novo.setDescricao(RS.getString("descricao"));
				retorno.add(novo);
			}
			cozinhaMapper.close();
			return retorno;
		}catch(SQLException e){
			e.printStackTrace();
			cozinhaMapper.close();
			return null;
		}
	}
	public static boolean delete(HttpSession session, String[] ids) throws Exception{
		cozinhaMapper.Connect();
		System.out.println("Entrou na função delete");
		String base_query = "DELETE FROM COZINHA WHERE ID IN(?)";
		if(ids == null){
			System.out.println("Erro Não é possivel remover todos de uma vez");
			return false;
		}
		ArrayList remover = new ArrayList<String>();
		for(String s : ids){
			remover.add(s);
		}
		System.out.println(remover.toArray());
		try {
			System.out.println("Entrou no Try");
			STM = con.prepareStatement(base_query);
			STM.setArray(1,remover);
			System.out.println(STM.toString());
			STM.execute();
			cozinhaMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			cozinhaMapper.close();
			return false;
		}

	}
}
