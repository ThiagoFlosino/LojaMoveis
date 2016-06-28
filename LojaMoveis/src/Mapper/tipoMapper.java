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
import objetos.Mobilia;
import objetos.Tipo;


public class tipoMapper {
	private static String username = "root";
	private static String senha = "root";
	private static Connection con = null;
	private static PreparedStatement STM = null;
	//private ResultSet result = null;
	private static String driver = "com.mysql.jdbc.Driver";

	public tipoMapper(){
		
	}
	
	public static void Connect() throws Exception {
			Class.forName(driver);		
		
		try {
			tipoMapper.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/comp3",username,senha);
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
	
	
	
	public static boolean insert(HttpSession session, String tipo) throws Exception{
		tipoMapper.Connect();
		
		String base_query = "insert into TIPO(descricao) values(?)";
		
		try {
			STM = con.prepareStatement(base_query);
			STM.setString(1, tipo);
			STM.execute();
			tipoMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			tipoMapper.close();
			return false;
		}

	}
	public static List<Tipo> listar(HttpSession session, String tipo) throws Exception{
		tipoMapper.Connect();
		List<Tipo> retorno = new ArrayList<Tipo>();
		String base_query = "SELECT * FROM TIPO";
		try{
			System.out.println(tipo);
			if(tipo != null){
				String q = " WHERE DESCRICAO in (?)";
				base_query += q;
				STM.setString(1, tipo);
			}
			STM = con.prepareStatement(base_query);
			ResultSet RS = STM.executeQuery();
			while(RS.next()){
				Tipo novo = new Tipo();
				novo.setId(RS.getLong("id"));
				novo.setDescricao(RS.getString("descricao"));	
				retorno.add(novo);
			}
			tipoMapper.close();
			System.out.println(retorno.get(0).getDescricao());
			return retorno;
		}catch(SQLException e){
			e.printStackTrace();
			tipoMapper.close();
			return null;
		}
	}
	public static boolean delete(HttpSession session, String[] ids) throws Exception{
		tipoMapper.Connect();
//		System.out.println("Entrou na função delete");
		String base_query = "DELETE FROM MOBILIA WHERE ID IN(?)";
		if(ids == null){
	//		System.out.println("Erro Não é possivel remover todos de uma vez");
			return false;
		}
		String remover = "";
		for(String s : ids){
			remover += s+",";
		}
		remover = remover.substring(0,remover.length()-1);
		System.out.println(remover);
		try {
			System.out.println("Entrou no Try");
			STM = con.prepareStatement(base_query);
			STM.setString(1,remover);
			System.out.println(STM.toString());
			STM.execute();
			tipoMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			tipoMapper.close();
			return false;
		}

	}
}
