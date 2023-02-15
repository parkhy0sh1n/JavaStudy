package practice;

import java.util.Scanner;

public class SeatGroup {
	
	private String seatType;
	private Seat[] seats;
	private Scanner sc;
	
	public SeatGroup(String seatType, int count) {
		this.seatType = seatType;
		seats = new Seat[count];
		for(int i = 0; i < count; i++	 ) {
			seats[i] = new Seat();
		}
		sc = new Scanner(System.in);
	}
	
	

}
