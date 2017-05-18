import java.awt.*;
import javax.swing.JFrame;

public class Board
{
 public int red  = 1;
 public int blue = -1;

 private JFrame frame;

 private int board [][] = new int[5][5];

 public Card boardCard;

 public Board(Card inbetween)
 {
  boardCard = inbetween;
  for(int i = 0; i < 5; i++)
  {
   board[0][i] = red;
   board[4][i] = blue;
  }

  board[0][2] *= 2;
  board[4][2] *= 2;

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

 public int[][] getBoard(){ return board;}

 public Card exchange(Card c)
 {
  Card temp = boardCard;
  boardCard = c;
  return temp;
 }

 public boolean checkWin()
 {
  //if
  if(board[0][2] == RED*2 || board[4][2] == BLUE*2)
   return true;
  return false;
 }

}

