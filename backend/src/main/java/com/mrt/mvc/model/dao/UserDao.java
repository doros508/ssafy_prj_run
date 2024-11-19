package com.mrt.mvc.model.dao;

import java.util.List;
import java.util.Map;

import com.mrt.mvc.model.dto.User;

public interface UserDao {
    // 전체 사용자 목록 조회
    List<User> selectAll();

    // 사용자 등록
    void insertUser(User user);

    // 로그인을 위한 사용자 조회
    User selectOne(Map<String, String> info);

    // 사용자 삭제 (회원탈퇴)
    void deleteUser(int userNo);

    // 아이디 중복 검사
    User checkDuplicateUserId(String userId);

    // 닉네임 중복 검사
    int checkDuplicateNickname(String userNickname);

    // 사용자 프로필 이미지 업데이트
    void updateUserProfileImage(String userId, String profileImagePath);
}
