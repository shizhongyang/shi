package com.mysocketserver;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;



public class SocketClientBean {
	
	
	public static HashMap<String, ServerClientThread> hashMap  = new  HashMap<String, ServerClientThread>();
	
	private Socket socket;
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
	public static void addClientThread(String uid,ServerClientThread cThread) {
		//添加一个同学线程
		hashMap.put(uid, cThread);
	}
	
	public static ServerClientThread getThread(String uid) {
		return (ServerClientThread) hashMap.get(uid);
	}
		
}
