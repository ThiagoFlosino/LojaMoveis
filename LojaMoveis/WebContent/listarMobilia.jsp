<jsp:include page = "header.jsp" />
<%@page import="Mapper.mobiliaMapper"%>
<%@page import="tabelas.tableMobilia"%>
<%@page import="objetos.Mobilia"%>
<%@page import="java.util.List"%>
<div id="main" class="container-fluid" style="padding-top: 20px;">
	<h3 class="page-header">Listar Mobilia</h3>
	<form action="Mobilia" method="get">
<%-- 		<% tableMobilia.listarMobilia(request, response);%>
 --%>		<table class="table table-hover col-md-4">
			<thead>
				<tr>
					<th>#</th>
					<th>Tipop Comodo</th>
					<th>Tipo de Mobília</th>
					<th>Descricao</th>
					<th>Custo</th>
					<th>Tempo de Entrega</th>
					
				</tr>
			</thead>
			<tbody>
				  <%
				  	try{
						List<Mobilia> mobilias = (List) request.getAttribute("mobilias");
						for (Mobilia mobiliai: mobilias){
				  %>
				<tr>
					<td><input type="checkbox" name="listID" value="<%=mobiliai.getId()%>"/></td>
					<td><%=mobiliai.getTipoComodo()%></td>
					<td><%=mobiliai.getTipoMobilia()%></td>
					<td><%=mobiliai.getDescricao()%></td>
					<td><%=mobiliai.getCusto()%></td>
					<td><%=mobiliai.getTempoEntrega()%></td>
				</tr>
				  <%}
						}catch(Exception e){ 
							e.printStackTrace();
					  }
				  %>
		</tbody>
		</table>
		<hr />
		<button type="submit" name="acao" class="btn btn-primary" value="Listar">Listar</button>
		<button type="submit" name="acao" class="btn btn-success" value="Cadastrar">Cadastar</button>
		<button type="submit" name="acao" class="btn btn-danger" value="Excluir">Excluir</button>		
	</form>
</div>

</body>
</html>