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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Ashish.manjhi
 *
 *This {@link User} class represents detail of the User.
 */
@Entity
@Table(name="user")
public class User {

	// User Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// User Name
	@NotNull
	@Column(name="name")
	private String name;

	// User Age
	@NotNull
	@Column(name="age")
	private int age;

	// User Sex
	@NotNull
	@Column(name="sex")
	private String sex;


	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
	})
	@JoinTable(name = "comic_user",
	joinColumns = { @JoinColumn(name = "user_id") },
	inverseJoinColumns = { @JoinColumn(name = "comic_id") })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<ComicBook> comicBooks = new HashSet<>();

	// ----------------
    // - CONSTRUCTORS -
    // ----------------

	/**
	 * Default Constructor for User.
	 */
	public User() {
	}

	/**
	 * Parameterized Constructor for User.
	 * 
	 * @param name
	 * @param age
	 * @param sex
	 */
	public User(@NotNull String name, @NotNull int age, @NotNull String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	// -----------
	// - METHODS -
	// -----------
	
	/**
	 * @return User Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param User Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return User Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param User Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return User Age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param User Age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return User Sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param User Sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	
	/**
	 * @return User Comic Books
	 */
	public Set<ComicBook> getComicBook() {
		return comicBooks;
	}

	/**
	 * @param User ComicBooks
	 */
	public void setComicBook(Set<ComicBook> comicBooks) {
		this.comicBooks = comicBooks;
	}	
	
}
