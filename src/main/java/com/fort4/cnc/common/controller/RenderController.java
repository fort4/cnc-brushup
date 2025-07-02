package com.fort4.cnc.common.controller;

import org.springframework.ui.Model;

public abstract class RenderController {
	
	/**
	 * 
	 * @param viewName
	 * @param model
	 * @return layout.html
	 */
	protected String render(String viewName, Model model) 
	{
		model.addAttribute("viewName", viewName);
		return "layout/layout";
	}
}
