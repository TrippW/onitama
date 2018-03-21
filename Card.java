import java.util.ArrayList;

public class Card {
//Name(Set of moves, name)
	private String name;
	private int[][] moves;
	private int color;

	public static ArrayList<Card> allCards = new ArrayList<Card>();

	public static Card tiger;
	public static Card dragon;
	public static Card frog;
	public static Card rabbit;
	public static Card crab;
	public static Card elephant;
	public static Card goose;
	public static Card rooster;
	public static Card monkey;
	public static Card mantis;
	public static Card horse;
	public static Card ox;
	public static Card crane;
	public static Card boar;
	public static Card eel;
	public static Card cobra;
	public static Card giraffe;
	public static Card kirin;
	public static Card dog;
	public static Card goat;
	public static Card fox;
	public static Card sheep;
	public static Card rat;
	public static Card mouse;
	public static Card panda;
	public static Card bear;
	public static Card otter;
	public static Card sable;
	public static Card tanuki;
	public static Card iguana;
	public static Card viper;
	public static Card sea_snake;
	public static Card turtle;
	public static Card pheonix;

	public String toString() {
		return name;
	}

	public static void init() {
		tiger    = new Card(new int[][] {{0,2},  {0,-1}}                 ,"Tiger",    Board.blue);
		dragon   = new Card(new int[][] {{-2,1}, {-1,-1}, {1,-1}, {2,1}} ,"Dragon",   Board.red);
		frog     = new Card(new int[][] {{-2,0}, {-1,1},  {1,-1}}        ,"Frog",     Board.red);
		rabbit   = new Card(new int[][] {{1,1},  {-1,-1}, {2,0}}         ,"Rabbit",   Board.blue);
		crab     = new Card(new int[][] {{2,0},  {-2,0},  {0,1}}         ,"Crab",     Board.blue);
		elephant = new Card(new int[][] {{1,0},  {-1,0},  {1,1},  {-1,1}},"Elephant", Board.red);
		goose    = new Card(new int[][] {{-1,1}, {1,-1},  {-1,0}, {1,0}} ,"Goose",    Board.blue);
		rooster  = new Card(new int[][] {{-1,-1},{1,1},   {-1,0}, {1,0}} ,"Rooster",  Board.red);
		monkey   = new Card(new int[][] {{1,1},  {-1,-1}, {1,-1}, {-1,1}},"Monkey",   Board.blue);
		mantis   = new Card(new int[][] {{0,-1}, {-1,1},  {1,1}}         ,"Mantis",   Board.red);
		horse    = new Card(new int[][] {{-1,0}, {0,1},   {0,-1}}        ,"Horse",    Board.red);
		ox       = new Card(new int[][] {{1,0},  {0,1},   {0,-1}}        ,"Ox",       Board.blue);
		crane    = new Card(new int[][] {{-1,-1},{1,-1},  {0,1}}         ,"Crane",    Board.blue);
		boar     = new Card(new int[][] {{-1,0}, {1,0},   {0,1}}         ,"Boar",     Board.red);
		eel      = new Card(new int[][] {{-1,1}, {-1,-1}, {1,0}}         ,"Eel",      Board.blue);
		cobra    = new Card(new int[][] {{1,1},  {1,-1},  {-1,0}}        ,"Cobra",    Board.red);
		giraffe  = new Card(new int[][] {{-2,1}, {0,-1},  {2,1}}		 ,"Giraffe",  Board.blue);
		kirin	 = new Card(new int[][] {{-1,2}, {0,-2},  {1,2}}		 ,"Kirin",    Board.red);
		dog		 = new Card(new int[][] {{-1,1}, {-1,0},  {-1,-1}}		 ,"Dog",	  Board.blue);
		goat	 = new Card(new int[][] {{-1,0}, {0,-1},  {1,1}}		 ,"Goat",	  Board.red);
		fox		 = new Card(new int[][] {{1,1},  {0,1},   {1,-1}}		 ,"Fox",	  Board.red);
		sheep	 = new Card(new int[][] {{-1,1}, {0,-1},  {1,0}}		 ,"Sheep",	  Board.blue);
		rat		 = new Card(new int[][] {{-1,0}, {0,1},   {1,-1}}		 ,"Rat",	  Board.red);
		mouse	 = new Card(new int[][] {{-1,-1},{0,1},   {1,0}}		 ,"Mouse",	  Board.blue);
		panda	 = new Card(new int[][] {{-1,-1},{0,1},   {1,1}}		 ,"Panda",	  Board.red);
		bear	 = new Card(new int[][] {{-1,1}, {0,1},   {1,-1}}		 ,"Bear",	  Board.blue);
		otter	 = new Card(new int[][] {{-1,1}, {1,-1},  {2,0}}		 ,"Otter",	  Board.red);
		sable	 = new Card(new int[][] {{-2,0}, {-1,-1}, {1,1}}		 ,"Sable",	  Board.blue);
		tanuki	 = new Card(new int[][] {{-1,-1},{0,1},   {2,1}}		 ,"Tanuki",   Board.blue);
		iguana	 = new Card(new int[][] {{-2,1}, {0,1},   {1,-1}}		 ,"Iguana",	  Board.red);
		viper	 = new Card(new int[][] {{-2,0}, {0,1},   {1,-1}}        ,"Viper",	  Board.red);
		sea_snake= new Card(new int[][] {{-1,-1},{0,1},   {2,0}}		 ,"Sea Snake",Board.blue);
		turtle	 = new Card(new int[][] {{-2,0}, {-1,-1}, {1,-1}, {2,0}} ,"Turtle",   Board.red);
		pheonix	 = new Card(new int[][] {{-2,0}, {-1,1},  {1,1},  {2,0}} ,"Pheonix",  Board.blue);
	}

	Card(int [][] moves, String name, int color) {
		this.moves = moves;
		this.name  = name;
		this.color = color;

		allCards.add(this);
	}

	public Card(Card c) {
		moves = c.getMoves();
		name  = c.getName();
		color = c.getColor();
	}

	public int [][] getMoves() {
		return moves;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public String getColorString() {
		String col = "Blue";
		if(color == Board.red) col = "Red";
		return col;
	}

	public static Card getCardByName(String name) {
		for(Card c : allCards)
			if(c.name == name)
				return c;
		return null;
	}

	public boolean equals(Card c) {
		return c.getName().equals(name);
	}
}

