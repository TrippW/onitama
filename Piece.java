public class Piece
{
 private Coordinate coord;
 private int color;
 private boolean isKing;

 public Piece(Coordinate c, int col, boolean isKing)
 {
  this.isKing = isKing;
  coord = c;
  color = col;
 }

 public Coordinate getCoord()
 {
  return coord;
 }

 public void setCoord(Coordinate c)
 {
  coord = c;
 }

 public int getColor()
 {
  return color;
 }

 public boolean isKing()
 {
  return isKing;
 }

 public String getStringColor()
 {
  String col = "Blue";
  if(color == Board.red) col = "Red";
  return col;
 }

}
