package weekA;
import java.io.FileWriter; //���� �������
import java.io.IOException;
import java.io.PrintWriter; // ���Ͽ� �ؽ�Ʈ�� ���°� ������
// �̰� 3���ÿ��� ��� ����.

public class FileError {
	private int[] list;
	private static final int SIZE = 10;
	
	public FileError() {
		list = new int[SIZE];
		for(int i=0; i<SIZE; i++)
			list[i] = i;
		writeList();
	}
	
	public void writeList() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("outfile.txt"));
			for (int i=0; i<SIZE; i++)
				out.println("�迭 ���� " + i + " = " + list[i]);
		} catch (ArrayIndexOutOfBoundsException e) {
			out.println("ArrayIndexOutOfBoundsException;");
		} catch (IOException e) {
			System.err.println("IOException;");
		} finally {
			if (out != null)
				out.close();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}