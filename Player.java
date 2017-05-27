import java.util.Arrays;

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

 public Player(Player p)
 {
  cards = Arrays.copyOf(p.getCards(),2);
  board = p.getBoard();
  color = p.getColor();
  isComputer = p.isComputer();
 }

 public boolean isComputer()
 {
  return isComputer;
 }

 public void setBoard(Board b)
 {
  if(board == null)
   board = b;
  else
   board = new Board(b);
 }

 public Board getBoard()
 {
  return board;
 }

 public void setCard(int index, Card card)
 {
  cards[index] = new Card(card);
 }

 public Coordinate [] getMove()
 {
  if(isComputer)
   return getMax();
  return null;
 }

 public Coordinate [] getMax()
 {
  return new Coordinate[]{new Coordinate(0,0), new Coordinate(0,0)};
 }

 public Card[] getCards()
 {
  return cards;
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
