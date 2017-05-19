import java.util.ArrayList;

public class Card
{
//Name(Set of moves, name)
 public String name;
 public int[][] moves;
 private int color;

 public static ArrayList<Card> allCards = new ArrayList<Card>();

 public static void init()
 {
  Card tiger    = new Card(new int[][]{{0,2},  {0,-1}}                 ,"Tiger", Board.blue);
  Card dragon   = new Card(new int[][]{{-2,1}, {-1,-1}, {1,-1}, {2,1}} ,"Dragon", Board.red);
  Card frog     = new Card(new int[][]{{-2,0}, {-1,1},  {1,-1}}        ,"Frog", Board.red);
  Card rabbit   = new Card(new int[][]{{1,1},  {-1,-1}, {2,0}}         ,"Rabbit", Board.blue);
  Card crab     = new Card(new int[][]{{2,0},  {-2,0},  {0,1}}         ,"Crab", Board.blue);
  Card elephant = new Card(new int[][]{{1,0},  {-1,0},  {1,1},  {-1,1}},"Elephant", Board.red);
  Card goose    = new Card(new int[][]{{-1,1}, {1,-1},  {-1,0}, {1,0}} ,"Goose", Board.blue);
  Card rooster  = new Card(new int[][]{{1,1},  {-1,-1}, {-1,0}, {1,0}} ,"Rooster", Board.red);
  Card monkey   = new Card(new int[][]{{1,1},  {-1,-1}, {1,-1}, {-1,1}},"Monkey", Board.blue);
  Card mantis   = new Card(new int[][]{{0,-1}, {-1,1},  {1,1}}         ,"Mantis", Board.red);
  Card horse    = new Card(new int[][]{{-1,0}, {0,1},   {0,-1}}        ,"Horse", Board.red);
  Card ox       = new Card(new int[][]{{0,1},  {0,-1},  {-1,0}}        ,"Ox", Board.blue);
  Card crane    = new Card(new int[][]{{-1,1}, {-1,-1}, {0,1}}         ,"Crane", Board.blue);
  Card boar     = new Card(new int[][]{{-1,0}, {1,0},   {0,1}}         ,"Boar", Board.red);
  Card eel      = new Card(new int[][]{{1,-1}, {-1,-1}, {1,0}}         ,"Eel", Board.blue);
  Card cobra    = new Card(new int[][]{{1,1},  {1,-1},  {-1,0}}        ,"Cobra", Board.red);
 }

 Card(int [][] moves, String name, int color)
 {
  this.moves = moves;
  this.name  = name;
  this.color = color;

  allCards.add(this);
 }

 public int getColor()
 {
  return color;
 }

 public String getColorString()
 {
  String col = "Blue";
  if(color == Board.red) col = "Red";
  return col;
 }

}
