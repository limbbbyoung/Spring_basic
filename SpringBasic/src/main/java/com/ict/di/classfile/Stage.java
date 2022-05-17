package com.ict.di.classfile;

public class Stage {
	
	private Singer singer; // ���뿡 ���� ����
	
	// ����� ������ �־�� �����մϴ�.
	
	public Stage(Singer singer) {
	    this.singer = singer; // ���뿡 �� ������ �Է��ؾ� ������ ������ �����ϰ� ó��
	}

	public void perform() {
		System.out.print("���뿡�� ");
		this.singer.sing();
	}
}