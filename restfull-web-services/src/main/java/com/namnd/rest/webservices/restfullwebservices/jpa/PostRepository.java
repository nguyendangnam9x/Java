package com.namnd.rest.webservices.restfullwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namnd.rest.webservices.restfullwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
