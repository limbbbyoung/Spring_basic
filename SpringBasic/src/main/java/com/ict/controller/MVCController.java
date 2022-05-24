package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// �� �����̳ʿ� �־��ּ���(��ϵ� ��Ʈ�ѷ��� �����մϴ�.)
@Controller
public class MVCController {
	
	// �⺻�ּ�(localhost:8181)�ڿ� /goA�� ���̸� 
	// goA() �޼��� ����
	@RequestMapping(value="/goA")
	public String goA() {
		System.out.println("goA �ּ� ���� ����");
		// ��� �������� views ���� �Ʒ��� A.jsp
		return "A";
	}
	
	// goB() ����
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB �ּ� ���� ����");
		return "goB";
	}
	
	@RequestMapping(value="/goC")
	// Model�� �������ָ� ���ε� �� ���������� .jsp���Ͽ� �����͸�
	// �߼��� �� �ֽ��ϴ�.
	public String goC(Model model) {
		// model.addAttribute("���޸�", �ڷ�);
		// ���ε��ؼ� ���� �ڷ�� .jsp���Ͽ���
		// ${��Ī}���� EL�� ����� ����� �� �ֽ��ϴ�.
		model.addAttribute("test", "goC���� ������ ���ڿ�");
		return "goC";
	}
	
	// goD�� �Ķ���͸� �Է¹��� �� �ֵ��� �غ��ڽ��ϴ�.
	@RequestMapping(value="/goD")
	// �ּ� �� ?dNum= �� ���·� ������ �ڷḦ ���� ��
	// dNum ������ �������ݴϴ�.
	// ���� �Ķ���͸� .jsp�� �����ֱ� ���ؼ��� ����
	// Model model�� �Ķ���Ϳ� �������ݴϴ�.
	public String goD(int dNum, Model model) {
		System.out.println("�ּҷ� ���޹��� �� : " + dNum);
		// ���ε����� ���� dNum�� D.jsp�� body�±� ���� ���
		model.addAttribute("dNum", dNum);
		return "D";
	}
	
	
	

}
