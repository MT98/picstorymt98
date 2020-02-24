package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name="Album.findAllOwned",
                query="SELECT a FROM Album a WHERE a.proprietaire=:owner"),
    @NamedQuery(name="Album.findAlbumSharedWith",
				query="SELECT a FROM Album a WHERE :sharedWith MEMBER OF a.partageAvec"),
})
public class Album implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String title;
	
	private String description;
	
	@NotNull
	@ManyToOne
	private Utilisateur proprietaire;
	
	@OneToMany(mappedBy="album", cascade=CascadeType.ALL)
	private List<Photo> photos;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Utilisateur> partageAvec;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public void setOwner(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<Utilisateur> getPartageAvec() {
		return partageAvec;
	}

	public void setPartageAvec(List<Utilisateur> partageAvec) {
		this.partageAvec = partageAvec;
	}
	
	protected Album() {
	}
	
	public Album(Utilisateur proprietaire) {
		this.proprietaire=proprietaire;
	}

}
