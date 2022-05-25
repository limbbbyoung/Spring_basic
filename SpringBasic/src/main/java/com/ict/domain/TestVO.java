package com.ict.domain;

import lombok.Data;

// lombok�� �̿��ϸ� �ڵ����� getter, setter, toString�� ���� �� �ֽ��ϴ�.
// �Ʒ��� ���� @Data�� Ŭ������ ���� ���̸� �ڵ������ǰ�
// ���� package explorer���� �������θ� Ȯ�� �����մϴ�.
@Data
public class TestVO {
	
	private String name;
	private int age;
	private int level;
	// �����е��� TestVO�� ���������
	// �ϳ� �� �߰��ؼ� ���� �޾ƺ�����.
	private String skill;
	

}
