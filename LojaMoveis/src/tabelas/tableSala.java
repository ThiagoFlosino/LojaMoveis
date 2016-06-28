package tabelas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapper.salaMapper;

@WebServlet("/Sala")
public class tableSala extends HttpServlet {
	
	private static final long serialVersionUID = -3159197991777622791L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do Post---------------------");
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
				case "Adicionar":
				criarSala(request,response);
					break;
			}
		}else{
			request.getRequestDispatcher("/teste.jsp").forward(request,response);	
		}
	}
	
	
	private void criarSala(HttpServletRequest request, HttpServletResponse response){
		String descricao = (String) request.getParameter("descricao");
		try {
			salaMapper.insert(request.getSession(), descricao);
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
					deletarSala(request,response);
					break;
				case "Listar":
					System.out.println("Entrou no doGet Listar");
					listarSalas(request, response);
					break;
			}
		}
	}
	
	private void listarSalas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("salas", salaMapper.listar(request.getSession(),null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/teste.jsp").forward(request,response);
	}
	
	private void deletarSala(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Entrou no deleta na Tabela Sala");
		String[] ids = request.getParameterValues("listID");
		if(ids == null){
			System.out.println("IDS Nulos ----------------------------------------------------------");
		}
		try {
			salaMapper.delete(request.getSession(), ids);
			request.getRequestDispatcher("/teste.jsp").forward(request, response);
			request.setAttribute("message", "Deletado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
