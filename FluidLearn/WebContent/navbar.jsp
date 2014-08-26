<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/jquery.min.js"></script>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
 <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
 <script src="js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Navbar</title>
</head>
<body>
<div class="col-sm-3 col-md-2 sidebar" id=navbar>
<div class="navbar main-menu">
  <ul class="nav">
    <li><a href="#" class="glyphicon glyphicon-th-large" title="" data-original-title="Menu"></a></li>
    <li><a href="#/back" onclick="window.history.go(-1);return false;" class="glyphicon glyphicon-chevron-left" title="" data-original-title="Back"></a></li>
    <li><a href="#/forward" onclick="window.history.go(+1);return false;" class="glyphicon glyphicon-chevron-right" title="" data-original-title="Forward"></a></li>
    
  </ul>
</div>
</div>

</body>
</html>