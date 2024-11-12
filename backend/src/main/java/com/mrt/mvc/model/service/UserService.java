package com.mrt.mvc.model.service;

import java.util.List;

import com.mrt.mvc.model.dto.User;

public interface UserService {
	//전체 사용자 목록 
	public List<User> getUserList();
	//회원가입
	public void signup(User user);
	//로그인
	public User login(String id, String password);
	//탈퇴 
	public void deleteAccount(int userNo);

}
