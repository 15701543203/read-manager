package com.web.read.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.bean.TbItemParam;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;
import com.web.read.service.ItemParamService;

/**
 * 
 * @Title ItemParamController.java
 *        <p>
 *        Description:
 *        </p>
 *        <p>
 *        Company:
 *        </p>
 * @Package com.web.read.controller
 * @Author Administrator
 * @Date 2018年3月19日下午5:44:04
 * @version v1.0
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	/**
	 * 查出规格参数 Description:
	 * 
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping(value = "/query/itemcatid/{itemCatId}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public ReadResult getItemParamByCid(@PathVariable long itemCatId) {
		ReadResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	/**
	 * 添加商品规格参数
	 * Description: 
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public ReadResult insertItemParam(@PathVariable Long cid, String paramData) {
		//创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		ReadResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}

	/**
	 * 查询商品规格参数列表
	 * Description: 
	 * @param rows
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public EUDataGridResult list(@RequestParam(value = "rows", defaultValue = "30") int rows,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		System.out.println("请问你是查询的啥？");
		return this.itemParamService.listItemParams(rows, page);
	}
}
