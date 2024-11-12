package com.mrt.mvc.model.dao;

import java.util.List;
import java.util.Map;

import com.mrt.mvc.model.dto.User;

public interface UserDao {
	public List<User> selectAll(); //전체 사용자 목록 

	public void insertUser(User user); //회원가입 (등록)
	
	public User selectOne(Map<String, String > info); // 로그인 

	public void deleteUser(int userNo); //탈퇴 (삭제)
	
	//아이디 찾기, 비밀번호 찾기 생략
	
	
	

}
 	