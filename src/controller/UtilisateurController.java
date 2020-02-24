package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Utilisateur;
import form.AjoutUtilisateurForm;
import service.ServiceException;
import service.UtilisateurService;

/**
 * Servlet implementation class UtilisateurController
 */
@WebServlet("/user/*")
public class UtilisateurController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String	VUE_AJOUT_UTILISATEUR = "/WEB-INF/addUser.jsp";
	private static final String				VUE_UPDATE_UTILISATEUR	= "/WEB-INF/modifierUtilisateur.jsp";
	private static final String				VUE_LIST_USER	= "/WEB-INF/listUsers.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestedUrl = request.getRequestURI();
		if (requestedUrl.endsWith("/user/add"))
		{
			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Entry");
		String requestedUrl = request.getRequestURI();
		UtilisateurService service = new UtilisateurService();
		if (requestedUrl.endsWith("/user/add"))
		{
			AjoutUtilisateurForm form = new AjoutUtilisateurForm(request);
			Utilisateur user = form.getUtilisateur();

			if (form.isValid())
			{
				try {
					service.createUser(user);
					
					form.setStatusMessage("Ajout effectué avec succés!");
			
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					form.getMessageErreurs().put("ALL-VALIDATION", "false");
					form.setStatusMessage("Erreur de persistence des données!");
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}

			request.setAttribute("utilisateur", user);
			request.setAttribute("messageErreurs", form.getMessageErreurs());
			request.setAttribute("statusMessage", form.getStatusMessage());

			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
					.forward(request, response);
		}
	}

}
