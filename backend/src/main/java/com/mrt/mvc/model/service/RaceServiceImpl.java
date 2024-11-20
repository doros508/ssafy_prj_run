package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.RaceDao;
import com.mrt.mvc.model.dto.Race;

@Service
public class RaceServiceImpl implements RaceService {
	private final RaceDao raceDao;
	
	public RaceServiceImpl(RaceDao raceDao) {
		this.raceDao = raceDao;
	}

	@Override
	public List<Race> getAllRaces() {
		return raceDao.selectAll();
	}

	@Override
	public Race getRaceByNo(int raceNo) {
		return raceDao.selectByNo(raceNo);
	}

	@Override
	public boolean createRace(Race race) {
		//MyBatis 입력 성공시 1 반환
		return raceDao.insert(race) >0;
	}

	@Override
	public boolean updateRace(Race race) {
		return raceDao.update(race)>0;
	}

	@Override
	public boolean deleteRace(int raceNo) {
		return raceDao.delete(raceNo)>0;
	}

	@Override
	public List<Race> getRacesByCity(int cityNo) {
		return raceDao.selectByCity(cityNo);
	}
}
