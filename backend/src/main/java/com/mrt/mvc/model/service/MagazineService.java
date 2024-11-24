package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.Magazine;
import com.mrt.mvc.model.dto.SearchCondition;

public interface MagazineService {

	// 매거진 전체 조회 및 검색 조회
	List<Magazine> getMagazineList(SearchCondition condition);

	// 매거진 상세 조회
	Magazine getMagazineByNo(int no);

	// 매거진 등록
	boolean writeMagazine(Magazine magazine, List<MultipartFile> files);

}
