<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    
</head>
<body>
	<%
		String messaggio = (String)request.getAttribute("errore");
		if (messaggio != null) {			
			%>			
			<p><%=messaggio %></p>
			<% 
		}
		%>	    
<h3> Inserisci i dati per completare la registrazionee </h3>
	
	<form action="RegistrazioneServlet" method="POST">
	<div class="form-row">
	    <div class="form-group col-md-3">
	      
	      <input type="text" class="form-control" name="nome" placeholder="Nome">
	    </div>
	    <div class="form-group col-md-3">
	      
	      <input type="text" class="form-control" name="cognome" placeholder="Cognome">
	    </div>
	  </div>
	  <div class="form-row">
	    <div class="form-group col-md-3">
	      <label for="inputEmail">Email</label>
	      <input type="email" class="form-control" name="inputEmail" placeholder="Email">
	    </div>
	    <div class="form-group col-md-3">
	      <label for="inputPassword">Password</label>
	      <input type="password" class="form-control" name="inputPassword" placeholder="Password">
	    </div>
	  </div>
	  <div class="form-row">
	    <div class="form-group col-3 ">
             <label for="dataNascita"> Data di nascita </label>
             <input type="date" class="form-control" name="dataNascita">
        </div>
	    
	  </div>
	  
	  <button type="submit" class="btn btn-primary">Sign in</button>
	</form>
	
	<form action="LoginServlet" method="GET">
		
      	<button type="submit" class="btn btn-primary" value="Indietro">Indietro</button>
 	</form>




		

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
    
</html>