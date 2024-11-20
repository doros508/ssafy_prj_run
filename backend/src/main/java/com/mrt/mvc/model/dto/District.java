package com.mrt.mvc.model.dto;

public class District {
private int districtNo;
private int cityNo;
private String districtName;

public int getDistrictNo() {
   return districtNo;
}

public void setDistrictNo(int districtNo) {
   this.districtNo = districtNo;
}

public int getCityNo() {
   return cityNo;
}

public void setCityNo(int cityNo) {
   this.cityNo = cityNo;
}

public String getDistrictName() {
   return districtName;
}

public void setDistrictName(String districtName) {
   this.districtName = districtName;
}

@Override
public String toString() {
   return "District{" +
           "districtNo=" + districtNo +
           ", cityNo=" + cityNo +
           ", districtName='" + districtName + '\'' +
           '}';
}
}