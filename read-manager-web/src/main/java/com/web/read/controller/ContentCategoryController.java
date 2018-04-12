package com.web.read.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.bean.TreeNode;
import com.web.read.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 获取内容分类管理
	 * Description: 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public List<TreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0")long parentId){
		
		List<TreeNode> nodes= contentCategoryService.getCategoryList(parentId);
		return nodes;
	}
	
	/**
	 * 添加内容分类管理
	 * Description: 
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public ReadResult createContetnCategory(long parentId,String name){
		ReadResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	/**
	 * 删除指定分类
	 * Description: 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ReadResult deleteContetnCategory(long id){
		ReadResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
	
	
}
