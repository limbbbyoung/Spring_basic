package com.ict.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.TestVO;

@RestController
@RequestMapping("/test")
public class TestController {
	
		@RequestMapping("/hello")
		public String sayHello() {
			return "Hello Hello";
		}
		
		@RequestMapping("/sendVO")
		public TestVO sendTestVO() {
			TestVO testVO = new TestVO();
			
			testVO.setName("임병영");
			testVO.setMno(1);
			testVO.setAge(25);
			return testVO;
		}
		
		@RequestMapping("/sendVOList")
		public List<TestVO> sendVOList(){
			
			List<TestVO> list = new ArrayList<>();
			for ( int i = 0; i < 10; i++) {
				TestVO vo = new TestVO();
				vo.setMno(i);
				vo.setName(i + "병영");
				vo.setAge(20+i);
				list.add(vo);
			}
			return list;
			
		}
		
		@RequestMapping("/sendMap")
		public Map<Integer, TestVO> sendMap(){
			
			Map<Integer, TestVO> map = new HashMap<>();
			
			for(int i=0; i<10; i++) {
				TestVO vo = new TestVO();
				vo.setName("임병영");
				vo.setMno(i);
				vo.setAge(20+i);
				map.put(i, vo);
			}
			System.out.println(map);
			return map;
		}
		
		@RequestMapping("/sendErrorAuth")
		public ResponseEntity<Void> sendListAuth(){
			
			return
				new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		@RequestMapping("/sendErrorNot")
		public ResponseEntity<List<TestVO>> sendListNot(){
			
			List<TestVO> list = new ArrayList<>();
			for(int i=0; i<10; i++) {
				TestVO vo = new TestVO();
				vo.setMno(i);
				vo.setName(i+"병영");
				vo.setAge(20+i);
				list.add(vo);
			}
			return
				new ResponseEntity<List<TestVO>>(list, HttpStatus.NOT_FOUND);
		}
}



