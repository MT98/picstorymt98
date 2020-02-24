package service;

import java.util.List;


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import entities.Utilisateur;


public class UtilisateurService {
	

	@PersistenceContext(unitName = "picstories_PU")
	private EntityManager em ;
	
	public Utilisateur createUser(Utilisateur utilisateur) throws ServiceException
	{
		try
		{
			this.em.persist(utilisateur);
			return utilisateur;
		}catch(Exception ex)
		{
			String message = ex.getMessage();
			throw new ServiceException("Echec de persistence!");
		}
		
	}
	
	public Utilisateur updateUser(Utilisateur utilisateur)	throws ServiceException
	{
		this.em.merge(utilisateur);
		return utilisateur;
	}
	
	public Utilisateur deleteUser(Utilisateur utilisateur ) throws ServiceException
	{
		this.em.remove(utilisateur);
		return utilisateur;
	}
	
	public List<Utilisateur> getUsers() throws ServiceException
	{
		Query query= this.em.createQuery("FROM Utilisateur");
		List<Utilisateur> listUsers= query.getResultList();
		return listUsers;
	}
	
	public Utilisateur getUserById(Long id) throws ServiceException
	{
		try {
			Utilisateur utilisateur = this.em.find(Utilisateur.class, id);
			return utilisateur;
		}catch(Exception ex)
		{
			throw new ServiceException("Echec de récupèration de l'utilisateur à travers son id!");
		}
		
	}
	
	public Utilisateur getUserByEmail(String email) throws ServiceException
	{
		try {
			String query = "SELECT u FROM Utilisateur u WHERE u.email LIKE :email";
			TypedQuery<Utilisateur> tq = this.em.createQuery(query, Utilisateur.class);
			tq.setParameter("email", email);
	
			Utilisateur utilisateur = null;
			
			utilisateur = tq.getSingleResult();
			
			return utilisateur;
			
		}catch(Exception ex)
		{
			throw new ServiceException("Echec de récupèration de l'utilisateur à travers son email!");
		}
		
	}
	
	public Utilisateur login(String email, String password) throws ServiceException {
		Query query = this.em.createNamedQuery("Utilisateur.login");
		query.setParameter("email", email);
		query.setParameter("password", password);
		try {
			return (Utilisateur) query.getSingleResult();
		}
		catch (NoResultException e) {
			throw new ServiceException("Utilisateur Inconnu",e);
		}
	}
	
}
