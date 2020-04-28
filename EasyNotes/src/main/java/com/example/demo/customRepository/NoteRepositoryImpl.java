package com.example.demo.customRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Note;

@Repository
public class NoteRepositoryImpl implements NoteRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Note> getNamesLike(String Name) {
	
		TypedQuery<Note> query=entityManager.createQuery("SELECT n FROM Note n WHERE n.content LIKE :contentName", Note.class);
			query.setParameter("contentName","%"+ Name+ "%");
		return query.getResultList();
	}

}
