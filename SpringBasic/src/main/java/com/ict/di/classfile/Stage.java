package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
public class Stage {
	
	// �� �����̳� ���ο��� �ڵ����� Stage ���ο� Singer�� ��������.
	//@Autowired
	//@Qualifier("rapper")
	private Singer singer; // ���뿡 ���� ����
	
	//Qualifier�� ���Ŷ�� �ƹ��͵� �Է¹��� �ʰ� �ƹ� ���൵ ���� �ʴ� �����ڸ� �߰��ؾ� �մϴ�.
	public Stage() {
		
	}
	
	// ����� ������ �־�� �����մϴ�.
	// public Stage(Singer singer) {
	//     this.singer = singer; // ���뿡 �� ������ �Է��ؾ� ������ ������ �����ϰ� ó��
	// }

	public void setSinger(Singer singer) {
		this.singer = singer;
	}
	
	public void perform() {
		System.out.print("���뿡�� ");
		this.singer.sing();
	}
}
