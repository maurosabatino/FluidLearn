<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<jsp:useBean id="HTMLc" class="bean.HtmlContent" scope="request" />
<jsp:setProperty  name="HTMLc" property="*"/>
<html>
<head>

  <script src="js/jquery-2.js"></script>
   <script src="js/bootstrap.js"></script>
   <script src="js/bootstrap-select.js"></script>

 
 <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
 <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
 <link rel="stylesheet" type="text/css" href="css/bootstrap-select.css"/>

 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Corso</title>
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>


<div class="col-xs-9  col-md-10 col-md-offset-1 main">





<jsp:getProperty name="HTMLc" property="content"/>
	


</div>

</body>
</html>