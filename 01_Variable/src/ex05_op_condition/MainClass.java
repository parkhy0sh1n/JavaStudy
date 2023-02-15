package ex05_op_condition;

public class MainClass {
	
	public static void ex01() {
		
		System.out.println("\n---- ex01 ----\n");
		
		int a = 3;
		int b = 4;
		
		boolean result1 = a > b;
		boolean result2 = a >= b;
		boolean result3 = a < b;
		boolean result4 = a <= b;
		boolean result5 = a == b;
		boolean result6 = a != b;
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		System.out.println(result6);
		
	}
	
	public static void ex02() {
		
		// 논리 연산
		// 1. 논리 AND : &&(모든 조건이 true이면 true, 아니면 false)
		// 2. 논리 OR : ||(하나의 조건이라도 true이면 true)
		// 3. 논리 NOT : !(조건이 true이면 false, 조건이 false이면 true)
		
		System.out.println("\n---- ex02 ----\n");
		
		int a = 10;
		int b = 10;
		
		boolean result1 = (a == 10) && (b == 10);
		boolean result2 = (a == 10) && (b == 20);
		boolean result3 = (a == 10) || (b == 10);
		boolean result4 = (a == 10) || (b == 20);
		boolean result5 = !(a == 10);
		boolean result6 = !(a == 20);
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		System.out.println(result6);
		
	}
	
	public static void ex03() {
		
		// Short Circuit Evaluation
		// 1. 논리 AND의 경우 조건의 결과가 false인 경우가 발생하면 더 이상 동작하지 않는다.(최종 결과가 false로 정해졌기 때문이다.)
		// 2. 논리 OR의 경우 조건의 결과가 true인 경우가 발생하면 더 이상 동작하지 않는다.(최종 결과가 true로 정해졌기 때문이다.)
		
		System.out.println("\n---- ex03 ----\n");
		
		int a = 10;
		int b = 10;
		
		boolean result1 = (a == 20) && (++b == 11);
		boolean result2 = (a == 10) || (++b == 11);		
		
		System.out.println(b);
		System.out.println(result1);
		System.out.println(b);
		System.out.println(result2);

	}
	
	public static void ex04() {
		
		System.out.println("\n---- ex04 ----\n");
		
		int score = 75;
		
		boolean result1 = (score >= 70) && (score <= 80); // 70점대는 true, 아니면 false
		boolean result2 = (score / 10) == 7; // 70점대는 true, 아니면 false
		System.out.println(result1);
		System.out.println(result2);
		
	}
	
	public static void ex05() {
		
		// 조건 연산
		// (조건식) ? true 일 때 처리 : false 일 때 처리
		
		
		System.out.println("\n---- ex05 ----\n");
		
		int score = 80;
		
		String result1 = (score >= 60) ? "합격" : "불합격";
		
		System.out.println(result1);
	
	}
	
	public static void ex06() {
		
		System.out.println("\n---- ex06 ----\n");
		
		int n = 3;
		
		String result = (n % 2 == 0) ? "짝수" : "홀수"; // 짝수, 홀수
		
		System.out.println(result);
	
	}
	
	public static void ex07() {
		
		System.out.println("\n---- ex07 ----\n");
	
		int n = 0; 
		
		String result = (n != 0 && n % 3 == 0) ? "3의 배수" : (n % 2 == 0) ? "짝수" : "홀수";
		
		System.out.println(result);
	
	}
	
	public static void main(String[] args) {
		
		ex01();
		ex02();
		ex03();
		ex04();
		ex05();
		ex06();
		ex07();

	}

}
