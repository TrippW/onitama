public class Player
{
 Card[] cards;
 Board board;
 int color;

 public Player(Card[] moves, boolean isComputer, int playerColor)
 {
  cards = moves;
  color = playerColor;
 }

 public void setBoard(Board b)
 {
  board = b;
 }

 public int[] getMove(int selectedCard, int option)
 {
  return cards[selectedCard].moves[option];
 }
}
