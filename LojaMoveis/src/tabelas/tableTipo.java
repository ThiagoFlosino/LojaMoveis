package tabelas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapper.cozinhaMapper;
import Mapper.tipoMapper;

@WebServlet("/Tipo")
public class tableTipo extends HttpServlet {
	
	private static final long serialVersionUID = -3159197991917622791L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Do Post---------------------");
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
				case "Adicionar":
				criarTipo(request,response);
					break;
			}
		}else{
			request.getRequestDispatcher("/teste.jsp").forward(request,response);	
		}
	}
	
	
	private void criarTipo(HttpServletRequest request, HttpServletResponse response){
		String descricao = (String) request.getParameter("descricao");
		try {
			tipoMapper.insert(request.getSession(), descricao);
			request.getRequestDispatcher("/teste.jsp").forward(request, response);;
			request.setAttribute("message", "Novo Tipo criado!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
				case "Excluir":
//					System.out.println("aqui--------------------------------");
					deletarTipos(request,response);
					break;
				case "Listar":
//					System.out.println("Entrou no doGet Listar");
					listarTipos(request, response);
					break;
			}
		}
	}
	
	private void listarTipos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("tipos", tipoMapper.listar(request.getSession(),null));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		request.getRequestDispatcher("/teste.jsp").forward(request,response);
	}
	
	private void deletarTipos(HttpServletRequest request, HttpServletResponse response){
//		System.out.println("Entrou no deleta na Tabela Cozinha");
		String[] ids = request.getParameterValues("listID");
		if(ids == null){
			System.out.println("IDS Nulos ----------------------------------------------------------");
		}
		try {
			tipoMapper.delete(request.getSession(), ids);
//			request.getRequestDispatcher("/teste.jsp").forward(request, response);;
			request.setAttribute("message", "Deletado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
