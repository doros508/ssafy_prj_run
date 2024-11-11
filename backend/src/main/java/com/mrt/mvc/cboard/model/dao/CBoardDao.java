package com.mrt.mvc.cboard.model.dao;

import java.util.List;

import com.mrt.mvc.cboard.model.dto.CBoard;

public interface CBoardDao {

	/** 전체 게시글 조회 */
	List<CBoard> selectAll();

	/** 전체 게시글 조회 */
	CBoard selectOne(int no);

	/** 게시글 등록 */
	void insertCBoard(CBoard cBoard);
	
	
	
}
