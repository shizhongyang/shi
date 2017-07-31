package com.mysocketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;

public class SocketThread extends Thread {
	Integer count = 0;
	private ServletContext servletContext;
	private ServerSocket serverSocket;

	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		this.servletContext = servletContext;
		// 从web.xml中context-param节点获取socket端口
		String port = this.servletContext.getInitParameter("socketPort");
		System.out.println("---------"+port);
		if (serverSocket == null) {
			try {
				this.serverSocket = new ServerSocket(Integer.parseInt(port));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		try {
			Integer count = 0;
			while (!this.isInterrupted()) {
				MyServer.rerfresh();
				Socket socket = serverSocket.accept();
				count++;
				System.out.println("Server SocketThread start:" + count);
				if (socket != null) {
					SocketClientBean client = new SocketClientBean();
					client.setSocket(socket);
					MyServer.clientlist.add(client);
					System.out.println("new commer:" + socket.getRemoteSocketAddress().toString());
					MyServer.invoke(socket);
				}
			}
		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}

	public void closeServerSocket() {
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
				MyServer.destroyedTimer();
			}

		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
		}
	}
}
