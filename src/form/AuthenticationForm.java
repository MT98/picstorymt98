package form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entities.Utilisateur;
import service.ServiceException;
import service.UtilisateurService;


public class AuthenticationForm
{
	private static final String	CHAMP_LOGIN		= "login";
	private static final String	CHAMP_PASSWORD	= "password";
	private HttpServletRequest	request;
	private String				login;

	public AuthenticationForm(HttpServletRequest request)
	{
		this.request = request;
	}

	public Utilisateur connect()
	{
		login = getParamater(CHAMP_LOGIN);
		String password = getParamater(CHAMP_PASSWORD);
		
		/*NB: Un super admin doit exister dans la base de données */
		
		/*Validation formulaire facultative à faire ici */
		
		UtilisateurService service = new UtilisateurService();
		Utilisateur user;
		try {
			user = service.getUserByEmail(login);
			if(user == null)
			{
				return null;
			}else
			{
				if(user.getPassword().equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("utilisateur", user);
					return user;
				}else
				{
					return null;
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	private String getParamater(String parametre)
	{
		String valeur = request.getParameter(parametre);
		valeur = valeur == null || valeur.trim().isEmpty() ? null
				: valeur.trim();
		return valeur;
	}

	public String getLogin()
	{
		return login;
	}
}
