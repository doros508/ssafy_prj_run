package com.mrt.mvc.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.User;

public interface UserService {
	//전체 사용자 목록 
	public List<User> getUserList();
	//회원가입
	public void registUser(User user, MultipartFile userPhoto) throws IOException;
	//로그인
	public User login(String id, String password);
	//탈퇴 
	public void deleteAccount(int userNo);
	//파일 저장하기. 
	public String saveFile(MultipartFile file) throws IOException;
	
}
