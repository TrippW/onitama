public class Player
{
 private Card[] cards;
 private Board board;
 private int color;

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

 public int getColor()
 {
  return color;
 }

 public String getColorString()
 {
  String col = "Blue";
  if(color == Board.red) col = "Red";
  return col;
 }

}
