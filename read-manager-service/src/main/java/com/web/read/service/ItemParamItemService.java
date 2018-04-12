package com.web.read.service;

public interface ItemParamItemService {
	/**
	 * 根据商品的ID，获取HTML的table格式的商品规格参数信息
	 * @param itemId
	 * @return
	 */
	String getItemParamByItemId(Long itemId);
}
