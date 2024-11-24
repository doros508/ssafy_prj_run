package com.mrt.mvc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mrt.mvc.model.dto.Board;
import com.mrt.mvc.model.dto.Magazine;
import com.mrt.mvc.model.dto.SearchCondition;
import com.mrt.mvc.model.service.MagazineService;

@RestController
@RequestMapping("/maratalk")
public class MagazineRestController {
	
	// 의존성 주입
	private final MagazineService service;
	public MagazineRestController(MagazineService service) {
		this.service = service;
	}
	
	@GetMapping("/magazine")
	public ResponseEntity<List<Magazine>> list(@ModelAttribute SearchCondition condition) {
		List<Magazine> list = service.getMagazineList(condition);
		System.out.println(list.toString());
		if (list == null || list.size() == 0) {
			System.out.println("리스트가 비어있습니다.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/magazine/{no}")
	public ResponseEntity<Magazine> detail(@PathVariable("no") int no) {
		Magazine magazine = service.getMagazineByNo(no);
		System.out.println("넘어온 매거진: " + magazine.toString());
		if (magazine != null) {
			System.out.println(no + "번 매거진을 조회합니다.");
			return new ResponseEntity<>(magazine, HttpStatus.OK);
		}
		System.out.println("조회한 게시글이 없습니다.");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/magazine")
	public ResponseEntity<String> write(
	    @RequestPart("magazine") Magazine magazine,
	    @RequestPart(value = "files", required = false) List<MultipartFile> files // 파일이 없을 수 있기 때문에 'required = false' 설정
	) {
	    System.out.println("게시글: " + magazine.toString());
	    
	    // 파일이 없을 경우, 빈 리스트나 null을 처리
	    if (files == null || files.isEmpty()) {
	        System.out.println("첨부된 파일이 없습니다.");
	    } else {
	        System.out.println("첨부된 파일은 " + files.size() + "개 입니다.");
	    }

	    // 매거진 등록 처리
	    boolean result = service.writeMagazine(magazine, files);
	    
	    if (result) {
	        return new ResponseEntity<>(magazine.getMagazineNo() + "번 게시글이 작성되었습니다.", HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>("게시글 작성에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
