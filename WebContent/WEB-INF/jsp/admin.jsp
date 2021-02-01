<%@page import="model.Categoria"%>
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
<h3> Queste sono tutte le categorie disponibili </h3>

		
<div class="row">
<div class="col-md-6" >		
<table class="table table-striped">
  <thead>
  
    <tr>
      <th scope="col">#id</th>
      <th scope="col">nome</th>
      <th scope="col">p_giornaliero</th>
      <th scope="col">p_settimanale</th>
      <th scope="col">p_mensile</th>
      <th scope="col">descrizione</th>
      <th scope="col">AUTO</th>
    </tr>
  </thead>
  <tbody>
    <%
 		ArrayList<Categoria> list = (ArrayList<Categoria>)request.getAttribute("list");
 		for(Categoria c : list ){
 			int id = c.getId();
			String nome= c.getNome();
			int prezzoGiornaliero = c.getPrezzoGiornaliero();
			int prezzoSettimanale =  c.getPrezzoSettimanale();
			int prezzoMensile = c.getPrezzoMensile();
			String descrizione= c.getDescrizione();
			
			

 			%>			
			
			<tr>
			
			<td><%=id %></td> 
			<td><%=nome %></td> 
			<td><%=prezzoGiornaliero %></td>
			<td><%=prezzoSettimanale %></td>
			<td><%=prezzoMensile %></td>
			<td><%=descrizione %></td> 
			
			<td>
				<form action="AutomobileServlet" method="GET">	
			    <input type= "Hidden" value=<%=id %> name="idCategoria"><br>
			    
					<button type="submit" class="btn btn-primary" value="visualizzaAuto">visualizzaAuto</button>     	  
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
<div class="col-md-1" >
</div>
<div class="col-md-4" >
<h4> AGGIUNGI UNA NUOVA CATEGORIA </h4>

	<form action="AggiungiCategoriaServlet" method="POST">
	    <div class="form-row " >
	    
	    <div class="form-group col-md-5">
	      <label for="nome">nome</label>
	      <input type="text" class="form-control" name="nome" placeholder="nome">
	    </div>
	    
	    <div class="form-group col-md-5">
	      <label for="prezzoMensile">descrizione</label>
	      <input type="text" class="form-control" name="descrizione" placeholder="descrizione">
	    </div>
	    </div>
	    
	  <div class="form-row">
	    <div class="form-group col-md-4">
	      <label for="prezzoGiornaliero">prezzoGiornaliero</label>
	      <input type="number" class="form-control" name="prezzoGiornaliero" placeholder="prezzoGiornaliero">
	    </div>
	    
	  
	  
	    <div class="form-group col-md-4">
	      <label for="prezzoSettimanale">prezzoSettimanale</label>
	      <input type="number" class="form-control" name="prezzoSettimanale" placeholder="prezzoSettimanale">
   	    </div>
	    
	    <div class="form-group col-md-4">
	      <label for="prezzoMensile">prezzoMensile</label>
	      <input type="number" class="form-control" name="prezzoMensile" placeholder="prezzoMensile">
	    </div>
   	    </div>
	    
	    
	    
   	  <button type="submit" class="btn btn-primary">Aggiungi</button>
	    
	 
	  </form>
	  
	  <form action="LoginServlet" method="GET">
	 	
      	<button type="submit" class="btn btn-primary" value="Indietro">Indietro</button>
	 	</form>
	   </div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
</html>