package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.persistence.NamedQueries;


@Entity

@NamedQueries({
	@NamedQuery(name="Photo.findAllPhotosFromAlbum",
		    query="SELECT p FROM Photo p WHERE p.album=:album"),
	@NamedQuery(name="Photo.findAllOwned",
    		query="SELECT p FROM Photo p WHERE p.album in (SELECT a FROM Album a WHERE a.proprietaire=:owner)"),
	@NamedQuery(name="Photo.findPhotoByListUri",
		    query="SELECT p FROM Photo p WHERE p.uri IN :uri"),

})
public class Photo implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Album album;
	
	@NotNull
	private String title;
	
	//@NotNull
	private String uri;
	
	@NotNull
	private byte[] data;
	
	public Photo() {
		
	}

	public Photo(Album album, @NotNull String title, String uri, @NotNull byte[] data) {
		this.album = album;
		this.title = title;
		this.uri = uri;
		this.data = data;
	}

	/*public Photo(Album album2, String string, Path filepath, String uri2) {
		// TODO Auto-generated constructor stub
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	
	

}
