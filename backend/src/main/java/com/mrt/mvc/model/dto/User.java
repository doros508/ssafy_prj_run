package com.mrt.mvc.model.dto;

import java.time.LocalDateTime;

public class User {

    private int userNo;          // 사용자 번호
    private String userId;       // 사용자 아이디
    private String userPassword; // 사용자 비밀번호
    private String userEmail;    // 사용자 이메일
    private String userName;     // 사용자 이름
    private String userBirthday; // 사용자 생일 (yyyy-MM-dd) -때문에 String
    private String userGender;   // 사용자 성별 (M/F/O 등)
    private String userAddress;  // 사용자 주소
    private String userDetailAddress; // 사용자 상세 주소
    private Integer userZipCode; // 사용자 우편번호
    private String userPhoneNumber; // 사용자 전화번호
    private Integer userAge;     // 사용자 나이
    private String userNickname; // 사용자 닉네임
    private LocalDateTime userRegDate;    // 사용자 등록일 //Date type
    private LocalDateTime userLoginDate;  // 사용자 마지막 로그인일
    private String userPhoto;    // 사용자 프로필 사진 URL
    private String userRole;     // 사용자 역할 (예: 'user', 'admin' 등)
    private String userIat;      // 사용자 발급시간 (JWT 관련)
    private String userExp;      // 사용자 만료시간 (JWT 관련)
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserDetailAddress() {
		return userDetailAddress;
	}
	public void setUserDetailAddress(String userDetailAddress) {
		this.userDetailAddress = userDetailAddress;
	}
	public Integer getUserZipCode() {
		return userZipCode;
	}
	public void setUserZipCode(Integer userZipCode) {
		this.userZipCode = userZipCode;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public LocalDateTime getUserRegDate() {
		return userRegDate;
	}
	public void setUserRegDate(LocalDateTime userRegDate) {
		this.userRegDate = userRegDate;
	}
	public LocalDateTime getUserLoginDate() {
		return userLoginDate;
	}
	public void setUserLoginDate(LocalDateTime userLoginDate) {
		this.userLoginDate = userLoginDate;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserIat() {
		return userIat;
	}
	public void setUserIat(String userIat) {
		this.userIat = userIat;
	}
	public String getUserExp() {
		return userExp;
	}
	public void setUserExp(String userExp) {
		this.userExp = userExp;
	}
	public User() {
	}
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userName=" + userName + ", userBirthday=" + userBirthday + ", userGender=" + userGender
				+ ", userAddress=" + userAddress + ", userDetailAddress=" + userDetailAddress + ", userZipCode="
				+ userZipCode + ", userPhoneNumber=" + userPhoneNumber + ", userAge=" + userAge + ", userNickname="
				+ userNickname + ", userRegDate=" + userRegDate + ", userLoginDate=" + userLoginDate + ", userPhoto="
				+ userPhoto + ", userRole=" + userRole + ", userIat=" + userIat + ", userExp=" + userExp + "]";
	}
	
    
}
