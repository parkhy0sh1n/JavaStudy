package ex06_op_stiring;

public class MainClass {
	
	public static void ex01() {
		
		// 연결 연산
		// 문자열과 + 연산자가 만나면 문자열로 연결된다.
		
		
		System.out.println("\n---- ex01 ----\n");
		
		String str1 = "구디 " + "아카데미";
		String str2 = 4 + "달러";
		String str3 = 100 + 10 + "번지";
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		
	}
	
	public static void ex02() {
		
		// 모든 타입의 데이터는 빈 문자열("")을 + 시키면 문자열이 된다.
		// String str = String.valueOf(a); (개발자들은 잘 사용하지 않는다.)
		
		System.out.println("\n---- ex02 ----\n");
		
		int a = 100;
		String str = a + "";
		
		System.out.println(str);
		
	}
	
	public static void ex03() {
		
		// 문자열의 비교는 == 또는 != 으로 하지 않는다.
		
		System.out.println("\n---- ex03 ----\n");
		
		String str1 = "hello";
		String str2 = new String("hello"); 
		
		System.out.println(str1 = "hello"); // true
		System.out.println(str2 = "hello"); // false
	
		
	}
	
	public static void main(String[] args) {

		ex01();
		ex02();
		ex03();
		
	}

}
