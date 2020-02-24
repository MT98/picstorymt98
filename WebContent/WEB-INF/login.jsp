<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Se connecter</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	
	<div class="container">
	  <h2>Connexion</h2>
	  <form method="post" action="login">
	    <div class="form-group">
	      <label for="login">Login:</label>
	      <input type="text" class="form-control" id="login"  name="login">
	    </div>
	    <div class="form-group">
	      <label for="password">Mot de passe:</label>
	      <input type="password" class="form-control" id="password"  name="password">
	    </div>
	     	
	    <button type="submit" class="btn btn-primary">Se connecter</button>
	  </form>
	</div>
</body>
</html>