package com.mrt.mvc.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.RaceDao;
import com.mrt.mvc.model.dto.Race;

@Service
public class RaceServiceImpl implements RaceService {
	
	// 의존성 주입
	private final RaceDao dao;
	public RaceServiceImpl(RaceDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Race> getRaceList() {
		return dao.selectAll();
	}
	
	@Override
	public Race getRaceByNo(int no) {
		return dao.selectOne(no);
	}

	@Override
	public boolean createRace(Race race) {
		//MyBatis 입력 성공시 1 반환
		return dao.insertRace(race) >0;
	}

	@Override
	public boolean modifyRace(Race race) {
		return dao.updateRace(race)>0;
	}

	@Override
	public boolean removeRace(int raceNo) {
		return dao.deleteRace(raceNo)>0;
	}

	@Override
	public List<Race> getRacesByCity(int cityNo) {
		return dao.selectByCity(cityNo);
	}

    @Override
    public List<Race> searchRaces(String date, Integer cityNo, String distance) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        params.put("cityNo", cityNo);
        params.put("distance", distance);
        return dao.searchRaces(params);
    }
}
