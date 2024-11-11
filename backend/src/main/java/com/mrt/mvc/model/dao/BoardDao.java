package com.mrt.mvc.model.dao;

import java.util.List;

import com.mrt.mvc.model.dto.Board;

public interface BoardDao {

	/** 전체 게시글 조회 */
	List<Board> selectAll();

	/** 전체 게시글 조회 */
	Board selectOne(int no);

	/** 게시글 등록 */
	void insertBoard(Board board);

	/** 게시글 수정 */
	void updateBoard(Board board);
	
	
	
}
