package com.web.read.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.read.bean.TbItem;
import com.web.read.bean.TbItemDesc;
import com.web.read.bean.TbItemExample;
import com.web.read.bean.TbItemExample.Criteria;
import com.web.read.bean.TbItemParamItem;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.ExceptionUtil;
import com.web.read.common.utils.IDUtils;
import com.web.read.mapper.TbItemDescMapper;
import com.web.read.mapper.TbItemMapper;
import com.web.read.mapper.TbItemParamItemMapper;
import com.web.read.service.ItemService;

/**
 * 商品管理service
 * @Author Administrator
 * @Date 2018年3月15日
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria eriteria = example.createCriteria();
		eriteria.andIdEqualTo(itemId);
		List<TbItem>list = itemMapper.selectByExample(example);
		if (list!=null&&list.size()>0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public EUDataGridResult getPaginatedItems(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> items = itemMapper.selectByExample(example);
		PageInfo<TbItem> info = new PageInfo<>(items);
		long total = info.getTotal();
		
		EUDataGridResult result = new EUDataGridResult();
		result.setTotal(total);
		result.setRows(items);
		return result;
	}

	@Override
	public ReadResult addItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) {
		try {
			// 生成商品id
			// 可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
			Long itemId = IDUtils.genItemId();
			// 补全不完整的字段
			item.setId(itemId);
			item.setStatus((byte) 1);
			Date date = new Date();
			item.setCreated(date);
			item.setUpdated(date);
			// 把数据插入到商品表
			itemMapper.insert(item);
			// 添加商品描述
			itemDesc.setItemId(itemId);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			// 把数据插入到商品描述表
			itemDescMapper.insert(itemDesc);
			
			itemParamItem.setCreated(date);
			itemParamItem.setUpdated(date);
			itemParamItem.setItemId(itemId);
//			itemParamItem.setId(id);
			//保存商品的规格参数信息
			this.itemParamItemMapper.insert(itemParamItem);
		} catch (Exception e) {
			e.printStackTrace();
			return ReadResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return ReadResult.ok();
	}

//	@Override
//	public ReadResult addItem(TbItem item, TbItemDesc itemDesc) {
//		try {
//			//生成商品id
//			Long itemId = IDUtils.genItemId();
//			
//			item.setId(itemId);
//			item.setStatus((byte)1);
//			Date date = new Date();
//			item.setCreated(date);
//			item.setUpdated(date);
//			
//			//添加到数据库，商品表中
//			itemMapper.insert(item);
//			
//			//添加商品描述
//			itemDesc.setItemId(itemId);
//			itemDesc.setCreated(date);
//			itemDesc.setUpdated(date);
//			
//			//添加到商品描述表
//			itemDescMapper.insert(itemDesc);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ReadResult.build(500, ExceptionUtil.getStackTrace(e));
//		}
//		return ReadResult.ok();
//	}

	


}
