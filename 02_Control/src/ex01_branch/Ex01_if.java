package ex01_branch;

public class Ex01_if {
	
	public static void ex01() {

		// if문
		
		System.out.println("\n---- ex01 ----\n");
		
		int score = 80;
		
		if(score >= 60) {
			System.out.println("합격");
		}
		
		if(score < 60) {
			System.out.println("불합격");
		}
		
	}
	
	public static void ex02() {
		
		// if문의 중괄호
		// 실행문이 1개인 경우 생략할 수 있다.
		// 살행문이 2개 이상인 경우 반드시 필요하다.
		
		System.out.println("\n---- ex02 ----\n");
		
		int score = 80;
		
		if(score >= 60) {
			System.out.println("합격");
			System.out.println("축하합니다");
		}
		
		if(score < 60) {
			System.out.println("불합격");
			System.out.println("보충 수업입니다.");
		}
		
	}

	public static void main(String[] args) {
		
		ex01();
		ex02();
		
		
	}

}
