package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.BoardDao;
import com.mrt.mvc.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	// 의존성 주입
	private BoardDao dao;
	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}
	
	/** 전체 게시글 조회 */
	@Override
	public List<Board> getBoardList() {
		return dao.selectAll();
	}

	/** 게시글 상세 조회 */
	@Override
	public Board getBoardByNo(int no) {
		return dao.selectOne(no);
	}

	/** 게시글 작성 */
	@Override
	public void writeBoard(Board board) {
		dao.insertBoard(board);
	}

	/** 게시글 수정 */
	@Override
	public void modify(Board board) {
		dao.updateBoard(board);
	}
}