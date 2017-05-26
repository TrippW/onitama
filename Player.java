public class Player
{
 private Card[] cards;
 private Board board;
 private int color;
 private boolean isComputer;

 public Player(Card[] moves, boolean isComputer, int playerColor)
 {
  cards = moves;
  color = playerColor;
  this.isComputer = isComputer;
 }

 public boolean isComputer()
 {
  return isComputer;
 }

 public void setBoard(Board b)
 {
  board = b;
 }

 public Coordinate [] getMove()
 {
  if(isComputer)
   return getMax();
  else
   return getMin();
 }

 public Coordinate [] getMin()
 {
  return new Coordinate[]{new Coordinate(0,0), new Coordinate(0,0)};
 }

 public Coordinate [] getMax()
 {
  return new Coordinate[]{new Coordinate(0,0), new Coordinate(0,0)};
 }

 public int[] getMove(int selectedCard, int option)
 {
  return cards[selectedCard].getMoves()[option];
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
