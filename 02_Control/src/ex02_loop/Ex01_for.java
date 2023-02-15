package ex02_loop;

public class Ex01_for {

	/*
	for문
	1. 연속된 숫자를 생성하는 반복문(배열에서 주로 사용)
	2. 형식
		for(초기문; 조건문; 증감문) {
			실행문
		}
	 */
	
	public static void ex01() {
		
		System.out.println("\n---- ex01 ----\n");
		
		// 1 ~ 10
		
		for(int a=1; a<=10; a++) {
			System.out.println(a);
		}
		
	}
	
	public static void ex02() {
		
		System.out.println("\n---- ex02 ----\n");
		
		// 횟수(5번)
		
		int count = 5;
		
		for(int a=0; a<count; a++) {
			System.out.println("Hello World");
		}
		
	}
	
	public static void ex03() {
		
		System.out.println("\n---- ex03 ----\n");
		
		// 10 ~ 1
		
		for(int a=10; a>=1; a--) {
			System.out.println(a);
		}
	}
	
	public static void ex04() {
		
		System.out.println("\n---- ex04 ----\n");
		
		// 구구단 2단 출력
		
		int a = 2;
		
		for(int b=1; b<=9; b++) {
				int result=a*b;
				System.out.println(a + " x " + b + " = " + result);
			}
		}
		
	
	public static void ex05() {
		
		System.out.println("\n---- ex05 ----\n");
		
		for(int a=2; a<=9; a++) {
			System.out.println("----" + a + "단" + "----");
			for(int b=1; b<=9; b++) {
				int result=a*b;
				System.out.println(a + " x " + b + " = " + result);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		ex01();
		ex02();
		ex03();
		ex04();
		ex05();

	}

}
