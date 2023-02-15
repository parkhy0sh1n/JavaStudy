package practice;

public class Seat {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void cancel() {
		name = null;
	}
	
	public boolean isOccupied() {
		return name != null;
	}
	
	public boolean isMatched(String name) {
		return name.equals(this.name);
	}

}
