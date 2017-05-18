import java.util.ArrayList;

public class Card
{
//Name(Set of moves, name)
 public String name;
 public int[][] moves;
 public int color;

 private static int RED = 1, BLUE = -1;



 public static ArrayList<Card> allCards = new ArrayList<Card>();

 public static void init()
 {
  Card tiger    = new Card(new int[][]{{0,2},  {0,-1}}                 ,"Tiger", BLUE);
  Card dragon   = new Card(new int[][]{{-2,1}, {-1,-1}, {1,-1}, {2,1}} ,"Dragon", RED);
  Card frog     = new Card(new int[][]{{-2,0}, {-1,1},  {1,-1}}        ,"Frog", RED);
  Card rabbit   = new Card(new int[][]{{1,1},  {-1,-1}, {2,0}}         ,"Rabbit", BLUE);
  Card crab     = new Card(new int[][]{{2,0},  {-2,0},  {0,1}}         ,"Crab", BLUE);
  Card elephant = new Card(new int[][]{{1,0},  {-1,0},  {1,1},  {-1,1}},"Elephant", RED);
  Card goose    = new Card(new int[][]{{-1,1}, {1,-1},  {-1,0}, {1,0}} ,"Goose", BLUE);
  Card rooster  = new Card(new int[][]{{1,1},  {-1,-1}, {-1,0}, {1,0}} ,"Rooster", RED);
  Card monkey   = new Card(new int[][]{{1,1},  {-1,-1}, {1,-1}, {-1,1}},"Monkey", BLUE);
  Card mantis   = new Card(new int[][]{{0,-1}, {-1,1},  {1,1}}         ,"Mantis", RED);
  Card horse    = new Card(new int[][]{{-1,0}, {0,1},   {0,-1}}        ,"Horse", RED);
  Card ox       = new Card(new int[][]{{0,1},  {0,-1},  {-1,0}}        ,"Ox", BLUE);
  Card crane    = new Card(new int[][]{{-1,1}, {-1,-1}, {0,1}}         ,"Crane", BLUE);
  Card boar     = new Card(new int[][]{{-1,0}, {1,0},   {0,1}}         ,"Boar", RED);
  Card eel      = new Card(new int[][]{{1,-1}, {-1,-1}, {1,0}}         ,"Eel", BLUE);
  Card cobra    = new Card(new int[][]{{1,1},  {1,-1},  {-1,0}}        ,"Cobra", RED);
 }

 Card(int [][] moves, String name, int color)
 {
  this.moves = moves;
  this.name  = name;
  this.color = color;

  allCards.add(this);
 }
}
