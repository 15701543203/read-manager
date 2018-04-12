package com.web.read.test;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.web.read.common.utils.FtpUtil;


public class FtpUtilTest {
	
//	@Test
//	public void testUpload() throws Exception {
//		
//		FileInputStream input = new FileInputStream("C:\\Users\\user\\Pictures\\Saved Pictures\\15.jpg");
//		
//		FtpUtil util = new FtpUtil();
//		boolean flag = util.uploadFile("192.168.10.3", 21, "ftpuser", "123456", "/home/ftpuser/www/images/", "2018/03/16/", "1.jpg", input);
//		Assert.assertTrue(flag);
//	}
	@Test
	public void testUpload() throws Exception {

		FileInputStream input = new FileInputStream(
				"C:\\Users\\user\\Pictures\\Saved Pictures\\15.jpg");

		boolean flag = FtpUtil.uploadFile("192.168.10.3", 21, "ftpuser",
				"123456", "/home/ftpuser/www/images/", "2018/03/19/",
				"hello.jpg", input);
		Assert.assertTrue(flag);
	}
	
}