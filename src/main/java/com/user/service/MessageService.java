package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.UserMessage;
import com.user.Dao.MessageDao;

@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	public  String save(UserMessage message){
		return messageDao.save(message);
	}
	
}
