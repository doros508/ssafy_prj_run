package com.mrt.mvc.model.service;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	//전체 사용자 목록 
	public List<User> getUserList();
	//회원가입
	public User registUser(User user, MultipartFile userPhoto) throws IOException;
	//로그인
	public User login(String id, String password);
	//탈퇴 
	public void deleteAccount(int userNo);
	//로그아웃
	public void logout(HttpSession session);
	
	//파일 저장하기. 해야하나?
	public String saveFile(MultipartFile file) throws IOException;
	// id 중복검사. 
	boolean checkDuplicateUserId(String userId);
	//별명 중복검사.  
	boolean checkDuplicateNickname(String userNickname);
}
