package com.example.demo.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.config.ServerMessageConfig;
import com.example.demo.model.Category;
import com.example.demo.model.Video;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.VideoRepository;
import com.example.demo.utils.ConstantUtils;

@Service
@Transactional(readOnly = true)
public class VideoServiceImpl implements VideoService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private ServerMessageConfig messageConfig;
	
	@Override
	public List<Video> list() {
		return (List<Video>) videoRepository.findAll();
	}

	@Override
	public Page<Video> findAll(Pageable pageable) {
		return videoRepository.findAll(PageRequest.of(pageable.getPageNumber() - 1, ConstantUtils.PAGINATION_SIZE));
	}

	@Override
	public Page<Video> findAll(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video findOne(Long id) {
		return videoRepository.findById(id).get();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String add(Video video) {
		String message = null;
		JSONObject jsonObject = new JSONObject();
		try {
			if(video.getId() == null) {
				message = messageConfig.getMessage("label.added");
			} else {
				message = messageConfig.getMessage("label.updated");
			}
			video.setDate(new Date());
			jsonObject.put("status", "success");
			String[] msg = {message};
			
			jsonObject.put("title", messageConfig.getMessage("label.confirmation", msg));
			video.setYouTubeURL(video.getYouTubeURL().replace("watch?v=", "embed/"));
			
			Set<Category> categorySet = new HashSet<Category>();
			Long[] categories = video.getSelectedCategories();
			for (Long categoryId : categories) {
				categorySet.add(categoryRepository.findById(categoryId).get());
			}
			video.setCategories(categorySet);
			
			String[] msg2 = {videoRepository.save(video).getTitle(), message};
			jsonObject.put("message", messageConfig.getMessage("video.save.success", msg2));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String delete(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			videoRepository.deleteById(id);
			jsonObject.put("message", messageConfig.getMessage("video.delete.success"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
