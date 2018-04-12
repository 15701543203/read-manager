package com.web.read.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.bean.TbItem;
import com.web.read.bean.TbItemDesc;
import com.web.read.bean.TbItemParam;
import com.web.read.bean.TbItemParamItem;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;
import com.web.read.service.ItemService;
/**
 * 
 * @Title   ItemController.java
 * <p>Description:</p>
 * <p>Company: </p>
 * @Package com.web.read.controller
 * @Author  Administrator
 * @Date    2018年3月20日下午11:41:23
 * @version v1.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	// http://localhost:8080/item/12345
	//	public TbItem getItemById(@PathVariable("itemId") Long id ) {
	//		TbItem tbItem = itemService.getItemById(itemId);
	/**
	 * restful风格url
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/item/{itemId}", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		System.out.println(tbItem);
		return tbItem;
	}
	
	/**
	 * 
	 * @param page 第几页
	 * @param rows 多少条	
	 * @return
	 */
	@RequestMapping(value = "/item/list", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getPaginatedItems(page, rows);
		return result;
	}

	
	/*@RequestMapping(value="/item/save",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public ReadResult addItem(TbItem item,String desc){
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		ReadResult result = itemService.addItem(item, itemDesc);
		return result;
	}*/
	
	@RequestMapping(value="/item/save",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public ReadResult addItem(TbItem item, String desc, String itemParams) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setParamData(itemParams);
		
		ReadResult result = itemService.addItem(item, itemDesc, itemParamItem);
		return result;
	}
	
}
