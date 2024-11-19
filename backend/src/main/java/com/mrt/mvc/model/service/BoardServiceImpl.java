package com.mrt.mvc.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dao.BoardDao;
import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.BoardFile;
import com.mrt.mvc.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	// 의존성 주입
	private final BoardDao dao;
	private final ResourceLoader resourceLoader;
	public BoardServiceImpl(BoardDao dao, ResourceLoader resourceLoader) {
		this.dao = dao;
		this.resourceLoader = resourceLoader;
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
		System.out.println("service: 넘어온 " + board.toString());
		return dao.insertBoard(board) == 1;
	}

	/** 게시글 수정 */
	@Transactional
	@Override
	public boolean modify(Board board) {
		System.out.println("service: 넘어온 " + board.toString());
		
		// 기존 게시글 저장
		Board tmp = dao.selectOne(board.getBoardNo());
		System.out.println("service: 임시 " + tmp.toString());
		
		// 만약 수정한 게시글에 내용이 없는 경우
		if (board.getBoardContent() == null)
			tmp.setBoardTitle(board.getBoardTitle()); // 제목만 변경
		// 수정한 게시글에 제목이 없는 경우
		if (board.getBoardTitle() == null)
			tmp.setBoardContent(board.getBoardContent()); // 내용만 변경
		
		if (board.getBoardContent() != null && board.getBoardTitle() != null) {
			tmp.setBoardTitle(board.getBoardTitle());
			tmp.setBoardContent(board.getBoardContent());
		}
		
		System.out.println("service: 수정된 " + tmp.toString());
		return dao.updateBoard(tmp) == 1;
	}

	/** 게시글 삭제 */
	@Transactional
	@Override
	public boolean removeBoard(int no) {
		return dao.deleteBoard(no) == 1;
	}

}
