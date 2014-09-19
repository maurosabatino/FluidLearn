<%@page import="controller.DatabaseController" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="partecipante.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>navbar</title>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="Index.jsp">FluidLearn</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
     <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <span class="glyphicon glyphicon-th-large"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="Servlet?operazione=formInserisciCorso"> inserisci Corso</a></li>
            <li class="divider"></li>
            <li><a href="Servlet?operazione=mostraAllCorsi"> mostra i Corsi</a></li>
          </ul>
        </li>
        <li><a href="#/back" onclick="window.history.go(-1);return false;" class="glyphicon glyphicon-chevron-left" title="" data-original-title="Back"></a></li>
    <li><a href="#/forward" onclick="window.history.go(+1);return false;" class="glyphicon glyphicon-chevron-right" title="" data-original-title="Forward"></a></li>
        
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
      
        <% Partecipante part = (Partecipante)session.getAttribute("partecipante");
		if (part==null){
		%>
		<li><button class="btn btn-info navbar-btn " data-toggle="modal" data-target="#login-modal">Login</button></li>
		</ul>
		<%}else{ %>
		
		<form class="navbar-form navbar-left" role="search">
       	 <div class="form-group">
       	  <a href="Servlet?operazione=mostraProfilo"> <%=part.getNome() %></a>
        	</div>
        <input type=hidden name=operazione value=logout>
		<button type="submit" class="btn btn-warning">Logout</button>
      </form>
      <% }%>
       
     
      <div class="modal fade login-modal" tabindex="-1" id="login-modal" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  				<div class="modal-dialog modal-sm">
    				<div class="modal-content">
     					<form  action="Servlet" name="dati" method="POST">
  							<div class="form-group">
  							<br>
  							<br>
  							<div class="row">
  							<div class="col-md-10 col-md-offset-1">
   							<label for="username">Username </label><input type="text" name="username" id="username" class="form-control" placeholder="username"/>
							</div>
							</div>
							<div class="row">
							<div class="col-md-10 col-md-offset-1">
							<label for="password">Password </label><input type="password" name="password" id = "password" class="form-control" placeholder="password"/>
							<input type="hidden" name="operazione" value="login"/>
							</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-10 col-md-offset-4">
							<button type="submit" class="btn btn-default navbar-btn">Login</button>
							</div>
							</div>
							<br>
							<br>
 						</form>
    				</div>
  				</div>
  		</div>
  				
  				
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


</body>
</html>