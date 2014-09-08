<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<jsp:useBean id="HTMLc" class="bean.HtmlContent" scope="request" />
<jsp:setProperty  name="HTMLc" property="*"/>
<html>
<head>
 <script src="js/jquery.min.js"></script>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
 <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css"/>
 <script src="js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Corso</title>
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<jsp:include page="barraLaterale.jsp"></jsp:include>

<div class="col-sm-9  col-md-10 col-md-offset-1 main">

<a href="Servlet?operazione=formInserisciCorso"> inserisci Corso</a>
<a href="Servlet?operazione=mostraAllCorsi"> mostra i Corsi</a>
<jsp:getProperty name="HTMLc" property="content"/>




                            

</div>
<script>
    $('#nodoTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    // store the currently selected tab in the hash value
    $("ul.nav-tabs > li > a").on("shown.bs.tab", function (e) {
        var id = $(e.target).attr("href").substr(1);
        window.location.hash = id;
    });

    // on load of the page: switch to the currently selected tab
    var hash = window.location.hash;
    $('#nodoTab a[href="' + hash + '"]').tab('show');
</script>

</body>
</html>