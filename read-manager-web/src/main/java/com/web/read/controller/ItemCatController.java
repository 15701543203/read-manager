package com.web.read.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.common.bean.TreeNode;
import com.web.read.service.ItemCatService;

/**
 * 
 * 后台分类列表控制器
 * @Author Administrator
 * @Date 2018年3月16日
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCarService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getItemCatList(@RequestParam(defaultValue="0",value="id") long parentId){
		List<TreeNode> treeNodeList = itemCarService.getItemCatList(parentId);
		return treeNodeList;
	}
}
