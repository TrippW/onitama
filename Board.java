import java.awt.*;
import javax.swing.JFrame;

public class Board
{
 public static int red  = 1;
 public static int blue = -1;

// private JFrame frame;

// private int simpleboard [][] = new int[5][5];
 private Piece board [][] = new Piece[5][5];

 private boolean containsBlueKing = true;
 private boolean containsRedKing  = true;

 private Card boardCard;

 private Player curPlayer;
 private Player offPlayer;

 public Board(Board b)
 {
  this.boardCard = b.getBoardCard();
  this.board = b.getBoard();
  this.curPlayer = getCurrentPlayer();
  this.offPlayer = getOffPlayer();
 }

 public Board(Card inbetween, Player starter, Player off)
 {
  curPlayer = starter;
  offPlayer = off;
  boardCard = inbetween;

  for(int i = 0; i < 5; i++)
  {
   board[0][i] = new Piece(new Coordinate(i,0), red, i == 2);
   board[4][i] = new Piece(new Coordinate(i,4), blue, i == 2);
  }
 }

 public Card getBoardCard()
 {
  return boardCard;
 }

 public Player getCurrentPlayer()
 {
  return curPlayer;
 }

 public Player getOffPlayer()
 {
  return offPlayer;
 }

 public void switchPlayer()
 {
  Player temp = curPlayer;
  curPlayer = offPlayer;
  offPlayer = temp;
 }

 public Piece[][] getBoard(){ return board;}

 public Card exchange(Card c)
 {
  Card temp = boardCard;
  boardCard = c;
  return temp;
 }

 public boolean checkWin()
 {
  if(board[0][2] != null && board[0][2].isKing() && board[0][2].getColor() == blue)
   return true;
  if(board[4][2] != null && board[4][2].isKing() && board[4][2].getColor() == red)
   return true;

  return !(containsBlueKing && containsRedKing);
 }

 public Coordinate[] getMove()
 {
  return new Coordinate[]{new Coordinate(0,0), new Coordinate(1,1)};
 }

 public void move(Coordinate [] play)
 {
  move(play[0],play[1]);
 }

 public void move(Coordinate from, Coordinate to)
 {
  if(board[to.getY()][to.getX()] != null && board[to.getY()][to.getX()].isKing() && board[to.getY()][to.getX()].getColor() != board[from.getY()][from.getX()].getColor())
   if(board[to.getY()][to.getX()].getColor() == blue)
    containsBlueKing = false;
   else
    containsRedKing = false;

  board[to.getY()][to.getX()] = board[from.getY()][from.getX()];
  board[from.getY()][from.getX()] = null;
 }

}

