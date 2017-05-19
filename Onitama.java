public class Onitama
{

 public static void main(String [] args)
 {
  Card.init();
  Deck.init();
  Onitama game = new Onitama();
 }

 public Onitama()
 {

  Card cards[] = Deck.draw();
  Player p1 = new Player(new Card[]{cards[0],cards[3]}, false, Board.red);
  Player p2 = new Player(new Card[]{cards[1],cards[4]}, false, Board.blue);

  Board board = null;

  if(cards[2].getColor() == Board.red)
   board = new Board(cards[2], p1, p2);
  else
   board = new Board(cards[2], p2, p1);

  GUI g = new GUI(cards, board.getCurrentPlayer());

  g.initGUIBoard(board.getBoard());

  p1.setBoard(board);
  p2.setBoard(board);

  while(board.checkWin() && false)
  {
   board.move(board.getMove());
   board.switchPlayer();
  }

  for(int i = 0;i < cards.length;i++)
   System.out.println(cards[i].name);

 }

}
