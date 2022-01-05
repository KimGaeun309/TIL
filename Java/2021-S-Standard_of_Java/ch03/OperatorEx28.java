package ch03;

public class OperatorEx28 {

	public static void main(String[] args) {
		int x = 0xAB, y = 0xF;
		
		System.out.printf("x = %#X \t\t%s%n", x, toBinaryString(x));
		System.out.printf("y = %#X \t\t%s%n", y, toBinaryString(y));
		System.out.printf("%#X | %#X = %#X \t%s%n", x, y, x | y, toBinaryString(x | y));
		System.out.printf("%#X & %#X = %#X \t%s%n", x, y, x & y, toBinaryString(x & y));
		System.out.printf("%#X ^ %#X \t%s%n", x, y, x ^ y, toBinaryString(x ^ y));
		System.out.printf("%#X ^ %#X ^ %#X = %#X %s%n", x, y, y, x ^ y ^ y, toBinaryString(x ^ y ^ y));
	} // main �Լ��� ��
	
	static String toBinaryString(int x) { // 10�� ������ 2������ ��ȯ�ϴ� �޼���
		String zero = "0000000000000000000000000000000";
		String tmp = zero + Integer.toBinaryString(x); // Integer Ŭ������ toBinaryString()�޼���.
		                                                   //10���� -> 2���� String
		return tmp.substring(tmp.length()-32); // ���ڿ� �ڸ��� �Լ�.
	}

}

