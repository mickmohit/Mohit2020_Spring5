package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleRead;
import com.example.demo.entity.Sresponsibiltiy;
import com.example.demo.entity.TimeToRead;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.ArticleRepository;
import com.example.demo.util.MethodUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

	@Autowired
    private ArticleRepository articleRepository;
	@Autowired
	Sresponsibiltiy sresponsibiltiy;
	
	@Value("${humanread.speed}")
	private int humanreadspeed;

    @GetMapping("/Articles")
    public List < Article > getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/article/{id}")
    public ResponseEntity < Article > getArticleById(@PathVariable(value = "id") int articleId)
    throws ResourceNotFoundException {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + articleId));
        return ResponseEntity.ok().body(article);
    }

    @PostMapping("/article")
    public Article createArticle(@Valid @RequestBody Article Article) {
    	String slug = Article.getTitle().toLowerCase().replace(' ', '-');
    	Article.setSlug(slug);
        return articleRepository.save(Article);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity < Article > updateArticle(@PathVariable(value = "id") int articleId,
        @Valid @RequestBody Article articleDetails) throws ResourceNotFoundException {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + articleId));

        article.setBody(articleDetails.getBody());
        article.setSlug(articleDetails.getTitle().toLowerCase().replace(' ', '-'));
        article.setTitle(articleDetails.getTitle());
        article.setDescription(articleDetails.getDescription());
        final Article updatedArticle = articleRepository.save(article);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/article/{id}")
    public Map< String, Boolean > deleteArticle(@PathVariable(value = "id") int articleId)
    throws ResourceNotFoundException {
        Article Article = articleRepository.findById(articleId)
            .orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + articleId));

        articleRepository.delete(Article);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    @GetMapping("/articleread/{id}")
    public ResponseEntity < ArticleRead > readArticleById(@PathVariable(value = "id") int articleId)
    throws ResourceNotFoundException {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + articleId));
        
        //ArticleRead articleRead=null;
        Sresponsibiltiy sresponsibiltiy=null;
        ArticleRead articleRead=sresponsibiltiy.calculateSpeed(article,humanreadspeed);
           return ResponseEntity.ok().body(articleRead);
    }

	/*
	 * private ArticleRead calculateSpeed(int articleId, float readTime) { int mins
	 * = (int) readTime; int seconds = (int) (60 * (readTime - mins));
	 * 
	 * //creating response ArticleRead articleRead = new ArticleRead(); TimeToRead
	 * timeToRead =new TimeToRead(); timeToRead.setMins(mins);
	 * timeToRead.setSeconds(seconds);
	 * articleRead.setArticleId("slug-"+Integer.toString(articleId));
	 * articleRead.setTimeread(timeToRead); return articleRead; }
	 */
	
}
