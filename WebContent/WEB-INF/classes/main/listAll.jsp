<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
	<tr>
		<th> 
			<form action="ControllerMain" method="get">
				<input type="submit" name="btnNew" value="Nuevo"/>
			</form>
		</th>
		<th>ID</th>
		<th>Nombre</th>
		<th>Apellidos</th>
		<th>Edad</th>
		<th>Genero</th>
		<th>Fecha Nacimeinto</th>
	</tr>
	<c:forEach items="${personas}" var="persona">
	<tr>
		<td>
			<form action="ControllerCocina" method="post">
				<input type="hidden" name="id" value="${persona.cve_persona}"/>
				<input type="submit" name="btnUpdate" value="Modificar"/>
				<input type="submit" name="btnDelete" value="Eliminar"/>
			</form>
		</td>
		<td>${persona.cve_persona}</td>
		<td>${persona.nombre}</td>
		<td>${persona.apellidos}</td>
		<td>${persona.edad}</td>
		<td>${persona.genero}</td>
		<td>${persona.fecha_nacimiento}</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>