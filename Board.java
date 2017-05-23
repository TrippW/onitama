import java.awt.*;

public class Board
{
 public static int red  = 1;
 public static int blue = -1;

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
   board[0][i] = new Piece(new Coordinate(i,0),  red, i == 2);
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

 public boolean isComputerTurn()
 {
  return curPlayer.isComputer();
 }

 public void computerTurn()
 {
  if(curPlayer.isComputer())
  {
   Coordinate [] moves = curPlayer.getMove();
   play(moves);
  }
 }

 public Piece[][] getBoard(){ return board;}

 public Piece getPiece(int x, int y)
 {
  return board[y][x];
 }

 public Piece getPiece(Coordinate c)
 {
  return getPiece(c.getX(),c.getY());
 }

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

 public void play(Coordinate [] play)
 {
  play(play[0],play[1]);
 }

 public void play(Coordinate from, Coordinate to)
 {
  if(getPiece(to) != null && getPiece(to).isKing() && getPiece(to).getColor() != getPiece(from).getColor())
   if(getPiece(to).getColor() == blue)
    containsBlueKing = false;
   else
    containsRedKing = false;
//  getPiece(to) = getPiece(from);
  board[to.getY()][to.getX()] = getPiece(from);//board[from.getY()][from.getX()];
  board[from.getY()][from.getX()] = null;

  switchPlayer();
//  printBoard();
 }

 public void printBoard()
 {
  for(int i = 0; i < 5; i++)
  {
   for(int j = 0; j < 5; j++)
    System.out.printf("|%2s ",(board[i][j] == null)?"":(board[i][j].getColor() == red)? "r":"b");
   System.out.println("|");
  }
 }

}

