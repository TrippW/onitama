public class Coordinate
{
 private int x,y;

 public Coordinate(int x, int y)
 {
  this(new int[]{x,y});
 }

 public Coordinate(int[] pair)
 {
  setPair(pair);
 }

 public Coordinate(Coordinate c)
 {
  this(c.getPair());
 }

 public void setPair(int[] pair)
 {
  setPair(pair[0],pair[1]);
 }

 public void setPair(int x, int y)
 {
  try{
   setX(x);
   setY(y);
  }
  catch(Exception e)
  {
   e.getStackTrace();
   System.exit(1);
  }
 }

 private void setX(int n) throws Exception
 {
  if(n < 0 || n >= 5) throw new Exception("Coordinate x is out of bounds.");
  x = n;
 }

 private void setY(int n) throws Exception
 {
  if(n < 0 || n >= 5) throw new Exception("Coordinate y is out of bounds.");
  y = n;
 }

 public int getX()
 {
  return x;
 }

 public int getY()
 {
  return y;
 }

 public int[] getPair()
 {
  return new int[]{x,y};
 }
}
