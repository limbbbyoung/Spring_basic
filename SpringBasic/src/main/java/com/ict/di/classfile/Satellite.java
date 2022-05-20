package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Satellite {
	
	private Broadcast broadcast;

		// Stage�� �Է¹޾ƾ߸� ���� �����մϴ�.
		public Satellite(Broadcast broadcast) {
		    this.broadcast = broadcast; 
		}
		
		// ���� ���
		public void satelliteBroad() {
			System.out.print("���� ");
			this.broadcast.broad();
		}

}
