package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Broadcast {
	
	@Autowired
	private Stage stage; // ���뿡 ���� ����

		// Stage�� �Է¹޾ƾ߸� ���� �����մϴ�.
		public Broadcast(Stage stage) {
		    this.stage = stage; // ���뿡 �� ������ �Է��ؾ� ������ ������ �����ϰ� ó��
		}
		// ��ۿ� ������ ��ü������ ���
		// ���� stage�� perform�޼��尡 ���
		
		public void broad() {
			System.out.print("��ۿ� ");
			this.stage.perform();
		}

	}

