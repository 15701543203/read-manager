package com.web.read.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.bean.TbContent;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;
import com.web.read.service.ContentService;

/**
 * 内容管理控制器
 * @Title   ContentController.java
 * <p>Description:</p>
 * <p>Company: </p>
 * @Package com.web.read.controller
 * @Author  Administrator
 * @Date    2018年3月22日下午5:26:02
 * @version v1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * 查看每个内容分类当中都包含是内容
	 * Description: 
	 * @param page
	 * @param rows
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/query/list", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public EUDataGridResult getContents(@RequestParam(value="page",defaultValue="30")int page,@RequestParam(value="rows",defaultValue="1")int rows ,long categoryId){
		
		EUDataGridResult result = contentService.contentList(page, rows, categoryId);
		System.out.println("read-manager-web/ContentController"+result);
		
		return result;
	}
	
	/**
	 * 添加内容管理内容
	 * Description: 
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/save" ,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public ReadResult addContent(TbContent content){
		ReadResult result = contentService.insertContent(content);
		return result;
	}
	
}
