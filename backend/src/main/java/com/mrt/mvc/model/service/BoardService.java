package com.mrt.mvc.model.service;

import java.util.List;

import com.mrt.mvc.model.dto.Board;

public interface BoardService {

	/** 전체 게시글 조회*/
	List<Board> getCBoardList();

	/** 게시글 상세 조회*/
	Board getCBoardByNo(int no);

	void writeCBoard(Board cBoard);
	
}
