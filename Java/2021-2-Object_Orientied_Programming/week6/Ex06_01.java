package Week6;

class Television {
	int channel;
	int volume;
	boolean onOff;
}

public class Ex06_01 {

	public static void main(String[] args) {
		Television tv = new Television();
		tv.channel = 7;
		tv.volume = 9;
		tv.onOff = true;
		System.out.println("�ڷ������� ä���� " + tv.channel + "�̰� ������ " + tv.volume + "�Դϴ�..");

	}

}