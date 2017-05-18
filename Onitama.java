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
  GUI g = new GUI();

  Card cards[] = Deck.draw();
  Player p1 = new Player(new Card[]{cards[0],cards[3]}, false, Board.red);
  Player p2 = new Player(new Card[]{cards[1],cards[4]}, false, Board.blue);

  Board b = new Board(cards[2], p1, p2);

  p1.setBoard(b);
  p2.setBoard(b);

  while(b.checkWin() && false)
  {
   b.move(b.getMove());
   b.switchPlayer();
  }

  for(int i = 0;i < cards.length;i++)
   System.out.println(cards[i].name);

 }

}
