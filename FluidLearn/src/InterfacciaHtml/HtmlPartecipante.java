package InterfacciaHtml;

import java.sql.SQLException;



import partecipante.*;

public class HtmlPartecipante {
	
	
	public static String mostraProfilo(Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		if(part.hasRole(Role.AMMINISTRATORE)){
			sb.append("<h3>amministratore</h3>");
			sb.append("<p><a href=\"Servlet?operazione=formInserisciUtente\" class=\"btn btn-primary\" role=\"button\">Crea Utente</a></p>");
		}
			
		if(part.hasRole(Role.IDEATORE))
			sb.append("<h3>ideatore</h3>");
		if(part.hasRole(Role.TECNICO))
			sb.append("<h3>Tecnico</h3>");
		if(part.hasRole(Role.UTENTE)){
			sb.append("<h3>Partecipante</h3>");
			sb.append("<p> funzione da implementare e capire meglio</p>");
		}
			
		
		// se studente mostra i corsi a cui sei iscritto, le attività, i compiti
		// se amministratore crea utenti
		// se tecnico 
		
		return sb.toString();
	}

	public static String formInsertUtente() {
		StringBuilder sb = new StringBuilder();

		sb.append("<form  class=\"form-horizontal\" action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo Utente</h4>");

		sb.append("<div class=\"form-group\">");
		sb.append("<label for=\"nome\" class=\"col-sm-2 control-label\">Nome</label>");
		sb.append("<div class=\"col-sm-10\">");
		sb.append("<input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\">");
		sb.append("</div>");
		sb.append("</div>");

		sb.append("<div class=\"form-group\">");
		sb.append("<label for=\"password\" class=\"col-sm-2 control-label\">Password</label>");
		sb.append("<div class=\"col-sm-10\">");
		sb.append("<input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\">");
		sb.append("</div>");
		sb.append("</div>");

		sb.append("<div class=\"form-group\">");
		sb.append("<label for=\"ruolo\" class=\"col-sm-2 control-label\">Ruolo</label>");
		sb.append("<div class=\"col-sm-10\">");
		sb.append("<select class=\"form-control\" name=\"ruolo\" id=\"ruolo\">");
		sb.append("<option value=\"amministratore\">Amministartore</option>");
		sb.append("<option value=\"tecnico\">Tecnico</option>");
		sb.append("<option value=\"ideatore\">Ideatore</option>");
		sb.append("<option value=\"utente\">Utente</option>");
		sb.append("</select>");
		sb.append("</div>");
		sb.append("</div>");

		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciUtente\">");
		sb.append(" <button type=\"submit\" class=\"btn btn-default\">Crea Utente</button>");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	
	public static String profiloAmministratore(){
		StringBuilder sb = new StringBuilder();
		
		
		return sb.toString();
	}
}
