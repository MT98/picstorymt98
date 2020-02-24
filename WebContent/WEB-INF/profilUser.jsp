<%@page import="entities.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil d'un utilisateur</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
	<h1>Mon Profil</h1>
	<div>
		<c:if test="${ !empty requestScope.utilisateur}">
					<strong>Nom:</strong> ${requestScope.utilisateur.nom} <br>
					<strong>Prénom:</strong>${requestScope.utilisateur.prenom}<br>
					<strong>Email:</strong>${requestScope.utilisateur.email}<br>
					<strong>Password:</strong>${requestScope.utilisateur.password}<br>	
							
		</c:if>
		<c:if test="${!empty requestScope.utilisateur and requestScope.utilisateur.isAdmin == true}">
			<strong>Statut:</strong> Administrateur <br>	
		</c:if>
		<c:if test="${!empty requestScope.utilisateur and requestScope.utilisateur.isAdmin == false}">
			<strong>Statut:</strong> Utilisateur Simple <br>	
		</c:if>
		
		<c:if test=" ${ empty requestScope.utilisateur}">
					<p>Erreur, veuillez contacter l'administrateur du site.</p>		
		</c:if>

	</div>

</div>
	
</body>
</html>