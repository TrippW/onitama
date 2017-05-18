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
  Board b = new Board(cards[2]);
  Player p1 = new Player(new Card[]{cards[0],cards[3]}, false, Board.red, b);
  Player p2 = new Player(new Card[]{cards[1],cards[4]}, false, Board.blue, b);


  b.display();

  for(int i = 0;i < cards.length;i++)
   System.out.println(cards[i].name);

 }

}
