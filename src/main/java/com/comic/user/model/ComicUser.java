package com.comic.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="comic_user", catalog = "comicuser")
public class ComicUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn
	private ComicBook comicbook;
	
	@Id
	@ManyToOne
	@JoinColumn
	private User user;
	
	@NotNull
	@Column(name="date")
	private Date date;

	public ComicUser() {
	}

	public ComicUser(User user, Date date) {
		this.user = user;
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comicbook.getTitle(), user.getName(), date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 	return true;
		if (!(obj instanceof ComicUser)) return false;
		ComicUser other = (ComicUser) obj;
		return Objects.equals(comicbook.getTitle(), other.comicbook.getTitle()) &&
				Objects.equals(user.getName(), other.user.getName()) &&
				Objects.equals(date, other.date);
	}
		

	public ComicBook getComicbook() {
		return comicbook;
	}

	public void setComicbook(ComicBook comicbook) {
		this.comicbook = comicbook;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
