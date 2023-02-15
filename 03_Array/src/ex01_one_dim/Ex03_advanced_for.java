package ex01_one_dim;

public class Ex03_advanced_for {

	public static void ex01() {
		
		System.out.println("\n---- ex01 ----\n");
		
		int[] scores = {50, 90, 60, 100, 80};
		
		int total = 0;
		int max = 0;
		int min = 100;
		
		for(int n : scores) {
			total += n;
			if(max < n) {
				max = n;
			}
			if(min > n) {
				min = n;
			}
		}
		System.out.println("합계: " + total);
		System.out.println("최대: " + max);
		System.out.println("최소: " + min);
	}
	
	public static void ex02() {
		
		System.out.println("\n---- ex02 ----\n");
		
		String[] files = {"hello.txt", "hi.txt", "안녕.txt"};
		
		for(String file : files) {
			System.out.println(file);
		}
		
	}
	
	public static void main(String[] args) {
		
		ex01();
		ex02();

	}

}
