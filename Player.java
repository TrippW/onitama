public class Player
{

 Card[] cards;
 Board board;
 int color;

 public Player(Card[] moves, boolean isComputer, int playerColor, Board b)
 {
  board = b;
  cards = moves;
  color = playerColor;
 }
}
