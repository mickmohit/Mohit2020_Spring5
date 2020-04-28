package com.example.demo.customRepository;

import java.util.List;

import com.example.demo.model.Note;

public interface NoteRepositoryCustom {

	List<Note> getNamesLike(String Name);
	
}
