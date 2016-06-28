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

import objetos.Quarto;

public class quartoMapper {
	
	private static String username = "root";
	private static String senha = "root";
	private static Connection con = null;
	private static PreparedStatement STM = null;
	//private ResultSet result = null;
	private static String driver = "com.mysql.jdbc.Driver";

	public quartoMapper(){
		
	}
	
	public static void Connect() throws Exception {
			Class.forName(driver);		
		
		try {
			quartoMapper.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/comp3",username,senha);
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
		quartoMapper.Connect();
		
		String base_query = "insert into quarto(descricao) values(?)";
		
		try {
			STM = con.prepareStatement(base_query);
			STM.setString(1, descricao);
			STM.execute();
			quartoMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			quartoMapper.close();
			return false;
		}

	}
	public static List<Quarto> listar(HttpSession session, String descricao) throws Exception{
		quartoMapper.Connect();
		List<Quarto> retorno = new ArrayList<Quarto>();
		String base_query = "SELECT * FROM QUARTO";
		try{
			if(descricao != null){
				String q = " WHERE DESCRICAO LIKE '%?%'";
				base_query += q;
				STM.setString(1, descricao);
			}
			STM = con.prepareStatement(base_query);
			ResultSet RS = STM.executeQuery();
			while(RS.next()){
				Quarto novo = new Quarto();
				novo.setId(RS.getLong("id"));
				novo.setDescricao(RS.getString("descricao"));
				retorno.add(novo);
			}
			quartoMapper.close();
			return retorno;
		}catch(SQLException e){
			e.printStackTrace();
			quartoMapper.close();
			return null;
		}
	}
	public static boolean delete(HttpSession session, String[] ids) throws Exception{
		quartoMapper.Connect();
		System.out.println("Entrou na função delete");
		String base_query = "DELETE FROM QUARTO WHERE ID IN(?)";
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
			//STM.setArray(1,remover);
			System.out.println(STM.toString());
			STM.execute();
			quartoMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			quartoMapper.close();
			return false;
		}

	}
}
