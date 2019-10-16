package kr.or.connect.guestbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.connect.guestbook.dto.Guestbook;

@Controller
public class GuestbookController {
	
	@RequestMapping(path="/index", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(path="/list", method=RequestMethod.GET)
	public String list(int num, ModelMap model) {
		return "list";
	}
	
	@RequestMapping(path="/list", method=RequestMethod.POST)
	public String write(Guestbook gb, HttpServletRequest req) {
		return "list";
	}

}
