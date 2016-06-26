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
		System.out.println("Do Post---------------------");
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
		
		try {
			mobiliaMapper.insert(request.getSession(), descricao, custo, tempoEntrega, tipoComodo);
			request.getRequestDispatcher("/listarMobilia.jsp").forward(request, response);;
//			request.setAttribute("message", "Novo departamento criado!");
			
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
			}
		}
	}
	
	private void listarMobilia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("mobilias", mobiliaMapper.listar(request.getSession(),request.getParameter("tipoComodo")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/listarMobilia.jsp").forward(request,response);
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
