package com.mrt.mvc.model.dao;

import java.util.List;

import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.BoardFile;
import com.mrt.mvc.model.dto.SearchCondition;

public interface BoardDao {

	/** 게시글 전체 조회 */
	List<Board> selectAll(SearchCondition condition);

	/** 게시글 상세 조회 */
	Board selectOne(int no);

	/** 게시글 등록 */
	void insertBoard(Board board);

	/** 게시글 수정 */
	int updateBoard(Board board);

	/** 파일 등록 */
	void insertBoardFile(BoardFile boardFile);

	/** 조회수 증가 */
	void updateViewCnt(int no);

	/** 게시글 삭제 */
	int deleteBoard(int no);
	
	
}
