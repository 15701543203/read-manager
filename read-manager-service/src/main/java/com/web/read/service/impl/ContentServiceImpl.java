package com.web.read.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.read.bean.TbContent;
import com.web.read.bean.TbContentExample;
import com.web.read.bean.TbContentExample.Criteria;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.HttpClientUtil;
import com.web.read.mapper.TbContentMapper;
import com.web.read.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("REST_BASE_URL")
	private String REST_BASE_URL;
	
	@Value("REDIS_SYNC_URL")
	private String REDIS_SYNC_URL;
	
	@Override
	public EUDataGridResult contentList(int page, int rows, long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		//设置分页
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbContent> contents = contentMapper.selectByExample(example);
		
		PageInfo <TbContent> pageInfo = new PageInfo<TbContent>(contents);
		//封装分页数据
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(contents);
		result.setTotal(pageInfo.getTotal());
		System.out.println("readProtal--->>>"+result);
		return result;
	}

	@Override
	public ReadResult insertContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		HttpClientUtil.doGet(REST_BASE_URL+REDIS_SYNC_URL+content.getCategoryId());
		return ReadResult.ok();
	}

	@Override
	public ReadResult deleteContent(long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
