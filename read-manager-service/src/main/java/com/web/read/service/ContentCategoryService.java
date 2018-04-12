package com.web.read.service;

import java.util.List;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.bean.TreeNode;

public interface ContentCategoryService {
	/**
	 * 根据父分类ID查询子分类信息
	 * Description: 子节点
	 * 
	 * @param parentId
	 * @return
	 */
	List<TreeNode> getCategoryList(long parentId);
	
	/**
	 * 内容分类管理的添加
	 * Description: 
	 * @param parentId 当前分类的父id
	 * @param name 新增分类内容的名称
	 * @return
	 */
	ReadResult insertContentCategory(long parentId,String name);
	
	/**
	 * 删除指定分类管理
	 * Description: 
	 * @param parentId 
	 * @param id
	 * @return
	 */
	ReadResult deleteContentCategory(long id);
	
	
}
