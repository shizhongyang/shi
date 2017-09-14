package com.user.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.mapper.JsonMapper;
import com.user.entity.MyFriend;
import com.user.entity.MyUser;
import com.user.service.MyFriendService;

@Controller
@RequestMapping("/fc")
public class FriendController {

	@Autowired
	MyFriendService myFriendService;

	@ResponseBody
	@RequestMapping(value = "/getdata")
	public String getDatas(HttpServletRequest request) {
		String id = request.getParameter("id");
		ArrayList<MyFriend> mdatas = (ArrayList<MyFriend>) myFriendService.getList(id);
		System.out.println("------" + mdatas.size());

		String string = JsonMapper.getInstance().toJson(mdatas);
		return string;
	}

	@RequestMapping(value = "addFriend")
	public String addFriends(HttpServletRequest request) {
		String id = request.getParameter("id");
		MyUser user = (MyUser) request.getSession().getAttribute("myUser");
		MyFriend friend = new MyFriend();
		friend.setFsrcid(user.getId());
		friend.setFDesId(id);

		myFriendService.save(friend);

		return "user/success";

	}

}
