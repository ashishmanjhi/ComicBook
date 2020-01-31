package com.comic.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comic.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {


}