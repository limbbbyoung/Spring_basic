package com.ict.di;

import com.ict.di.classfile.BalladSinger;
import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Rapper;
import com.ict.di.classfile.Satellite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainJavaver {

	public static void main(String[] args) {
		// Singer�� �����ؼ� �뷡�ϰ� ��������.
		Singer singer = new Singer();
		singer.sing();
		
		// Stage�� ���� ������ ���Ѻ�����.
		Stage stage = new Stage(singer);
		stage.perform();
		
		// Broadcast�� �����ؼ� ��۹��븦 �����غ��ڽ��ϴ�.
		Broadcast broadcast1 = new Broadcast(stage);
		broadcast1.broad();
		
		//Satellite
		Satellite satellite = new Satellite(broadcast1);
		satellite.satelliteBroad();

	}

}
