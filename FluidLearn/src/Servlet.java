

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contributo.*;
import contributo.valutazione.Valutazione;
import controller.*;
import partecipante.*;
import corso.*;
import bean.*;
import InterfacciaHtml.*;
import InterfacciaHtml.corso.HtmlCorso;
import InterfacciaHtml.corso.HtmlNodo;
import InterfacciaHtml.corso.HtmlUDA;


/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			processRequest(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
		String operazione = request.getParameter("operazione");
		System.out.println("operazione: "+operazione);
		HttpSession session = request.getSession();
		if(operazione.equals("formInserisciCorso")){
			String content = HtmlCorso.formInsertCorso();
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("inserisciCorso")){
			Corso corso = CorsoController.nuovoCorso(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlCorso.mostraCorso(corso.getIdCorso(),part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formModificaCorso")){
			String idCorso = request.getParameter("idCorso");
			String content = HtmlCorso.formModificaCorso(Integer.parseInt(idCorso));
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraCorso")){
			int idCorso = Integer.parseInt(request.getParameter("idCorso"));
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = " ";
			if(part==null){
				content = "<h3> non sei un utente registrato e non puoi visualizzare i corsi, fai il <strong>Login</strong></h3>";
			}
			else{
				part = DatabaseController.decorate(part, idCorso);
				session.setAttribute("partecipante", part);
				content = HtmlCorso.mostraCorso(idCorso, part);
			}
			 
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("modificaCorso")){
			Corso corso = CorsoController.modificaCorso(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlCorso.mostraCorso(corso.getIdCorso(),part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraAllCorsi")){
			String content = HtmlCorso.mostraAllCorsi();
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("eliminaCorso")){
			boolean succes = CorsoController.eliminaCorso(request);
			String content;
			if(!succes) content ="<p> ho eliminato con successo il corso </p>";
			else content ="<p> non ho eliminato con successo il corso </p>";
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formInserisciUDA")){
			int idCorso = Integer.parseInt(request.getParameter("idCorso"));
			String content = HtmlUDA.formInsertUDA(idCorso);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");			
		}
		else if(operazione.equals("inserisciUDA")){
			UnitaDA UDA = CorsoController.nuovaUDA(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlUDA.mostraUDA(UDA.getIdUDA(),part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("mostraUDA")){
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlUDA.mostraUDA(idUDA,part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formModificaUDA")){
			String content = HtmlUDA.formModificaUDA(Integer.parseInt(request.getParameter("idUDA")));
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("modificaUDA")){
			UnitaDA UDA = CorsoController.modificaUDA(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlUDA.mostraUDA(UDA.getIdUDA(),part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("eliminaUDA")){
			CorsoController.deleteUDA(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlCorso.mostraCorso(Integer.parseInt(request.getParameter("idCorso")), part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formInserisciNodo")){
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content = HtmlNodo.formInsertNodo(idUDA);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("inserisciNodo")){
			Nodo nodo = CorsoController.nuovoNodo(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlNodo.mostraNodiUDA(nodo.getIdUDA(),part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraNodiUDA")){
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlNodo.mostraNodiUDA(idUDA,part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraNodo")){
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlNodo.mostraNodo(idNodo,part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formInserisciNodoLeaf")){
			int idNodoPadre = Integer.parseInt(request.getParameter("idNodoPadre"));
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content = HtmlNodo.formInsertNodoLeaf(idUDA,idNodoPadre);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("inserisciNodoLeaf")){
			Nodo nodo = CorsoController.nuovoNodoLeaf(request);
			String content = HtmlNodo.mostraNodiLeaf(nodo.getIdNodo());
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraNodiLeaf")){
			int idNodoPadre = Integer.parseInt(request.getParameter("idNodoPadre"));
			String content = HtmlNodo.mostraNodiLeaf(idNodoPadre);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formModificaNodo")){
			String content = HtmlNodo.formModificaNodo(Integer.parseInt(request.getParameter("idNodo")));
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("modificaNodo")){
			Nodo nd = CorsoController.modificaNodo(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlNodo.mostraNodo(nd.getIdNodo(), part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("eliminaNodo")){
			CorsoController.eliminaNodo(request);
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlUDA.mostraUDA(Integer.parseInt(request.getParameter("idUDA")), part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("inserisciPost")){
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			Azione post = ContributoController.nuovaAzione(request,part.getIDPartecipante());
			String content = HtmlContributo.mostraPost(post.getIDUDA(),post.getIDNodo(),part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("eliminaAzione")){
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			Azione post = ContributoController.eliminaAzione(request, part);
			String content="";
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			if(idNodo==0) content = HtmlUDA.mostraUDA(idUDA, part);
			else content = HtmlNodo.mostraNodo(post.getIDNodo(), part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formModificaAzione")){
			
			int idPost = Integer.parseInt(request.getParameter("idPost"));
			String content = HtmlContributo.formModificaPost(idPost);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("modificaAzione")){
			
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			Azione post = ContributoController.modificaAzione(request, part);
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content="";
			if(idNodo==0) content = HtmlUDA.mostraUDA(idUDA, part);
			else content = HtmlNodo.mostraNodo(idNodo, part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("inserisciReazione")){
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			ContributoController.nuovaReazione(request,part.getIDPartecipante());
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content = "";
			if(idNodo!=0) content = HtmlNodo.mostraNodo(idNodo,part);
			else content = HtmlUDA.mostraUDA(idUDA, part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("eliminaReazione")){
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			ContributoController.eliminaReazione(request, part);
			String content="";
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			if(idNodo==0) content = HtmlUDA.mostraUDA(idUDA, part);
			else content = HtmlNodo.mostraNodo(idNodo, part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formModificaReazione")){
			Azione post = DatabaseController.selectPost(Integer.parseInt(request.getParameter("idPost")));
			int idCommento = Integer.parseInt(request.getParameter("idCommento"));
			String content = HtmlContributo.formModificaCommento(post, idCommento);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("modificaReazione")){
			
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			Reazione commento = ContributoController.modificaReazione(request, part);
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content="";
			if(idNodo==0) content = HtmlUDA.mostraUDA(idUDA, part);
			else content = HtmlNodo.mostraNodo(idNodo, part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formValutazione")){
			int idCommento = Integer.parseInt(request.getParameter("idCommento"));
			String content = HtmlContributo.forminputValutazione(idCommento);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("inserisciValutazione")){
			Valutazione valutazione = ContributoController.nuovaValutazione(request);
			
		}
		else if(operazione.equals("login")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Partecipante partecipante = DatabaseController.login(username, password);
			session.setAttribute("partecipante", partecipante);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("inserisciUtente")){
			Partecipante partecipante = PartecipanteController.nuovoPartecipante(request);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formInserisciUtente")){
			String content = HtmlPartecipante.formInsertUtente();
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("logout")){
			response.setHeader("Cache-Control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/corso.jsp");
		}
		else if(operazione.equals("mostraProfilo")){
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlPartecipante.mostraProfilo(part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
			
		}
	}
	
	
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String page)  throws ServletException, IOException {
    ServletContext sc = getServletContext();
    RequestDispatcher rd = sc.getRequestDispatcher(page);
    rd.forward(request,response);
}

}
