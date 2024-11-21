package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.MagazineDao;
import com.mrt.mvc.model.dto.Magazine;
import com.mrt.mvc.model.dto.SearchCondition;

@Service
public class MagazineServiceImpl implements MagazineService {

	// 의존성 주입
	private final MagazineDao dao;
	public MagazineServiceImpl(MagazineDao dao) {
		this.dao = dao;
	}
	
	// 매거진 전체 조회 및 검색 조회
	@Override
	public List<Magazine> getMagazineList(SearchCondition condition) {
		return dao.selectAll(condition);
	}

	// 매거진 상세 조회
	@Override
	public Magazine getMagazineByNo(int no) {
		dao.updateViewCnt(no);
		return dao.selectOne(no);
	}
	
}
