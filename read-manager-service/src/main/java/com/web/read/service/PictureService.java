package com.web.read.service;

import org.springframework.web.multipart.MultipartFile;

import com.web.read.common.bean.PictureResult;

public interface PictureService {

	/**
	 * 处理文件上传
	 * Description: 通过ftp上传 
	 * @param uploadFile
	 * @return
	 */
	PictureResult uploadPicture(MultipartFile uploadFile);
}
