package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

}