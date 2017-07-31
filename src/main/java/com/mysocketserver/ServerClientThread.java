package com.mysocketserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import net.sf.json.JSONObject;

public class ServerClientThread extends Thread{

	public Socket client;
	public ServerClientThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.client = socket;
	}
	public void run() {
		/*
		 * String errcmd = "{\"cmd\":-1}"; String nocmd = "{\"cmd\":0}";
		 */
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			
			Integer count = 0;
			
			while (true) {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream());
				String message = in.readLine();
				count++;
				System.out.println(count+"Server received " + message);
				JSONObject mes = JSONObject.fromObject(message);
				String uid = mes.optString("id");
				String content = mes.optString("content");
				
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
