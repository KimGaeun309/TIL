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
		System.out.println("텔레비전의 채널은 " + tv.channel + "이고 볼륨은 " + tv.volume + "입니다..");

	}

}
