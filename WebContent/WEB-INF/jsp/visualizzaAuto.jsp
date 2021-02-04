<%@page import="model.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="model.Automobile"%>
<%@page import="model.Noleggio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="model.Utente"%>
<%@page import= "java.text.ParseException" %>
<%@page import= "java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
 
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- <meta http-equiv="refresh" content="5" > -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
  <script src="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
  
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/static/css/main.css">

<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right">FLAMINIO NOLEGGI</span>
  <input type="hidden" id="apikey" value="{{ user.api }}">
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s4">
      <img src="{{ user.image }}" class="w3-circle w3-margin-right" style="width:46px">
    </div>
    <div class="w3-col s8 w3-bar">
      
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>Dashboard</h5>
  </div>
  <div class="w3-bar-block">
  <div class="w3-bar-block">
  <form action="CategorieServlet" method="GET">	
   	        	<button type="submit" class="w3-bar-item w3-button w3-padding w3-blue" ><i class="fa fa-users fa-fw"></i>Categorie</button>				
			    	    
	</form>
	 <form action="CategorieServlet" method="POST">	
   	        	<button type="submit" class="w3-bar-item w3-button w3-padding " ><i class="fa fa-users fa-fw "></i>Aggiungi categoria</button>				
			    	    
	</form>	
	
	<form action="LoginServlet" method="GET">
      		<button type="submit" class="btn btn-primary" value="Indietro">Log Out</button>
	</form>
	 <%
				String messaggio = (String)request.getAttribute("errore");
				if (messaggio != null) {			
					%>			
					<p style="color:red;"><%=messaggio %></p>
					<% 
				}
				%>
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->


<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <p class="w3-margin" style="color: lightgrey;">.   </p>
 
  <div class="w3-container w3-white w3-margin w3-card-4 w3-padding">
    <br>
    <!-- Header -->
  <header class="w3-container" style="padding-top:0px">
    <div class="col-md-4" >
	<h3> Aggiungi una nuova Automobile </h3>
		
	<form action="AutomobileServlet" method="POST">
	
 	<input type= "text" placeholder="marca" name="marca"><br>		
 	<input type= "text" placeholder="targa" name="targa"><br>
 	<input type= "number" placeholder="numeroPorte" name="numeroPorte"><br>
 	
      	<button type="submit" class="btn btn-primary" value="ADD">ADD</button>
 	</form>
 	
	
</div>
  </header>
  <br>
     <%
	List<Automobile> list = (List<Automobile>)request.getAttribute("list");
 	Categoria c = (Categoria)session.getAttribute("categoria");

	  if(!list.isEmpty()){
	  %>
	  
	  <div class="card-body">
	<h3> Auto disponibili per categoria : <%=c.getNome() %> </h3>
	
<table class="table table-striped">
  <thead>
  
    <tr>
      <th scope="col">#id</th>
      <th scope="col">marca</th>
      <th scope="col">targa</th>
      <th scope="col">numeroPorte</th>
      <th scope="col">Disponibile</th>
      <th scope="col">modifica</th>
      
    </tr>
  </thead>
  <tbody>
    <%
 		
		//int idCategoria = (int)request.getAttribute("idCategoria");

 		for(Automobile a : list ){
 			int idAuto = a.getId();
			String targa= a.getTarga();
			String marca= a.getMarca();
			int numeroPorte = a.getNumeroPorte();
			int idCategoria = a.getCategoria().getId();
			boolean prenotabile = a.getPrenotabile();
 			%>			
			
			<tr>
		
			<td><%=idAuto %></td> 			
			<td><%=marca %></td>
			<td><%=targa %></td> 
			<td><%=numeroPorte %></td>
			
			<td>					
			<form action="modificaPrenotabileServlet" method="POST">
			  <label for="cars">Disponibile:</label>
			  <select id="cars" name="cars" >
				   <option value="Si"<% if(prenotabile){ %> selected <%} %>>Si</option>
				   <option value="No" <% if(!prenotabile){ %> selected <%} %>>No</option>
			  </select>
	  	 	<input type= "Hidden" placeholder="cars" name="cars"><br>		
	  	 	<input type= "Hidden" value=<%=idAuto %> name="idAuto"><br>		
		  	 	
        	<button type="submit" class="btn btn-primary" value="Modifica">cambia</button>
	        </form>	
			</td> 
			
			<td>
			<form action="ModificaAutomobileServlet" method="GET">
		  	 	<input type= "Hidden" value=<%=idAuto %> name="idAuto"><br>		
			  	
	
		 		<button type="submit" class="btn btn-primary" value="Modificaa">Modificaa</button>
		 	</form>
 			</td> 
 			
 			<%
 		}
 	%>
 	
 	-----------------------------------------------------------------------------------------
  </tbody>
</table>
</div>
<%
  }else{
	  %>
	  	Nessuna auto presente :(
	  <%
  }
%>

</div>

</div>
	  
	    
	  	
	</div>
	



<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}

document.getElementById("apikey_holder").innerHTML = "XXXX XXXX XXXX XXXX XXXX";

function show_hide(){
  var x = document.getElementById("apikey_holder");
  var btn = document.getElementById("togglebtn");
  var actual_key = document.getElementById('apikey').value;
  
  if (x.innerHTML === "XXXX XXXX XXXX XXXX XXXX") {
    x.innerHTML = actual_key;
  } else {
    x.innerHTML = "XXXX XXXX XXXX XXXX XXXX";
  }
}
</script>

<script src="/static/JS/main.js"> </script>

</body>
</html>
