<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<jsp:useBean id="HTMLc" class="bean.HtmlContent" scope="request" />
<jsp:setProperty  name="HTMLc" property="*"/>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
 <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
 <script src="js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Corso</title>
</head>
<body>
<a href="Servlet?operazione=formInserisciCorso"> inserisci Corso</a>
<a href="Servlet?operazione=mostraAllCorsi"> mostra i Corsi</a>
<jsp:getProperty name="HTMLc" property="content"/>
</body>
</html>