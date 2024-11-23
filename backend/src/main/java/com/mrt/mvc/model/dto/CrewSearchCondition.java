package com.mrt.mvc.model.dto;

public class CrewSearchCondition {
    private Integer cityNo;        // 도시 번호
    private Integer districtNo;    // 구 번호
    private String[] meetingDays;  // 모임 요일

    public CrewSearchCondition() {
    }

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

    public String[] getMeetingDays() {
        return meetingDays;
    }

    public void setMeetingDays(String[] meetingDays) {
        this.meetingDays = meetingDays;
    }

    @Override
    public String toString() {
        return "CrewSearchCondition [cityNo=" + cityNo + ", districtNo=" + districtNo +
               ", meetingDays=" + String.join(", ", meetingDays) + "]";
    }
}
