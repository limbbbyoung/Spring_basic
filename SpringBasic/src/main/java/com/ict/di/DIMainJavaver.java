package com.ict.di;

import com.ict.di.classfile.BalladSinger;
import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Rapper;
import com.ict.di.classfile.Satellite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainJavaver {

	public static void main(String[] args) {
		// Singer를 생성해서 노래하게 만들어보세요.
		Singer singer = new Singer();
		singer.sing();
		
		// Stage도 만들어서 공연을 시켜보세요.
		Stage stage = new Stage(singer);
		stage.perform();
		
		// Broadcast를 생성해서 방송무대를 송출해보겠습니다.
		Broadcast broadcast1 = new Broadcast(stage);
		broadcast1.broad();
		
		//Satellite
		Satellite satellite = new Satellite(broadcast1);
		satellite.satelliteBroad();

	}

}
