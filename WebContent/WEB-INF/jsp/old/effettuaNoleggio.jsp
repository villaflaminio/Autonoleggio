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
    <%
 		Automobile a = (Automobile)request.getAttribute("autoDaNoleggiare");   
 		
 			int id = a.getId();
			String targa= a.getTarga();
			String marca= a.getMarca();
			int numeroPorte = a.getNumeroPorte();
			boolean prenotabile = a.getPrenotabile();
	 			%>			
				
	<div class="card" style="width: 50rem;">
	  
	  <div class="card-body">
	    <h3 class="card-title">Completa il noleggio della seguente auto </h3>
	  </div>
	  <ul class="list-group list-group-flush">
	  
	    <li class="list-group-item">id: <%=id %></li>
	    <li class="list-group-item">targa: <%=targa %></li>
	    <li class="list-group-item">marca: <%=marca %></li>
	    <li class="list-group-item">Porte: <%=numeroPorte %></li>
	    
	  </ul>
	  <div class="card-body">
	  <form action="EseguiNoleggioServlet" method="POST">
	  	  <div class="row">	  
	  
		<div class="col-md-6" >
		
			
             <label for="dataInizio"> Data inizio </label>
             <input type="date" class="form-control" name="dataInizio">
	    
	  	</div>
	  	<div class="col-md-6" >
		
			
             <label for="dataFine"> Data fine </label>
             <input type="date" class="form-control" name="dataFine">
	    
	  	</div>	
	  		  	
	  	
	  	  	</div>
	  	 <div class="row col-md-6">	 
	  	 <h3>Prezzo Noleggio: </h3>			     
		<input class="form-control" type="text" placeholder="Readonly " readonly>     
	  	
  		</div>
	  	<div class="row col-md-6"> 			     
		<button type="submit" class="btn btn-primary" value="Noleggia">Noleggia</button>     
	  	
  		</div>
	  	
	  </form>    
	</div>
			
		
				
				
 			
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 

</body>
</html>