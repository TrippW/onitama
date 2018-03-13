import java.awt.*;

public class Board {
	public static final int red  = 1;
	public static final int blue = -1;

	private Piece board [][] = new Piece[5][5];

	private boolean containsBlueKing = true;
	private boolean containsRedKing  = true;

	private boolean blueWin = false;
	private boolean redWin = false;

	private Card boardCard;

	private Player curPlayer;
	private Player offPlayer;

	private Player aiCurPlayer;
	private Player aiOffPlayer;

	private Coordinate [] aiCoord;
	private Card aiCard;

	public Board(Board b) {
		boardCard = new Card(b.getBoardCard());

		for(int i = 0; i < 25; i++)
			if(b.getPiece(i%5,i/5) != null)
				board[i/5][i%5] = new Piece(b.getPiece(i%5,i/5));

		curPlayer = new Player(b.getCurrentPlayer());
		offPlayer = new Player(b.getOffPlayer());
	}

	public Board(Card inbetween, Player starter, Player off) {
		curPlayer = starter;
		offPlayer = off;
		boardCard = inbetween;

		for(int i = 0; i < 5; i++) {
			board[0][i] = new Piece(new Coordinate(i,0),  red, i == 2);
			board[4][i] = new Piece(new Coordinate(i,4), blue, i == 2);
		}

	}

	public Card getBoardCard() {
		return boardCard;
	}

	public Player getCurrentPlayer() {
		return curPlayer;
	}

	public Player getOffPlayer() {
		return offPlayer;
	}

	public void switchPlayer() {
		Player temp = curPlayer;
		curPlayer = new Player(offPlayer);
		offPlayer = new Player(temp);
	}

	public boolean isComputerTurn() {
		return curPlayer.isComputer();
	}

	public void computerTurn() {
		if(isComputerTurn()) {
			alphabeta();
			play(aiCoord[0], aiCoord[1]);
			System.out.println(curPlayer.getColorString()+" ("+aiCoord[0]+"; "+aiCoord[1]+")"+"\t move uses card "+aiCard);
			swapCard(aiCard);
		}
	}

	private void alphabeta() {
		aiCoord = null;
		aiCard  = null;
		aiCurPlayer = new Player(curPlayer);
		aiOffPlayer = new Player(offPlayer);

		Board temp = new Board(boardCard, aiCurPlayer, aiOffPlayer);

		aiCurPlayer.setBoard(temp);
		aiOffPlayer.setBoard(temp);

		System.out.println("Selected move score: "+alphabeta(this, aiCurPlayer.getDifficulty(), Integer.MIN_VALUE, Integer.MAX_VALUE, true));

	}

	public Piece getKingOfColor(int color) {
		for(Piece p: getPiecesOfColor(color))
			if(p != null && p.isKing())
				return p;
		return null;
	}

	public int getPieceCountByColor(int color) {
		int count = 0;
		for(Piece p: getPiecesOfColor(color))
			if(p != null)
				++count;
		return count;
	}

	private int alphabeta(Board node, int depth, int alpha, int beta, boolean maximize) {
		if(node.checkWin()) {
			if(node.getWinner().getColor() == aiCurPlayer.getColor()) {
				return 100+depth;
			} else {
				return -(100+depth);
			}
		}

		if(depth <= 0) {
			int curCount = node.getPieceCountByColor(aiCurPlayer.getColor());
			int offCount = node.getPieceCountByColor(aiOffPlayer.getColor());

			int curCloseness = node.getKingOfColor(aiCurPlayer.getColor()).getCoord().compare(
								   ((aiCurPlayer.getColor() == red) ? Coordinate.blueEnd : Coordinate.redEnd));

			int offCloseness = node.getKingOfColor(aiOffPlayer.getColor()).getCoord().compare(
								   ((aiOffPlayer.getColor() == red) ? Coordinate.blueEnd : Coordinate.redEnd));

			return (curCount-curCloseness)-(offCount-offCloseness);
		}

		if(maximize) {
			int v =  Integer.MIN_VALUE;
			for(Card c : node.getCardsOfPlayerColor(aiCurPlayer.getColor())) {
				for(Piece p: node.getPiecesOfColor(aiCurPlayer.getColor())) {
					if(p == null) continue;

					for(int [] move : c.getMoves()) {
						Board child = new Board(node);

						Coordinate from = new Coordinate(p.getCoord());
						Coordinate to   = new Coordinate(from);

						if(!to.move(move, aiCurPlayer.getColor())) continue;

						if(child.getPiece(from) == null) continue;
						if(child.getPiece(to) != null && child.getPiece(from).getColor() == child.getPiece(to).getColor()) continue;

						child.play(from, to);
						child.swapCard(c);
						child.switchPlayer();

						int retVal = alphabeta(child, depth-1, alpha, beta, false);

						if(v < retVal) {
							v = retVal;
							if(alpha < v) {
								if(depth == aiCurPlayer.getDifficulty()) {
									aiCoord = new Coordinate[] {from, to};
									aiCard  = c;
								}
								alpha = v;
								if(beta <= alpha)
									return v;
							}
						}
					}
				}
			}
			return v;
		} else {
			int v =  Integer.MAX_VALUE;
			for(Card c : node.getCardsOfPlayerColor(aiOffPlayer.getColor())) {
				if(c==null) continue;
				for(Piece p: node.getPiecesOfColor(aiOffPlayer.getColor())) {
					if(p == null) continue;

					for(int [] move : c.getMoves()) {
						if(move == null) continue;

						Board child = new Board(node);

						Coordinate from = new Coordinate(p.getCoord());
						Coordinate to   = new Coordinate(from);


						if(!to.move(move, aiOffPlayer.getColor()) || from.equals(to)) continue;
						if(child.getPiece(from) == null) continue;
						if(child.getPiece(to) != null && child.getPiece(from).getColor() == child.getPiece(to).getColor()) continue;

						child.play(from, to);
						child.swapCard(c);
						child.switchPlayer();

						int retVal = alphabeta(child, depth-1, alpha, beta, true);

						if(v > retVal) {
							v = retVal;
							if(beta > v) {
								beta = v;
								if(beta <= alpha)
									return v;
							}
						}
					}
				}
			}
			return v;
		}
	}

	public Piece[][] getBoard() {
		return board;
	}

	public Card[] getCardsOfPlayerColor(int color) {
		return getPlayerOfColor(color).getCards();
	}

	public Piece getPiece(int x, int y) {
		return board[y][x];
	}

	public Piece [] getPiecesOfPlayer(Player p) {
		return getPiecesOfColor(p.getColor());
	}

	public Piece [] getPiecesOfColor(int color) {
		int index = 0;
		Piece [] pieces = new Piece[5];

		for(int i = 0; i < 25; i++)
			if(getPiece(i%5,i/5) != null && getPiece(i%5,i/5).getColor() == color)
				pieces[index++] = new Piece(getPiece(i%5,i/5));

		return pieces;
	}

	public Piece getPiece(Coordinate c) {
		return getPiece(c.getX(),c.getY());
	}

	public Card exchange(Card c) {
		Card temp = boardCard;
		boardCard = c;
		return temp;
	}

	public Player getWinner() {
		if(blueWin || !containsRedKing)
			return getPlayerOfColor(blue);
		if(redWin || !containsBlueKing)
			return getPlayerOfColor(red);
		return null;
	}

	public boolean checkWin() {

		Piece p = getPiece(Coordinate.redEnd);

		if(p != null && p.isKing() && p.getColor() == blue)
			return (blueWin = true);

		p = getPiece(Coordinate.blueEnd);
		if(p != null && p.isKing() && p.getColor() == red)
			return (redWin = true);

		containsBlueKing = (getKingOfColor(blue)  != null);
		containsRedKing  = (getKingOfColor(red)   != null);

		return !(containsBlueKing && containsRedKing);
	}

	public void play(Coordinate from, Coordinate to) {
		if(getPiece(to) != null && getPiece(to).isKing() && getPiece(to).getColor() != getPiece(from).getColor())
			if(getPiece(to).getColor() == blue)
				containsBlueKing = false;
			else
				containsRedKing = false;

		board[to.getY()][to.getX()] = new Piece(getPiece(from));
		getPiece(to.getX(),to.getY()).setCoord(to);
		board[from.getY()][from.getX()] = null;
	}

	public void printBoard() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++)
				System.out.printf("|%2s ",(board[i][j] == null)?"":(board[i][j].getColor() == red)? "r":"b");
			System.out.println("|");
		}
	}

	public Player getPlayerOfColor(int color) {
		if(curPlayer.getColor() == color)
			return curPlayer;
		return offPlayer;
	}

	public void swapCard(Card handCard) {
		for(int i = 0; i < 2; i++) {
			if(curPlayer.getCards()[i].equals(handCard)) {
				curPlayer.setCard(i, boardCard);
				boardCard = new Card(handCard);
				break;
			}

			if(offPlayer.getCards()[i].equals(handCard)) {
				offPlayer.setCard(i, boardCard);
				boardCard = new Card(handCard);
				break;
			}
		}
	}

	public Board copy() {
		return new Board(this);
	}

	public String toString() {
		return "Current Player:\n"+curPlayer+"\nOff Player:\n"+offPlayer+"\nBoardCard:"+boardCard+"\nBoard:\n"+getBoardString();
	}

	private String getBoardString() {
		String ret = "";
		for(int i = 0; i < 25; i++)
			ret+= "|"+(i%5)+((getPiece(i%5,i/5) == null)? " " : ((getPiece(i%5,i/5).getColor() == red) ? "r" : "b"))+(i/5)+((i % 5 == 4)?"|\n":"");
		return ret;
	}
}

