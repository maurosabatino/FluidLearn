package InterfacciaHtml;



import java.sql.SQLException;
import java.util.ArrayList;



import partecipante.Partecipante;
import contributo.*;
import controller.DatabaseController;

public class HtmlContributo {
	public static String formInputPost(int idUDA,int idNodo){
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"row\">                                                                                                                         ");
		sb.append("  <div class=\"col-md-6\">                                                                                                                  ");
		sb.append("<div class=\"panel panel-default\">                                                                                                         ");
		sb.append("  <div class=\"panel-heading\">                                                                                                             ");
		sb.append("    <h3 class=\"panel-title\">Crea Un Post</h3>                                                                                             ");
		sb.append("  </div>                                                                                                                                  ");
		sb.append("  <div class=\"panel-body\">                                                                                                                ");
		sb.append("  <textarea class=\"form-control\" rows=\"5\" name=\"testo\"></textarea>                                                                                    ");
		sb.append("  </div>                                                                                                                                  ");
		sb.append("  <div class=\"panel-footer\">                                                                                                              ");
		sb.append("  <div class=\"row\">                                                                                                                       ");
		sb.append("  <div class=\"col-md-3 col-md-offset-6 \">                                                                                                 ");
		sb.append("  <button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">Visibilità <span class=\"caret\"></span></button>    ");
		sb.append(" 	  <ul class=\"dropdown-menu\" role=\"menu\">                                                                                             ");
		sb.append("          <li><a href=\"#\">Privato</a></li>                                                                                                ");
		sb.append("          <li><a href=\"#\">Docente</a></li>                                                                                                ");
		sb.append("          <li><a href=\"#\">Classe</a></li>                                                                                                 ");
		sb.append("         </ul>                                                                                                                            ");
		sb.append("         </div>                                                                                                                           ");
		sb.append("          <div class=\"col-md-3\">                                                                                                        ");
		sb.append("          <button type=\"submit\" class=\"btn btn-primary\">Post</button>                                                                     ");
		sb.append("          </div>                                                                                                                          ");
		sb.append("                                                                                                                                          ");
		sb.append("   </div>                                                                                                                                 ");
        sb.append("                                                                                                                                          ");
		sb.append("  </div>                                                                                                                                  ");
		sb.append("</div>                                                                                                                                    ");
		sb.append("</div>                                                                                                                                    ");
		sb.append("</div>                                                                                                                                    ");
		sb.append("                                                                                                                                          ");
		
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+idUDA+"\">");
		sb.append("<input type=\"hidden\" name=\"idNodo\" value=\""+idNodo+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciPost\">");
		
		
		sb.append("</form>");
		return sb.toString();
	}
	public static String mostraPost(int idUDA,int idNodo) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Azione> postNodo = DatabaseController.selectAllPost(idUDA,idNodo);
		
		for(Azione azione:postNodo){
			sb.append("<div class=\"col-sm-6\">           ");
			sb.append("<div class=\"panel panel-default\">");
			sb.append("<div class=\"panel-heading\">");
			Partecipante utente = DatabaseController.selectPartecipante(azione.getIDPartecipante());
			sb.append(""+utente.getNome()+" <strong>"+azione.getCorpo().getText()+"</strong>");
			sb.append("</div> ");
			sb.append("<div class=\"panel-body\"> ");
			sb.append(""+mostraCommentiPost(azione.getIDPost())+"");
			sb.append("</div>                             ");
			sb.append(""+formInputCommento(azione)+"");
			
			sb.append("</div>                             ");
			sb.append("</div>                             ");
			
		}
		
		return sb.toString();		
	}
	
	/*commento*/
	public static String formInputCommento(Azione post){
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"> <input type=\"text\" name=\"testo\" id=\"testo\" class=\"form-control\" placeholder=\"testo del commento\" ></div>");
		sb.append("	<button type=\"submit\" class=\"btn btn-primary\">Commenta</button>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+post.getIDUDA()+"\">");
		sb.append("<input type=\"hidden\" name=\"idNodo\" value=\""+post.getIDNodo()+"\">");
		sb.append("<input type=\"hidden\" name=\"idPost\" value=\""+post.getIDPost()+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciCommento\">");                                                                                                     		 
		sb.append("</form>");
		
		return sb.toString();
	}
	public static String mostraCommentiPost(int idPost) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Reazione> commentiPost = DatabaseController.selectCommentiPost(idPost);
		
		for(Reazione commento: commentiPost ){
			sb.append("<div class=\"row\">");
			Partecipante utente = DatabaseController.selectPartecipante(commento.getIDPartecipante());
			sb.append("<div class=\"col-xs-5 \"><p>"+utente.getNome()+" "+commento.getCorpo().getText()+"</p>");
			sb.append("</div>");
			sb.append("</div>");
			
		}
		
		return sb.toString();		
	}
	
}
