<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Sistema de control de alumnos.">
<meta name="author" content="UTNG">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="/DWTest/resources/css/bootstrap.css" rel="stylesheet">
<link href="/DWTest/resources/css/bootstrapValidator.css" rel="stylesheet">
<script src="/DWTest/resources/js/jquery.js"></script>
<script src="/DWTest/resources/js/bootstrap.js"></script>
<script src="/DWTest/resources/js/bootstrapValidator.js"></script>
<script type="text/javascript">
	/** Esperamos que se termine de cargar los elementos de la pagina para poder
	trabajar con ellos y asignarles eventos correspondientes. */
	$(document).ready(function() {
		/** Se implemento un plugin para la validacion del formulario.
		Y asi se le asigna la funcion de las alidaciones con jquery usando como selector
		el ID del formulario. */
		$("#crearAlumnoForm").bootstrapValidator({
			submitHandler : function(validator, form, submitButton) {
				/** Una vez validado el formulario, se envia una peticion a AJAX para el registro del nuevo alumno. */
				/** El plugin ofrece este metodo al estar validado y lanzarse el evento "submit" podremos
				trabajar con los elementos del formulario y traer sus valores para enviarlos al servidor. */
				var datos = $("#crearAlumnoForm").serialize();
				console.log(datos);
				
				$.ajax({
					url : "",
					type : "POST",
					data : datos,
					success : function(response, xhm) {
						console.log(response);
						console.log(xhm);
					},
					beforeSend : function() {
					
					},
					error : function() {

					}
				});

			}
		});
		/** Se asigna el evento "click" para mostrar el modal con el formulario de registro de alummno.  */
		$("#btnAgregar").on('click', function() {
			$("#crearAlumno").modal();
		});
		/** Se asgina el evento de Autocompletar. */
		$("#univeridad").on('keypress', function() {
			$.ajax({
				url : "",
				type : "POST",
				data : datos,
				success : function(response, xhm) {
					
				},
				beforeSend : function() {
					
				},
				error : function() {
			
				}
			});
		});
		$("#grado").on('keypress', function() {
			$.ajax({
				url : "",
				type : "POST",
				data : datos,
				success : function() {

				},
				beforeSend : function() {

				},
				error : function() {

				}
			});
		});
	});
</script>
<title>Gestionar alumnos</title>
</head>
<body>
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
				<li class="active"><a href="inicioUsuario.html">Inicio</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> Acciones. <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#"></a></li>
						<li><a href="#">Historial academico.</a></li>
						<li><a href="documentos.html">Documentos.</a></li>
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
						<li><a href="perfil.html">Perfil</a></li>
						<li><a href="acercade.html">Acerca de</a></li>
						<li class="divider"></li>
						<li><a href="/core/controller/controller_usuarios.php">Salir</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div class="container" style="margin-top: 50px">
		<h1>Gestion de Personas.</h1>
		<button class="btn btn-primary" id="btnAgregar">
			<span class="glyphicon glyphicon-plus"></span> Agregar
		</button>
		<form action="report" method="post" style="text-align: right;">
			<input class="btn btn-default" type="submit" name="btnPersonas" value="Reporte"/>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Edad</th>
					<th>Genero</th>
					<th>Fecha_Nacimeinto</th>
					<th>Estado Civil</th>
					<th>Domicilio</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="personas">
				<tr>
					<td colspan="9">
						<div class="alert alert-info">
							<strong>No hay registros.</strong>
						</div>
					</td>
				</tr>
				</c:if>
				<c:forEach items="${personas}" var="persona">
					<tr>
						<td>${persona.nombre}</td>
						<td>${persona.apellidos}</td>
						<td>${persona.edad}</td>
						<td>${persona.genero}</td>
						<td>${persona.fecha_nacimiento}</td>
						<td>${persona.cve_estado_civil.descripcion}</td>
						<td>${persona.cve_domicilio.calle}</td>
						<td>
							<form action="main" method="post">
								<input type="hidden" name="id" value="${persona.cve_persona}"/>
								<input class="btn btn-success" type="submit" name="btnUpdate" value="Modificar"/>
								<input class="btn btn-danger" type="submit" name="btnDelete" value="Eliminar"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="modal fade" id="crearAlumno">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="titulo">Crear alumno</h4>
				</div>
				<div class="modal-body">

					<form method="post" id="crearAlumnoForm"
						data-bv-message="Valor invalido."
						data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
						data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
						data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
						<div class="row">
							<div class="col-md-6">
								<fieldset>
									<legend>Datos personales</legend>
									<div class="form-group">
										<label for="nombre">Nombre</label> <input type="text"
											class="form-control" placeholder="Nombre de alumno"
											name="nombre" id="nombre" data-bv-notempty="true"
											data-bv-notempty-message="El nombre no puede ir vacio.">
									</div>
									<div class="form-group">
										<label for="apellidos">Apellidos</label> <input type="text"
											class="form-control" name="apellidos" placeholder="Apeliidos"
											data-bv-notempty="true"
											data-bv-notempty-message="Los apellidos son requeridos.">
									</div>
									<div class="form-group">
										<label for="edad">Edad</label> <input type="number" id="edad"
											name="edad" class="form-control"
											placeholder="Edad del alumno" data-bv-notempty="true"
											data-bv-notempty-message="El edad no puede ir vacia."
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros">
									</div>
									<div class="form-group">
										<label for="fecha">Fecha de nacimiento</label> <input
											type="text" id="fecha" name="fecha_nacimiento" class="form-control"
											placeholder="Fecha de nacimiento" data-bv-notempty="true"
											data-bv-notempty-message="El fecha no puede ir vacio."
											data-bv-date
											data-bv-date-format="YYYY-MM-DD"
											data-bv-date-message="Ingresar un formato de fecha valido DD-MM-YYYY.">
									</div>
									<div class="form-group">
										<label for="estado_civil">Estado civil</label> <select
											id="estado_civil" name="cve_estado_civil.descripcion" class="form-control"
											data-bv-notempty="true"
											data-bv-notempty-message="Seleccione el estado civil.">
											<option>-- Selecciona --</option>
										</select>
									</div>
									<div class="radio">
										<label> <input type="radio" name="genero"
											id="masculino" value="masculino"
											data-bv-notempty="true"
											data-bv-notempty-message="Seleccione uno de los generos.">
											Masculino
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="genero"
											id="femenino" value="femenino">
											Femenino
										</label>
									</div>
								</fieldset>
								<fieldset>
									<legend>Domicilio</legend>
									<div class="form-group">
										<label for="codigo_postal">Codigo postal</label> <input
											type="text" class="form-control" id="codigo_postal"
											name="cve_domicilio.codigo_postal" placeholder="Codigo postal #####"
											data-bv-notempty="true"
											data-bv-notempty-message="El codigo postal no puede ir vacio."
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros">
									</div>
									<div class="form-group">
										<label for="calle">Calle</label> <input type="text"
											class="form-control" id="calle" name="cve_domicilio.calle"
											placeholder="Calle" data-bv-notempty="true"
											data-bv-notempty-message="La calle no puede ir vacia.">
									</div>
									<div class="form-group">
										<label for="num_ext">Numero Exterior</label> <input
											type="number" id="num_ext" name="cve_domicilio.num_exterior"
											class="form-control" placeholder="Numero exterior"
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros">
									</div>
									<div class="form-group">
										<label for="num_int">Numero Interior</label> <input
											type="number" id="num_int" name="cve_domicilio.num_interior"
											class="form-control" placeholder="Numero interior"
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros">
									</div>
								</fieldset>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="num_depto">Numero de departamento</label> <input
										type="number" id="num_depto" name="cve_domicilio.numero_depto"
										class="form-control" placeholder="Numero del departamento"
										data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros">
								</div>
								<div class="form-group">
									<label for="comentario">Comentarios</label>
									<textarea id="comentario" name="cve_domicilio.comentario"
										class="form-control" placeholder="Comentarios"></textarea>
								</div>
								<div class="checkbox">
									<label> <input type="checkbox" id="estado"
										name="activo" value="">
										Estatus(activo/inactivo).
									</label>
								</div>
								<div class="form-group">
									<label for="estado">Estado</label> <select id="estado"
										name="activi" class="form-control">
										<option>-- Selecciona --</option>
									</select>
								</div>
								<div class="form-group">
									<label for="municipio">Municipio</label> <select id="municipio"
										name="municipio" class="form-control">
										<option>-- Selecciona --</option>
									</select>
								</div>
								<fieldset>
									<legend>Datos escolares</legend>
									<div class="form-group">
										<label for="grupo">Grupo</label> <input type="text"
											class="form-control" id="grupo" name="grupo"
											placeholder="Grupo del alummno" data-bv-notempty="true"
											data-bv-notempty-message="El grupo no puede ir vacio.">
									</div>
									<div class="form-group">
										<label for="universidad">Universidad</label> <input
											type="text" class="form-control" id="univeridad"
											name="univeridad" placeholder="Universidad (Autocomplete)"
											data-bv-notempty="true"
											data-bv-notempty-message="La universidad no puede ir vacio.">
									</div>
									<div class="form-group">
										<label for="grado">Grado academico</label> <input type="text"
											class="form-control" id="grado" name="grado"
											placeholder="Grado academico (Autocomplete)"
											data-bv-notempty="true"
											data-bv-notempty-message="El grado academico no puede ir vacio.">
									</div>
									<div class="form-group">
										<label for="saldo">Saldo del alummno</label> <input
											type="text" class="form-control" id="saldo" name="saldo"
											placeholder="Saldo del alumno"
											data-bv-numeric="true"
											data-bv-numeric-message="Solo se permiten numeros enteros">
									</div>
								</fieldset>
								<fieldset>
									<legend>Datos medicos del alumno</legend>
									<div class="form-group">
										<label for="padecimientos">Padecimientos</label>
										<textarea class="form-control" id="padecimientos"
											name="padecimientos"
											placeholder="Notas generales de los padecimientos que tenga."></textarea>
									</div>
								</fieldset>
							</div>
						</div>
						<div class="pull-right">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cancelar</button>
							<input type="hidden" name="btnInsert" value="insert" />
							<button type="submit" class="btn btn-primary">Agregar</button>
						</div>
						<br> <br>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>