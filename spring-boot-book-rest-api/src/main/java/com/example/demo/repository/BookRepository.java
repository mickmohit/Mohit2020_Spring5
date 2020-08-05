package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	@Query("From Book b WHERE b.title=:searchText OR b.author=:searchText OR b.language=:searchText OR b.genre=:searchText ORDER by b.price DESC ")
	Page<Book> findAllBooks(Pageable pageable, @Param("searchText") String searchText);

}
