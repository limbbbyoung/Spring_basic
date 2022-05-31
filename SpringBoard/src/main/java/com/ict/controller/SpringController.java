package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// �� ��Ʈ�ѷ� ���� ��� �޼ҵ�� �����ּҷ� �տ� /spring/�� �����ϴ�.
@RequestMapping("/spring/*")
public class SpringController {
	
	@RequestMapping("")
	public void basic() {
		System.out.println("�⺻ URL �ּ��Դϴ�.");
	}
	
	@RequestMapping(value="/base",
			method= {RequestMethod.GET, RequestMethod.POST})
	public void baseGet() {
		System.out.println("base get");
	}
}
