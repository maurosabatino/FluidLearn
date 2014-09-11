

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
			if(part==null) part = new Utente(new PartecipanteConcreto());
			else part = DatabaseController.decorate(part, idCorso);
			session.setAttribute("partecipante", part);
			String content = HtmlCorso.mostraCorso(idCorso, part);
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
		}else if(operazione.equals("formInserisciPost")){
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			String content = HtmlContributo.formInputPost(idNodo);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("inserisciPost")){
			Azione post = ContributoController.nuovaAzione(request);
			String content = HtmlContributo.mostraPostNodo(post.getIDNodo());
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraPostNodo")){
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			String content = HtmlContributo.mostraPostNodo(idNodo);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("inserisciCommento")){
			Reazione commento = ContributoController.nuovaReazione(request);
			int idNodo = Integer.parseInt(request.getParameter("idNodo"));
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			String content = HtmlNodo.mostraNodo(idNodo,part);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("login")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Partecipante partecipante = DatabaseController.selectPartecipante(username, password);
			session.setAttribute("partecipante", partecipante);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("logout")){
			response.setHeader("Cache-Control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/Index.jsp");
		}
		else if(operazione.equals("mostraProfilo")){
			int idCorso = Integer.parseInt(request.getParameter("idCorso"));
			Partecipante part = (Partecipante)session.getAttribute("partecipante");
			if(part==null) part = new Utente(new PartecipanteConcreto());
			else part = DatabaseController.decorate(part, idCorso);
			session.setAttribute("partecipante", part);
			String content = HtmlProfilo.mostraProfilo(part);
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
