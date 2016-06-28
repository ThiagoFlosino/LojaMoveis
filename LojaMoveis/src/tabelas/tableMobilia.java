package tabelas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapper.cozinhaMapper;
import Mapper.mobiliaMapper;


@WebServlet("/Mobilia")
public class tableMobilia extends HttpServlet {

	

	private static final long serialVersionUID = -3159197901777622791L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Do Post---------------------");
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
				case "Adicionar":
					criarMobilia(request,response);
					break;
			}
		}else{
			request.getRequestDispatcher("/listarMobilia.jsp").forward(request,response);	
		}
	}
	
	
	private void criarMobilia(HttpServletRequest request, HttpServletResponse response){
		String descricao = (String) request.getParameter("descricao");
		float custo = Float.parseFloat(request.getParameter("custo"));
		int tempoEntrega = Integer.parseInt(request.getParameter("tempoEntrega"));
		String tipoComodo = (String)request.getParameter("tipoComodo");
		String tipoMobilia = (String)request.getParameter("tipoMobilia");
		
		try {
			mobiliaMapper.insert(request.getSession(), descricao, custo, tempoEntrega, tipoComodo, tipoMobilia);
			request.getRequestDispatcher("/listarMobilia.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
				case "Excluir":
					System.out.println("aqui--------------------------------");
					deletarMobilia(request,response);
					break;
				case "Listar":
					System.out.println("Entrou no doGet Listar");
					listarMobilia(request, response);
					break;
				case "Cadastrar":
					System.out.println("Entrou no doGet Cadastrar");
					request.getRequestDispatcher("/cadastrarMobilia.jsp").forward(request, response);
					break;
			}
		}
	}
	
	static void listarMobilia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("mobilias", mobiliaMapper.listar(request.getSession(),request.getParameter("tipoComodo"),
					request.getParameter("tipoMobilia")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String acao = (String)request.getParameter("acao");
		String filtro = "listarMobilia"; 
		if(acao != filtro ){
			System.out.println("Entrou nesta condição -----------------");
			request.getRequestDispatcher("/listarMobilia.jsp").forward(request,response);
		}
	}
	
	private void deletarMobilia(HttpServletRequest request, HttpServletResponse response){
		//System.out.println("Entrou no deleta na Tabela Cozinha");
		String[] ids = request.getParameterValues("listID");
		if(ids == null){
			System.out.println("IDS Nulos ----------------------------------------------------------");
		}
		try {
			cozinhaMapper.delete(request.getSession(), ids);
			request.getRequestDispatcher("/listaMobilia.jsp").forward(request, response);;
			request.setAttribute("message", "Deletado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
