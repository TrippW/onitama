import java.awt.*;

public class Board
{
 public static int red  = 1;
 public static int blue = -1;

 private Piece board [][] = new Piece[5][5];

 private boolean containsBlueKing = true;
 private boolean containsRedKing  = true;

 private boolean blueWin = false;
 private boolean redWin = false;

 private Card boardCard;

 private Player curPlayer;
 private Player offPlayer;

 public Board(Board b)
 {
  this.boardCard = b.getBoardCard();
  for(int i = 0; i < 25; i++)
   if(b.getPiece(i/5,i%5) != null)
    board[i/5][i%5] = new Piece(b.getPiece(i/5,i%5));

  curPlayer = getCurrentPlayer();
  offPlayer = getOffPlayer();
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
  curPlayer = new Player(offPlayer);
  offPlayer = new Player(temp);
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

 public Piece [] getPieceOfPlayer(Player p)
 {
  return getPieceOfColor(p.getColor());
 }

 public Piece [] getPieceOfColor(int color)
 {
  int index = 0;
  Piece [] pieces = new Piece[5];

  for(int i = 0; i < 25; i++)
   if(getPiece(i/5,i%5).getColor() == color)
    pieces[index++] = new Piece(getPiece(i/5,i%5));

  return pieces;
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

 public Player getWinner()
 {
  if(blueWin || !containsRedKing)
   return getPlayerOfColor(blue);
  if(redWin || !containsBlueKing)
   return getPlayerOfColor(red);
  return null;
 }

 public boolean checkWin()
 {
  if(board[0][2] != null && board[0][2].isKing() && board[0][2].getColor() == blue)
   return (blueWin = true);
  if(board[4][2] != null && board[4][2].isKing() && board[4][2].getColor() == red)
   return (redWin = true);

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

//TODO
//getPiece -> new Obj
  board[to.getY()][to.getX()] = getPiece(from);
  board[from.getY()][from.getX()] = null;
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

 public Player getPlayerOfColor(int color)
 {
  if(curPlayer.getColor() == color)
   return curPlayer;
  return offPlayer;
 }

 public void swapCard(Card handCard)
 {
  for(int i = 0; i < 2; i++)
  {
   if(curPlayer.getCards()[i].equals(handCard))
   {
    curPlayer.setCard(i, boardCard);
    boardCard = new Card(handCard);
    break;
   }
   if(offPlayer.getCards()[i].equals(handCard))
   {
    offPlayer.setCard(i, boardCard);
    boardCard = new Card(handCard);
    break;
   }
  }
 }

 public Board copy()
 {
  return new Board(this);
 }

 public String toStirng()
 {
  return "Current Player:\n"+curPlayer+"\nOff Player:\n"+offPlayer+"\nBoardCard:"+boardCard+"\nBoard:\n"+getBoardString();
 }

 private String getBoardString()
 {
  String ret = "";
  for(int i = 0; i < 25; i++)
   ret+= "| "+((getPiece(i%5,i/5) == null)? "  " : (getPiece(i%5,i/5).getColor() == red) ? "r " : "b ")+((i != 0 && i % 5 == 0)?"|\n":"");
  return ret;
 }
}

