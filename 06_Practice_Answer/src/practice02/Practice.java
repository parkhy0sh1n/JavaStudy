package practice02;

import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("얼마나 구입하시겠습니까? >>> ");
		int money = sc.nextInt();
		while(money > 0) {
			int row = (money >= 5000) ? 5 : money / 1000;
			int[][] lotto = new int[row][6];
			for(int i = 0; i < lotto.length; i++ ) {
				int ballCount = 45;
				int[] ball = new int[ballCount];
				for(int j = 0; j < ball.length; j++) {
					ball[j] = j + 1;
				}
				for(int j = 0; j < lotto[i].length; j++) {
					int random = (int)(Math.random() * ballCount);
					lotto[i][j] = ball[random];
					int lastIdx = 44 - j;
					if(random != lastIdx) {
						ball[random] = ball[lastIdx];
					}
					ballCount--;
				}
			}
			for(int i = 0; i < lotto.length; i++) {
				System.out.print("0" + (i + 1) + " : ");
				for(int j = 0; j < lotto[i].length; j++) {
					System.out.print(String.format("%4d", lotto[i][j]));
				}
				System.out.println();
			}
			System.out.println("----------------------------");
			money -= 5000;
		}
		sc.close();
	}
}
