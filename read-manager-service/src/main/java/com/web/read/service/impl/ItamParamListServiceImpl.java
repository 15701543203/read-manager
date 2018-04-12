//package com.web.read.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.web.read.bean.TbItemParam;
//import com.web.read.bean.TbItemParamExample;
//import com.web.read.common.bean.EUDataGridResult;
//import com.web.read.mapper.TbItemParamMapper;
//import com.web.read.service.ItemParamListService;
//
//@Service
//public class ItamParamListServiceImpl implements ItemParamListService {
//
//	@Autowired
//	private TbItemParamMapper itemParamMapper;
//	
//	@Override
//	public EUDataGridResult getPaginatedParamItems(int page, int rows) {
//		PageHelper.startPage(page, rows);
//		TbItemParamExample example = new TbItemParamExample();
//		List<TbItemParam> items = itemParamMapper.selectByExample(example);
//		for (TbItemParam tbItemParam : items) {
//			System.out.println(tbItemParam);
//		}
//		
//		PageInfo<TbItemParam> tbitemParamInfo = new PageInfo<>(items);
//		long total = tbitemParamInfo.getTotal();
//		
//		EUDataGridResult result = new EUDataGridResult();
//		result.setTotal(total);
//		result.setRows(items);
//		return result;
//	}
//
//}
