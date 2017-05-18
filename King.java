public class King extends Piece
{

 public King(Coordinate c, int col)
 {
  super(c, col);
 }

@Overrides
 public String getType()
 {
  return getColorString()+" King";
 }
}
