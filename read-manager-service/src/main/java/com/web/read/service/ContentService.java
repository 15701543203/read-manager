package com.web.read.service;

import com.web.read.bean.TbContent;
import com.web.read.common.bean.EUDataGridResult;
import com.web.read.common.bean.ReadResult;

public interface ContentService {
 
	/**
	 * 分页
	 * Description: 根据分类id查询内容消息
	 * @param page 第几页
	 * @param rows 多少条
	 * @param categoryId 分类ID
	 * @return
	 */
	 EUDataGridResult contentList(int page,int rows, long categoryId);
	 
	 /**
	  * 添加内容管理内容
	  * Description: 
	  * @param content
	  * @return
	  */
	 ReadResult insertContent(TbContent content);
	 
	 /**
	  * 删除
	  * Description: 
	  * @param ids
	  * @return
	  */
	 ReadResult deleteContent(long[] ids);
	 
}
