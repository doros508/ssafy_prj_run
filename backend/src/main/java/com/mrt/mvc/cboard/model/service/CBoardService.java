package com.mrt.mvc.cboard.model.service;

import java.util.List;

import com.mrt.mvc.cboard.model.dto.CBoard;

public interface CBoardService {

	/** 전체 게시글 조회*/
	List<CBoard> getCBoardList();

	/** 게시글 상세 조회*/
	CBoard getCBoardByNo(int no);

	void writeCBoard(CBoard cBoard);
	
}
