package com.mysocketserver;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

public class MessageIntent {

	//public static LinkedHashMap<String,JSONObject> map = new  LinkedHashMap<String, JSONObject>();
	private static BlockingQueue<JSONObject> mQueue = new LinkedBlockingQueue<JSONObject>();
	public static void putMessage(JSONObject message) throws InterruptedException {
		mQueue.put(message);
	}
	
	public static JSONObject getMessage() throws InterruptedException {
		return mQueue.take();
	}
	
	
	
	
}
