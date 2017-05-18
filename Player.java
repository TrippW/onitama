public class Player
{

 Card[] cards;
 Board board;

 public Player(Card[] moves, boolean isComputer, int playerColor, Board b)
 {
  board = b;
  cards = moves;
 }
}
