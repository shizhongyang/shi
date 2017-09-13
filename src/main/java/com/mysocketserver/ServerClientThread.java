package com.mysocketserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
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
public class ServerClientThread extends Thread {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("myUserService") private MyUserService myUserService;
	 * 
	 * @Autowired private MyUserDao userDao;
	 */

	public Socket client;

	public ServerClientThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.client = socket;
		// userDao = new MyUserDao();
	}

	public ServerClientThread() {

	}

	public void run() {
		BufferedReader in = null;
		PrintStream out = null;
		try {
			// System.out.println("----------11"+myUserService+userDao);
			while (true) {
				System.out.println("---1");
				//in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				DataInputStream reader = new DataInputStream( client.getInputStream());
				System.out.println("---2");
				String str = reader.readUTF();
				System.out.println("---3");
				if (str != null) {
					JSONObject message = JSONObject.fromObject(str);
					// JSONObject message = MessageIntent.getMessage();
					System.out.println("Server received " + message);
					String uid = message.optString("userId");
					String content = message.optString("content");
					String userNm = message.optString("name");
					String toSb = message.optString("toSb");

					ServerClientThread thread = SocketClientBean.getThread(toSb);
					if (thread!= null && !thread.client.isClosed()) {
						PrintStream oWriter = new PrintStream(thread.client.getOutputStream());
						oWriter.println("回复消息");
						oWriter.flush();
					} else {
						// String cmd = jsmsg.getString("cmd");
						/*out = new PrintStream(client.getOutputStream());
						System.out.println("---");
						out.println("返回");
						out.flush();
						System.out.println("---结束");*/
					}

					// System.out.println("JSONObject success");

					/*
					 * if (message.equals("bye")) { break; }
					 */
				}

			}
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (client != null && !client.isClosed())
					client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
