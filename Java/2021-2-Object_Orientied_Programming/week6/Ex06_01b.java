package Week6;

class MyCounter {
	int counter;
	
	MyCounter(int value) {
		counter = value;
	}
}

public class Ex06_01b {

	public static void main(String[] args) {
		MyCounter obj1 = new MyCounter(100);
		MyCounter obj2 = new MyCounter(200);
		
		System.out.println("��ü 1�� counter = " + obj1.counter);
		System.out.println("��ü 2�� counter = " + obj2.counter);
	}

}
