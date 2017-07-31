package com.mysocketserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
			Integer count = 0;
			
			while (true) {
		
				String cmdmsg = in.readLine();
				System.out.println("-----------------");
				count++;
				System.out.println(count);
				System.out.println("Server received " + cmdmsg);

				// JSONObject jsmsg = JSONObject.fromObject(cmdmsg);
				// System.out.println("JSONObject success");
				// String cmd = jsmsg.getString("cmd");
				out.println("----");
				out.flush();
				if (cmdmsg.equals("bye")) {
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
