package com.mrt.mvc.model.service;

import java.util.List;

import com.mrt.mvc.model.dto.Race;

public interface RaceService {
	
	// 전체 목록 조회	
	List<Race> getRaceList();
	// 1개 조회
	Race getRaceByNo(int no);
	//생성
	boolean createRace(Race race);
	//수정
	boolean modifyRace(Race race);	
	//삭제
	boolean removeRace(int no);
	
	//도시번호로 경기 가져오기
	List<Race> getRacesByCity(int cityNo);
	
	 //  검색 메소드 
    List<Race> searchRaces(String date, Integer cityNo, String distance);
}
