<!DOCTYPE html>
<%@ tag description="Main Layout" language="java" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<html>
	<head>
 		<title>${title}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="Sistema de control de alumnos.">
		<meta name="author" content="UTNG">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link href="/DWTest/resources/css/bootstrap.css" rel="stylesheet">
		<link href="/DWTest/resources/css/bootstrapValidator.css" rel="stylesheet">
		<link href="/DWTest/resources/css/jquery-ui.css" rel="stylesheet">

		<script src="/DWTest/resources/js/jquery.js"></script>
		<script src="/DWTest/resources/js/bootstrap.js"></script>
		<script src="/DWTest/resources/js/bootstrapValidator.js"></script>
		<script type="text/javascript" src="/DWTest/resources/js/jquery-ui.js"></script>
     	<jsp:invoke fragment="head_area"/>
 </head>
 <body onload="cargarEventos()">
 	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Desplegar navegación</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="#" class="navbar-toggle notificaciones"> <span
				class="badge pull-right">0</span> <span
				class="glyphicon glyphicon-bell"></span>
			</a> <a class="navbar-brand" href="#">UTNG</a>
		</div>
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main">Inicio</a></li>
				<li><a href="users">Usuarios</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> Acciones. <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#"></a></li>
						<li><a href="#">Historial academico.</a></li>
						<li><a href="#">Documentos.</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="notificaciones" id="noticacion2" href="#"> <span
						class="badge pull-right">0</span> <span
						class="glyphicon glyphicon-bell"></span>
				</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
						<span id="nom_usuario"></span><b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li class="divider"></li>
						<li><a href="/DWTest">Salir</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div class="container" style="margin-top: 50px">
		<jsp:invoke fragment="body_area"/>
	</div>
 </body>
</html>
