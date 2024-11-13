package com.mrt.mvc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrt.mvc.model.dto.User;
import com.mrt.mvc.model.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api-user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//목록 전체 가져오기
	@GetMapping("/users")
	public ResponseEntity<List<User>> userList() {
		return new ResponseEntity<List<User>>(userService.getUserList(),HttpStatus.OK);	
	}
	
	//회원가입. 
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
		userService.signup(user);
		return new ResponseEntity<>("회원가입 완료",HttpStatus.CREATED);
	}
	
	//로그인 
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody User user, HttpSession session) {
		User tmpUser = userService.login(user.getUserId(), user.getUserPassword());
		
		if(tmpUser !=null) {
			session.setAttribute("user", tmpUser);
			return new ResponseEntity<>("로그인 성공했습니다.", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("로그인 실패 했습니다.",HttpStatus.UNAUTHORIZED);
		}
	}
	@DeleteMapping("/mypage/deleteAccount") //아직 없지만 페이지 있다고 가정하고 구현함. 
	public ResponseEntity deleteAccount(@RequestParam int userNo) {
		userService.deleteAccount(userNo);
		return new ResponseEntity("계정 삭제 완료",HttpStatus.OK);
	}
}
