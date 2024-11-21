package com.mrt.mvc.model.dao;

import java.util.List;

import com.mrt.mvc.model.dto.Magazine;
import com.mrt.mvc.model.dto.SearchCondition;

public interface MagazineDao {
	
	// 매거진 전체 조회 및 검색 조회
	List<Magazine> selectAll(SearchCondition condition);

	// 매거진 상세 조회
	Magazine selectOne(int no);

	// 매거진 조회수 증가
	void updateViewCnt(int no);
}
