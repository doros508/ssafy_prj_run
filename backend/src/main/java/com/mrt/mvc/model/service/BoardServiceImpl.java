package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrt.mvc.model.dao.BoardDao;
import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.BoardFile;
import com.mrt.mvc.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	// 의존성 주입
	private final BoardDao dao;
	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}
	
	/** 전체 게시글 검색 조회 */
	@Override
	public List<Board> getBoardList(SearchCondition condition) {
		System.out.println("BoardService) getBoardList 메서드 호출");
		List<Board> list = dao.selectAll(condition);
		System.out.println("불러온 게시글 목록: " + list.toString());
		return list;
	}

	/** 게시글 상세 조회 */
	@Override
	public Board getBoardByNo(int no) {
		dao.updateViewCnt(no);
		Board board = dao.selectOne(no);
		System.out.println(board.toString());
		return board;
	}

	/** 게시글 작성 */
	@Transactional
	@Override
	public boolean writeBoard(Board board) {
		BoardFile boardFile = board.getBoardFile(); // 게시글 번호가 없는 상태임
		if (boardFile != null) {
			boardFile.setNo(board.getBoardNo());
			dao.insertBoardFile(boardFile);
		}
		return dao.insertBoard(board) == 1;
	}

	/** 게시글 수정 */
	@Transactional
	@Override
	public boolean modify(Board board) {
		return dao.updateBoard(board) == 1;
	}

	/** 게시글 삭제 */
	@Transactional
	@Override
	public boolean removeBoard(int no) {
		return dao.deleteBoard(no) == 1;
	}
}
