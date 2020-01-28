package com.comic.user.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "comicbook", catalog = "comicuser")
public class ComicBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ComicId")
	private long comicId;

	@NotNull
	@Column(name="title")
	private String title;

	@NotNull
	@Column(name="writer")
	private String writer;

	@NotNull
	@Column(name="publisher")
	private String publisher;

	@NotNull
	@Column(name="genre")
	private String genre;

	@OneToMany(mappedBy = "comicbook", cascade = CascadeType.ALL)
	private Set<ComicUser> comicUsers;

	
	public ComicBook() {
	}
	
	public ComicBook(String title, String writer, String publisher, String genre, ComicUser... comicUsers) {
		this.title = title;
		this.writer=writer;
		this.publisher=publisher;
		this.genre=genre;
		for(ComicUser comicUser : comicUsers) comicUser.setComicbook(this);
		this.comicUsers = Stream.of(comicUsers).collect(Collectors.toSet());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<ComicUser> getComicUsers() {
		return comicUsers;
	}

	public void setComicUsers(Set<ComicUser> comicUsers) {
		this.comicUsers = comicUsers;
	}

}
