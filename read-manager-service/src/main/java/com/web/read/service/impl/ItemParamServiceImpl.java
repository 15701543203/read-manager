package com.web.read.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.read.bean.TbItemParam;
import com.web.read.bean.TbItemParamExample;
import com.web.read.bean.TbItemParamExample.Criteria;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;
import com.web.read.mapper.TbItemParamMapper;
import com.web.read.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public ReadResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
//		List<TbItemParam> itemParams = itemParamMapper.selectByExample(example);
		
		List<TbItemParam> itemParams =  itemParamMapper.selectByExampleWithBLOBs(example);
		if (itemParams != null && itemParams.size() > 0) {
			return ReadResult.ok(itemParams.get(0));
		}
		return ReadResult.ok();
	}

	
	@Override
	public ReadResult insertItemParam(TbItemParam itemParam) {
		Date date = new Date();
		//补全需要的数据
		itemParam.setUpdated(date);
		itemParam.setCreated(date);
		//执行insert操作
		itemParamMapper.insert(itemParam);
		return ReadResult.ok();
	}

	@Override
	public EUDataGridResult listItemParams(int rows, int page) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> itemParams = this.itemParamMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbItemParam> info = new PageInfo<>(itemParams);
		EUDataGridResult result = new EUDataGridResult();
		result.setTotal(info.getTotal());
		result.setRows(itemParams);
		return result;
	}


}
