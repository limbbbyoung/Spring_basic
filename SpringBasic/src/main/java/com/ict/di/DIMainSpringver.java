package com.ict.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Satellite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainSpringver {

	public static void main(String[] args) {
		// root-context가 여러개라면 미리 문자 배열로 경로를 만들어두기
		String[] path = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
				"file:src/main/webapp/WEB-INF/spring/root-context2.xml"};
		
		
		// 빈 컨테이너에 들어있는 객체를 꺼내기 위해 호출기 생성
		// 목표 빈 컨테이너(root-context.xml)의 경로를 적어줘서 그쪽 빈컨테이너와 통신하도록 설정
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext(path);
		
		/*GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext(file:src/main/webapp/WEB-INF/spring/root-*.xml); 
			*을 집어넣으면 root-뒤에 붙는 어떤 것이든 처리해주는 코드 */

		// Singer없이 바로 다이렉트로 Stage를 만들어보겠습니다.
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
