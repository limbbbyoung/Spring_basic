package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class Rapper extends Singer{
	
	@Override
	public void sing() {
		System.out.println("���۰� ���뿡�� ���� �մϴ�.");
	}

}
