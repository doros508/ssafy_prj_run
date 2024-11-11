package com.mrt.mvc.cboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrt.mvc.cboard.model.dto.CBoard;
import com.mrt.mvc.cboard.model.service.CBoardService;

@RestController
public class CBoardController {
	
	// 의존성 주입
	private final CBoardService service;
	public CBoardController(CBoardService service) {
		this.service = service;
	}
	
	/** 전체 게시글 조회*/
	@GetMapping("/board")
	public ResponseEntity<List<CBoard>> list() {
		List<CBoard> list = service.getCBoardList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/** 게시글 상세 조회*/
	@GetMapping("/board/{no}")
	public ResponseEntity<CBoard> detail(@PathVariable("no") int no) {
		CBoard cBoard = service.getCBoardByNo(no);
		return new ResponseEntity<>(cBoard, HttpStatus.OK);
	}
	
	/** 게시글 등록*/
	@PostMapping("/board")
	public ResponseEntity<?> write(@ModelAttribute CBoard cBoard) {
		service.writeCBoard(cBoard);
	}
}
