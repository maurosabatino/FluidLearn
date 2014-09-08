package InterfacciaHtml;

import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static String mostraCorso(int idCorso) throws SQLException{
		Corso c = DatabaseController.SelectCorso(idCorso);
		StringBuilder sb = new StringBuilder();
		sb.append("<p>nome: "+c.getNome()+"</p>");
		sb.append("<p>descrizione: "+c.getDescrizione()+"</p>");
		sb.append("<p><a href=\"Servlet?operazione=formModificaCorso&idCorso="+c.getIdCorso()+"\"> modifica Corso</a></p>");
		return sb.toString();
	}
	
	public static String mostraAllCorsi() throws SQLException{
		ArrayList<Corso> corsi = DatabaseController.SelectAllCorso();
		StringBuilder sb = new StringBuilder();
		int column = 0;
		//sb.append("<table class=\"table\">");
		//sb.append("<tr><th>nome</th><th>descrizione</th><th>modifica</th><th>elimina</th><th>aggiungi UDA</th><th>mostra UDA</th></tr>");
		for(Corso c : corsi){
			System.out.println(column);
			if(column%3==0)sb.append("	<div class=\"row\">                                                                                                                       ");
		sb.append("	  <div class=\"col-sm-6 col-md-4\">                                                                                                       ");
		sb.append("	    <div class=\"thumbnail\">                                                                                                             ");
		sb.append("	      <img src=\"images/STUDENT_img.jpg\" alt=\"Immagine del corso\">                                                                                      ");
		sb.append("	      <div class=\"caption\">                                                                                                             ");
		sb.append("	        <h3>"+c.getNome()+"</h3>                                                                                                        ");
		sb.append("	        <p>"+c.getDescrizione()+"</p>                                                                                                                      ");
		sb.append("	        <p class=\"text-center\"><a href=\"Servlet?operazione=mostraUDACorso&idCorso="+c.getIdCorso()+"\" class=\"btn btn-primary\" role=\"button\">Entra</a></p>");
		sb.append("	      </div>                                                                                                                            ");
		sb.append("	    </div>                                                                                                                              ");
		sb.append("	  </div>                                                                                                                                ");
		if((column+1)%3==0)sb.append("	</div>                                                                                                                                  ");
			column++;
			
			
			/*sb.append("<td><a href=\"Servlet?operazione=formModificaCorso&idCorso="+c.getIdCorso()+"\"> modifica</a></td>");
			sb.append("<td><a href=\"Servlet?operazione=eliminaCorso&idCorso="+c.getIdCorso()+"\"> elimina</a></td>");
			sb.append("<td><a href=\"Servlet?operazione=formInserisciUDA&idCorso="+c.getIdCorso()+"\"> aggiungi UDA</a></td>");
			sb.append("<td><a href=\"Servlet?operazione=mostraUDACorso&idCorso="+c.getIdCorso()+"\"> mostra UDA</a></td>");
			sb.append("</tr>");*/
		}
		//sb.append("</table>");
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
	
	/*--------UDA---------*/
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
		return sb.toString();
	}
	public static String mostraUDA(int idUDA) throws SQLException{
		StringBuilder sb = new StringBuilder();
		UnitaDA UDA = DatabaseController.selectUDA(idUDA);
		Corso c = DatabaseController.SelectCorso(UDA.getIdCorso());
		sb.append("<p>nome corso: "+c.getNome()+"</p>");
		sb.append("<p>nome UDA: "+UDA.getNome()+"</p>");
		sb.append("<p>descrizione: "+UDA.getDescrizione()+"</p>");
		sb.append("<p>data attivazione: "+UDA.getData()+"</p>");
		
		return sb.toString();
	}
	
	public static String mostraUDACorso(int idCorso) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<UnitaDA> UDACorso = DatabaseController.selectAllUDA(idCorso);
		String nomeCorso = DatabaseController.SelectCorso(idCorso).getNome();
		sb.append("<h3>unità di apprendimento di "+nomeCorso+"</h3>");
		for(UnitaDA UDA:UDACorso){
			
			sb.append("<div class=\"row\">                                                                                                                         ");
			sb.append("  <div class=\"col-sm-6 col-md-4\">                                                                                                         ");
			sb.append("    <div class=\"thumbnail\">                                                                                                               ");
			sb.append("                                                                                                                                          ");
			sb.append("      <div class=\"caption\">                                                                                                               ");
			sb.append("        <h3>"+UDA.getNome()+"</h3>                                                                                                          ");
			sb.append("        <p>"+UDA.getDescrizione()+"</p>                                                                                                                        ");
			sb.append("        <p><a href=\"Servlet?operazione=mostraNodiUDA&idUDA="+UDA.getIdUDA()+"\" class=\"btn btn-primary\" role=\"button\">Mostra i Nodi</a> </p>  ");
			sb.append("      </div>                                                                                                                              ");
			sb.append("    </div>                                                                                                                                ");
			sb.append("  </div>                                                                                                                                  ");
			sb.append("</div>																																	 ");
			
			
			
			//sb.append("<td><a href=\"Servlet?operazione=formInserisciNodo&idUDA="+UDA.getIdUDA()+"\"> aggiungi Nodo</a></td>");
			//sb.append("<td><a href=\"Servlet?operazione=mostraNodiUDA&idUDA="+UDA.getIdUDA()+"\"> mostra i Nodi</a></td>");
			
		}
		
		return sb.toString();		
	}
	
	/*------nodo----*/
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
	public static String mostraNodiUDA(int idUDA) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Nodo> NodiUDA = DatabaseController.selectAllNodi(idUDA);
		String nomeUDA = DatabaseController.selectUDA(idUDA).getNome();
		sb.append("<h3>nodi dell'unità di apprendimento "+nomeUDA+"</h3>");
		for(Nodo nodo:NodiUDA){
			sb.append("<div class=\"row\">                                                                                                                         ");
			sb.append("  <div class=\"col-sm-6 col-md-4\">                                                                                                         ");
			sb.append("    <div class=\"thumbnail\">                                                                                                               ");
			sb.append("                                                                                                                                          ");
			sb.append("      <div class=\"caption\">                                                                                                               ");
			sb.append("        <h3>"+nodo.getNome()+"</h3>                                                                                                          ");
			sb.append("        <p>"+nodo.getDescrizione()+"</p>                                                                                                                        ");
			sb.append("<p><a href=\"Servlet?operazione=mostraNodo&idNodo="+nodo.getIdNodo()+"#post \" class=\"btn btn-primary\" role=\"button\">Entra</a> </p>  ");
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
	public static String mostraNodo(int idNodo) throws SQLException{
		StringBuilder sb = new StringBuilder();
		Nodo nd = DatabaseController.selectNodo(idNodo);
		//sb.append(scriptSelectTag());
		sb.append("<h3>"+nd.getNome()+"</h3>");
		sb.append("<ul id=\"nodoTab\" class=\"nav nav-tabs\">                                     ");
		sb.append("   <li>                                                     ");
		sb.append("      <a href=\"#home\" data-toggle=\"tab\">                                 ");
		sb.append("          Decrizione                                                         ");
		sb.append("      </a>                                                                   ");
		sb.append("   </li>                                                                     ");
		sb.append("   <li><a href=\"#post\" data-toggle=\"tab\">Post</a></li>                   ");
		sb.append("   <li><a href=\"#risorse\" data-toggle=\"tab\">Risorse</a></li>             ");
		sb.append("</ul>                                                                        ");
		sb.append("<div id=\"myTabContent\" class=\"tab-content\">                              ");
		sb.append("   <div class=\"tab-pane fade in active\" id=\"home\">                       ");
		sb.append("      <p>"+nd.getDescrizione()+"</p>   							            ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"post\">                                 ");
		sb.append("      <p> "+HtmlContributo.mostraPostNodo(idNodo)+" </p>                     ");
		sb.append("   </div>                                                                    ");
		sb.append("   <div class=\"tab-pane fade\" id=\"risorse\">                              ");
		sb.append("      <p> </p>																	");
		sb.append("   </div>                                                                    ");
		sb.append("                                                                             ");
		sb.append("</div>                                                                       ");
		return sb.toString();
	}
	public static String scriptSelectTag(){
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("('#nodoTab a').click(function (e) {                             ");
	    sb.append("    e.preventDefault();                                         ");
	    sb.append("    $(this).tab('show');                                        ");
	    sb.append("});                                                             ");
        sb.append("                                                                ");
	    sb.append("// store the currently selected tab in the hash value           ");
	    sb.append("$(\"ul.nav-tabs > li > a\").on(\"shown.bs.tab\", function (e) { ");
	    sb.append("    var id = $(e.target).attr(\"href\").substr(1);              ");
	    sb.append("    window.location.hash = id;                                  ");
	    sb.append("});                                                             ");
        sb.append("                                                                ");
	    sb.append("// on load of the page: switch to the currently selected tab    ");
	    sb.append("var hash = window.location.hash;                                ");
	    sb.append("$('#nodoTab a[href=\"' + hash + '\"]').tab('show');             ");
	    sb.append("</script>");
	    return sb.toString();
	}
}
