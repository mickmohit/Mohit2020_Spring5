package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
    NoteRepository noteRepository;
	
	@GetMapping("/notes")
	public List<Note> getAllNotes()
	{
		return noteRepository.findAll();
	}
	
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
	    return noteRepository.save(note);
	}
	
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
	    return noteRepository.findById(noteId)
	            .orElseThrow(() ->new ResourceNotFoundException("Note", "id", noteId));
	}
	
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId,
	                                        @Valid @RequestBody Note noteDetails) {

	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());

	    Note updatedNote = noteRepository.save(note);
	    return updatedNote;
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    noteRepository.delete(note);

	    return ResponseEntity.ok().build();
	}
	
	//custom repository
	 @GetMapping("/note/filter/{name}")
	    public List<Note> getContentFiltered(@PathVariable("name") String name) {
	        return noteRepository.getNamesLike(name);
	    }
	 
	 @GetMapping("/note/title/{title}")
	    public List<Note> findByTitleOrderByContentAsc(@PathVariable("title") String title) {
	        return noteRepository.findByTitleOrderByContentAsc(title);
	    }
	 
	 @GetMapping("/note/fullfilter/{columnName}")
	    public List<Note> findByContentOrTitle(@PathVariable("columnName") String columnName) {
	        return noteRepository.findByContentOrTitle(columnName, columnName);
	    }
	 
	 @GetMapping("/page")
	    public List<Note> PageQuery() {
		 Pageable pageable = PageRequest.of(0, 10);
		 Page<Note> page = noteRepository.findAll(pageable);
		 if(page.hasContent()) {
	            return page.getContent();
	        } else {
	            return new ArrayList<Note>();
	        }
	    }
	 
	 @GetMapping("/slice/{columnName}")
	    public List<Note> SliceQuery(@PathVariable("columnName") String columnName) {
		 Pageable pageable = PageRequest.of(0, 10);
		 Slice<Note> slice = noteRepository.findByTitleOrContent(columnName, columnName, pageable);
		 if(slice.hasContent()) {
	            return slice.getContent();
	        } else {
	            return new ArrayList<Note>();
	        }
	    }
	 
}

