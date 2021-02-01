<%@page import="model.Categoria"%>
<%@page import="model.Automobile"%>
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
<h3> Automobili disponibili </h3>

<h3> Queste sono tutte le categorie disponibili </h3>

<div class="row">
<div class="col-md-4" >
<table class="table table-striped">
  <thead>
  
    <tr>
      <th scope="col">#id</th>
      <th scope="col">marca</th>
      <th scope="col">targa</th>
      <th scope="col">numeroPorte</th>
      
      
    </tr>
  </thead>
  <tbody>
    <%
 		Automobile a = (Automobile)request.getAttribute("autoDaModificare");  
    	int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
 			int id = a.getId();
			String targa= a.getTarga();
			String marca= a.getMarca();
			int numeroPorte = a.getNumeroPorte();
			
			
			boolean prenotabile = a.isPrenotabile();
 			%>			
			
			<tr>
		
			<td><%=id %></td> 
			<td><%=targa %></td> 
			<td><%=marca %></td>
			<td><%=numeroPorte %></td>
			
			
 			
 			
  </tbody>
</table>
</div>
<div class="col-md-2" >
</div>
<div class="col-md-6" >

	<h3> Modifica Automobile </h3>
	
		
			
		<form action="ModificaAutomobileServlet" method="POST">
		
	 	<input type= "text" placeholder="marca" name="marca"><br>		
	 	<input type= "text" placeholder="targa" name="targa"><br>
	 	<input type= "number" placeholder="numeroPorte" name="numeroPorte"><br>
  	 	<input type= "Hidden" value=<%=id %> name=idAuto><br>		
	 	<input type= "Hidden" value=<%=idCategoria %> name=idCategoria><br>		
	 	
      	<button type="submit" class="btn btn-primary" value="Modifica">Modifica</button>
	 	</form>
	 	
	 	<form action="AutomobileServlet" method="GET">
		
	 	<input type= "Hidden" value=<%=idCategoria %> name=idCategoria><br>		
	 			
	 	
      	<button type="submit" class="btn btn-primary" value="Indietro">Indietro</button>
	 	</form>
		 	
	 	</div>
	 	
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
</html>