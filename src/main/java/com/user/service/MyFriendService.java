package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.Dao.FrinedsDao;
import com.user.entity.MyFriend;

@Service
public class MyFriendService {
  
	@Autowired
	FrinedsDao frinedsDao;
	
	public List<MyFriend> getList(String id) {	
		return frinedsDao.getList(id);
	}
	

	public void save(MyFriend friend){
		frinedsDao.addFriend(friend);
	}
}
