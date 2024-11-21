package com.mrt.mvc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		if (list == null || list.size() == 0) {
			System.out.println("리스트가 비어있습니다.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
