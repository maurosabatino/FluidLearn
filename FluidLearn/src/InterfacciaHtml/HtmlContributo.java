package InterfacciaHtml;



import java.sql.SQLException;
import java.util.ArrayList;


import contributo.*;
import controller.DatabaseController;

public class HtmlContributo {
	public static String formInputPost(int idNodo){
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo corso</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"testo\">testo Del Post</label> <input type=\"text\" name=\"testo\" id=\"testo\" class=\"form-control\" placeholder=\"testo\" ></div>");
		
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idNodo\" value=\""+idNodo+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciPost\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"Pubblica\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	public static String mostraPostNodo(int idNodo) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Azione> postNodo = DatabaseController.selectAllPostNodo(idNodo);
		sb.append("<table class=\"table\">");
		sb.append("<tr><th>testo</th><th>data</th></tr>");
		for(Azione azione:postNodo){
			sb.append("<tr><td>"+azione.getCorpo().getText()+"</td><td>"+azione.getData()+"</td>");
			sb.append("<td><button class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" data-target=\"#CommentaPost\">Commenta</button></td>");
			sb.append("<td>"+formInputCommento(azione.getIDPost())+"</td>");
			sb.append("</tr>");
			
		}
		sb.append("</table>");
		return sb.toString();		
	}
	public static String formInputCommento(int idPost){
		StringBuilder sb = new StringBuilder();
		sb.append("	<div class=\"modal fade\" id=\"CommentaPost\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">                	     ");
		sb.append("	  <div class=\"modal-dialog\">                                                                                                                       		 ");
		sb.append("	    <div class=\"modal-content\">                                                                                                                    		 ");
		sb.append("	      <div class=\"modal-header\">                                                                                                                   		 ");
		sb.append("	        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Chiudi</span></button>");
		sb.append("	        <h4 class=\"modal-title\" id=\"myModalLabel\">Nuovo Commento</h4>                                                                               		 ");
		sb.append("	      </div>                                                                                                                                       	 		 ");
		sb.append("	      <div class=\"modal-body\"> "
				+ "                                                                                                                     		 ");
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"testo\">testo Del Commento</label> <input type=\"text\" name=\"testo\" id=\"testo\" class=\"form-control\" placeholder=\"testo\" ></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idPost\" value=\""+idPost+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciCommento\">");                                                                                                     		 
		sb.append("	      </div>      ");
		
		sb.append("	      <div class=\"modal-footer\">                                                                                                                    		 ");
		sb.append("	        <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Esci</button>                                                           	 ");
		sb.append("	        <button type=\"submit\" class=\"btn btn-primary\">Commenta</button>                                                                         	 ");
		sb.append("	      </div>");                                                                                              
		sb.append("	    </div>");
		sb.append("	  </div>");
		sb.append("	</div>");
		
		
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
}
