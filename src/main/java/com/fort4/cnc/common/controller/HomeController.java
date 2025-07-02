package com.fort4.cnc.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends RenderController {
	
	// 첫 진입 시 로그인 페이지
	@GetMapping("/")
	public String home(Model model) { return "member/login"; }
	
	// 로그인 성공시 인덱스로 감
	@GetMapping("/index")
	public String indexGET(Model model) { return render("index", model); }
	
}
