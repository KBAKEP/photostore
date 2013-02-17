package by.cources.photostore.model;


import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;	
	 
/*	@NotEmpty(message="{validation.login.NotEmpty.message}") 
	@Size(min=3, max=60, message="{validation.login.Size.message}") */
	@Column
	private String login;
	
/*	@Email(message="Incorrect Email")*/
	@Column
	private String email;
	
/*	@NotEmpty(message="{validation.password.NotEmpty.message}") 
	@Size(min=3, max=60, message="{validation.password.Size.message}") */
	@Column
	private String password;
	/*
	@OneToMany(mappedBy = "user")
	private List<Album> albums = new ArrayList<Album>();
	*/
	
	@Transient
	private String confirmPassword;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")	
	private Role grantedAuthority;
		
	@OneToMany(mappedBy = "user")
	private List<Picture> pictures = new ArrayList<Picture>();
	
	
	public User() {
	}

	public User(String login, 
			String email, String password) {
		super();
		this.login = login;
		
		this.email = email;
		this.password = password;
	}
	
	/*
	public void addAlbum(Album albumToAdd) {
		albumToAdd.setUser(this);
		getAlbums().add(albumToAdd);
	}
*/
	
	public void addPicture(Picture pictureToAdd) {
		pictureToAdd.setUser(this);
		getPictures().add(pictureToAdd);
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(Role grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}
	
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((pictures == null) ? 0 : pictures.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pictures == null) {
			if (other.pictures != null)
				return false;
		} else if (!pictures.equals(other.pictures))
			return false;
		return true;
	}

	
/*
 * needs for UserService interface
 * 
	public String getUsername() {
		
		return login;
	}
*/
	
	/*
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	*/
	
	
	
}
