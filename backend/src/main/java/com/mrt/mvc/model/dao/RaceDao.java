package com.mrt.mvc.model.dao;

import java.util.List;

import com.mrt.mvc.model.dto.Race;

public interface RaceDao {
    List<Race> selectAll();
    Race selectByNo(int raceNo);
    int insert(Race race);
    int update(Race race);
    int delete(int raceNo);
    List<Race> selectByCity(int cityNo);
}
