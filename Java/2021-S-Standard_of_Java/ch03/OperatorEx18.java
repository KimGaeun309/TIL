package ch03;

public class OperatorEx18 {

	public static void main(String[] args) {
		double pi = 3.141592;
		double shortPi = Math.round(pi * 1000) / 1000.0;
		System.out.println(shortPi);
	}

}
// round�޼���� �Ű������� ���� ���� �Ҽ��� ù°�ڸ����� �ݿø��פ� �ϰ� �� ����� ������ �����ִ� �޼����̴�.
// �׷��� Math.round(3141.592)�� ����� 3142�̴�.