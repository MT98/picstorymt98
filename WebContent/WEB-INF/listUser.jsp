<%@page import="java.util.ArrayList"%>
<%@page import="entities.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<c:choose>
		<c:when test="${ empty utilisateurs }">
			<p>La liste des utilisateurs est vide.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-striped">
				<thead>
					<tr>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Email</th>
					<th>Password</th>
					<th>Statut</th>
					<th>Actions</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${ utilisateurs }" var="utilisateur">
					<tr>
						<td><c:out value="${ utilisateur.nom }"/></td>
						<td><c:out value="${ utilisateur.prenom }"/></td>
						<td><c:out value="${ utilisateur.email }"/></td>
						<td><c:out value="${ utilisateur.password }"/></td>
						<c:if test="${utilisateur.isAdmin == true }"> <td> Administrateur </td></c:if>
						<c:if test="${utilisateur.isAdmin == false }"> <td> Utilisateur Simple </td></c:if>
						<td><a href="<c:url value='/user/view?id=${utilisateur.id}' />">Voir</a> | <a href="<c:url value='/user/modify?id=${utilisateur.id}'/>">Modifier</a> | <a href="<c:url value='/user/delete?id=${utilisateur.id}'/>">Supprimer</a> </td>
					</tr>
					</c:forEach>
				</tbody>
				
			</table>
			<p> <span class="${param.statut}">${param.message}</span> </p>
	
		</c:otherwise>
	</c:choose>
	</div>
	
	
</body>
</html>