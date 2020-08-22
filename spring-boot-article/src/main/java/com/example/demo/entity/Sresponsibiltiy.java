package com.example.demo.entity;

import com.example.demo.util.MethodUtil;

public class Sresponsibiltiy {

	
	// way-1 API call 
	
	// way-2 manual
	public ArticleRead calculateSpeed(Article article, int humanreadspeed) {
		

		 int word_count= MethodUtil.wordcount(article.getBody());
	     float readTime= word_count/humanreadspeed;
	      
	         
		int mins = (int) readTime;
        int seconds = (int) (60 * (readTime - mins));
      
        //creating response
        ArticleRead articleRead = new ArticleRead();
        TimeToRead timeToRead =new TimeToRead();
        timeToRead.setMins(mins);
        timeToRead.setSeconds(seconds);
        articleRead.setArticleId("slug-"+Integer.toString(article.getId()));
        articleRead.setTimeread(timeToRead);
		return articleRead;
	}
	
}
