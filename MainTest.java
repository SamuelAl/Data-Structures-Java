
public class MainTest {

	public static void main(String[] args) {
		MyArray<Integer> test = new MyArray<Integer>();
		test.add(5);
		test.add(7);
		test.removeAt(0);
		System.out.println(test.get(0));
	}

}
