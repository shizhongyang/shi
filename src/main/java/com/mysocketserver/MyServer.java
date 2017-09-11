package com.mysocketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.UserMessage;
import com.user.entity.MyUser;
import com.user.service.MessageService;
import com.user.service.MyUserService;

import net.sf.json.JSONObject;

@Component
public class MyServer {
	public static List<SocketClientBean> clientlist = new ArrayList<SocketClientBean>();

	/*	private static MessageService messageService;
	@Autowired
	private MyUserService myUserService;
	private static MyUserService userService;

	@Autowired
	private MyUserService userService2;

	@PostConstruct
	public void beforeInit() {
		userService = userService2;
	}

	@Autowired
	public MyServer(MessageService messageService, MyUserService myUserService) {
		MyServer.messageService = messageService;
		System.out.println("------MyServer"+myUserService);
		//MyServer.myUserService = myUserService;
	}
*/
	private static Timer timer = new Timer();
	private static Executor service = null;
	private static ObjectMapper objectMapper = null;

	public static void rerfresh() {
		timer.schedule(new MyClientRefreshTask(), 1000 * 0, 1000 * 15);
		service = Executors.newCachedThreadPool();
		objectMapper = new ObjectMapper();
	}

	public static void destroyedTimer() {
		/*
		 * if (timer1 != null) { timer1.cancel(); }
		 */
		if (timer != null) {
			timer.cancel();
		}
	}

	/**
	 * @param client
	 * @throws IOException
	 */
	public static void invoke(final Socket client) throws IOException {

		BufferedReader re = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String message = re.readLine();
		System.out.println("invoke--------" + message);
		// objectMapper.readValue(message,JsonOb)
		JSONObject object = JSONObject.fromObject(message);
		String uid = object.optString("userId");
		object.get("userNm");

		/*//System.out.println("invoke--------" + myUserService + userService);
	//	MyUser myUser = myUserService.findUserById(uid);
		UserMessage mUserMessage = new UserMessage();
		mUserMessage.setContent(message);
	//	mUserMessage.setUser(myUser);
		mUserMessage.setRead(false);
		messageService.save(mUserMessage);*/

		ServerClientThread thread = new ServerClientThread(client);
		SocketClientBean.addClientThread(uid, thread);
		service.execute(thread);
<<<<<<< HEAD

=======
	
>>>>>>> dd26b8ca52b0c41b714edbc94894896e9f225bc7
		// thread.start();
		/*
		 * new Thread(new Runnable() { public void run() { // String errcmd =
		 * "{\"cmd\":-1}"; String nocmd = "{\"cmd\":0}"; BufferedReader in =
		 * null; PrintWriter out = null; try { in = new BufferedReader(new
		 * InputStreamReader(client.getInputStream())); out = new
		 * PrintWriter(client.getOutputStream()); Integer count = 0; while
		 * (true) { System.out.println("----------------"); String cmdmsg =
		 * in.readLine(); count++; System.out.println(count);
		 * System.out.println("Server received " + cmdmsg);
		 * 
		 * // JSONObject jsmsg = JSONObject.fromObject(cmdmsg); //
		 * System.out.println("JSONObject success"); // String cmd =
		 * jsmsg.getString("cmd"); out.println("----"); out.flush(); if (cmdmsg
		 * != null && cmdmsg.equals("bye")) { break; } } } catch (Exception ex)
		 * { ex.printStackTrace(); } finally { try { in.close(); out.close();
		 * client.close(); } catch (Exception e) { e.printStackTrace(); } } }
		 * }).start();
		 */
	}

	static class MyClientRefreshTask extends java.util.TimerTask {
		public void run() {
			refreshClient();
		}
	}

	private static void refreshClient() {
		List<SocketClientBean> dels = new ArrayList<SocketClientBean>();
		for (int i = 0; i < MyServer.clientlist.size(); i++) {
			if (MyServer.clientlist.get(i).getSocket().isClosed()) {
				System.out.println("it is the del client!");
				dels.add(clientlist.get(i));
			}
		}
		System.out.println("clientlist num:" + clientlist.size());
		System.out.println("dels num:" + dels.size());
		clientlist.removeAll(dels);
		System.out.println("clientlist left num:" + clientlist.size());
	}
}
