package com.mrt.mvc.model.dao;

import java.util.List;

import com.mrt.mvc.model.dto.Magazine;
import com.mrt.mvc.model.dto.SearchCondition;

public interface MagazineDao {
	
	// 게시글 전체 조회 및 검색 조회
	List<Magazine> selectAll(SearchCondition condition);
}
