

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CorsoController;
import controller.DatabaseController;
import corso.*;
import bean.HtmlContent;
import InterfacciaHtml.HtmlCorso;

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
		}
	}
	
	
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String operazione = request.getParameter("operazione");
		if(operazione.equals("formInserisciCorso")){
			String content = HtmlCorso.formInsertCorso();
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("inserisciCorso")){
			Corso corso = CorsoController.nuovoCorso(request);
			String content = HtmlCorso.mostraCorso(corso.getIdCorso());
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
		else if(operazione.equals("modificaCorso")){
			Corso corso = CorsoController.creaCorso(request);
			String idCorso = request.getParameter("idCorso");
			corso.setIdCorso(Integer.parseInt(idCorso));
			DatabaseController.UpdateCorso(corso);
			String content = HtmlCorso.mostraCorso(corso.getIdCorso());
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraCorso")){
			String idCorso = request.getParameter("idCorso");
			String content = HtmlCorso.mostraCorso(Integer.parseInt(idCorso));
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
			String idCorso = request.getParameter("idCorso");
			Corso corso = DatabaseController.SelectCorso(Integer.parseInt(idCorso));
			boolean succes = DatabaseController.DeleteCorso(Integer.parseInt(idCorso));
			String content;
			if(!succes) content ="<p> ho eliminato con successo il corso "+corso.getNome()+"</p>";
			else content ="<p> non ho eliminato con successo il corso "+corso.getNome()+"</p>";
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