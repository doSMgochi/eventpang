package com.callor.eventpang.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "home";
	}
	@RequestMapping(value = "/upload-image", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestParam("imageFile") MultipartFile imageFile,
	        HttpServletRequest request) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        if (!imageFile.isEmpty()) {
	            String uploadDir = request.getServletContext().getRealPath("/uploaded/");
	            File uploadDirFile = new File(uploadDir);

	            if (!uploadDirFile.exists()) {
	                uploadDirFile.mkdirs();
	            }

	            String filename = imageFile.getOriginalFilename();
	            File uploadedFile = new File(uploadDir + File.separator + filename);
	            imageFile.transferTo(uploadedFile);

	            String imageUrl = request.getContextPath() + "/uploaded/" + filename;
	            result.put("success", true);
	            result.put("imageUrl", imageUrl);
	        } else {
	            result.put("success", false);
	            result.put("message", "No file selected");
	        }
	    } catch (IOException e) {
	        result.put("success", false);
	        result.put("message", "File upload failed: " + e.getMessage());
	    }
	    return result;
	}
	

}
