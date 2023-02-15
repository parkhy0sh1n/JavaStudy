package practice;

public class Gun {
	
	private int bullet;
	public static final int MAX_BULLET = 10;
	
	public void reload(int bullet) throws RuntimeException {
		if(this.bullet + bullet > MAX_BULLET) {
			throw new RuntimeException("장전 불가능");
		}
	}
	
	public void shoot() throws RuntimeException {
		if(bullet == 0) {
			throw new RuntimeException("총알 부족");
		}
	}

	public int getBullet() {
		return bullet;
	}

	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
}
