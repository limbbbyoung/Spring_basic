package com.ict.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Satellite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainSpringver {

	public static void main(String[] args) {
		// root-context�� ��������� �̸� ���� �迭�� ��θ� �����α�
		String[] path = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
				"file:src/main/webapp/WEB-INF/spring/root-context2.xml"};
		
		
		// �� �����̳ʿ� ����ִ� ��ü�� ������ ���� ȣ��� ����
		// ��ǥ �� �����̳�(root-context.xml)�� ��θ� �����༭ ���� �������̳ʿ� ����ϵ��� ����
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext(path);
		
		/*GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext(file:src/main/webapp/WEB-INF/spring/root-*.xml); 
			*�� ��������� root-�ڿ� �ٴ� � ���̵� ó�����ִ� �ڵ� */

		// Singer���� �ٷ� ���̷�Ʈ�� Stage�� �����ڽ��ϴ�.
		/*
		Stage stage = context.getBean("stage", Stage.class);
		stage.perform();
		
		Singer singer = context.getBean("singer", Singer.class);
		singer.sing();
		
		Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
		broadcast.broad();
		*/
		
		/*Satellite satellite = context.getBean("satellite", Satellite.class);
		satellite.satelliteBroad();*/
		
		Broadcast broadcast = context.getBean("balladBroadcast", Broadcast.class);
		broadcast.broad();
	}

}
