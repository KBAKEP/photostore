package by.cources.photostore.model;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "picture")
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;	
	@Column
	private String name;
	@Column
	private String description;
/*		@Column
	private long size;
	
	@ManyToOne
	@JoinColumn(name = "album_id", referencedColumnName = "id")
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private Album album;
*/	
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private User user;
	
	@Basic(fetch=FetchType.LAZY)
	@Lob @Column(name = "photo")	
	private byte[] photo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime = new Date();
			
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
/*	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
*/	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/*	
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
*/	
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	//	result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
//		result = prime * result
//				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + Arrays.hashCode(photo);
//		result = prime * result + (int) (size ^ (size >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Picture other = (Picture) obj;
/*		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
*/
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
/*		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
*/
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
/*		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
		return false;
*/		if (!Arrays.equals(photo, other.photo))
			return false;
/*		if (size != other.size)
			return false;
*/		return true;
	}	
		
}
