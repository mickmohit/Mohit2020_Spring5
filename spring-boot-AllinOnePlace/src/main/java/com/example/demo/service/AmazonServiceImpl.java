package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.model.User;
import com.example.demo.utils.ConstantUtils;

@Service
public class AmazonServiceImpl implements AmazonService {

	private AmazonS3 s3client;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct //spring call it only once, just after the initialization of bean properties i.e. after creating amazons3client object
	private void intiliazeAmazon()
	{
		this.s3client=new AmazonS3Client(new BasicAWSCredentials(ConstantUtils.ACCESS_KEY, ConstantUtils.SECRET_KEY));
	}
	
	@Override
	public String uploadFile(MultipartFile multipartFile, User user) {
		
		String fileUrl="";
		JSONObject jsonObject= new JSONObject();
		
		try {
			File file= convertMultipartToFile(multipartFile);
			String fileName= new Date().getTime()+"-"+multipartFile.getOriginalFilename().replace(" ", "_");
			fileUrl=ConstantUtils.END_POINT_URL+"/"+ConstantUtils.BUCKET_NAME+"/"+fileName;
			s3client.putObject(new PutObjectRequest(ConstantUtils.BUCKET_NAME, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
			
			user.setProfilePhoto(fileUrl);
			userService.addUser(user);
			
			jsonObject.put("status","success");
			jsonObject.put("imageUrl", fileUrl);
			jsonObject.put("message", "File Uploaded Successfully");
			
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		
		
		return jsonObject.toString();
	}

	private File convertMultipartToFile(MultipartFile file) throws IOException
	{
		File convertFile= new File(file.getOriginalFilename());
		FileOutputStream fos=new FileOutputStream(convertFile);
		fos.write(file.getBytes());
		fos.close();
		return convertFile;
	}
	
}
