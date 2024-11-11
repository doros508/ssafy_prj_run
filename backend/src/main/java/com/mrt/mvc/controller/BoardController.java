package com.mrt.mvc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.service.BoardService;

@RestController
public class BoardController {
	
	// 의존성 주입
	private final BoardService service;
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	/** 전체 게시글 조회*/
	@GetMapping("/board")
	public ResponseEntity<List<Board>> list() {
		List<Board> list = service.getCBoardList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/** 게시글 상세 조회*/
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> detail(@PathVariable("no") int no) {
		Board cBoard = service.getCBoardByNo(no);
		return new ResponseEntity<>(cBoard, HttpStatus.OK);
	}
	
	/** 게시글 등록*/
	@PostMapping("/board")
	public ResponseEntity<?> write(@ModelAttribute Board cBoard) {
		service.writeCBoard(cBoard);
	}
}
