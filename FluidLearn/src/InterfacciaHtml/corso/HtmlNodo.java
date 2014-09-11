package InterfacciaHtml.corso;

import java.sql.SQLException;
import java.util.ArrayList;

import partecipante.Partecipante;
import partecipante.Role;
import InterfacciaHtml.HtmlContributo;
import controller.DatabaseController;
import corso.Nodo;

public class HtmlNodo {
	public static String formInsertNodo(int idUDA){
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo nodo</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del Nodo</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" ></div>");
		sb.append("</div>");
		
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\"></textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+idUDA+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciNodo\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"OK\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	public static String formInsertNodoLeaf(int idUDA,int idNodoPadre){
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo nodo</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del Nodo</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" ></div>");
		sb.append("</div>");
		
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\"></textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+idUDA+"\">");
		sb.append("<input type=\"hidden\" name=\"idNodoPadre\" value=\""+idNodoPadre+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciNodoLeaf\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"OK\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	public static String mostraNodiUDA(int idUDA,Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Nodo> NodiUDA = DatabaseController.selectAllNodi(idUDA);
		String nomeUDA = DatabaseController.selectUDA(idUDA).getNome();
		for(Nodo nodo:NodiUDA){
			sb.append("<div class=\"row\">                                                                                                                         ");
			sb.append("  <div class=\"col-sm-6 col-md-4\">                                                                                                         ");
			sb.append("    <div class=\"thumbnail\">                                                                                                               ");
			sb.append("                                                                                                                                          ");
			sb.append("      <div class=\"caption\">                                                                                                               ");
			sb.append("        <h3>"+nodo.getNome()+"</h3>                                                                                                          ");
			sb.append("        <p>"+nodo.getDescrizione()+"</p>                                                                                                                        ");
			sb.append("<p><a href=\"Servlet?operazione=mostraNodo&idNodo="+nodo.getIdNodo()+" \" class=\"btn btn-primary\" role=\"button\">Entra</a> </p>  ");
			sb.append("      </div>                                                                                                                              ");
			sb.append("    </div>                                                                                                                                ");
			sb.append("  </div>                                                                                                                                  ");
			sb.append("</div>																																	 ");
			
			
			
			
			/*if(nodo.isComposite()){
				sb.append("<tr><td>"+nomeUDA+"</td><td>"+nodo.getNome()+"</td><td>"+nodo.getDescrizione()+"</td>");
				sb.append("<td><a href=\"Servlet?operazione=formInserisciNodoLeaf&idNodoPadre="+nodo.getIdNodo()+"&idUDA="+idUDA+"\"> aggiungi Nodo</a></td>");
				sb.append("<td><a href=\"Servlet?operazione=mostraNodiLeaf&idNodoPadre="+nodo.getIdNodo()+"\"> mostra nodi figli</a></td>");
				sb.append("<td><a href=\"Servlet?operazione=formInserisciPost&idNodo="+nodo.getIdNodo()+"\"> pubblica un post</a></td>");
				sb.append("<td><a href=\"Servlet?operazione=mostraPostNodo&idNodo="+nodo.getIdNodo()+"\"> visualizza i post</a></td>");
				sb.append("</tr>");
			}*/
		}
		
		return sb.toString();		
	}
	
	public static String mostraNodo(int idNodo,Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		Nodo nd = DatabaseController.selectNodo(idNodo);
		sb.append("<h3>"+nd.getNome()+"</h3>");
		sb.append("<ul id=\"nodoTab\" class=\"nav nav-tabs\">                                     ");
		sb.append("   <li>                                                     ");
		sb.append("      <a href=\"#home\" data-toggle=\"tab\">                                 ");
		sb.append("          Decrizione                                                         ");
		sb.append("      </a>                                                                   ");
		sb.append("   </li>                                                                     ");
		sb.append("   <li><a href=\"#post\" data-toggle=\"tab\">Post</a></li>                   ");
		sb.append("   <li><a href=\"#risorse\" data-toggle=\"tab\">Risorse</a></li>             ");
		if(part.hasRole(Role.DOCENTE)) sb.append("   <li><a href=\"#ElStudenti\" data-toggle=\"tab\">Elenco studenti</a></li>             ");
		if(nd.isComposite()) sb.append("   <li><a href=\"#ElNodi\" data-toggle=\"tab\">Sotto nodi</a></li>             ");
		sb.append("</ul>                                                                        ");
		sb.append("<div id=\"myTabContent\" class=\"tab-content\">                              ");
		sb.append("   <div class=\"tab-pane fade in active\" id=\"home\">                       ");
		sb.append("      <p>"+nd.getDescrizione()+"</p>   							            ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"post\">                                 ");
		sb.append("      <p> "+HtmlContributo.mostraPostNodo(idNodo)+" </p>                     ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"risorse\">                              ");
		sb.append("      <p> </p>																");
		sb.append("   </div>                                                                    ");
		if(nd.isComposite()){
			sb.append("   <div class=\"tab-pane fade\" id=\"ElNodi\">                            ");
			sb.append("      <p> "+mostraNodiLeaf(idNodo)+" </p>                     ");
			sb.append("   </div>                                                                    ");
		}
		sb.append("</div>                                                                       ");
		return sb.toString();
	}
	public static String mostraNodiLeaf(int idNodoPadre) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Nodo> nodi = DatabaseController.selectNodiLeaf(idNodoPadre);
		sb.append("<table class=\"table\">");
		sb.append("<tr><th>UDA</th><th>nome</th><th>descrizione</th></tr>");
		for(Nodo nd:nodi){
			sb.append("<tr><td>"+nd.getNome()+"</td><td>"+"</td><td>"+nd.getDescrizione()+"</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();	
	}
}
