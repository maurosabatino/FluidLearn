<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FluidLearn</title>

  <script src="js/jquery-2.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/bootstrap.js"></script>
  <script src="js/bootstrap-select.js"></script>

 <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
 <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>
 <link rel="stylesheet" type="text/css" href="css/bootstrap-select.css"/>
 <link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
 <link rel="stylesheet" type="text/css" href="css/mio.css"/>


</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div class="row">
 	 <div class="col-md-8 col-md-offset-2">
		<div class="jumbotron">
  			<h1>Benvenuti in FluidLearn</h1>
  			<p><a href="Servlet?operazione=mostraAllCorsi" class="btn btn-primary btn-lg" role="submit">Esplora i corsi</a></p>
 		</div>
  	</div>
  
</div>


</body>
</html>