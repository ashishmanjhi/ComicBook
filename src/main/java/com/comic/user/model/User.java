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

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(name="name")
	private String name;

	@NotNull
	@Column(name="age")
	private int age;

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

	public User() {
	}

	public User(@NotNull String name, @NotNull int age, @NotNull String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public Set<ComicBook> getComicBook() {
		return comicBooks;
	}

	public void setComicBook(Set<ComicBook> comicBooks) {
		this.comicBooks = comicBooks;
	}	
	
}
