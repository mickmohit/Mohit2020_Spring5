package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepository.NoteRepositoryCustom;
import com.example.demo.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom{

	//custom query for JPA
	List<Note> findByTitleOrderByContentAsc(String title);
	
	//advanced JPQL
	@Query("select u from Note u where u.content = :content or u.title = :title")
	List<Note> findByContentOrTitle(@Param("content") String content,
	                                 @Param("title") String title);
	
	
	//PagingAndSortingRepository Custom from JPA
	Page<Note> findAll(Pageable pageable);
    
    Slice<Note> findByTitleOrContent(String title, String content, Pageable pageable);
}
