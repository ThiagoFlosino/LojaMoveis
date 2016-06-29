<jsp:include page="header.jsp" />
<%@page import="Mapper.mobiliaMapper"%>
<%@page import="tabelas.tableCozinha"%>
<%@page import="objetos.Cozinha"%>
<%@page import="objetos.Mobilia"%>
<%@page import="java.util.List"%>
<title>Lista Cozinhas</title>
<div id="main" class="container-fluid" style="padding-top: 20px;">
	<h3 class="page-header">Listar Cozinhas</h3>
	<form action="Cozinha" method="get">
		<%
//			tableCozinha.listarCozinhas(request, response);
		%>
		<table class="table table-hover col-md-4">
			<thead>
				<tr>
					<th>#</th>
					<th>Descrição</th>
				</tr>
			</thead>
			<tbody>
				<%
					try {
						List<Cozinha> cozinhas = (List) request.getAttribute("cozinhas");
						for (Cozinha cozinhai : cozinhas) {
				%>
				<tr>
					<td><input type="checkbox" name="listID"
						value="<%=cozinhai.getId()%>" /></td>
					<td><%=cozinhai.getDescricao()%></td>
					<div>
						<table>
							<thead>
								<tr>
									<th>Descricao</th>
									<th>Custo</th>
									<th>Tempo de Entrega</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<Mobilia> mobilias = cozinhai.getMobilias();
											for (Mobilia mobiliai : mobilias) {
								%>
								<tr>
									<td><%=mobiliai.getDescricao()%></td>
									<td><%=mobiliai.getCusto()%></td>
									<td><%=mobiliai.getTempoEntrega()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</tr>
				<%
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
				%>
			</tbody>
		</table>
		<hr />
		<button type="submit" name="acao" class="btn btn-primary"
			value="Listar">Listar</button>
		<button type="submit" name="acao" class="btn btn-success"
			value="Cadastrar">Cadastar</button>
		<button type="submit" name="acao" class="btn btn-danger"
			value="Excluir">Excluir</button>
	</form>
</div>

</body>
</html>