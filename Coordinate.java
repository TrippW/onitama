public class Coordinate {
	public static final Coordinate blueEnd = new Coordinate(2,4);
	public static final Coordinate redEnd = new Coordinate(2,0);

	private int x,y;

	public Coordinate(int x, int y) {
		this(new int[] {x,y});
	}

	public Coordinate(int[] pair) {
		setPair(pair);
	}

	public Coordinate(Coordinate c) {
		this(c.getPair());
	}

	public void setPair(int[] pair) {
		setPair(pair[0],pair[1]);
	}

	public boolean move(int[] move, int color) {
		return move(move[0], move[1], color);
	}

	public boolean move(int dx, int dy, int color) {
		int newX = x;
		int newY = y;

		newX += dx*-1*color;
		newY += dy*color;

		if((newX >= 0 && newX < 5) && (newY >= 0 && newY < 5)) {
			setPair(newX, newY);
			return true; // worked propperly
		}
		return false;
	}

	public void setPair(int x, int y) {
		try {
			setX(x);
			setY(y);
		} catch(Exception e) {
			e.getStackTrace();
			System.out.println("Problem setting Coordinate pair");
			System.out.printf("x:%d\ty:%d%n",x,y);
			System.exit(1);
		}
	}

	private void setX(int n) throws Exception {
		if(n < 0 || n >= 5) throw new Exception("Coordinate x is out of bounds.");
		x = n;
	}

	private void setY(int n) throws Exception {
		if(n < 0 || n >= 5) throw new Exception("Coordinate y is out of bounds.");
		y = n;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[] getPair() {
		return new int[] {x,y};
	}

	public int compare(Coordinate c) {
		return (c.getX()-x)*(c.getX()-x)+(c.getY()-y)*(c.getY()-y);
	}

	public boolean equals(Coordinate c) {
		return compare(c) == 0;
	}

	public String toString() {
		return "("+x+","+y+")";
	}
}
