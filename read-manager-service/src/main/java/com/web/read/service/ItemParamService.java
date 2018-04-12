package com.web.read.service;

import com.web.read.bean.TbItemParam;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;

public interface ItemParamService {
	
	/**
	 * 根绝商品分类id查询商品规格参数模板
	 * Description: 
	 * @param cid
	 * @return
	 */
	ReadResult getItemParamByCid(long cid);
	
	/**
	 * 新增商品规格参数模板
	 * Description: 操作的数据库表 tb_item_param
	 * @param itemParam
	 * @return
	 */
	ReadResult insertItemParam(TbItemParam itemParam);
	
	/**
	 * 分页查询商品规格参数模板信息
	 * @param rows
	 * @param page
	 * @return
	 */
	EUDataGridResult listItemParams(int rows, int page);

}
