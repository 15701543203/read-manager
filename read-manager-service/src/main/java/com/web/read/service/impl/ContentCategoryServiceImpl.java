package com.web.read.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.read.bean.TbContentCategory;
import com.web.read.bean.TbContentCategoryExample;
import com.web.read.bean.TbContentCategoryExample.Criteria;
import com.web.read.common.bean.ReadResult;
import com.web.read.common.bean.TreeNode;
import com.web.read.mapper.TbContentCategoryMapper;
import com.web.read.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCateoryMapper;

	@Override
	public List<TreeNode> getCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		// 设置查询条件，让parentId=parent_id参数的值
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> categories = contentCateoryMapper.selectByExample(example);

		if (categories != null && categories.size() > 0) {
			List<TreeNode> nodes = new ArrayList<>();
			TreeNode node = null;
			// 遍历categories将其装换为nodes集合
			for (TbContentCategory category : categories) {
				//是否是一个父节点是的话返回closed不是返回open
				node = new TreeNode(category.getId(), category.getName(), category.getIsParent() ? "closed" : "open");
				nodes.add(node);
			}
			return nodes;
		}

		return null;
	}

	@Override
	public ReadResult insertContentCategory(long parentId, String name) {
		
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//状态的可选值：1正常;2删除
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		//向表中添加此条记录
		contentCateoryMapper.insert(contentCategory);
		
		//使用父id查询一条记录并查看他的isParent是否为true,如果不是true改成true
		TbContentCategory parentCat = contentCateoryMapper.selectByPrimaryKey(parentId);
		//判断
		if(!parentCat.getIsParent()){
			parentCat.setIsParent(true);
			//并更新这个父节点
			contentCateoryMapper.updateByPrimaryKey(parentCat);
		}
		
		return ReadResult.ok(contentCategory);
	}

	@Override
	public ReadResult deleteContentCategory(long id) {
		//使用id获取category对象
		TbContentCategory category = contentCateoryMapper.selectByPrimaryKey(id);
		//获得category的父id
		long parentId = category.getParentId();
		
		//首先查询出当前父分类下有多少个子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria  = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> categoryList =contentCateoryMapper.selectByExample(example);
		
		//如果父分类下没有子节点(那它就是一个子节点)
		if(categoryList.size()==0){
			//查询出父节点信息
			TbContentCategory parent = contentCateoryMapper.selectByPrimaryKey(parentId);
			//是否为父节点，置为false
			parent.setIsParent(false);
			//更新
			contentCateoryMapper.updateByPrimaryKey(parent);
		}
		
		contentCateoryMapper.deleteByPrimaryKey(id);	
		
		return ReadResult.ok();
	}

}
