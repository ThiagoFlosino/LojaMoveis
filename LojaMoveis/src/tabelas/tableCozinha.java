package tabelas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapper.cozinhaMapper;
import Mapper.mobiliaMapper;

@WebServlet("/Cozinha")
public class tableCozinha extends HttpServlet {

	

	private static final long serialVersionUID = -3159197991777622791L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do Post---------------------");
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
				case "Adicionar":
				criarCozinha(request,response);
					break;
				case "listarMobilia":
					listarMobilia(request,response);
					break;

			}
		}else{
			request.getRequestDispatcher("/teste.jsp").forward(request,response);	
		}
	}
	
	
	private void criarCozinha(HttpServletRequest request, HttpServletResponse response){
		String descricao = (String)request.getParameter("descricao");
		System.out.println(descricao);
		String[] mobilias = request.getParameterValues("listID");
		System.out.println(mobilias.length);
		try {
			cozinhaMapper.insert(request.getSession(), descricao, mobilias);
			request.getRequestDispatcher("/teste.jsp").forward(request, response);;
			request.setAttribute("message", "Novo departamento criado!");
			
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
					deletarCozinha(request,response);
					break;
				case "Listar":
					System.out.println("Entrou no doGet Listar");
					listarCozinhas(request, response);
					break;
			}
		}
	}
	
	public static void listarCozinhas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("cozinhas", cozinhaMapper.listar(request.getSession(),null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/listarCozinha.jsp").forward(request,response);
	}
	
	private void deletarCozinha(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Entrou no deleta na Tabela Cozinha");
		String[] ids = request.getParameterValues("listID");
		if(ids == null){
			System.out.println("IDS Nulos ----------------------------------------------------------");
		}
		try {
			cozinhaMapper.delete(request.getSession(), ids);
			request.getRequestDispatcher("/teste.jsp").forward(request, response);
			request.setAttribute("message", "Deletado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	void listarMobilia(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String tipo_mobilia = request.getParameter("tipoMobilia");
		String descricao = request.getParameter("descricao");
		try {
			request.setAttribute("mobilias",mobiliaMapper.listar(request.getSession(),(String)request.getParameter("tipoComodo"),
					(String)request.getParameter("tipoMobilia"),(Integer) null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/cadastrarCozinha.jsp").forward(request, response);
		request.setAttribute("tipoMobilia", tipo_mobilia);
		request.setAttribute("descricao", descricao);
		request.setAttribute("visibilidade", true);
	}
}
