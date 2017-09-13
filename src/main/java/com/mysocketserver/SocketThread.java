package com.mysocketserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.user.Dao.MyUserDao;
import com.user.entity.MyUser;
import com.user.service.MyUserService;

import net.sf.json.JSONObject;

@Component
public class SocketThread extends Thread {
	Integer count = 0;
	private ServletContext servletContext;
	private ServerSocket serverSocket;

	@Autowired
	MyUserService myUserService;
	
	
	@Autowired
	MyUserDao myUserDao;
	
	HttpServletRequest request;
	
	
	public SocketThread() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		this.servletContext = servletContext;
		// 从web.xml中context-param节点获取socket端口
		String port = this.servletContext.getInitParameter("socketPort");
		//request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//System.out.println("---------" + port);
		//myUserService = new MyUserService();
		
		if (serverSocket == null) {
			try {
				this.serverSocket = new ServerSocket(Integer.parseInt(port));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		PrintStream out = null;
		BufferedReader re = null;
		//HttpServletRequest request = ServletActionContext.getRequest();
		try {
			while (!this.isInterrupted()) {
				MyServer.rerfresh();
				System.out.println("--------------Hello in"+myUserService);
				Socket socket = serverSocket.accept();
				System.out.println("Server SocketThread start:" + count + socket);
				//re = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataInputStream reader = new DataInputStream( socket.getInputStream());
				System.out.println("-----------------待读取");
				String message = reader.readUTF();
				System.out.println("invoke--------" + message);
				if (socket != null && message != null) {
					JSONObject object = JSONObject.fromObject(message);
					String uid = object.optString("userId");
					String pwd = object.optString("pwd");
					//myUserDao = new MyUserDao();
					//MyUser user = myUserDao.findUserById(uid);
					//System.out.println(user.getId());
					out = new PrintStream(socket.getOutputStream());

					if ("123".equals(pwd)) {
						out.println("success");
						out.flush();
						ServerClientThread thread = new ServerClientThread(socket);
						SocketClientBean.addClientThread(uid, thread);
						thread.start();
					} else {
						out.println("failure");
					}
				}
				// 将数据存入map中

				/*
				 * if (socket != null) { SocketClientBean client = new
				 * SocketClientBean(); client.setSocket(socket);
				 * MyServer.clientlist.add(client); MyServer.invoke(socket);
				 * System.out.println("new commer:" +
				 * socket.getRemoteSocketAddress().toString()); }
				 */
			}
		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (re!=null) {
					re.close();
				}
				
				if (out!=null) {
					out.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
