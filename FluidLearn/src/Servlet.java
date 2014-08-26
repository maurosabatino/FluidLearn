

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

import contributo.Azione;
import contributo.Reazione;
import controller.ContributoController;
import controller.CorsoController;
import controller.DatabaseController;
import corso.*;
import bean.HtmlContent;
import InterfacciaHtml.HtmlContributo;
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
			Corso corso = CorsoController.modificaCorso(request);
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
			String content = HtmlCorso.formInsertUDA(idCorso);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");			
		}
		else if(operazione.equals("inserisciUDA")){
			UnitaDA UDA = CorsoController.nuovaUDA(request);
			String content = HtmlCorso.mostraUDA(UDA.getIdUDA());
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("mostraUDACorso")){
			int idCorso = Integer.parseInt(request.getParameter("idCorso"));
			String content = HtmlCorso.mostraUDACorso(idCorso);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formInserisciNodo")){
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content = HtmlCorso.formInsertNodo(idUDA);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("inserisciNodo")){
			Nodo nodo = CorsoController.nuovoNodo(request);
			String content = HtmlCorso.mostraNodiUDA(nodo.getIdUDA());
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraNodiUDA")){
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content = HtmlCorso.mostraNodiUDA(idUDA);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("formInserisciNodoLeaf")){
			int idNodoPadre = Integer.parseInt(request.getParameter("idNodoPadre"));
			int idUDA = Integer.parseInt(request.getParameter("idUDA"));
			String content = HtmlCorso.formInsertNodoLeaf(idUDA,idNodoPadre);
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");	
		}
		else if(operazione.equals("inserisciNodoLeaf")){
			Nodo nodo = CorsoController.nuovoNodoLeaf(request);
			String content = HtmlCorso.mostraNodiLeaf(nodo.getIdNodo());
			HtmlContent c = new HtmlContent();
			c.setContent(content);
			request.setAttribute("HTMLc", c);
			forward(request,response,"/corso.jsp");
		}
		else if(operazione.equals("mostraNodiLeaf")){
			int idNodoPadre = Integer.parseInt(request.getParameter("idNodoPadre"));
			String content = HtmlCorso.mostraNodiLeaf(idNodoPadre);
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
		}
	}
	
	
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String page)  throws ServletException, IOException {
    ServletContext sc = getServletContext();
    RequestDispatcher rd = sc.getRequestDispatcher(page);
    rd.forward(request,response);
}

}
