package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entities.Utilisateur;

public class ModifyUtilisateurForm
{
	private static final String	CHAMP_NOM			= "lastname";
	private static final String	CHAMP_PRENOM		= "firstname";
	private static final String	CHAMP_EMAIL			= "email";
	private static final String	CHAMP_PASSWORD		= "password";
	private static final String	CHAMP_PASSWORD_BIS	= "passwordBis";
	private static final String	CHAMP_ROLE	= "role";

	private HttpServletRequest	request;
	private Utilisateur			utilisateur;
	private String				statusMessage;
	private Map<String, String>	messageErreurs		= new HashMap<String, String>();

	public ModifyUtilisateurForm(HttpServletRequest request)
	{
		this.request = request;

		String nom = getParamater(CHAMP_NOM);
		String prenom = getParamater(CHAMP_PRENOM);
		String email = getParamater(CHAMP_EMAIL);
		String password = getParamater(CHAMP_PASSWORD);
		String role = getParamater(CHAMP_ROLE);
		if(role == null)
		{
			role = "off";
		}

		utilisateur = new Utilisateur(role.equalsIgnoreCase("on")?true:false, email, nom, prenom, password);

		validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_EMAIL, CHAMP_PASSWORD,
				CHAMP_PASSWORD_BIS, CHAMP_ROLE);
		validerPasswords();
		validerEmail();
		
	}

	private void validerChamps(String... champs)
	{
		for (String champ : champs)
		{
			if (getParamater(champ) == null)
			{
				messageErreurs.put(champ, "Vous devez renseigner ce champ");
			}
		}
	}

	private String getParamater(String parametre)
	{
		String valeur = request.getParameter(parametre);
		valeur = valeur == null || valeur.trim().isEmpty() ? null
				: valeur.trim();
		if(valeur == null && parametre.equals(CHAMP_ROLE))
		{
			return "off";
		}
	
		return valeur;
	}

	private void validerPasswords()
	{
		String password = getParamater(CHAMP_PASSWORD);
		String passwordBis = getParamater(CHAMP_PASSWORD_BIS);
		if (password != null && !password.equals(passwordBis))
		{
			messageErreurs.put(CHAMP_PASSWORD,
					"Les mots de passe ne sont pas conformes");
			messageErreurs.put(CHAMP_PASSWORD_BIS,
					"Les mots de passe ne sont pas conformes");
		}
	}
	
	private void validerEmail()
	{
		String email = getParamater(CHAMP_EMAIL);

		if (email != null && !email.matches("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"))
		{
			messageErreurs.put(CHAMP_EMAIL,
					"Cette adresse email n'est pas valide!");
		}
	}

	public Utilisateur getUtilisateur()
	{
		return utilisateur;
	}

	public String getStatusMessage()
	{
		return statusMessage;
	}
	public void setStatusMessage(String str)
	{
		this.statusMessage = str;
	}

	public Map<String, String> getMessageErreurs()
	{
		return messageErreurs;
	}

	public boolean isValid()
	{
		return messageErreurs.isEmpty();
	}

}
