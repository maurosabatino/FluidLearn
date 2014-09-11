package InterfacciaHtml;

import java.sql.SQLException;


import partecipante.*;

public class HtmlProfilo {
	public static String mostraProfilo(Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		if(part.hasRole(Role.AMMINISTRATORE))
			sb.append("<h3>amministratore</h3>");
		// se studente mostra i corsi a cui sei iscritto, le attività, i compiti
		// se amministratore crea utenti
		// se tecnico 
		
		return sb.toString();
	}
}
