package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	
	@Query(value="select * from tags p JOIN posts t on p.id= 1",nativeQuery = true)//native query
	public List<Tag> findByTid(@Param("id") Long id);
	  
	 
	public List<Tag> findByPosts_Id(Long id);//through spring jpa property expression read
			// here method name would be x.post.id x=Tag here and also method should include posts not post.
		//it works for postid
	
	@Query("select p from Tag p JOIN p.posts t WHERE p.id= :id")//it is JPQL
	public List<Tag> findByTagi(@Param("id") Long id); // here second joiner condition on post should follow  
												//p of Tag as p.posts post is the name of entity/db table name

}
