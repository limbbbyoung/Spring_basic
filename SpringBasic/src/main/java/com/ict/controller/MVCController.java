package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// 빈 컨테이너에 넣어주세요(등록된 컨트롤러만 동작합니다.)
@Controller
public class MVCController {
	
	// 기본주소(localhost:8181)뒤에 /goA를 붙이면 
	// goA() 메서드 실행
	@RequestMapping(value="/goA")
	public String goA() {
		System.out.println("goA 주소 접속 감지");
		// 결과 페이지는 views 폴더 아래의 A.jsp
		return "A";
	}
	
	// goB() 접속
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB 주소 접속 감지");
		return "goB";
	}
	
	@RequestMapping(value="/goC")
	// Model을 선언해주면 바인딩 및 포워딩으로 .jsp파일에 데이터를
	// 발송할 수 있습니다.
	public String goC(Model model) {
		// model.addAttribute("전달명", 자료);
		// 바인딩해서 보낸 자료는 .jsp파일에서
		// ${명칭}으로 EL을 사용해 출력할 수 있습니다.
		model.addAttribute("test", "goC에서 보내온 문자열");
		return "goC";
	}
	
	// goD는 파라미터를 입력받을 수 있도록 해보겠습니다.
	@RequestMapping(value="/goD")
	// 주소 뒤 ?dNum= 값 형태로 들어오는 자료를 로직 내
	// dNum 변수에 대입해줍니다.
	// 들어온 파라미터를 .jsp로 보내주기 위해서는 역시
	// Model model을 파라미터에 선언해줍니다.
	public String goD(int dNum, Model model) {
		System.out.println("주소로 전달받은 값 : " + dNum);
		// 바인딩으로 받은 dNum을 D.jsp의 body태그 내에 출력
		model.addAttribute("dNum", dNum);
		return "D";
	}
	
	// post방식 요청만 처리하게 하고 싶다면 method 속성을 추가합니다.
	
	@RequestMapping(value="/ctof", method=RequestMethod.POST)
	public String cToF(double C, Model model) {
		double F = C*1.8 + 32;
		
		model.addAttribute("C", C);
		model.addAttribute("F", F);
		return "ctof";
	}
	
	// 같은 주소이면서 처리 방식이 다른 메서드를 만듦으로서 
	// 핵심이 되어 있는 실행 로직이 드러나지 않게 하는 것. 접속이 불가능하도록 만드는 것이 목적임
	// 폼을 만들어서 폼에서 입력된 온도를 그대로 섭씨온도로 처리하도록 만듦
	@RequestMapping(value="/ctof", method=RequestMethod.GET)
	public String cToFform() {
		return "ctofform";
	}
	
	// 위와 같은 방식으로 bmi 측정 페이지를 만들어보겠습니다.
	// 폼페이지와 결과페이지 조합으로 구성되며 접근주소는 /bmi로 통일합니다.
	// bmi공식은 체중 / 키(m) * 2로 나오는 결과입니다.
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
	
	// PathVariable을 이용하면 url패턴만으로도 특정 파라미터를 받아올 수 있습니다.
	// rest방식으로 url을 처리할때 주로 사용하는 방식입니다.
	// /pathtest/숫자    중 숫자 위치에 온 것을 page변수에 전달할 값으로 간주합니다.
	@RequestMapping(value="/pathtest/{page}")
	// int page 왼쪽에 @PathVariable을 붙여야 연동됨 
	public String pathTest(@PathVariable int page, Model model) {
		System.out.println(page);
		
		// 받아온 page변수를 path.jsp로 보내주세요.
		// path.jsp에는 {page} 페이지 조회중입니다 라는 문장이 뜨게 해주세요.
		model.addAttribute("page", page);
		return "path";
	}
	

}
