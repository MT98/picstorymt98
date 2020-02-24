package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Utilisateur;
import environnement.Constantes;
import form.AjoutUtilisateurForm;
import form.ModifyUtilisateurForm;
import service.ServiceException;
import service.UtilisateurService;

/**
 * Servlet implementation class UtilisateurController
 */
@WebServlet("/user/*")
public class UtilisateurController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String	VUE_AJOUT_UTILISATEUR = "/WEB-INF/addUser.jsp";
	private static final String	VUE_PROFIL_UTILISATEUR = "/WEB-INF/profilUser.jsp";
	private static final String	VUE_VIEW_UTILISATEUR = "/WEB-INF/viewUser.jsp";
	private static final String	VUE_UPDATE_UTILISATEUR	= "/WEB-INF/modifyUser.jsp";
	private static final String	VUE_LIST_UTILISATEUR	= "/WEB-INF/listUser.jsp";
       
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
		}else if (requestedUrl.endsWith("/user/view"))
		{
			String id = request.getParameter("id");

			UtilisateurService userService = new UtilisateurService();
			if(id != null && id.trim() != "") {
			
				try {
					request.setAttribute("utilisateur", userService.getUserById(Long.valueOf(id)));
					getServletContext().getRequestDispatcher(VUE_VIEW_UTILISATEUR).forward(request, response);
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Vous n\'avez renseigner un identifiant correct!");
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Oups une petite erreur, veuillez contacter l'administrateur du site!");
				}	
				
				
			}else{
			  
			  response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Vous n\'avez renseigner aucun login!");
			}
		  				
			
		}else if (requestedUrl.endsWith("/user/profil"))
		{
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
			
			if(utilisateur != null) {
				request.setAttribute("utilisateur", utilisateur);				
				getServletContext().getRequestDispatcher(VUE_PROFIL_UTILISATEUR).forward(request, response);
			}else {
			response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage='Oups une petite erreur, veuillez contacter l'administrateur du site!'");
			}
		}
		else if (requestedUrl.endsWith("/user/list"))
		{
			try {
				UtilisateurService userService = new UtilisateurService();
				List<Utilisateur> users = userService.getUsers();
				request.setAttribute("utilisateurs", users);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR)
					.forward(request, response);
		}else if (requestedUrl.endsWith("/user/modify"))
		{
			String id = request.getParameter("id");
			UtilisateurService userService = new UtilisateurService();
			if(id != null && id.trim() != "") {
			
				try {
					Utilisateur user = userService.getUserById(Long.valueOf(id));
					if(user != null)
					{
						request.setAttribute("utilisateur", user);
						getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR).forward(request, response);
					}else
					{
						response.sendRedirect(request.getContextPath() + "/user/list" + "?message=Un utilisateur avec un tel identifiant n'existe pas!");
					}
					
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Vous n\'avez renseigner un identifiant correct!");
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Oups une petite erreur, veuillez contacter l'administrateur du site!");
				}	
				
				
			}else{
			  
			  response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Vous n\'avez renseigner aucun login!");
			}
		  		
		
		}else if (requestedUrl.endsWith("user/delete"))
		{
			String id = request.getParameter("id");
			if(id != null && id.trim() != "")
			{
				try {
					UtilisateurService userService = new UtilisateurService();
					Utilisateur user = userService.getUserById(Long.valueOf(id));
					/* la supression doit s'etendre sur les albums de l'utilisateur en cascade */
					user = userService.deleteUser(user);
					if(user != null)
					{
						response.sendRedirect(request.getContextPath() + "/user/list" + "?message=L\'utilisateur "+user.getEmail()+" a ete supprime avec success!&statut=succes");
					}else
					{
						response.sendRedirect(request.getContextPath() + "/user/list" + "?message=Il n\'existe aucun utilisateur avec l'identifiant "+id+"!&statut=erreur");
					}
					
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Vous n\'avez renseigner un identifiant correct!");
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR + "?errorMessage=Oups une petite erreur, veuillez contacter l'administrateur du site!");
				}
		
				
			}else
			{
				response.sendRedirect(request.getContextPath() + Constantes.LIEN_PAGE_ERROR  + "?errorMessage=Cet identifiant d\'utilisateur n\'existe pas!");
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath());
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
		}else if (requestedUrl.endsWith("/users/modify"))
		{
			ModifyUtilisateurForm form = new ModifyUtilisateurForm(request);
			Utilisateur utilisateur = form.getUtilisateur();
			Utilisateur user = null;
			if (form.isValid())
			{
				UtilisateurService userService = new UtilisateurService();
				try {
					 user = userService.getUserById(Long.valueOf(request.getParameter("id")));
					user.setEmail(utilisateur.getEmail());
					user.setFirstname(utilisateur.getFirstname());
					user.setLastname(utilisateur.getLastname());
					user.setPassword(utilisateur.getPassword());
					user.setIsAdmin(utilisateur.getIsAdmin());
					
					userService.updateUser(user);
					
					form.setStatusMessage("Modification effectuée avec succés!");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					form.getMessageErreurs().put("ALL-VALIDATION", "false");
					form.setStatusMessage("Erreur de persistence des données!");
					System.out.println(e.getMessage());
					e.printStackTrace();
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

			getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR)
					.forward(request, response);
		}
	}

}
