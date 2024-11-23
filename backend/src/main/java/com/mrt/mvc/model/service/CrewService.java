package com.mrt.mvc.model.service;

import java.util.List;

import com.mrt.mvc.model.dto.City;
import com.mrt.mvc.model.dto.Crew;
import com.mrt.mvc.model.dto.CrewSearchCondition;
import com.mrt.mvc.model.dto.District;

public interface CrewService {
    List<Crew> getCrewList(CrewSearchCondition crewSearchCondition);
    Crew getCrewByNo(int crewNo);
    boolean writeCrew(Crew crew);
    boolean modify(Crew crew);
    boolean removeCrew(int crewNo);
    List<Crew> getCrewsByLocation(double lat, double lng, double radius);
    
    
    List<City> getCityList();
    // 특정 시에 속한 구 목록 가져오기
    List<District> getDistrictList(int cityNo);
    
    
    
}