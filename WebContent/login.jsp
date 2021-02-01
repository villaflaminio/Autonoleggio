
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		
	<h3>  benvenuto </h3>
	<h3>  Effettua il login </h3>

  <div class="form-group">
  <form action="LoginServlet" method="POST">
  <div class="form-row">
	    <div class="form-group col-md-3">
	      <input type="text" class="form-control" name="username" placeholder="username">
	    </div>
   </div>
   <div class="form-row">
	    <div class="form-group col-md-3">
	      <input type="password" class="form-control" name="password" placeholder="password">
	    </div>
	</div>
	
  <button type="submit" class="btn btn-primary">Login</button>
  
</form>



<form action="RegistrazioneServlet" method="GET">
      <button type="submit" class="btn btn-primary" value="Registrati">Register</button>
    
    
</form>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
</html>
