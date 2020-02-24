<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ajout d'un utilisateur</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
	  <h2>Ajout d'un utilisateur</h2>
	  <form method="post" action="add">
	    <div class="form-group">
	      <label for="nom">Nom:</label>
	      <input type="text" class="form-control" id="nom"  name="lastname" value="${ requestScope.utilisateur.lastname }">
	      <span>${ messageErreurs.nom }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="prenom">Prenom:</label>
	      <input type="text" class="form-control" id="prenom"  name="firstname" value="${ requestScope.utilisateur.firstname }">
	      <span>${ messageErreurs.prenom }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="email">Email:</label>
	      <input type="text" class="form-control" id="email"  name="email" value="${ requestScope.utilisateur.email }">
	      <span>${ messageErreurs.email }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="password">Mot de passe:</label>
	      <input type="password" class="form-control" id="password"  name="password">
	      <span>${ messageErreurs.password }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="passwordBis">Mot de passe:</label>
	      <input type="password" class="form-control" id="passwordBis"  name="passwordBis">
	      <span>${ messageErreurs.passwordBis }</span><br>
	    </div>
	    <div class="form-group">
	    	<div class="form-check">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="on" name="role"> Elever au rang d'administrateur
			  </label>
			</div>
	    </div>
	    
	
	    <button type="submit" class="btn btn-primary">Submit</button>
	    <span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
	  </form>
	</div>
</body>
</html>