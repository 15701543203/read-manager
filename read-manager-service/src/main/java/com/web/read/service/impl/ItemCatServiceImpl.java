package com.web.read.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.read.bean.TbItemCat;
import com.web.read.bean.TbItemCatExample;
import com.web.read.bean.TbItemCatExample.Criteria;
import com.web.read.common.bean.TreeNode;
import com.web.read.mapper.TbItemCatMapper;
import com.web.read.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemMapper;

	@Override
	public List<TreeNode> getItemCatList(Long parentId) {
		// 根据parentID查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> catList = itemMapper.selectByExample(example);
		// 将分类列表转换成TreeNode列表
		List<TreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemcat : catList) {
			//节点id 节点名称  是父文件夹关闭,子文件打开
			TreeNode treeNode = new TreeNode(tbItemcat.getId(), tbItemcat.getName(), tbItemcat.getIsParent()?"closed":"open");
			resultList.add(treeNode);
		}

		return resultList;
	}

}
