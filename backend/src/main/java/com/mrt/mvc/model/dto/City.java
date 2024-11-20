package com.mrt.mvc.model.dto;
public class City {
 private int cityNo;
 private String cityName;

 public int getCityNo() {
     return cityNo;
 }

 public void setCityNo(int cityNo) {
     this.cityNo = cityNo;
 }

 public String getCityName() {
     return cityName;
 }

 public void setCityName(String cityName) {
     this.cityName = cityName;
 }

 @Override
 public String toString() {
     return "City{" +
             "cityNo=" + cityNo +
             ", cityName='" + cityName + '\'' +
             '}';
 }
}

