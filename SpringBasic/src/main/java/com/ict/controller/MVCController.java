package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// post��� ��û�� ó���ϰ� �ϰ� �ʹٸ� method �Ӽ��� �߰��մϴ�.
	
	@RequestMapping(value="/ctof", method=RequestMethod.POST)
	public String cToF(double C, Model model) {
		double F = C*1.8 + 32;
		
		model.addAttribute("C", C);
		model.addAttribute("F", F);
		return "ctof";
	}
	
	// ���� �ּ��̸鼭 ó�� ����� �ٸ� �޼��带 �������μ� 
	// �ٽ��� �Ǿ� �ִ� ���� ������ �巯���� �ʰ� �ϴ� ��. ������ �Ұ����ϵ��� ����� ���� ������
	// ���� ���� ������ �Էµ� �µ��� �״�� �����µ��� ó���ϵ��� ����
	@RequestMapping(value="/ctof", method=RequestMethod.GET)
	public String cToFform() {
		return "ctofform";
	}
	
	// ���� ���� ������� bmi ���� �������� �����ڽ��ϴ�.
	// ���������� ��������� �������� �����Ǹ� �����ּҴ� /bmi�� �����մϴ�.
	// bmi������ ü�� / Ű(m) * 2�� ������ ����Դϴ�.
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String BMI(double kg,@RequestParam("height") double cm, Model model) {
		double m = cm / 100.0;
		double BMI = kg / (m * m);
		
		model.addAttribute("kg", kg);
		model.addAttribute("cm", cm);
		model.addAttribute("BMI", BMI);
		
		return "bmi";
	}
	
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String BMIform() {
		return "bmiform";
	}
	
	// PathVariable�� �̿��ϸ� url���ϸ����ε� Ư�� �Ķ���͸� �޾ƿ� �� �ֽ��ϴ�.
	// rest������� url�� ó���Ҷ� �ַ� ����ϴ� ����Դϴ�.
	// /pathtest/����    �� ���� ��ġ�� �� ���� page������ ������ ������ �����մϴ�.
	@RequestMapping(value="/pathtest/{page}")
	// int page ���ʿ� @PathVariable�� �ٿ��� ������ 
	public String pathTest(@PathVariable int page, Model model) {
		System.out.println(page);
		
		// �޾ƿ� page������ path.jsp�� �����ּ���.
		// path.jsp���� {page} ������ ��ȸ���Դϴ� ��� ������ �߰� ���ּ���.
		model.addAttribute("page", page);
		return "path";
	}
	

}
