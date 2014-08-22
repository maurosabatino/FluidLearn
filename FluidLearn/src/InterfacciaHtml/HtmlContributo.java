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
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();		
	}
}
