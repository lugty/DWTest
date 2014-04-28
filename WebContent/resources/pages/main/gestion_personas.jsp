<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Gestionar Usuarios">
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
			<c:if test="${!empty datosPersona}">
				$("#crearAlumno").modal();
			</c:if>
			$("#btnAgregar").on('click', function() {
				$("#crearAlumno").modal();
			});
		});
		
		var cargarEventos = function () {
			/** Valores que se mostraran en el autocompletar. */
			var universidades = [
				<c:forEach items="${universidades}" var="universidad">
				 {
					value : ${universidad.cve_universidad},
					label : "${universidad.nombre}"
				 },
				</c:forEach>
			 ];
			$( "#auto_complete_universidad" ).autocomplete({
			      minLength: 0,
			      source: universidades,
			      focus: function( event, ui ) {
			    	  console.log(ui);
			        $( "#auto_complete_universidad" ).val( ui.item.label );
			        $( "#universidadH" ).val( ui.item.value );	
			        return false;
			      },
			      select: function( event, ui ) {
			    	  console.log(ui);
			        $( "#auto_complete_universidad" ).val( ui.item.label );
			        $( "#universidadH" ).val( ui.item.value );		 
			        return false;
			      }
			    }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
					$(ul).css("z-index", 99999);
			        return $( "<li>" )
			          .append( "<a>" + item.label + "</a>" )
			          .appendTo( ul );
			      };
			var grados = [
	       			<c:forEach items="${grados}" var="grado">
	       			 {
	       				value : ${grado.cve_area_grado_academico},
	       				label : "${grado.descripcion}"
	       			 },
	       			</c:forEach>
	       		 ];
	       		$( "#auto_complete_grado" ).autocomplete({
	       		      minLength: 0,
	       		      source: grados,
	       		      focus: function( event, ui ) {
	       		    	console.log(ui);
	       		        $( "#auto_complete_grado" ).val( ui.item.label );
	       		     	$( "#gradoH" ).val( ui.item.value );
	       		        return false;
	       		      },
	       		      select: function( event, ui ) {
	       		    	  console.log(ui);
	       		        $( "#auto_complete_grado" ).val( ui.item.label );
	       		        $( "#gradoH" ).val( ui.item.value );		 
	       		        return false;
	       		      }
	       		    }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	       			$(ul).css("z-index", 99999);
	       	      return $( "<li>" )
	       	        .append( "<a>" + item.label + "</a>" )
	       	        .appendTo( ul );
	       	    };
	       	    
	       	 var ciclos = [
	              			<c:forEach items="${ciclos}" var="ciclo">
	              			 {
	              				value : ${ciclo.cve_ciclo},
	              				label : "${ciclo.ciclo_escolar}"
	              			 },
	              			</c:forEach>
	              		 ];
	              		$( "#auto_complete_ciclo" ).autocomplete({
	              		      minLength: 0,
	              		      source: ciclos,
	              		      focus: function( event, ui ) {
	              		        $( "#auto_complete_ciclo" ).val( ui.item.label );
	              		        return false;
	              		      },
	              		      select: function( event, ui ) {
	              		        $( "#auto_complete_ciclo" ).val( ui.item.label );
	              		        $( "#ciclo" ).val( ui.item.value );		 
	              		        return false;
	              		      }
	              		    }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	              			$(ul).css("z-index", 99999);
	              	      return $( "<li>" )
	              	        .append( "<a>" + item.label + "</a>" )
	              	        .appendTo( ul );
	              	    };
		};
	</script>
	</jsp:attribute>
	<jsp:attribute name="body_area">
		
	<div>
		<h1>Gestion de Personas.</h1>
		<button class="btn btn-primary" id="btnAgregar">
			<span class="glyphicon glyphicon-plus"></span> Agregar
		</button>
		<form action="report" method="post" style="text-align: right;">
			<input class="btn btn-default" type="submit" name="listPersonas"
				value="Reporte Alumnos" />
		</form>
		<form action="report" method="post" style="text-align: right;">
			<input class="btn btn-default" type="submit" name="listUniversidades"
				value="Reporte Universidades" />
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
				<c:if test="${empty personas}">
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
						<td>${persona.cve_persona.nombre}</td>
						<td>${persona.cve_persona.apellidos}</td>
						<td>${persona.cve_persona.edad}</td>
						<td>${persona.cve_persona.genero}</td>
						<td>${persona.cve_persona.fecha_nacimiento}</td>
						<td>${persona.cve_persona.cve_estado_civil.descripcion}</td>
						<td>${persona.cve_persona.cve_domicilio.calle}</td>
						<td>
							<form action="main" method="post">
								<input type="hidden" name="id" value="${persona.cve_alumno}" />
								<input class="btn btn-success" type="submit" name="btnUpdate"
									value="Modificar" /> <input class="btn btn-danger"
									type="submit" name="btnDelete" value="Eliminar" />
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
							<input type="hidden" name="id" value="${datosPersona.cve_alumno}" />
							<div class="col-md-6">
								<fieldset>
									<legend>Datos personales</legend>
									<div class="form-group">
										<label for="nombre">Nombre</label> <input type="text"
											class="form-control" placeholder="Nombre de alumno"
											name="cve_persona.nombre" id="nombre" data-bv-notempty="true"
											data-bv-notempty-message="El nombre no puede ir vacio."
											value="${datosPersona.cve_persona.nombre }">
									</div>
									<div class="form-group">
										<label for="apellidos">Apellidos</label> <input type="text"
											class="form-control" name="cve_persona.apellidos"
											placeholder="Apeliidos" data-bv-notempty="true"
											data-bv-notempty-message="Los apellidos son requeridos."
											value="${datosPersona.cve_persona.apellidos }">
									</div>
									<div class="form-group">
										<label for="edad">Edad</label> <input type="number" id="edad"
											name="cve_persona.edad" class="form-control"
											placeholder="Edad del alumno" data-bv-notempty="true"
											data-bv-notempty-message="El edad no puede ir vacia."
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros"
											value="${datosPersona.cve_persona.edad }">
									</div>
									<div class="form-group">
										<label for="fecha">Fecha de nacimiento</label> <input
											type="text" id="fecha" name="cve_persona.fecha_nacimiento"
											class="form-control" placeholder="Fecha de nacimiento"
											data-bv-notempty="true"
											data-bv-notempty-message="El fecha no puede ir vacio."
											data-bv-date data-bv-date-format="YYYY-MM-DD"
											data-bv-date-message="Ingresar un formato de fecha valido YYYY-MM-DD."
											value="${datosPersona.cve_persona.fecha_nacimiento }">
									</div>
									<div class="form-group">
										<label for="estado_civil">Estado civil</label> <select
											id="estado_civil"
											name="cve_persona.cve_estado_civil.descripcion"
											class="form-control" data-bv-notempty="true"
											data-bv-notempty-message="Seleccione el estado civil.">
											<option>-- Selecciona --</option>
											<c:forEach items="${estados_civil}" var="estado_civil">
												<option value="${estado_civil.cve_estado_civil}"
													<c:if test="${estado_civil.cve_estado_civil == datosPersona.cve_persona.cve_estado_civil.cve_estado_civil}">selected</c:if>>${estado_civil.abreviatura }</option>
											</c:forEach>
										</select>
									</div>
									<div class="radio">
										<label> <input type="radio" name="cve_persona.genero"
											id="masculino" value="masculino" data-bv-notempty="true"
											data-bv-notempty-message="Seleccione uno de los generos."
											<c:if test="${datosPersona.cve_persona.genero == 'masculino'}">checked</c:if>>
											Masculino
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="cve_persona.genero"
											id="femenino" value="femenino"
											<c:if test="${datosPersona.cve_persona.genero == 'femenino'}">checked</c:if>>
											Femenino
										</label>
									</div>
								</fieldset>
								<fieldset>
									<legend>Domicilio</legend>
									<div class="form-group">
										<label for="codigo_postal">Codigo postal</label> <input
											type="text" class="form-control" id="codigo_postal"
											name="cve_persona.cve_domicilio.codigo_postal"
											placeholder="Codigo postal #####" data-bv-notempty="true"
											data-bv-notempty-message="El codigo postal no puede ir vacio."
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros"
											value="${datosPersona.cve_persona.cve_domicilio.codigo_postal }">
									</div>
									<div class="form-group">
										<label for="calle">Calle</label> <input type="text"
											class="form-control" id="calle"
											name="cve_persona.cve_domicilio.calle" placeholder="Calle"
											data-bv-notempty="true"
											data-bv-notempty-message="La calle no puede ir vacia."
											value="${datosPersona.cve_persona.cve_domicilio.calle }">
									</div>
									<div class="form-group">
										<label for="num_ext">Numero Exterior</label> <input
											type="number" id="num_ext"
											name="cve_persona.cve_domicilio.numero_exterior"
											class="form-control" placeholder="Numero exterior"
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros"
											value="${datosPersona.cve_persona.cve_domicilio.numero_exterior }">
									</div>
									<div class="form-group">
										<label for="num_int">Numero Interior</label> <input
											type="number" id="num_int"
											name="cve_persona.cve_domicilio.numero_interior"
											class="form-control" placeholder="Numero interior"
											data-bv-integer="true"
											data-bv-integer-message="Solo se permiten numeros enteros"
											value="${datosPersona.cve_persona.cve_domicilio.numero_interior }">
									</div>
								</fieldset>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="num_depto">Numero de departamento</label> <input
										type="number" id="num_depto"
										name="cve_persona.cve_domicilio.numero_depto"
										class="form-control" placeholder="Numero del departamento"
										data-bv-integer="true"
										data-bv-integer-message="Solo se permiten numeros enteros"
										value="${datosPersona.cve_persona.cve_domicilio.numero_depto }">
								</div>
								<div class="form-group">
									<label for="comentario">Comentarios</label>
									<textarea id="comentario"
										name="cve_persona.cve_domicilio.comentario"
										class="form-control" placeholder="Comentarios">${datosPersona.cve_persona.cve_domicilio.comentario }</textarea>
								</div>
								<div class="checkbox">
									<label> <input type="checkbox" id="estado"
										name="cve_persona.cve_domicilio.activo" value=""
										<c:if test="${datosPersona.cve_persona.cve_domicilio.activo}">checked</c:if>>
										Estatus(activo/inactivo).
									</label>
								</div>
								<div class="form-group">
									<label for="estado">Estado</label> <select id="estado" name=""
										class="form-control">
										<option>-- Selecciona --</option>
										<c:forEach items="${estados}" var="estado">
											<option value="${estado.cve_estado}">${estado.estado }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="municipio">Municipio</label> <select id="municipio"
										name="cve_persona.cve_domicilio.cve_municipio.cve_municipio"
										class="form-control">
										<option>-- Selecciona --</option>
										<c:forEach items="${municipios}" var="municipio">
											<option value="${municipio.cve_municipio}"
												<c:if test="${municipio.cve_municipio == datosPersona.cve_persona.cve_domicilio.cve_municipio.cve_municipio}">selected</c:if>>${municipio.municipio }</option>
										</c:forEach>
									</select>
								</div>
								<fieldset>
									<legend>Datos escolares</legend>
									<div class="form-group">
										<label for="grupo">Grupo</label> <input type="text"
											class="form-control" id="grupo" name="grupo"
											placeholder="Grupo del alummno" data-bv-notempty="true"
											data-bv-notempty-message="El grupo no puede ir vacio."
											value="${datosPersona.grupo }">
									</div>
									<div class="form-group">
										<label for="universidad">Universidad (Presione tecla
											hacia abajo para mostrar opciones)</label> <input type="text"
											class="form-control" id="auto_complete_universidad"
											name="auto_complete_univeridad"
											placeholder="Universidad (Autocomplete)"
											data-bv-notempty="true"
											data-bv-notempty-message="La universidad no puede ir vacio."
											value="${datosPersona.cve_universidad.nombre}"> <input
											type="hidden" name="cve_universidad.cve_universidad"
											id="universidadH"
											value="${datosPersona.cve_universidad.cve_universidad}">
									</div>
									<div class="form-group">
										<label for="grado">Grado academico Grado academico
											(Presione tecla hacia abajo para mostrar opciones)</label> <input
											type="text" class="form-control" id="auto_complete_grado"
											name="auto_complete_grado"
											placeholder="Grado academico (Autocomplete)"
											data-bv-notempty="true"
											data-bv-notempty-message="El grado academico no puede ir vacio."
											value="${datosPersona.cve_area_grado_academico.descripcion}">
										<input type="hidden" id="gradoH"
											name="cve_area_grado_academico.cve_area_grado_academico"
											value="${datosPersona.cve_area_grado_academico.cve_area_grado_academico}">
									</div>
									<div class="form-group">
										<label for="ciclo">Ciclo (Presione tecla hacia abajo
											para mostrar opciones)</label> <input type="text"
											class="form-control" id="auto_complete_ciclo"
											placeholder="Ciclo escolar (Autocomplete)"
											data-bv-notempty="true"
											data-bv-notempty-message="La universidad no puede ir vacio.">
										<input type="hidden" id="cicloH" name="ciclo">
									</div>
									<div class="form-group">
										<label for="saldo">Saldo del alummno</label> <input
											type="text" class="form-control" id="saldo" name="saldo"
											placeholder="Saldo del alumno" data-bv-numeric="true"
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
							<input type="hidden"
								name="<c:if test="${!empty datosPersona}">btnEdit</c:if><c:if test="${empty datosPersona}">btnInsert</c:if>"
								value="insert" />
							<button type="submit" class="btn btn-primary">
								<c:if test="${!empty datosPersona}">Editar</c:if>
								<c:if test="${empty datosPersona}">Crear</c:if>
							</button>
						</div>
						<br> <br>
					</form>
				</div>
			</div>
		</div>
	</div>
		
	</jsp:attribute>
</t:layout>