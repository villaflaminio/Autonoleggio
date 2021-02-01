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
	<%
		int utente = (int)request.getAttribute("utente"); // utente da passare
	%>
 		
<form action="BackToNuovoNoleggio" method="GET">
 	 	<input type= "Hidden" value=<%=utente %> name=utente><br>		

      	<button type="submit" class="btn btn-primary" value="Indietro">Cambia le date del noleggio </button>
</form>
<h3>Queste sono le categorie disponibili per il noleggio</h3>
	
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
				<form action="RedirectNoleggioServlet" method="POST">	
			    <input type= "Hidden" value=<%=id %> name="idCategoria"><br>
   			    <input type= "Hidden" value=<%=utente %> name="utente"><br>
			    
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

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
</html>