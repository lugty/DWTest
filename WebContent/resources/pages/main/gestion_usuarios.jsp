<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Gestionar Personal">
	<jsp:attribute name="head_area">
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
								if(xhm == 'success')
									location.reload();
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
			});
		</script>		
	</jsp:attribute>
	<jsp:attribute name="body_area">
		<div>
			<h1>Gestion de Usuarios.</h1>
			<button class="btn btn-primary" id="btnAgregar">
				<span class="glyphicon glyphicon-plus"></span> Agregar
			</button>
			<form action="report" method="post" style="text-align: right;">
				<input class="btn btn-default" type="submit" name="btnPersonas" value="Reporte"/>
			</form>
			<table class="table">
				<thead>
					<tr>
						<th>Id Usuario</th>
						<th>Nombre de Usuario</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="usuarios">
					<tr>
						<td colspan="9">
							<div class="alert alert-info">
								<strong>No hay registros.</strong>
							</div>
						</td>
					</tr>
					</c:if>
					<c:forEach items="${usuarios}" var="usuario">
						<tr>
							<td>${usuario.cve_usuario}</td>
							<td>${usuario.login}</td>
							<td>
								<form action="users" method="post">
									<input type="hidden" name="id" value="${usuario.cve_usuario}"/>
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
						<h4 class="modal-title" id="titulo">Crear Usuario</h4>
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
										<legend>Nueva Cuenta</legend>
										<div class="form-group">
											<label for="nombre">Username</label> 
											<input type="text"
												class="form-control" placeholder="Nombre de usuario"
												name="login" id="login" 
												
												data-bv-message="El nombre de usuario no es valido"

								                data-bv-notempty="true"
								                data-bv-notempty-message="El nombre de usuario es requerido y no puede estar vacio"
								
								                data-bv-regexp="true"
								                data-bv-regexp-regexp="[a-zA-Z0-9_\.]+"
								                data-bv-regexp-message="El nombre de usuario solo puede contener alfabeto, numeros, puntos y guins bajo"
								
								                data-bv-stringlength="true"
								                data-bv-stringlength-min="6"
								                data-bv-stringlength-max="30"
								                data-bv-stringlength-message="El nombre de usuario debe ser mayor a seis y menor de 30 caracteres"
								
								                data-bv-different="true"
								                data-bv-different-field="password"
								                data-bv-different-message="El nombre de usuario y contrasenia no pueden ser los mismos" 
												/>
										</div>
										<div class="form-group">
											<label for="apellidos">Password</label>
											<input type="password"
												class="form-control" name="password" placeholder="password"
												
												data-bv-notempty="true"
								                data-bv-notempty-message="El password es requerido y no puede estar vacio"
								
								                data-bv-identical="true"
								                data-bv-identical-field="re_password"
								                data-bv-identical-message="El password y su confirmacion no son la misma"
								
								                data-bv-different="true"
								                data-bv-different-field="login"
								                data-bv-different-message="El password no puede ser el mismo que el nombre de usuario"
												/>
										</div>
										<div class="form-group">
											<label for="edad">Confirmar Password</label> 
											<input type="password" id="re_password"
												name="re_password" class="form-control"
												placeholder="Edad del alumno" 
												
												data-bv-notempty="true"
								                data-bv-notempty-message="La confirmacion del password es requerida y no puede estar vacia"
								
								                data-bv-identical="true"
								                data-bv-identical-field="password"
								                data-bv-identical-message="El password y su confirmacion no son iguales"
								
								                data-bv-different="true"
								                data-bv-different-field="login"
								                data-bv-different-message="El password no puede ser el mismo que el nombre de usuario"
												/>
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
	</jsp:attribute>
</t:layout>