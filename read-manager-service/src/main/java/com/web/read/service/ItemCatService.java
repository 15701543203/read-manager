package com.web.read.service;

import java.util.List;

import com.web.read.common.bean.TreeNode;

public interface ItemCatService {

	/**
	 * 获取分类列表
	 * @param parentId
	 * @return
	 */
	List<TreeNode> getItemCatList(Long parentId);	
}
