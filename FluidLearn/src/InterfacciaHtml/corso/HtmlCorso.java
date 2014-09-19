package InterfacciaHtml.corso;

import java.sql.SQLException;
import java.util.ArrayList;

import partecipante.*;
import controller.*;
import corso.*;

public class HtmlCorso {
	/*----------corso----*/
	public static String formInsertCorso(){
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo corso</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del Corso</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" ></div>");
		sb.append("</div>");
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\">Max 36 caratteri</textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciCorso\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"OK\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	
	public static String mostraAllCorsi() throws SQLException{
		ArrayList<Corso> corsi = DatabaseController.SelectAllCorso();
		StringBuilder sb = new StringBuilder();
		int column = 0;
		for(Corso c : corsi){
			if(column%3==0)sb.append("	<div class=\"row\">                                                                                                                       ");
		sb.append("	  <div class=\"col-sm-6 col-md-4\">                                                                                                       ");
		sb.append("	    <div class=\"thumbnail\">                                                                                                             ");
		sb.append("	      <img src=\"images/STUDENT_img.jpg\" alt=\"Immagine del corso\">                                                                                      ");
		sb.append("	      <div class=\"caption\">                                                                                                             ");
		sb.append("	        <h3>"+c.getNome()+"</h3>                                                                                                        ");
		sb.append("	        <p>"+c.getDescrizione()+"</p>                                                                                                                      ");
		sb.append("	        <p class=\"text-center\"><a href=\"Servlet?operazione=mostraCorso&idCorso="+c.getIdCorso()+"\" class=\"btn btn-primary\" role=\"button\">Entra</a></p>");
		sb.append("	      </div>                                                                                                                            ");
		sb.append("	    </div>                                                                                                                              ");
		sb.append("	  </div>                                                                                                                                ");
		if((column+1)%3==0)sb.append("	</div>                                                                                                                                  ");
			column++;
		}
		return sb.toString();
	}
	public static String mostraCorso(int idCorso,Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		Corso c = DatabaseController.SelectCorso(idCorso);
		sb.append("<h3>"+c.getNome()+"</h3>");
		sb.append(getRuolo(part));
		sb.append("<ul id=\"corsoTab\" class=\"nav nav-tabs\">                                     ");
		sb.append("   <li class=\"active\">                                                     ");
		sb.append("      <a href=\"#home\" data-toggle=\"tab\">                                 ");
		sb.append("          Decrizione del corso                                                         ");
		sb.append("      </a>                                                                   ");
		sb.append("   </li>                                                                     ");
		sb.append("   <li><a href=\"#UDA\" data-toggle=\"tab\">Unità di apprendimento</a></li>                   ");
		if(part!=null && part.hasRole(Role.DOCENTE)) sb.append("   <li><a href=\"#gestCorso\" data-toggle=\"tab\">Gestione del Corso</a></li>             ");
		sb.append("</ul>                                                                        ");
		sb.append("<div id=\"myTabContent\" class=\"tab-content\">                              ");
		sb.append("   <div class=\"tab-pane fade in active\" id=\"home\">                       ");
		
		sb.append("      <p>"+c.getDescrizione()+"</p>   							            ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"UDA\">                                 ");
		sb.append("      <p> "+HtmlUDA.mostraUDACorso(idCorso)+" </p>                     ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"gestCorso\">                              ");
		sb.append("      <p>																");
		sb.append("<a href=\"Servlet?operazione=formModificaCorso&idCorso="+c.getIdCorso()+"\"> modifica</a>");
		sb.append("<a href=\"Servlet?operazione=eliminaCorso&idCorso="+c.getIdCorso()+"\"> elimina</a>");
		sb.append("<a href=\"Servlet?operazione=formInserisciUDA&idCorso="+c.getIdCorso()+"\"> aggiungi UDA</a>");
		sb.append("      </p>																	");
		sb.append("   </div>                                                                    ");
		sb.append("                                                                             ");
		sb.append("</div>"); 
		
		return sb.toString();
	}
	
	public static String formModificaCorso(int idCorso) throws SQLException{
		Corso c = DatabaseController.SelectCorso(idCorso);
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo corso</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del Corso</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" value=\""+c.getNome()+"\" ></div>");
		sb.append("</div>");
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\">"+c.getDescrizione()+"</textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idCorso\" value=\""+c.getIdCorso()+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"modificaCorso\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"OK\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	public static String getRuolo(Partecipante part){
		for (Role r: Role.values())
		if(part.hasRole(r) && r!= Role.AMMINISTRATORE && r!= Role.TECNICO && r!= Role.UTENTE && r!= Role.IDEATORE)
			return "<h2 class=\"pull-right\">"+r.toString()+"</h2>";
	 return "";
	}
	
}
