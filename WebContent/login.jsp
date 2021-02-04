
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="main.css">
    
</head>
<body>

<div class="row">
    <div class="col-md-6 mx-auto p-0">
        <div class="card">
            <div class="login-box">
                <div class="login-snip"> <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Login</label> <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
                    <div class="login-space">
                        <div class="login">
							<form action="LoginServlet" method="POST">
							<div class="group"> <label for="username" class="label">Username</label> <input id="username" name="username" type="text" class="input" placeholder="Enter your username"> </div>
                            <div class="group"> <label for="pass" class="label">Password</label> <input id="pass" type="password" name="password" class="input" data-type="password" placeholder="Enter your password"> </div>
                            <div class="group"> <input type="submit" class="button" value="Sign In"> </div>
                            <div class="hr"></div>													
							</form>
                        </div>
                        <div class="sign-up-form">
                        <form action="RegistrazioneServlet" method="POST">
                            <div class="group"> <label for="nome" class="label">Nome</label> <input id="nome" type="text" name="nome" class="input" placeholder=" Nome"> </div>
                            <div class="group"> <label for="cognome" class="label">Cognome</label> <input id="cognome" type="text" name="cognome" class="input" placeholder=" Cognome"> </div>
                            <div class="group"> <label for="inputEmail" class="label">Email Address</label> <input id="inputEmail" type="text" name="inputEmail" class="input" placeholder="Enter your email address"> </div>
                            <div class="group"> <label for="inputPassword" class="label">Password</label> <input id="inputPassword" type="password" name="inputPassword" class="input" data-type="password" placeholder="Create your password"> </div>
                            <div class="group">  
                            	<label for="dataNascita"> Data di nascita </label>
             					<input type="date" class="form-control" name="dataNascita">
                            
                            </div>
                            
                            <div class="group"> <input type="submit" class="button" value="Sign Up"> </div>
                            <div class="hr"></div>
                       	</form>
                            
                        </div>
                         
                    </div>
                    <%
				String messaggio = (String)request.getAttribute("errore");
				if (messaggio != null) {			
					%>			
					<p style="color:red;"><%=messaggio %></p>
					<% 
				}
				%>
                </div>
            </div>
	          
        </div>
    </div>
   
</div>

 

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
</body>
</html>
