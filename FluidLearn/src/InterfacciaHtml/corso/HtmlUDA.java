package InterfacciaHtml.corso;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import InterfacciaHtml.HtmlContributo;
import partecipante.*;
import controller.*;
import corso.*;

public class HtmlUDA {
	
	public static String formInsertUDA(int idCorso){
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuova UDA</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del UDA</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" ></div>");
		sb.append("</div>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"dataAttivazione\">data di attivazione</label> <input type=\"text\" name=\"dataAttivazione\" id=\"dataAttivazione\" class=\"form-control\" placeholder=\"yyyy-MM-dd\" ></div>");
		sb.append("</div>");
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\"></textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idCorso\" value=\""+idCorso+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciUDA\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"OK\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		sb.append("<script>                              ");
	  sb.append("$(function() {                        ");
	  sb.append("  $( \"#dataAttivazione\" ).datepicker({ dateFormat: \"yy-mm-dd\" });    ");
	  sb.append("});                                   ");
	  sb.append("</script>                             ");
		return sb.toString();
	}
	
	public static String mostraUDA(int idUDA,Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		UnitaDA UDA = DatabaseController.selectUDA(idUDA);
		sb.append("<h3>"+UDA.getNome()+"</h3>");
		sb.append("<ul id=\"UDATab\" class=\"nav nav-tabs\">                                     ");
		sb.append("   <li class=\"active\">                                                     ");
		sb.append("      <a href=\"#home\" data-toggle=\"tab\">                                 ");
		sb.append("          Decrizione dell'unità di apprendimento                                                         ");
		sb.append("      </a>                                                                   ");
		sb.append("   </li>                                                                     ");
		sb.append("   <li><a href=\"#post\" data-toggle=\"tab\">Post</a></li>                   ");
		sb.append("   <li><a href=\"#nodi\" data-toggle=\"tab\">Nodi</a></li>                   ");
		if(part!=null && (part.hasRole(Role.DOCENTE)||part.hasRole(Role.AMMINISTRATORE))) sb.append("   <li><a href=\"#gestUDA\" data-toggle=\"tab\">Gestione dell'unità di apprendimento</a></li>             ");
		sb.append("</ul>                                                                        ");
		sb.append("<div id=\"myTabContent\" class=\"tab-content\">                              ");
		sb.append("   <div class=\"tab-pane fade in active\" id=\"home\">                       ");
		sb.append("      <p>"+UDA.getDescrizione()+"</p>   							            ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"post\">                                 ");
		sb.append(HtmlContributo.formInputPost(idUDA, 0));
		sb.append("      <p> "+HtmlContributo.mostraPost(idUDA,0,part)+" </p>                     ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"nodi\">                                 ");
		sb.append("      <p> "+HtmlNodo.mostraNodiUDA(UDA.getIdUDA(), part)+" </p>                     ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"gestUDA\">                              ");
		sb.append("      <p>																");
		sb.append("<a href=\"Servlet?operazione=formModificaUDA&idUDA="+UDA.getIdUDA()+"\" class=\"btn btn-primary btn-lg\" role=\"submit\"> modifica</a>");
		sb.append("<a href=\"Servlet?operazione=eliminaUDA&idUDA="+idUDA+"&idCorso="+UDA.getIdCorso()+"\" class=\"btn btn-primary btn-lg\" role=\"submit\"> elimina</a>");
		sb.append("<a href=\"Servlet?operazione=formInserisciNodo&idUDA="+idUDA+"\" class=\"btn btn-primary btn-lg\" role=\"submit\"> aggiungi Nodo</a>");
		sb.append("      </p>																	");
		sb.append("   </div>                                                                    ");
		sb.append("                                                                             ");
		sb.append("</div>"); 
		sb.append(HtmlContributo.scriptDropDownSelect());
		return sb.toString();
	}
	
	public static String mostraUDACorso(int idCorso) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<UnitaDA> UDACorso = DatabaseController.selectAllUDA(idCorso);
		for(UnitaDA UDA:UDACorso){
			
			sb.append("<div class=\"row\">                                                                                                                         ");
			sb.append("  <div class=\"col-sm-6 col-md-4\">                                                                                                         ");
			sb.append("    <div class=\"thumbnail\">                                                                                                               ");
			sb.append("                                                                                                                                          ");
			sb.append("      <div class=\"caption\">                                                                                                               ");
			sb.append("        <h3>"+UDA.getNome()+"</h3>                                                                                                          ");
			sb.append("        <p>"+UDA.getDescrizione()+"</p>                                                                                                                        ");
			sb.append("        <p><a href=\"Servlet?operazione=mostraUDA&idUDA="+UDA.getIdUDA()+"\" class=\"btn btn-primary\" role=\"button\">Entra</a> </p>  ");
			sb.append("      </div>                                                                                                                              ");
			sb.append("    </div>                                                                                                                                ");
			sb.append("  </div>                                                                                                                                  ");
			sb.append("</div>																																	 ");
			
			
			
			//sb.append("<td><a href=\"Servlet?operazione=formInserisciNodo&idUDA="+UDA.getIdUDA()+"\"> aggiungi Nodo</a></td>");
			//sb.append("<td><a href=\"Servlet?operazione=mostraNodiUDA&idUDA="+UDA.getIdUDA()+"\"> mostra i Nodi</a></td>");
			
		}
		
		return sb.toString();		
	}
	
	public static String formModificaUDA(int idUDA) throws SQLException{
		StringBuilder sb = new StringBuilder();
		UnitaDA UDA = DatabaseController.selectUDA(idUDA);
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Modifica l'UDA</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del UDA</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" value=\""+UDA.getNome()+"\" ></div>");
		sb.append("</div>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"dataAttivazione\">data di attivazione</label> <input type=\"text\" name=\"dataAttivazione\" id=\"dataAttivazione\" class=\"form-control\" placeholder=\"yyyy-MM-dd\" value=\""+new SimpleDateFormat("yyyy-MM-dd").format(UDA.getData())+"\"></div>");
		sb.append("</div>");
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\">"+UDA.getDescrizione()+"</textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idCorso\" value=\""+UDA.getIdCorso()+"\">");
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+idUDA+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"modificaUDA\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"Modifica\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		sb.append("<script>                              ");
	  sb.append("$(function() {                        ");
	  sb.append("  $( \"#dataAttivazione\" ).datepicker({ dateFormat: \"yy-mm-dd\" });    ");
	  sb.append("});                                   ");
	  sb.append("</script>                             ");
		return sb.toString();
	}
	public static String mostraPercorsoDiApprendimmento(int idCorso) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<UnitaDA> UDACorso = DatabaseController.selectAllUDA(idCorso);
		
		
		return sb.toString();
	}
	
	public static ArrayList<UnitaDA> findUDaPozzo(ArrayList<UnitaDA> corsoUDA){
		ArrayList<UnitaDA> UDAPozzo = new ArrayList<UnitaDA>();
		for(UnitaDA uda : corsoUDA){
			if(uda.getUDADipendenti() == null || uda.getUDADipendenti().isEmpty())
				UDAPozzo.add(uda);
		}
		return UDAPozzo;
	}
	//TODO
	public static ArrayList<UnitaDA> makeTree(ArrayList<UnitaDA> corsoUDA){
		ArrayList<UnitaDA> UDAPozzo = findUDaPozzo(corsoUDA);
		ArrayList<UnitaDA> tree = new ArrayList<UnitaDA>();
		for(UnitaDA uda : UDAPozzo) tree.add(uda);
		return tree;
	}
}
