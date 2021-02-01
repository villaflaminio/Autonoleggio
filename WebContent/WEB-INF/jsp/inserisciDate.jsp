
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Utente"%>
<%@page import="model.Automobile"%>
<%@page import="model.Noleggio"%>

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
<form action="LoginServlet" method="GET">
      	<button type="submit" class="btn btn-primary" value="Indietro">Log Out</button>
</form>

    <%
  	   		int utente = (int)request.getAttribute("utente");
    
    //stampare la list di noleggi e poi mettere la possibilita di poter annullare

    %>
	<div class="card" style="width: 50rem;">
	  
	  <div class="card-body">
	    <h3 class="card-title">Effettua un nuovo noleggio </h3>
	  </div>
	  
	  <div class="card-body">
	  <form action="RedirectNoleggioServlet" method="GET">
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
 	 	<input type= "Hidden" value=<%=utente %> name=utente><br>		
	  	 
	  	<div class="row col-md-6"> 			     
		<button type="submit" class="btn btn-primary" value="Noleggia">Visualizza Categorie disponibili</button>     
	  	
  		</div>
	  	
	  </form>    
	</div>
	

<h3> I tuoi noleggi: </h3>

<div class="row">	  
	  
	
<table class="table table-striped">
  <thead>
  
    <tr>
      <th scope="col">#codiceNoleggio</th>
      <th scope="col">dataInizio</th>
      <th scope="col">dataFine</th>
      <th scope="col">marcaAuto</th>
      <th scope="col">targa</th>
      <th scope="col">numeroPorte</th>
      <th scope="col">Cancella</th>
      
    </tr>
  </thead>
  <tbody>	  	  	
  
    <%
 		ArrayList<Noleggio> list = (ArrayList<Noleggio>)request.getAttribute("noleggiUtente");

 		for(Noleggio n : list ){
 			int id = n.getId();
			Automobile auto = n.getAutomobile();
			Date dataInizio =n.getDataInizio();
			Date dataFine =n.getDataFine();
			String marcaAuto= auto.getMarca();
			String targa = auto.getTarga();
			int numeroPorte = auto.getNumeroPorte();
			
 			%>			
			
			<tr>
			
			<td><%=id %></td> 
			<td><%=dataInizio %></td>
			<td><%=dataFine %></td>
			<td><%=marcaAuto %></td>
			<td><%=targa %></td> 
			<td><%=numeroPorte %></td> 
			
			<td>
				<form action="EseguiNoleggioServlet" method="GET">	
			    <input type= "Hidden" value=<%=id %> name="idNoleggio"><br>
   			    <input type= "Hidden" value=<%=utente %> name="utente"><br>
			    
	        	<button type="submit" class="btn btn-primary" value="annullaPrenotazione">ANNULLA PRENOTAZIONE</button>				
	 			</form>
			</td> 
			
			</tr>
			</form>
			
			
 			<%
 		}
 	%>
 
 </tbody>
</table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
</html>