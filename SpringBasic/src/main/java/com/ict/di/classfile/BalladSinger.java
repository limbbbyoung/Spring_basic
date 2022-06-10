package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class BalladSinger extends Singer{
	
	@Override
	public void sing() {
		System.out.println("발라드가수가 무대에서 노래를 합니다.");
	}

}
