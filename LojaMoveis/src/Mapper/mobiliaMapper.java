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


public class mobiliaMapper {
	private static String username = "root";
	private static String senha = "root";
	private static Connection con = null;
	private static PreparedStatement STM = null;
	//private ResultSet result = null;
	private static String driver = "com.mysql.jdbc.Driver";

	public mobiliaMapper(){
		
	}
	
	public static void Connect() throws Exception {
			Class.forName(driver);		
		
		try {
			mobiliaMapper.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/comp3",username,senha);
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
	
	public static boolean insert(HttpSession session, String descricao, float custo, int tempoEspera, String tipoComodo, String tipoMobilia) throws Exception{
		mobiliaMapper.Connect();
		
		String base_query = "insert into MOBILIA(descricao,custo,tempo_entrega,tipo_comodo, tipo_mobilia) values(?,?,?,?,?)";
		
		try {
			STM = con.prepareStatement(base_query);
			STM.setString(1, descricao);
			STM.setFloat(2, custo);
			STM.setInt(3, tempoEspera);
			STM.setString(4, tipoComodo);
			STM.setString(5, tipoMobilia);
			STM.execute();
			mobiliaMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			mobiliaMapper.close();
			return false;
		}

	}
	public static List<Mobilia> listar(HttpSession session, String tipoComodo, String tipoMobilia) throws Exception{
		mobiliaMapper.Connect();
		List<Mobilia> retorno = new ArrayList<Mobilia>();
		String base_query = "SELECT * FROM MOBILIA";
		try{
			if(tipoComodo != null && tipoMobilia != null){
				System.out.println("Entrou no não nulo");
				String q = " WHERE TIPO_COMODO like '%"+ tipoComodo + "%'";
				String p = " AND TIPO_MOBILIA like '%"+tipoMobilia+"%'";
				base_query += q + p;
				STM.setString(1, tipoComodo);
				STM.setString(2, tipoMobilia);
			}if(tipoComodo == null && tipoMobilia != null){
				System.out.println("Entrou no mobilia não nulo");
				String q = " WHERE TIPO_MOBILIA like '%"+tipoMobilia+"%'";
				base_query += q;
			}if(tipoComodo != null && tipoMobilia == null){
				System.out.println("Entrou no comodo não nulo");
				String q = " WHERE TIPO_COMODO like '%"+tipoComodo+"%'";
				base_query += q;
				System.out.println(base_query);
			}
			STM = con.prepareStatement(base_query);
			ResultSet RS = STM.executeQuery();
			while(RS.next()){
				Mobilia novo = new Mobilia();
				novo.setId(RS.getLong("id"));
				novo.setDescricao(RS.getString("descricao"));
				novo.setCusto(RS.getFloat("custo"));
				novo.setTempoEntrega(RS.getInt("tempo_entrega"));
				novo.setTipoComodo(RS.getString("tipo_comodo"));
				novo.setTipoMobilia(RS.getString("tipo_mobilia"));
				retorno.add(novo);
			}
			mobiliaMapper.close();
			return retorno;
		}catch(SQLException e){
			e.printStackTrace();
			mobiliaMapper.close();
			return null;
		}
	}
	public static boolean delete(HttpSession session, String[] ids) throws Exception{
		mobiliaMapper.Connect();
		String base_query = "DELETE FROM MOBILIA WHERE ID IN(?)";
		if(ids == null){
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
			STM.setString(1,"0");
			System.out.println(STM.toString());
			STM.execute();
			mobiliaMapper.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			mobiliaMapper.close();
			return false;
		}

	}
}
