<%@page import="model.Automobile"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
ArrayList<Automobile> list = (ArrayList<Automobile>) request.getAttribute("autoDaNoleggiare");
double prezzoNoleggio = (double) request.getAttribute("prezzoTotale");
int idCategoria = (int) request.getAttribute("idCategoria");
String dataInizio = (String) request.getAttribute("dataInizio");
String dataFine = (String) request.getAttribute("dataFine");
%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <meta http-equiv="refresh" content="5" > -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
<script
	src="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/static/css/main.css">

<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}
</style>
<body class="w3-light-grey">

	<!-- Top container -->
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>  Menu
		</button>
		<span class="w3-bar-item w3-right">FLAMINIO NOLEGGI</span> <input
			type="hidden" id="apikey" value="{{ user.api }}">
	</div>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
		style="z-index: 3; width: 300px;" id="mySidebar">
		<br>
		<div class="w3-container w3-row">
			<div class="w3-col s4">
				<img src="{{ user.image }}" class="w3-circle w3-margin-right"
					style="width: 46px">
			</div>
			<div class="w3-col s8 w3-bar"></div>
		</div>
		<hr>
		<div class="w3-container">
			<h5>Dashboard</h5>
			 <%
				String messaggio = (String)request.getAttribute("errore");
				if (messaggio != null) {			
					%>			
					<p style="color:red;"><%=messaggio %></p>
					<% 
				}
				%>
		</div>
		<div class="w3-bar-block">
			<div class="w3-bar-block">
				<form action="NuovoNoleggioServlet" method="GET">
					<button type="submit"
						class="w3-bar-item w3-button w3-padding w3-blue">
						<i class="fa fa-users fa-fw"></i>Nuovo noleggio
					</button>

				</form>
				<form action="ITuoiNoleggiServlet" method="GET">
					<button type="submit" class="w3-bar-item w3-button w3-padding ">
						<i class="fa fa-users fa-fw"></i> I tuoi noleggi
					</button>

				</form>

				<form action="NuovoNoleggioServlet" method="POST">
					<button type="submit" class="w3-bar-item w3-button w3-padding ">
						<i class="fa fa-cog fa-fw"></i> Profile Settings
					</button>

				</form>
				<form action="LoginServlet" method="GET">
					<button type="submit" class="btn btn-primary" value="Indietro">Log
						Out</button>
				</form>
			</div>
	</nav>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">

		<p class="w3-margin" style="color: lightgrey;">.</p>

		<div class="w3-container w3-white w3-margin w3-card-4 w3-padding">
			<br>
			<!-- Header -->
			<header class="w3-container" style="padding-top: 0px">
				<center>
					<h2>
						<b> User Profile</b>
					</h2>
				</center>
			</header>
			<br>

			<div class="card-body">
				<h3 class="card-title">Effettua un nuovo noleggio</h3>
			</div>

			<div class="card-body">


				<div class="row">


					<%
					for (Automobile a : list) {
						int id = a.getId();
						String targa = a.getTarga();
						String marca = a.getMarca();
						int numeroPorte = a.getNumeroPorte();
						boolean prenotabile = a.getPrenotabile();
					%>
					<div class="col-12 col-sm-6 col-md-4 col-lg-4 ">

						<div class="card">

							<div class="card-body">
								<h3 class="card-title">Completa il noleggio della seguente
									auto</h3>
							</div>

							<div class="card-body">
								<ul class="list-group list-group-flush">

									<li class="list-group-item">id: <%=id%></li>
									<li class="list-group-item">targa: <%=targa%></li>
									<li class="list-group-item">marca: <%=marca%></li>
									<li class="list-group-item">Porte: <%=numeroPorte%></li>
									<li class="list-group-item">Prezzo Noleggio: $ <%=prezzoNoleggio%></li>


								</ul>

								<form action="EseguiNoleggioServlet" method="POST">

									<input type="Hidden" value=<%=idCategoria%> name=idCategoria><br>
									<input type="Hidden" value=<%=id%> name=idaAutoDaNoleggiare><br>

									<input type="Hidden" value=<%=prezzoNoleggio%>
										name=prezzoNoleggio> <input type="Hidden"
										value=<%=dataInizio%> name=dataInizio> <input
										type="Hidden" value=<%=dataFine%> name=dataFine>

									<button type="submit" class="btn btn-primary" value="Noleggia">Noleggia</button>

								</form>

							</div>
						</div>
					</div>
					<%
					}
					%>

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

				function show_hide() {
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

			<script src="/static/JS/main.js">
				
			</script>

			<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
				integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
				crossorigin="anonymous"></script>
			<script
				src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
				crossorigin="anonymous"></script>
</body>
</html>


