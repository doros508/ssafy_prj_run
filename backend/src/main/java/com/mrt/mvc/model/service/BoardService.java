package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.SearchCondition;

public interface BoardService {

	/** 전체 게시글 조회*/
	List<Board> getBoardList(SearchCondition condition);

	/** 게시글 상세 조회*/
	Board getBoardByNo(int no);

	/** 게시글 등록 */
	boolean writeBoard(Board board);
//	boolean writeBoard(Board board, MultipartFile file);

	/** 게시글 수정 */
	boolean modify(Board board);

	/** 게시글 삭제 */
	boolean removeBoard(int no);	
}
