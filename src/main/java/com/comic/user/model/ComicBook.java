package com.comic.user.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Ashish.manjhi
 * 
 * This {@link ComicBook} class represents detail of the ComicBook.
 *
 */
@Entity
@Table(name="comicbook")
public class ComicBook {

	// ComicBook Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// ComicBook Title
	@NotNull
	@Column(name="title")
	private String title;

	// ComicBook Writer
	@NotNull
	@Column(name="writer")
	private String writer;

	// ComicBook Publisher
	@NotNull
	@Column(name="publisher")
	private String publisher;

	// ComicBook Genre
	@NotNull
	@Column(name="genre")
	private String genre;

	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
	},
			mappedBy = "comicBooks")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<User> users = new HashSet<>();

	// ----------------
    // - CONSTRUCTORS -
    // ----------------

	/**
	 * Default Constructor for Comic Book.
	 */
	public ComicBook() {

	}

	/**
	 * Parameterized Constructor for Comic Book.
	 * 
	 * @param title
	 * @param writer
	 * @param publisher
	 * @param genre
	 */
	public ComicBook(@NotNull String title, @NotNull String writer, @NotNull String publisher, @NotNull String genre) {
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.genre = genre;
	}


	// -----------
	// - METHODS -
	// -----------
	/**
	 * @return Comic Book ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param Comic Book ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return Comic Book Title
	 */
	public String getTitle() { 
		return title;
	}

	/**
	 * @param Comic Book Title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Comic Book Writer
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * @param Comic Book Writer
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}

	/**
	 * @return Comic Book Publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param Comic Book Publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return Comic Book Genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param Comic Book Genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return Comic Book Users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param Comic Book Users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}


}
