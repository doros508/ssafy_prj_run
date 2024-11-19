package com.mrt.mvc.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
    // 전체 사용자 목록 조회
    List<User> getUserList();
    
    // 회원가입
    User registUser(User user, MultipartFile userPhoto) throws IOException;
    
    // 로그인
    User login(String id, String password);
    
    // 계정 탈퇴
    void deleteAccount(int userNo);
    
    // 로그아웃
    void logout(HttpSession session);
    
    // 파일 저장
    String saveFile(MultipartFile file) throws IOException;
    
    // 아이디 중복 검사
    boolean checkDuplicateUserId(String userId);
    
    // 닉네임 중복 검사
    boolean checkDuplicateNickname(String userNickname);
    
    // 프로필 사진 업데이트
    String updateProfileImage(String userId, MultipartFile file) throws IOException;
}
