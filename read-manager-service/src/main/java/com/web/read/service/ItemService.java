package com.web.read.service;

import com.web.read.bean.TbItem;
import com.web.read.bean.TbItemDesc;
import com.web.read.bean.TbItemParam;
import com.web.read.bean.TbItemParamItem;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;

public interface ItemService {

	/**
	 * 根据项目id查询商品信息
	 * @param itemId
	 * @return
	 */
	TbItem getItemById(long itemId);    
	
	/**
	 * 
	 * @param page 页
	 * @param rows 条
	 * @return
	 */
	EUDataGridResult getPaginatedItems(int page,int rows);

	/**
	 * 保存商品信息
	 * Description:
	 * @param item 商品信息 
	 * @param itemDesc 商品描述信息详情
	 * @return
	 */
	//ReadResult addItem(TbItem item, TbItemDesc itemDesc);
	
	
	ReadResult addItem(TbItem item, TbItemDesc itemDesc ,TbItemParamItem itemParamItem);
	
	
}
