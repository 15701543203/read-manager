package com.web.read.test;

import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPClientTest1 {
	@Test
	public void testFTPClient() throws Exception {
		//创建client的实例
		FTPClient client = new FTPClient();
		//ftp连接服务器，指定服务器的ip地址和端口号
		client.connect("192.168.10.3", 21);
		//登录服务器：提供用户名和密码
		boolean loggedIn = client.login("ftpuser", "123456");
		if (loggedIn) {
			//设置通过FTP发送文件之前，设置文件的类型，二进制文件
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			//获取文件的输入流
			FileInputStream fileInputStream = new FileInputStream("F:/house.jpg");
			//向ftp服务器上传文件
			//第一个参数指定在服务器上的文件名
			//第二个参数指定输入流。
			boolean flag = client.storeFile("ftpimages/hello.jpg", fileInputStream);
			//传完之后关闭文件的输入流
			fileInputStream.close();
			System.out.println(flag ? "成功" : "失败");
		}
		//用户登出系统
		client.logout();
		//关闭ftp服务器连接
		client.disconnect();
	}
	
}