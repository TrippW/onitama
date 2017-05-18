import java.awt.*;
import javax.swing.JFrame;

public class Board
{
 public static int red  = 1;
 public static int blue = -1;

 private JFrame frame;

 private int simpleboard [][] = new int[5][5];
 private Piece board [][] = new Piece[5][5];

 private boolean containsBlueKing = true;
 private boolean containsRedKing  = true;

 public Card boardCard;

 public Board(Card inbetween)
 {
  boardCard = inbetween;
  for(int i = 0; i < 5; i++)
  {
   if(i == 2) continue;
   board[0][i] = new Piece(new Coordinate(i,0), red);
   board[4][i] = new Piece(new Coordinate(i,4), blue);
  }

  board[0][2] = new King(new Coordinate(2,0), red);
  board[4][2] = new King(new Coordinate(2,4), blue);

  initFrame();
 }

 private void initFrame()
 {
  frame = new JFrame("Oniama");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }

 public void display()
 {
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
 }

 public int[][] getSimpleBoard(){ return simpleboard;}

 public Piece[][] getBoard(){ return board;}

 public Card exchange(Card c)
 {
  Card temp = boardCard;
  boardCard = c;
  return temp;
 }

 public boolean checkWin()
 {
  if(board[0][2] != null && board[0][2].getType() == "Blue King")
   return true;
  if(board[4][2] != null && board[4][2].getType() == "Red King")
   return true;

  return !(containsBlueKing && containsRedKing);

 }

 public void move(Card used, Player p, Coordinate from, Coordinate to)
 {
  
 }

}

