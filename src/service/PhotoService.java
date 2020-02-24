package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Album;
import entities.Photo;
import entities.Utilisateur;


public class PhotoService {
	
	@PersistenceContext
	private EntityManager em ;
	
	public void create(Photo photo)  {
		Album album = photo.getAlbum();
		album.setOwner(this.em.merge(this.em.merge( album.getProprietaire())));
		photo.setAlbum(this.em.merge(this.em.merge(album)));
		this.em.persist(photo);
		
		//sparqlUpdateService.insertPicture(p);
	}
	
	public Photo getPictureById(Long id) {
		Photo photo = this.em.find(Photo.class, id);
		this.em.refresh(photo);
		
		return photo;
	}
	
	public Photo getPictureByURI(String uri) {
		Query query = this.em.createNamedQuery("Photo.findPhotoByURI");
		query.setParameter("uri", this.em.merge(uri));
		return (Photo) query.getSingleResult();
	}
	
	public List<Photo> listPhotoFromListURI(List<String> listURI) {
		Query query = this.em.createNamedQuery("Photo.findPhotoByListUri");
		query.setParameter("uri", listURI);
		return query.getResultList();
	}
	
	
	public List<Photo> listPhotoFromAlbum(Album a){
		System.out.println(a.getId());
		Query query = this.em.createNamedQuery("Photo.findAllPhotosFromAlbum");
		query.setParameter("album", this.em.merge(a));
		return query.getResultList();
	}
	
	public List<Photo> listPhotosOwnedBy(Utilisateur utilisateur)  {
		Query query = this.em.createNamedQuery("Picture.findAllOwned");
		query.setParameter("proprietaire", this.em.merge(utilisateur));
		List<Photo> results = query.getResultList();
		return results;
	}
	
	public void deletePhotoById(Long id) throws ServiceException {
		Photo photo = this.em.find(Photo.class, id);
		this.em.remove(photo);		
		// sparqlDeleteService.deletePicture(picture);
	}
	
	public void deletePhoto(Photo photo) {
		
		this.em.remove(photo);		
		// sparqlDeleteService.deletePicture(picture);
	}

}
