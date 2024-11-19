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
        
        // 프로필 사진 저장 (있는 경우)
        if (userPhoto != null && !userPhoto.isEmpty()) {
            String profileImagePath = saveFile(userPhoto);
            user.setUserPhoto(profileImagePath);
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
    public void logout(HttpSession session) {
        if (session != null) {
            // 세션 무효화
            session.invalidate();
        }
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
        String uploadDir = "c:/uploads/profile/"; // 절대 경로 사용
        
        // 디렉토리 생성
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장 경로
        String filePath = uploadDir + uniqueFileName;
        
        // 파일 저장
        File dest = new File(filePath);
        file.transferTo(dest);
        
        // 웹에서 접근 가능한 상대 경로 반환
        return "/uploads/profile/" + uniqueFileName;
    }

    @Override
    public boolean checkDuplicateUserId(String userId) {
        // 데이터베이스에서 해당 userId 존재 여부 확인
        User existingUser = userDao.checkDuplicateUserId(userId);
        return existingUser != null;
    }

    @Override
    public boolean checkDuplicateNickname(String userNickname) {
        // COUNT(*) 결과를 기반으로 중복 여부 판단
        return userDao.checkDuplicateNickname(userNickname) > 0;
    }

    @Override
    public String updateProfileImage(String userId, MultipartFile file) throws IOException {
        // 프로필 사진 저장 로직
        String profileImagePath = saveFile(file);
        
        // 사용자 프로필 사진 정보 업데이트
        userDao.updateUserProfileImage(userId, profileImagePath);
        
        return profileImagePath;
    }
}
