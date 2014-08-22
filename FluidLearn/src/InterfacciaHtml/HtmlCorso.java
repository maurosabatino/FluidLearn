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
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\"></textarea></div>");
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
		sb.append("<table class=\"table\">");
		sb.append("<tr><th>nome</th><th>descrizione</th><th>modifica</th><th>elimina</th><th>aggiungi UDA</th><th>mostra UDA</th></tr>");
		for(Corso c : corsi){
			sb.append("<tr><td>"+c.getNome()+"</td><td>"+c.getDescrizione()+"</td>");
			sb.append("<td><a href=\"Servlet?operazione=formModificaCorso&idCorso="+c.getIdCorso()+"\"> modifica</a></td>");
			sb.append("<td><a href=\"Servlet?operazione=eliminaCorso&idCorso="+c.getIdCorso()+"\"> elimina</a></td>");
			sb.append("<td><a href=\"Servlet?operazione=formInserisciUDA&idCorso="+c.getIdCorso()+"\"> aggiungi UDA</a></td>");
			sb.append("<td><a href=\"Servlet?operazione=mostraUDACorso&idCorso="+c.getIdCorso()+"\"> mostra UDA</a></td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
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
		sb.append("<table class=\"table\">");
		sb.append("<tr><th>corso</th><th>UDA</th><th>descrizione</th><th>data attivazione</th><th>aggiungi nodo</th><th>mostra nodi</th></tr>");
		for(UnitaDA UDA:UDACorso){
			sb.append("<tr><td>"+nomeCorso+"</td><td>"+UDA.getNome()+"</td><td>"+UDA.getDescrizione()+"</td><td>"+UDA.getData()+"</td> ");
			sb.append("<td><a href=\"Servlet?operazione=formInserisciNodo&idUDA="+UDA.getIdUDA()+"\"> aggiungi Nodo</a></td>");
			sb.append("<td><a href=\"Servlet?operazione=mostraNodiUDA&idUDA="+UDA.getIdUDA()+"\"> mostra i Nodi</a></td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
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
		sb.append("<table class=\"table\">");
		sb.append("<tr><th>UDA</th><th>nome</th><th>descrizione</th><th>aggiungi un nodo figlio</th><th>mostra i nodi figli</th></tr>");
		for(Nodo nodo:NodiUDA){
			if(nodo.isComposite()){
				sb.append("<tr><td>"+nomeUDA+"</td><td>"+nodo.getNome()+"</td><td>"+nodo.getDescrizione()+"</td>");
				sb.append("<td><a href=\"Servlet?operazione=formInserisciNodoLeaf&idNodoPadre="+nodo.getIdNodo()+"&idUDA="+idUDA+"\"> aggiungi Nodo</a></td>");
				sb.append("<td><a href=\"Servlet?operazione=mostraNodiLeaf&idNodoPadre="+nodo.getIdNodo()+"\"> mostra nodi figli</a></td>");
				sb.append("<td><a href=\"Servlet?operazione=formInserisciPost&idNodo="+nodo.getIdNodo()+"\"> pubblica un post</a></td>");
				sb.append("<td><a href=\"Servlet?operazione=mostraPostNodo&idNodo="+nodo.getIdNodo()+"\"> visualizza i post</a></td>");
				sb.append("</tr>");
			}
		}
		sb.append("</table>");
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
