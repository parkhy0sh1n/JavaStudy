package ex02_loop;

public class Ex05_nested_loop {
	
	public static void ex01() {
		
		System.out.println("\n---- ex01 ---\n");
		
		for(int day=1; day<=5; day++) {
			for(int hour=1; hour<=8; hour++) {
				System.out.println(day + "일차 " + hour + "교시 수업");
			}
		}
		
	}
	
	public static void ex02() {
		
		System.out.println("\n---- ex02 ---\n");
		
		int day = 1;
		while(day<=5) {
			int hour = 1;
			while(hour<=8) {
				System.out.println(day + "일차 " + hour + "교시 수업");
				hour++;
			}
				day++;
		}
		
	}
	
	public static void ex03() {
		
		System.out.println("\n---- ex03 ---\n");
		
		for(int a=2; a<=9; a++) {
			System.out.println("----" + a + "단" + "----");
			for(int b=1; b<=9; b++) {
				int result = a*b;
				System.out.println(a + " x " + b + " = " + result);
			}
		}
	}
	
	
	public static void ex04() {
		
		System.out.println("\n---- ex04 ---\n");
		
		for(int a=1; a<=9; a++) {
			for(int b=2; b<=9; b++) {
				System.out.print(b + "x" + a + "=" + (b*a) + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		ex01();
		ex02();
		ex03();
		ex04();

	}

}
