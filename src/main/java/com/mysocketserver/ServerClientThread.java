package com.mysocketserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.user.Dao.MyUserDao;
import com.user.service.MyUserService;

import net.sf.json.JSONObject;


@Component
public class ServerClientThread extends Thread{

	@Autowired
	@Qualifier("myUserService")
	private MyUserService myUserService;
	
	@Autowired
	private MyUserDao userDao;
	
	public Socket client;
	public ServerClientThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.client = socket;
		userDao = new MyUserDao();
	}
	
	public ServerClientThread() {
	
	}
	
	
	public void run() {
		/*
		 * String errcmd = "{\"cmd\":-1}"; String nocmd = "{\"cmd\":0}";
		 */
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			
			System.out.println("----------11"+myUserService+userDao);
			Integer count = 0;
			while (true) {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream());
				String message = in.readLine();
				count++;
				System.out.println(count+"Server received " + message);
				JSONObject mes = JSONObject.fromObject(message);
				String uid = mes.optString("userId");
				String content = mes.optString("appMark");
				

				ServerClientThread thread = SocketClientBean.getThread(uid);
				
				// System.out.println("JSONObject success");
				// String cmd = jsmsg.getString("cmd");
				out.println("----");
				out.flush();
				if (message.equals("bye")) {
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
