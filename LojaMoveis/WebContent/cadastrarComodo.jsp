<%@page import="Mapper.mobiliaMapper"%>
<%@page import="objetos.Mobilia"%>
<%@page import="Mapper.tipoMapper"%>
<%@page import="tabelas.tableTipo"%>
<%@page import="objetos.Tipo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<title>Cadastrar Comodos</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Loja de Moveis Personalizados</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<!--<ul class="nav navbar-nav navbar-right">
		    <li><a href="#">Início</a></li>
		    <li><a href="#">Opções</a></li>
		    <li><a href="#">Perfil</a></li>
		    <li><a href="#">Ajuda</a></li>
		   </ul>
		-->
		</div>
	</div>
	</nav>
	<div id="main" class="container-fluid" style="padding-top: 20px;">
		<input type="hidden" name="tipoComodo" value="Cozinha">
		<h3 class="page-header">Cadastrar Cozinha</h3>
		<form action="Cozinha" method="post">
			<div id="actions" class="row">
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
							style="margin-top: 25px; padding: 10px;" value="listarMobilia" onclick="function(){document.getElementById('formMobilia').style.visibility = 'visible';}">
							<span class="glyphicon glyphicon-search " id="sizing-addon1"></span>
						</button>
					</div>
				</div>
			</div>
		</form>
		<hr />
<!--		<form action="Mobilia" method="get" > -->
			<table width="80%" class="table table-hover col-md-8" style="display: block; visibility:hidden" id="formMobilia">
				<thead>
					<tr>
						<th>#</th>
						<th>Tipo Comodo</th>
						<th>Tipo de Mobília</th>
						<th>Descricao</th>
						<th>Custo</th>
						<th>Tempo de Entrega</th>
					</tr>
				</thead>
				<tbody>
					<%try {
								List<Mobilia> mobilias = (List) request.getAttribute("mobilias");
								for (Mobilia mobiliai : mobilias) {
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
							} catch (Exception e) {
								e.printStackTrace();
							}%>
				</tbody>
			</table>
<!--		</form>  -->
		<button type="submit" class="btn btn-success" name="acao"
			value="Adicionar">Adicionar</button>
		<hr />
	</div>
</body>
</html>