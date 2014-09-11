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
		
		for(Azione azione:postNodo){
			sb.append("<div class=\"col-sm-7\">           ");
			sb.append("<div class=\"panel panel-default\">");
			sb.append("<div class=\"panel-body\">         ");
			sb.append(""+azione.getCorpo().getText()+""    );
			sb.append(""+formInputCommento(azione)+"");
			sb.append("</div>                             ");
			sb.append("</div>                             ");
			sb.append("</div>                             ");
			
		}
		
		return sb.toString();		
	}
	public static String formInputCommento(Azione post){
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"> <input type=\"text\" name=\"testo\" id=\"testo\" class=\"form-control\" placeholder=\"testo del commento\" ></div>");
		sb.append("	<button type=\"submit\" class=\"btn btn-primary\">Commenta</button>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idNodo\" value=\""+post.getIDNodo()+"\">");
		sb.append("<input type=\"hidden\" name=\"idPost\" value=\""+post.getIDPost()+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciCommento\">");                                                                                                     		 
		sb.append("</form>");
		
		return sb.toString();
	}
	
}
