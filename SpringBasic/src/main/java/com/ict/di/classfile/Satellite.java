package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Satellite {
	
	private Broadcast broadcast;

		// Stage를 입력받아야만 생성 가능합니다.
		public Satellite(Broadcast broadcast) {
		    this.broadcast = broadcast; 
		}
		
		// 위성 방송
		public void satelliteBroad() {
			System.out.print("위성 ");
			this.broadcast.broad();
		}

}
