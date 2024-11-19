package com.mrt.mvc.model.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dao.UserDao;
import com.mrt.mvc.model.dto.User;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService {
	private final UserDao userDao;
    
    
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}
	

	
	
	@Override
	public User registUser(User user, MultipartFile userPhoto) throws IOException {
	    // 아이디 중복 체크
	    if (checkDuplicateUserId(user.getUserId())) {
	        throw new RuntimeException("이미 존재하는 아이디입니다.");
	    }

	    // 닉네임 중복 체크
	    if (checkDuplicateNickname(user.getUserNickname())) {
	        throw new RuntimeException("이미 존재하는 닉네임입니다.");
	    }
	    
	    // 프로필 사진 저장 (선택적)
	    if (userPhoto != null && !userPhoto.isEmpty()) {
	        String filePath = saveFile(userPhoto);
	        user.setUserPhoto(filePath);
	    }
	    
	    // 회원가입 시간 설정
	    user.setUserRegDate(LocalDateTime.now());
	    
	    // 기본 역할 설정
	    user.setUserRole("user");
	    
	    // DB에 사용자 정보 저장
	    userDao.insertUser(user);
	    
	    return user;
	}
	
	

	@Override
	public User login(String id, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("id", id);
		info.put("password", password);
		User tmp = userDao.selectOne(info);
		return tmp;
		
	}

	@Override
	public void deleteAccount(int userNo) {
		userDao.deleteUser(userNo);
	}

	@Override
	public String saveFile(MultipartFile file) throws IOException {
	    if (file == null || file.isEmpty()) {
	        return null; // 파일이 없는 경우 null 반환
	    }

	    // 고유한 파일명 생성
	    String originalFileName = file.getOriginalFilename();
	    String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
	    
	    // 업로드 디렉토리 설정
	    String uploadDir = "c:/uploads/profile"; // 절대 경로 사용
	    
	    // 디렉토리 생성
	    File directory = new File(uploadDir);
	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    // 파일 저장 경로
	    String filePath = uploadDir + File.separator + uniqueFileName;
	    
	    // 파일 저장
	    File dest = new File(filePath);
	    file.transferTo(dest);
	    
	    // 웹에서 접근 가능한 경로 반환
	    return "/uploads/profile/" + uniqueFileName;
	}
	
	
	
	

	@Override
	public boolean checkDuplicateUserId(String userId) {
		User existingUser=userDao.checkDuplicateUserId(userId);
        return existingUser != null;
	}
	
	

	@Override
	public boolean checkDuplicateNickname(String userNickname) {
		User existingUser = userDao.checkDuplicateNickname(userNickname);
        return existingUser != null;
	}

	@Override
	public void logout(HttpSession session) {
	    if (session != null) {
	        // 세션 무효화
	        session.invalidate();
	    }
	}
	
	
	
}
