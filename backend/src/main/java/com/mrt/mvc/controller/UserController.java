package com.mrt.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.User;
import com.mrt.mvc.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/maratalk")

public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 목록 전체 가져오기
	@GetMapping("/users")
	public ResponseEntity<List<User>> userList() {
		return new ResponseEntity<List<User>>(userService.getUserList(), HttpStatus.OK);
	}

	// 회원가입.
	@PostMapping(value = "/regist", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> signup(
	        @RequestPart(value = "userData") User user,
	        @RequestPart(value = "file", required = false) MultipartFile file) {
		
	    try {
	    	System.out.println("-------------Received user data: " + user.toString()); // 임시검사 
	    	
	    	
	        // 아이디 중복체크
	        if (userService.checkDuplicateUserId(user.getUserId())) {
	            System.out.println("아이디 중복 검사 요청: " + user.getUserId());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("message", "이미 존재하는 아이디입니다."));
	        }

	        // 닉네임 중복 체크
	        if (userService.checkDuplicateNickname(user.getUserNickname())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("message", "이미 존재하는 닉네임입니다."));
	        }

	        // 회원가입 처리 (파일 포함)
	        System.out.println("**************************************Received user data: " + user.toString()); //임시검사
	        User registeredUser = userService.registUser(user, file);
	        
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(Map.of(
	                    "message", "회원가입 완료",
	                    "user", registeredUser
	                ));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("message", "회원가입 중 오류 발생: " + e.getMessage()));
	    }
	}

	// 프로필 사진 별도 업로드 엔드포인트 추가 (선택)
	@PostMapping(value = "/upload-profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadProfileImage(@RequestParam("userId") String userId,
			@RequestParam("file") MultipartFile file) {
		try {
			// 프로필 사진 업데이트 로직
			String profileImagePath = userService.updateProfileImage(userId, file);

			return ResponseEntity.ok(Map.of("message", "프로필 사진 업로드 성공", "profilePath", profileImagePath));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "프로필 사진 업로드 실패"));
		}
	}

	// 아이디 중복검사
	@GetMapping("/check-userid")
	public ResponseEntity<?> checkDuplicateUserId(@RequestParam(value = "userId", required = true) String userId) {
		// 아이디 입력 검증 추가
		if (userId == null || userId.trim().isEmpty()) {
			return ResponseEntity.badRequest().body(Map.of("message", "아이디를 입력해주세요."));
		}

		// 아이디 중복 검사
		boolean isDuplicate = userService.checkDuplicateUserId(userId);

		return ResponseEntity.ok(Map.of("isDuplicate", isDuplicate));
	}

	// 닉네임 중복검사
	@GetMapping("/check-nickname")
	public ResponseEntity<?> checkDuplicateNickname(
			@RequestParam(value = "userNickname", required = true) String userNickname) {
		boolean isDuplicate = userService.checkDuplicateNickname(userNickname);
		return ResponseEntity.ok(Map.of("isDuplicate", isDuplicate));
	}

//	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
	    User tmpUser = userService.login(user.getUserId(), user.getUserPassword());

	    if (tmpUser != null) {
	        session.setAttribute("user", tmpUser);
	        return ResponseEntity.ok(Map.of(
	            "message", "로그인 성공했습니다.",
	            "user", tmpUser
	        ));
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(Map.of("message", "로그인 실패했습니다."));
	    }
	}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
//	    User tmpUser = userService.login(user.getUserId(), user.getUserPassword());
//
//	    if (tmpUser != null) {
//	        session.setAttribute("user", tmpUser);
//	        return ResponseEntity.ok(Map.of(
//	            "message", "로그인 성공했습니다.",
//	            "user", tmpUser
//	        ));
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//	            .body(Map.of("message", "로그인 실패했습니다."));
//	    }
//	}

	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
	    if (session != null) {
	        session.invalidate(); // 세션 무효화
	        return ResponseEntity.ok(Map.of("message", "로그아웃 성공"));
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Map.of("message", "세션이 존재하지 않습니다."));
	    }
	}

	// 계정 삭제(회원탈퇴)
	@DeleteMapping("/mypage/deleteAccount") // 아직 없지만 페이지 있다고 가정하고 구현함.
	public ResponseEntity deleteAccount(@RequestParam int userNo) {
		userService.deleteAccount(userNo);
		return new ResponseEntity("계정 삭제 완료", HttpStatus.OK);
	}

}
