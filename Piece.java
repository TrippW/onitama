public class Piece
{
 protected Coordinate coord;
 protected int color;

 public Piece(Coordinate c, int col)
 {
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

 public String getStringColor()
 {
  String col = "Blue";
  if(color == Board.red) col = "Red";
  return col;
 }

 public String getType()
 {
  return getStringColor()+" Pawn";
 }

}
