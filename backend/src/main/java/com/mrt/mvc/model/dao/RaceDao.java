package com.mrt.mvc.model.dao;

import java.util.List;
import java.util.Map;

import com.mrt.mvc.model.dto.Race;

public interface RaceDao {
    List<Race> selectAll();
    Race selectOne(int raceNo);
    int insertRace(Race race);
    int updateRace(Race race);
    int deleteRace(int raceNo);
    List<Race> selectByCity(int cityNo);
    
    // 검색을 위한 새로운 메서드
    List<Race> searchRaces(Map<String, Object> params);
}
