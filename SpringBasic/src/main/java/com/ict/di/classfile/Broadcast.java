package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Broadcast {
	
	@Autowired
	private Stage stage; // 무대에 서는 가수

		// Stage를 입력받아야만 생성 가능합니다.
		public Broadcast(Stage stage) {
		    this.stage = stage; // 무대에 설 가수를 입력해야 생성자 실행이 가능하게 처리
		}
		// 방송용 까지만 자체적으로 담당
		// 이후 stage의 perform메서드가 담당
		
		public void broad() {
			System.out.print("방송용 ");
			this.stage.perform();
		}

	}

