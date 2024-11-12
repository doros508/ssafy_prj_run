package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.BoardDao;
import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.BoardFile;

@Service
public class BoardServiceImpl implements BoardService {
	// 의존성 주입
	private final BoardDao dao;
	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}
	
	/** 전체 게시글 조회 */
	@Override
	public List<Board> getBoardList() {
		System.out.println("게시글 전체 조회(service)");
		List<Board> list = dao.selectAll();
		System.out.println(list.toString());
		return list;
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
		BoardFile boardFile = board.getBoardFile(); // 게시글 번호가 없는 상태임
		if (boardFile != null) {
			boardFile.setNo(board.getBoardNo());
			dao.insertBoardFile(boardFile);
		}
		
	}

	/** 게시글 수정 */
	@Override
	public void modify(Board board) {
		dao.updateBoard(board);
	}
}
