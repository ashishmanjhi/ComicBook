package com.comic.user.model;

import java.util.HashSet;
import java.util.Set;
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
@Table(name = "user", catalog = "comicuser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserId")
	private long userId;

	@NotNull
	@Column(name="name")
	private String name;

	@NotNull
	@Column(name="age")
	private int age;

	@NotNull
	@Column(name="sex")
	private String sex;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<ComicUser> comicUsers = new HashSet<ComicUser>();
	
	

	public User() {
	}

public User(@NotNull String name, @NotNull int age, @NotNull String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
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

	public Set<ComicUser> getComicUsers() {
		return comicUsers;
	}

	public void setComicUsers(Set<ComicUser> comicUsers) {
		this.comicUsers = comicUsers;
	}
	
}
