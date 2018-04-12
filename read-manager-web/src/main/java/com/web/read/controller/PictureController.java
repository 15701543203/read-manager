package com.web.read.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.read.common.bean.PictureResult;
import com.web.read.service.PictureService;

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	
	@RequestMapping(value="/pic/upload",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile){
		PictureResult result = pictureService.uploadPicture(uploadFile);
		return result;
	}
	
}
