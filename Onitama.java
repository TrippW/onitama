public class Onitama {
	private static Onitama game;

	private GUI gui;

	private Player p1, p2;

	private Board board;

	private static int p1Difficulty=Player.EASY, p2Difficulty=Player.EASY;

	private static int playerColor = Board.blue;

	private static int playerCount = 1;

	public static void main(String [] args) {
		Card.init();
		Deck.init();
		game = new Onitama();
		game.run();
	}

	public static void reset() {
		Deck.shuffle();
		game.resetGame();
		game.run();
	}

	public static void updatePlayerCount(int n) {
		playerCount = n;
	}

	public static void updateDifficulty(int newDifficulty) {
		p1Difficulty = p2Difficulty = newDifficulty;
		game.updateDifficulty();
	}

	public Onitama() {
		Card cards[] = Deck.draw();
		p1 = new Player(new Card[] {cards[0],cards[3]}, Board.red,  Player.EASY);
		p2 = new Player(new Card[] {cards[1],cards[4]}, Board.blue);

		board = null;

		if(cards[2].getColor() == Board.red)
			board = new Board(cards[2], p1, p2);
		else
			board = new Board(cards[2], p2, p1);

		gui = new GUI(cards, board);

		p1.setBoard(board);
		p2.setBoard(board);
	}

	public void run() {
		if(board.isComputerTurn())
			gui.compTurn();
	}

	private void updateDifficulty() {
		switch(playerCount) {
		case 2:
			System.out.println("No change");
			break;
		case 1:
			if(playerColor == Board.red) {
				p2.setDifficulty(p2Difficulty);
			} else {
				p1.setDifficulty(p1Difficulty);
			}
			break;
		case 0:
			p1.setDifficulty(p1Difficulty);
			p2.setDifficulty(p2Difficulty);
			break;
		}
	}

	public void resetGame() {
		Card cards[] = Deck.draw();
		switch(playerCount) {
		case 2:
			p1 = new Player(new Card[] {cards[0],cards[3]}, Board.red);
			p2 = new Player(new Card[] {cards[1],cards[4]}, Board.blue);
			break;
		case 1:
			if(playerColor == Board.red) {
				p1 = new Player(new Card[] {cards[0],cards[3]}, Board.red);
				p2 = new Player(new Card[] {cards[1],cards[4]}, Board.blue, p2Difficulty);
			} else {
				p1 = new Player(new Card[] {cards[0],cards[3]}, Board.red, p1Difficulty);
				p2 = new Player(new Card[] {cards[1],cards[4]}, Board.blue);
			}
			break;
		case 0:
			p1 = new Player(new Card[] {cards[0],cards[3]}, Board.red,  p1Difficulty);
			p2 = new Player(new Card[] {cards[1],cards[4]}, Board.blue, p2Difficulty);
			break;
		}
		board = null;

		if(cards[2].getColor() == Board.red)
			board = new Board(cards[2], p1, p2);
		else
			board = new Board(cards[2], p2, p1);

		p1.setBoard(board);
		p2.setBoard(board);

		gui.reset(cards, board);
	}
}
