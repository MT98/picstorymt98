package service;

import java.util.List;


import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import entities.Utilisateur;



public class UtilisateurService {
	

	@PersistenceContext(name="picstories")
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
		Utilisateur utilisateur = this.em.find(Utilisateur.class, id);
		return utilisateur;
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
