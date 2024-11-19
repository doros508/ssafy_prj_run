package com.mrt.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrt.mvc.model.service.MagazineService;

@RestController
@RequestMapping("/maratalk")
public class MagazineRestController {
	
	// 의존성 주입
	private final MagazineService service;
	public MagazineRestController(MagazineService service) {
		this.service = service;
	}
	
}
