package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class Rapper extends Singer{
	
	@Override
	public void sing() {
		System.out.println("래퍼가 무대에서 랩을 합니다.");
	}

}
