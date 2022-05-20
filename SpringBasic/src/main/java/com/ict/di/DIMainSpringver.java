package com.ict.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Satellite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainSpringver {

	public static void main(String[] args) {
		// �� �����̳ʿ� ����ִ� ��ü�� ������ ���� ȣ��� ����
		// ��ǥ �� �����̳�(root-context.xml)�� ��θ� �����༭ ���� �������̳ʿ� ����ϵ��� ����
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");

		// Singer���� �ٷ� ���̷�Ʈ�� Stage�� �����ڽ��ϴ�.
		/*
		Stage stage = context.getBean("stage", Stage.class);
		stage.perform();
		
		Singer singer = context.getBean("singer", Singer.class);
		singer.sing();
		
		Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
		broadcast.broad();
		*/
		
		Satellite satellite = context.getBean("satellite", Satellite.class);
		satellite.satelliteBroad();
	}

}
