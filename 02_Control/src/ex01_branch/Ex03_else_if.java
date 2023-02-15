package ex01_branch;

public class Ex03_else_if {
	
	public static void ex01() {
		
		System.out.println("\n---- ex01 ----\n");
		
		// 80 이상 : 상
		// 60 이상 : 중
		// 나머지 : 하
		
		int score = 80;
		
		if(score >= 80) {
			System.out.println("상");
		} else if(score >60) {
			System.out.println("중");
		} else {
			System.out.println("하");
		}
		
	}
	
	public static void ex02() {
		
		System.out.println("\n---- ex02 ----\n");
		
		// 수, 우, 미, 양, 가, 잘못된 점수
		
		int score = 80;
		
		if(score < 0 || score > 100) {
			System.out.println("잘못된 점수");
		} else if(score >= 90) {
			System.out.println("수");
		} else if(score >= 80) {
			System.out.println("우");
		} else if(score >= 70) {
			System.out.println("미");
		} else if(score >= 60) {
			System.out.println("양");
		} else {
			System.out.println("가");
		}
		
	}

	public static void ex03() {
		
		System.out.println("\n---- ex03 ----\n");
		
		int month = 1;
		
		int mod = month % 12;
		
		if(mod <= 2 ) {
			System.out.println("겨울");
		} else if(mod <= 5) {
			System.out.println("봄");
		} else if(mod <= 8) {
			System.out.println("여름");
		} else {
			System.out.println("가을");
		}
		
	}
		
	public static void ex04() {
		
		System.out.println("\n---- ex04 ----\n");
		
		int day = 6; 
				
		int nDay = 8; // 일 후
		
		// day = day + nday;
		day += nDay;
		
		String weekName; // 월요일
		
		int mod = day % 7;
		
		if(mod == 0) {
			weekName = "토";
		} else if(mod == 1) {
			weekName = "일";
		} else if(mod == 2) {
			weekName = "월";
		} else if(mod == 3) {
			weekName = "화";
		} else if(mod == 4) {
			weekName = "수";
		} else if(mod == 5) {
			weekName = "목";
		} else {
			weekName = "금";
		}
		
		System.out.println(nDay + "일 후는 " + weekName + "요일이다.");
		
	}
	public static void main(String[] args) {
		
		ex01();
		ex02();
		ex03();
		ex04();

	}

}
