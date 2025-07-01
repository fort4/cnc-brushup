package com.fort4.cnc.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends RenderController {
	
	// 첫 진입 시
	@GetMapping("/")
	public String home(Model model) { return render("member/login", model); }
	
	@GetMapping("/index")
	public String indexGET(Model model) { return render("index", model); }
	
}
