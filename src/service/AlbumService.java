package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Album;
import entities.Utilisateur;


public class AlbumService {
	
	@PersistenceContext
	private EntityManager em ;
	
	public Album createAlbum(Album album) throws ServiceException
	{
		this.em.persist(album);
		return album;
	}
	
	public Album updateAlbum(Album album) throws ServiceException
	{
		this.em.merge(album);
		return album;
	}
	
	public Album deleteAlbumById(Long id ) throws ServiceException
	{
		Album album = this.em.find(Album.class, id);
		this.em.remove(album);
		return album;
	}
	
	public List<Album> getAlbums()
	{
		Query query= this.em.createQuery("FROM Album");
		List<Album> listAlbums= query.getResultList();
		return listAlbums;
	}
	
	public Album getAlbumById(Long id) throws ServiceException
	{
		Album album = this.em.find(Album.class, id);
		return album;
	}
	
	public void partager(String albumId, String userId) throws ServiceException
	{
		Utilisateur utilisateur= this.em.find(Utilisateur.class, userId);
		Album album = this.em.find(Album.class, albumId);
		album.getPartageAvec().add(utilisateur);
		this.em.merge(album);
	}
	
	public List<Album> listAlbumOwnedBy(Utilisateur utilisateur)
	{
		Query query = this.em.createNamedQuery("Album.findAllOwned");
		query.setParameter("owner", this.em.merge(utilisateur));
		return query.getResultList();
	}
	
	public List<Album> listAlbumSharedWith(Utilisateur utilisateur) {
		Query query = this.em.createNamedQuery("Album.findAlbumSharedWith");
		query.setParameter("sharedWith", this.em.merge(utilisateur));
		return query.getResultList();
	}

}
