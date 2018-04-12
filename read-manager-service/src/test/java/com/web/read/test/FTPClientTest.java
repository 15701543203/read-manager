package com.web.read.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPClientTest {

	@Test
	public void testFTPD() throws SocketException, IOException{
		//连接FTP服务器，创建client的实例
		FTPClient ftpClient  = new FTPClient();
		ftpClient.connect("192.168.10.3",21);
		
		//登录FTP服务器
		ftpClient.login("ftpuser", "123456");
		
		//读取本地文件
		FileInputStream inputStream = new  FileInputStream(new File("C:\\Users\\user\\Pictures\\Saved Pictures\\15.jpg"));
		
		//上传文件目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//指定上传文件的类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		/**
		 * 第一个参数：文件在图片服务器的名称
		 * 
		 * 第二个参数：文件流
		 */
		ftpClient.storeFile("18.jpg", inputStream);
		
		//关闭图片服务器
		ftpClient.logout();
	}
	
	
}
