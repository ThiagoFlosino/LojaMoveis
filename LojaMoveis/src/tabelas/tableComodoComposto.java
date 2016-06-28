package tabelas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapper.comodoCompostoMapper;
import Mapper.quartoMapper;

@WebServlet("/ComodoComposto")
public class tableComodoComposto extends HttpServlet{
	
	private static final long serialVersionUID = -3159197991777622791L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do Post---------------------");
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
				case "Adicionar":
				criarComodoComposto(request,response);
					break;
			}
		}else{
			request.getRequestDispatcher("/teste.jsp").forward(request,response);	
		}
	}
	
	
	private void criarComodoComposto(HttpServletRequest request, HttpServletResponse response){
		String descricao = (String) request.getParameter("descricao");
		try {
			comodoCompostoMapper.insert(request.getSession(), descricao);
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
					deletarComodoComposto(request,response);
					break;
				case "Listar":
					System.out.println("Entrou no doGet Listar");
					listarComodosCompostos(request, response);
					break;
			}
		}
	}
	
	private void listarComodosCompostos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("ComodoCompostos", comodoCompostoMapper.listar(request.getSession(),null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/teste.jsp").forward(request,response);
	}
	
	private void deletarComodoComposto(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Entrou no deleta na Tabela ComodoComposto");
		String[] ids = request.getParameterValues("listID");
		if(ids == null){
			System.out.println("IDS Nulos ----------------------------------------------------------");
		}
		try {
			comodoCompostoMapper.delete(request.getSession(), ids);
			request.getRequestDispatcher("/teste.jsp").forward(request, response);
			request.setAttribute("message", "Deletado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	

}
