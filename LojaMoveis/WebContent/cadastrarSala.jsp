<%@page import="Mapper.mobiliaMapper"%>
<%@page import="objetos.Mobilia"%>
<%@page import="Mapper.tipoMapper"%>
<%@page import="tabelas.tableMobilia"%>
<%@page import="tabelas.tableTipo"%>
<%@page import="objetos.Tipo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="main" class="container-fluid" style="padding-top: 20px;">
	<input type="hidden" name="tipoComodo" value="Sala">
	<input type="hidden" name="visibilidade" value="false">
	<h3 class="page-header">Cadastrar Sala</h3>
	<form action="Sala" method="post">
		<div id="actions" class="row" onload="mostarTabela();">
			<div class="col-md-8">
				<div class="row">
					<div class="form-group col-md-4">
						<label for="campo2">Descrição</label> <input type="text"
							class="form-control" name="descricao">
					</div>
					<div class="form-group col-md-4">
						<input type="hidden" name=""> <label for="campo4">Tipo
							de Mobílias</label> <select class="form-control" name="tipoMobilia">
							<option value="">----------------</option>
							<%
										List<Tipo> tipos = tipoMapper.listar(null, null);
										for (Tipo T : tipos) {
									%>
							<option value="<%=T.getDescricao()%>"><%=T.getDescricao()%></option>
							<%
										}
									%>
						</select>
					</div>
					<button type="submit" class="btn btn-default" name="acao"
						style="margin-top: 25px; padding: 10px;" value="listarMobilia" onclick="listarMobilias();">
						<span class="glyphicon glyphicon-search " id="sizing-addon1"></span>
					</button>
				</div>
			</div>
		</div>
	<hr />
	<table class="table table-hover col-md-4" id="tabela">
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
				<td><input type="checkbox" name="listID"
					value="<%=mobiliai.getId()%>" /></td>
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
		<button type="submit" class="btn btn-success" name="acao" value="Adicionar">Adicionar</button>
	</form>
	<hr />
</div>
</body>
</html>