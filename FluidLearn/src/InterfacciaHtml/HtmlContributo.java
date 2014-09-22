package InterfacciaHtml;



import java.sql.SQLException;
import java.util.ArrayList;



import java.util.Date;

import partecipante.Partecipante;
import partecipante.Role;
import contributo.*;
import controller.DatabaseController;

public class HtmlContributo {
	
	//Post
	/**
	 * fornisce un interfaccia html per eseguire l'input dei dati necessari alla formazione di un post
	 * @param idUDA unità di apprendimento di riferimento
	 * @param idNodo nodo di riferimento, 0 in caso di post a livello solo uda l'id
	 * @return stringa contentente il codice html 
	 */
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
		sb.append("  <div class=\"col-md-2\">                                                          ");                                    
		sb.append("<select class=\"selectpicker\" data-width=\"100px\" name=\"visibilita\">");
    sb.append("<option value=\"1\" >Privato</option>      ");
    sb.append("<option value=\"2\">Docente</option>       ");
    sb.append("<option value=\"3\">Classe</option>         ");
    sb.append("</select>                                      ");                                                                           
		sb.append("         </div>                                                                                      ");                                  
		sb.append("          <div class=\"col-md-2\">                                                                                                        ");
		sb.append("          <button type=\"submit\" name=\"stato\" value=\"PUBBLICA\" class=\"btn btn-primary\">Pubblica</button>                                                                     ");       
		sb.append("          </div>                                                                                                                          ");
		sb.append("          <div class=\"col-md-2\">                                                                                                        ");
		sb.append("          <button type=\"submit\" name=\"stato\" value=\"DRAFT\" class=\"btn btn-primary\">Bozza</button>");                              
		sb.append("          </div>                                                                                                                          ");
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
	public static String scriptDropDownSelect(){
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">    ");
	  sb.append("	window.onload = function () {       ");
	  sb.append("		$('.selectpicker').selectpicker();");
	  sb.append("	}                                   ");
	  sb.append("</script>                            ");
		return sb.toString();
	}
	public static String mostraPost(int idUDA,int idNodo,Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Azione> postNodo = DatabaseController.selectAllAzione(idUDA,idNodo);
		
		for(Azione azione:postNodo){
			if(!azione.hasDeadline()){
				sb.append("<div class=\"col-sm-6\">           ");
				sb.append("<div class=\"panel panel-default\">");
				sb.append("<div class=\"panel-heading\">");
				Partecipante utente = DatabaseController.selectPartecipante(azione.getIDPartecipante());
				sb.append("<div class=\"row\">");
				sb.append("<div class=\"col-xs-10\">");
				sb.append("<strong>"+utente.getNome()+"</strong>");
				sb.append("</div> ");
				sb.append("</div> ");
				sb.append("<div class=\"row\">");
				sb.append("<div class=\" col-md-8 col-md-offset-1 text-block\">");
				sb.append("<p class=\"bd\">"+azione.getCorpo().getText()+"</p>");
				sb.append("</div> ");
				sb.append("</div> ");
				sb.append("<div class=\"row\">");
				sb.append("<div class=\" col-md-8 col-md-offset-4\">");
				if(part.isAutorePost(azione.getIDPost())){
					sb.append("<a href=\"Servlet?operazione=eliminaAzione&idPost="+azione.getIDPost()+"&idNodo="+azione.getIDNodo()+"&idUDA="+azione.getIDUDA()+"\" role=\"submit\"><footer>Elimina</footer></a>");
					sb.append("<a href=\"Servlet?operazione=formModificaAzione&idPost="+azione.getIDPost()+"\" role=\"submit\"><footer>Modifica</footer></a>");
				}
				sb.append("</div> ");
				sb.append("</div> ");
				sb.append("</div> ");
				sb.append("<div class=\"panel-body col-md-8 col-md-offset-1\"> ");
				sb.append(""+mostraCommentiPost(azione,part)+"");
				sb.append("</div>                             ");
				sb.append(""+formInputCommento(azione)+"");
				sb.append("<br>");
				sb.append("</div>                             ");
				sb.append("</div>                             ");
			}
		}
		
		return sb.toString();		
	}
	public static String formModificaPost(int idPost) throws SQLException{
		StringBuilder sb = new StringBuilder();
		Azione post = DatabaseController.selectPost(idPost);
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"row\">                                                                                                                         ");
		sb.append("  <div class=\"col-md-6\">                                                                                                                  ");
		sb.append("<div class=\"panel panel-default\">                                                                                                         ");
		sb.append("  <div class=\"panel-heading\">                                                                                                             ");
		sb.append("    <h3 class=\"panel-title\">Modifica Post</h3>                                                                                             ");
		sb.append("  </div>                                                                                                                                  ");
		sb.append("  <div class=\"panel-body\">                                                                                                                ");
		sb.append("  <textarea class=\"form-control\" rows=\"5\" name=\"testo\"></textarea>                                                                                    ");
		sb.append("  </div>                                                                                                                                  ");
		sb.append("  <div class=\"panel-footer\">                                                                                                              ");
		sb.append("  <div class=\"row\">                                                                                                                       ");
		sb.append("  <div class=\"col-md-2\">                                                          ");                                    
		sb.append("<select class=\"selectpicker\" data-width=\"100px\" name=\"visibilita\">");
    sb.append("<option value=\"1\" >Privato</option>      ");
    sb.append("<option value=\"2\">Docente</option>       ");
    sb.append("<option value=\"3\">Classe</option>         ");
    sb.append("</select>                                      ");                                                                           
		sb.append("         </div>                                                                                      ");                                  
		sb.append("          <div class=\"col-md-2\">                                                                                                        ");
		sb.append("          <button type=\"submit\" name=\"stato\" value=\"PUBBLICA\" class=\"btn btn-primary\">Pubblica</button>                                                                     ");       
		sb.append("          </div>                                                                                                                          ");
		sb.append("          <div class=\"col-md-2\">                                                                                                        ");
		sb.append("          <button type=\"submit\" name=\"stato\" value=\"DRAFT\" class=\"btn btn-primary\">Bozza</button>");                              
		sb.append("          </div>                                                                                                                          ");
		sb.append("   </div>                                                                                                                                 ");
        sb.append("                                                                                                                                          ");
		sb.append("  </div>                                                                                                                                  ");
		sb.append("</div>                                                                                                                                    ");
		sb.append("</div>                                                                                                                                    ");
		sb.append("</div>                                                                                                                                    ");
		sb.append("                                                                                                                                          ");
		
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+post.getIDUDA()+"\">");
		sb.append("<input type=\"hidden\" name=\"idNodo\" value=\""+post.getIDNodo()+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"modificaPost\">");
		
		
		sb.append("</form>");
		return sb.toString();
	}
	
	/*commento*/
	public static String formInputCommento(Azione post){
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		
		sb.append("<div class=\"row\">");
		
		sb.append("<div class=\"col-xs-6 col-md-6\"> <input type=\"text\" name=\"testo\" id=\"testo\" class=\"form-control\" placeholder=\"testo del commento\" ></div>");
		sb.append("<div class=\"col-xs-6 col-md-6\">");
		if(post.hasDeadline()) sb.append("	<button type=\"submit\" class=\"btn btn-primary\">Rispondi</button></div>");
		
		else sb.append("	<button type=\"submit\" class=\"btn btn-primary\">Commenta</button></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+post.getIDUDA()+"\">");
		sb.append("<input type=\"hidden\" name=\"idNodo\" value=\""+post.getIDNodo()+"\">");
		sb.append("<input type=\"hidden\" name=\"idPost\" value=\""+post.getIDPost()+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciReazione\">");                                                                                                     		 
		sb.append("</form>");
		
		return sb.toString();
	}
	public static String mostraCommentiPost(Azione post,Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Reazione> commentiPost = DatabaseController.selectCommentiPost(post.getIDPost());
		sb.append("<ul class=\"list-unstyled text-block\">");
		for(Reazione commento: commentiPost ){
			
			Partecipante utente = DatabaseController.selectPartecipante(commento.getIDPartecipante());
			sb.append("<li class=\"bd\"><strong class=\"img\">"+utente.getNome()+"  </strong> "+commento.getCorpo().getText()+"");
			if(part.isAutoreCommento(commento.getIDCommento())){
				sb.append("<li><a href=\"Servlet?operazione=eliminaReazione&idCommento="+commento.getIDCommento()+"&idNodo="+post.getIDNodo()+"&idUDA="+post.getIDUDA()+"\" role=\"submit\"><footer>Elimina</footer></a><a href=\"Servlet?operazione=formModificaReazione&idCommento="+commento.getIDCommento()+"&idPost="+post.getIDPost()+"\" role=\"submit\"><footer>Modifica</footer></a></li>");
			}
			sb.append("</li>");
			
			if(post.hasDeadline()&&(part.hasRole(Role.ESAMINATORE)||part.hasRole(Role.DOCENTE))){
				sb.append("<div class=\"row\">");
				sb.append("<div class=\"col-md-3 col-md-offset-8\">");
				sb.append("<a href=\"Servlet?operazione=formValutazione&&idCommento="+commento.getIDCommento()+"\" class=\"btn btn-primary btn-lg \" role=\"submit\">Valuta</a>");
				sb.append("</div>");
				sb.append("</div>");
			}
			
		}
		sb.append("</ul>");
		
		return sb.toString();		
	}
	
	public static String formModificaCommento(Azione post,int idCommento) throws SQLException{
		StringBuilder sb = new StringBuilder();
		Reazione commento = DatabaseController.selectCommento(idCommento);
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"> <input type=\"text\" name=\"testo\" id=\"testo\" class=\"form-control\" placeholder=\"testo del commento\" value=\""+commento.getCorpo().getText()+"\"></div>");
		if(post.hasDeadline()) sb.append("	<button type=\"submit\" class=\"btn btn-primary\">Rispondi</button>");
		
		else sb.append("	<button type=\"submit\" class=\"btn btn-primary\">Commenta</button>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idUDA\" value=\""+post.getIDUDA()+"\">");
		sb.append("<input type=\"hidden\" name=\"idNodo\" value=\""+post.getIDNodo()+"\">");
		sb.append("<input type=\"hidden\" name=\"idPost\" value=\""+post.getIDPost()+"\">");
		sb.append("<input type=\"hidden\" name=\"idCommento\" value=\""+idCommento+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"modificaReazione\">");                                                                                                     		 
		sb.append("</form>");
		
		return sb.toString();
	}
	/*----sollecitazione----*/
	/**
	 * 
	 * @param idUDA
	 * @param idNodo
	 * @return
	 */
	public static String formInputSollecitazione(int idUDA,int idNodo){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"row\">                                                                                                                         ");
		sb.append("  <div class=\"col-md-6\">                                                                                                                  ");
		sb.append("<div class=\"panel panel-default\">                                                                                                         ");
		sb.append("  <div class=\"panel-heading\">                                                                                                             ");
		sb.append("    <h3 class=\"panel-title\">Crea Una Sollecitazione</h3>                                         ");                                                
		sb.append("<div class=\"form-group col-xs-6 col-md-4\">                                                                           ");
    sb.append("<input type=\"text\" class=\"form-control\" name=\"deadline\" id=\"datepicker\" placeholder=\"Deadline\">    ");
    sb.append("</div>                                                                                             ");
		sb.append("  </div>                                                                                           ");                                     
		sb.append("  <div class=\"panel-body\">                                                                                                                ");
		sb.append("  <textarea class=\"form-control\" rows=\"5\" name=\"testo\"></textarea>                                                                                    ");
		sb.append("  </div>                                                                                                                                  ");
		sb.append("  <div class=\"panel-footer\">                                                                                                              ");
		sb.append("  <div class=\"row\">                                                                                                                       ");
		sb.append("  <div class=\"col-md-2 col-md-offset-3\">                                                          ");                                    
		sb.append("<select class=\"selectpicker\" data-width=\"50%\" name=\"visibilita\">");
    sb.append("<option value=\"1\" >Privato</option>      ");
    sb.append("<option value=\"2\">Docente</option>       ");
    sb.append("<option value=\"3\">Classe</option>         ");
    sb.append("</select>                                      ");                                                                           
		sb.append("         </div>                                                                                      ");                                  
		sb.append("          <div class=\"col-md-2\">                                                                                                        ");
		sb.append("          <button type=\"submit\" name=\"stato\" value=\"PUBBLICA\" class=\"btn btn-primary\">Pubblica</button>                                                                     ");       
		sb.append("          </div>                                                                                                                          ");
		sb.append("          <div class=\"col-md-2\">                                                                                                        ");
		sb.append("          <button type=\"submit\" name=\"stato\" value=\"DRAFT\" class=\"btn btn-primary\">Bozza</button>");                              
		sb.append("          </div>                                                                                                                          ");
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
		sb.append("<script>                              ");
	  sb.append("$(function() {                        ");
	  sb.append("  $( \"#datepicker\" ).datepicker({ dateFormat: \"yy-mm-dd\" });    ");
	  sb.append("});                                   ");
	  sb.append("</script>                             ");
		return sb.toString();
	}
	
	
	public static String mostraSollecitazioni(int idUDA,int idNodo,Partecipante part) throws SQLException{
		StringBuilder sb = new StringBuilder();
		ArrayList<Azione> postNodo = DatabaseController.selectAllAzione(idUDA,idNodo);
		for(Azione azione:postNodo){
			if(azione.hasDeadline() && ((Sollecitazione)azione).getDeadline().after(new Date())){
				System.out.println("ho una deadline");
				sb.append("<div class=\"col-sm-6\">");
				sb.append("<div class=\"panel panel-default\">");
				sb.append("<div class=\"panel-heading\">");
				Partecipante utente = DatabaseController.selectPartecipante(azione.getIDPartecipante());
				sb.append(""+utente.getNome()+" <strong>"+azione.getCorpo().getText()+"</strong>");
				sb.append("</div>");
				sb.append("<div class=\"panel-body\"> ");
				sb.append(""+mostraCommentiPost(azione,part)+"");
				sb.append("</div>                             ");
				sb.append(""+formInputCommento(azione)+"");
				sb.append("</div>");
				sb.append("</div>");
			}
		}
		if(sb.length()==0) sb.append("<h3>non sono presenti sollecitazioni</h3>");
		return sb.toString();	
	}
	
	public static String forminputValutazione(int idCommento) throws SQLException{
		StringBuilder sb = new StringBuilder();
		Reazione risposta = DatabaseController.selectCommento(idCommento);
		sb.append("<h4>valutzione della risposta:</h4>");
		sb.append("<p class=\"text-block\">"+risposta.getCorpo().getText()+"</p>");
		sb.append("<form class=\"form-horizontal col-md-8 \" role=\"form\">");
		sb.append("<div class=\"form-group\">                                                                       ");
		sb.append("  <label for=\"voto\" class=\"col-sm-2 control-label\">Voto</label>                                ");
		sb.append("  <div class=\"col-sm-4\">                                                                       ");
		sb.append("    <input type=\"text\" class=\"form-control\" id=\"voto\" name=\"voto\" placeholder=\"Inserici Voto\"> ");
		sb.append("  </div>                                                                                       ");
		sb.append("  <div class=\"col-sm-2\">                                                                       ");
		sb.append("    <div class=\"checkbox-inline\">                                                              ");
		sb.append("      <label>                                                                                  ");
		sb.append("        <input type=\"checkbox\"> Lode                                                           ");
		sb.append("      </label>                                                                                 ");
		sb.append("    </div>                                                                                     ");
		sb.append("</div>                                                                                         ");
		sb.append("</div>                                                                                         ");
		sb.append("<div class=\"form-group\">                                                                       ");
		sb.append("  <label for=\"note\" class=\"col-sm-2 control-label\">Note</label>                                ");
		sb.append("  <div class=\"col-sm-10\">                                                                      ");
		sb.append("    <textarea class=\"form-control\" id=\"note\" name=\"note\" placeholder=\"Note\"></textarea>                    ");
		sb.append("  </div>                                                                                       ");
		sb.append("</div>                                                                                         ");
		sb.append("<div class=\"form-group\">                                                                       ");
		sb.append("  <div class=\"col-sm-offset-2 col-sm-3\">                                                       ");
		sb.append("<select class=\"selectpicker\" data-width=\"90%\" name=\"tipo\">");
    sb.append("<option value=\"1\" >Intero</option>      ");
    sb.append("<option value=\"2\">Stringa</option>       ");
    sb.append("</select>                                      ");     
		sb.append("  </div>                                                                                       ");
		sb.append("  <div class=\"col-sm-offset-6 col-sm-3\">                                                       ");
		sb.append("<select class=\"selectpicker\" data-width=\"90%\" name=\"visibilita\">");
    sb.append("<option value=\"1\" >Bozza</option>      ");
    sb.append("<option value=\"2\">Pubblica</option>       ");
    sb.append("<option value=\"3\">Verbalizza</option>         ");
    sb.append("</select>                                      ");     
		sb.append("  </div>                                                                                       ");
		
		sb.append("<input type=\"hidden\" name=\"idRisposta\" value=\""+idCommento+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciValutazione\">");
		sb.append("  <div class=\"col-sm-3\">                                                                       ");
		sb.append("    <button type=\"submit\" class=\"btn btn-default\">invia</button>                               ");
		sb.append("  </div>                                                                                       ");
		sb.append("</div>                                                                                         ");
		sb.append("</form>                                                                                        ");

		return sb.toString();
	}
	
}
