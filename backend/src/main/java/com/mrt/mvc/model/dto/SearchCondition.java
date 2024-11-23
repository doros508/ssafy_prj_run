package com.mrt.mvc.model.dto;

public class SearchCondition {
    private Integer cityNo;       // 도시 번호
    private Integer districtNo;   // 구 번호
    private String meetingDay;    // 모임 요일

    // Getters and Setters
    public Integer getCityNo() {
        return cityNo;
    }

    public void setCityNo(Integer cityNo) {
        this.cityNo = cityNo;
    }

    public Integer getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(Integer districtNo) {
        this.districtNo = districtNo;
    }

    public String getMeetingDay() {
        return meetingDay;
    }

    public void setMeetingDay(String meetingDay) {
        this.meetingDay = meetingDay;
    }
}
