package ex01_Thread;

public class Main {

	public static void main(String[] args) {

		Calculator c = new Calculator("조우민"); // c 쓰레드 생성
		
		c.start(); // start 메소드는 run 메소드를 스스로 호출한다. main은 c 쓰레드의 동작 시작만 처리하고 동작 완료까지 기다리지 않는다.
		
		Calculator c2 = new Calculator("민우조");
		c2.start();
		
		System.out.println("메인 메소드 종료");
	}

}
