package com.mrt.mvc.cboard.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrt.mvc.cboard.model.dao.CBoardDao;
import com.mrt.mvc.cboard.model.dto.CBoard;

@Service
public class CBoardServiceImpl implements CBoardService {
	// 의존성 주입
	private CBoardDao dao;
	public CBoardServiceImpl(CBoardDao dao) {
		this.dao = dao;
	}
	
	/** 전체 게시글 조회 */
	@Override
	public List<CBoard> getCBoardList() {
		return dao.selectAll();
	}

	/** 게시글 상세 조회 */
	@Override
	public CBoard getCBoardByNo(int no) {
		return dao.selectOne(no);
	}

	/** 게시글 작성 */
	@Override
	public void writeCBoard(CBoard cBoard) {
		dao.insertCBoard(cBoard);
	}
}
