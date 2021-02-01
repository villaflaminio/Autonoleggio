<%@page import="model.Automobile"%>
<%@page import="java.util.ArrayList"%>
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
		int utente = (int)request.getAttribute("utente"); // utente da passare
		ArrayList<Automobile> list = (ArrayList<Automobile>)request.getAttribute("autoDaNoleggiare");
		double prezzoNoleggio = (double)request.getAttribute("prezzoTotale");
		int idCategoria = (int)request.getAttribute("idCategoria");
		String dataInizio = (String)request.getAttribute("dataInizio");
		String dataFine = (String)request.getAttribute("dataFine");
	%>
	
<form action="BackToNuovoNoleggio" method="GET">
 	 	<input type= "Hidden" value=<%=utente %> name=utente><br>		

      	<button type="submit" class="btn btn-primary" value="Indietro">esci</button>
</form>
	
<form action="BackToNuovoNoleggio" method="POST">
 	 	<input type= "Hidden" value=<%=utente %> name=utente><br>		
 	 	<input type= "Hidden" value=<%=idCategoria %> name=idCategorias><br>		
 	 	<input type= "Hidden" value=<%=dataInizio %> name=dataInizio><br>		
 	 	<input type= "Hidden" value=<%=dataFine %> name=dataFine><br>		

      	<button type="submit" class="btn btn-primary" value="Indietro">Cambia categoria</button>
</form>
<h3> Automobili disponibili </h3>

<div class="row">	  
	  
	  	  	
  
    <%
 		

 		for(Automobile a : list ){
 			int id = a.getId();
			String targa= a.getTarga();
			String marca= a.getMarca();
			int numeroPorte = a.getNumeroPorte();
			boolean prenotabile = a.isPrenotabile();
 			%>			
			
	<div class="card" style="width: 30rem;">
	  
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
	  	  
	  	 <div class="row col-md-6">	 
	  	 <h5>Prezzo Noleggio: $ <%=prezzoNoleggio %>	 </h5>		     
 	 	<input type= "Hidden" value=<%=utente %> name=utente><br>		
 	 	<input type= "Hidden" value=<%=idCategoria %> name=idCategoria><br>		
 	 	<input type= "Hidden" value=<%=id %> name=idaAutoDaNoleggiare><br>		
 	 	
 	 	<input type= "Hidden" value=<%=prezzoNoleggio %> name=prezzoNoleggio><br>		
 	 	<input type= "Hidden" value=<%=dataInizio %> name=dataInizio><br>		
 	 	<input type= "Hidden" value=<%=dataFine %> name=dataFine><br>		
		
	  	
  		</div>
	  	<div class="row col-md-6"> 			     
		<button type="submit" class="btn btn-primary" value="Noleggia">Noleggia</button>     	  	
  		</div>
	  	<br>
	  </form>    
	</div>
			
			
 			<%
 		}
 	%>
 
		
</div>		


		
	
	 	

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
</html>