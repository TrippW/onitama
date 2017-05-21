import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GUI implements MouseListener
{
 private class CardGUI extends JPanel
 {
  JLabel [][] moves = new JLabel[5][5];

  JLabel name;

  JPanel movePanel;

  GridBagLayout gridbag = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();

  public CardGUI(CardGUI cg)
  {
   init();
   set(cg);
  }

  public CardGUI(Card c)
  {
   init();

   name.setText(c.getName());

   for(int[] m : c.getMoves())
   {
    int x = 2+m[0], y = 2+m[1]*-1;
    moves[y][x].setBackground(Color.YELLOW);
   }
   gbc = new GridBagConstraints();
   gbc.gridwidth = 2;
   gbc.gridheight = 1;
   gridbag.setConstraints(movePanel,gbc);
   add(movePanel);
  }

  private void init()
  {
   gridbag = new GridBagLayout();
   gbc = new GridBagConstraints();

   name = new JLabel("");

   setLayout(gridbag);

   movePanel = new JPanel();
   movePanel.setLayout(new GridLayout(5,5));

   gbc = new GridBagConstraints();
   gbc.gridwidth = 1;
   gbc.gridheight = 1;
   gridbag.setConstraints(name,gbc);
   add(name);

   for(int y = 0; y < 5; y++)
    for(int x = 0; x < 5; x++)
    {
     int width = 15, height = 15;

     moves[y][x] = new JLabel(" ");
     moves[y][x].setBackground(Color.WHITE);
     moves[y][x].setOpaque(true);
     moves[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
     moves[y][x].setMinimumSize(new Dimension(width, height));
     moves[y][x].setPreferredSize(new Dimension(width, height));
     moves[y][x].setMaximumSize(new Dimension(width, height));
     movePanel.add(moves[y][x]);
    }
   moves[2][2].setBackground(Color.BLACK);
  }

  public void setText(String n)
  {
   name.setText(n);
  }

  public void setMoves(JLabel [][] mp)
  {
   for(int y = 0; y < 5; y++)
    for(int x = 0; x < 5; x++)
     moves[y][x].setBackground(mp[y][x].getBackground());
  }

  public JLabel[][] getMoves()
  {
   return moves;
  }

  public String getText()
  {
   return name.getText();
  }

  public void set(CardGUI cg)
  {
   setText(cg.getText());
   setMoves(cg.getMoves());
  }
 }

 JFrame frame;
 JPanel main, cards, board;

 JLabel curPlayer;

 JPanel blue1, blue2, red1, red2, tableCard;

 JLabel [][] lblBoard = new JLabel[5][5];

 Component lastCardHighlighted = null;
 Component selectedPawn = null;

 Board gameBoard;

 public GUI(Card [] list, Board b)
 {
  gameBoard = b;
  if(b == null)
  {
   System.out.println("Game board not initialized");
   System.exit(1);
  }
  curPlayer = new JLabel(b.getCurrentPlayer().getColorString());
  initFrame(list);
 }

 public void initGUIBoard()
 {

  for(Piece [] pRow : gameBoard.getBoard())
   for(Piece p : pRow)
   {
    if(p == null) continue;
    Coordinate c = p.getCoord();
    int x = c.getX();
    int y = c.getY();

    if(p.isKing())
     lblBoard[y][x].setText("K");
    else
     lblBoard[y][x].setText("P");

    if(p.getColor() == Board.blue)
     lblBoard[y][x].setBackground(Color.BLUE);
    else
     lblBoard[y][x].setBackground(Color.RED);

   }
 }

 private void initFrame(Card [] list)
 {
  frame = new JFrame("Oniama");

  GridBagLayout gridbag = new GridBagLayout();

  main = new JPanel();
  main.setLayout(gridbag);
  cards = new JPanel();
  cards.setLayout(new GridLayout(3,3));
  board = new JPanel();
  board.setLayout(new GridLayout(5,5));

//create card labels
  red1 = new CardGUI(list[1]);//JLabel(list[1].name, SwingConstants.CENTER);
  red2 = new CardGUI(list[4]);//.name, SwingConstants.CENTER);
  blue1 = new CardGUI(list[0]);//.name, SwingConstants.CENTER);
  blue2 = new CardGUI(list[3]);//.name, SwingConstants.CENTER);
  tableCard = new CardGUI(list[2]);//.name, SwingConstants.CENTER);

/*
//set opaque
  red1.setOpaque(true);
  red2.setOpaque(true);
  tableCard.setOpaque(true);
  blue1.setOpaque(true);
  blue2.setOpaque(true);

//set color
  red1.setBackground(Color.WHITE);
  red2.setBackground(Color.WHITE);
  tableCard.setBackground(Color.WHITE);
  blue1.setBackground(Color.WHITE);
  blue2.setBackground(Color.WHITE);
*/
//add card labels mouse listener
  red1.addMouseListener(this);
  red2.addMouseListener(this);
  tableCard.addMouseListener(this);
  blue1.addMouseListener(this);
  blue2.addMouseListener(this);

  for(int i = 0; i < 5; i++)
  {
   for(int j = 0; j < 5; j++)
   {
    int width = 50, height = 50;

    JLabel label = new JLabel("", SwingConstants.CENTER);
    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    label.setMinimumSize(new Dimension(width, height));
    label.setPreferredSize(new Dimension(width, height));
    label.setMaximumSize(new Dimension(width, height));
    label.setOpaque(true);
    label.addMouseListener(this);
    label.setBackground(Color.WHITE);

    lblBoard[i][j] = label;
    board.add(label);
   }
  }

  cards.add(red1);
  cards.add(new JLabel("< Red's Hand >", SwingConstants.CENTER));
  cards.add(red2);

  cards.add(new JLabel(""));
  cards.add(tableCard);
  cards.add(new JLabel(""));

  cards.add(blue1);
  cards.add(new JLabel("< Blue's Hand >", SwingConstants.CENTER));
  cards.add(blue2);

  GridBagConstraints gbc = new GridBagConstraints();
  gbc.gridwidth = 1;
  gbc.gridheight = 1;
  JLabel temp = new JLabel("Current Player:");
  gridbag.setConstraints(temp,gbc);
  main.add(temp);

  gbc = new GridBagConstraints();
  gbc.gridwidth = 2;
  gbc.gridheight = 1;
  gridbag.setConstraints(curPlayer,gbc);
  main.add(curPlayer);

  gbc = new GridBagConstraints();
  gbc.gridy = 1;
  gbc.gridwidth = 1;
  gbc.gridheight = 2;
  gridbag.setConstraints(cards,gbc);
  main.add(cards);

  gbc = new GridBagConstraints();
  gbc.fill = GridBagConstraints.BOTH;
  gbc.gridy = 1;
  gbc.gridwidth = 2;
  gbc.gridheight = 2;
  gridbag.setConstraints(board,gbc);
  main.add(board);

  frame.add(main);


  frame.pack();

  frame.setLocationRelativeTo(null);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
 }

 public void mouseExited(MouseEvent e)
 {

 }
 public void mouseEntered(MouseEvent e)
 {

 }
 public void mouseReleased(MouseEvent e)
 {
 }
 public void mousePressed(MouseEvent e)
 {
 }

 public void mouseClicked(MouseEvent e)
 {
  if(e.getComponent().getParent() == cards)
  {
   selectCard(e);
  }

  if(e.getComponent().getParent() == board)
  {
   if(selectedPawn != null && e.getComponent().getBackground()==Color.YELLOW)
   {
    move(e);
   }
   else
   {
    if((e.getComponent().getBackground() == Color.RED && curPlayer.getText().equals("Red")) || (e.getComponent().getBackground() == Color.BLUE && curPlayer.getText().equals("Blue")))
     highlightMoves(e);
   }
  }
 }

 private void selectCard(MouseEvent e)
 {
  if(curPlayer.getText().equals("Red"))
   if(e.getComponent() != red1 && e.getComponent() != red2) return;

  if(curPlayer.getText().equals("Blue"))
   if(e.getComponent() != blue1 && e.getComponent() != blue2) return;

  unhighlightCard();

  e.getComponent().setBackground(Color.YELLOW);

  if(selectedPawn != null && lastCardHighlighted != null)
   unhighlightMoves();

  lastCardHighlighted = e.getComponent();

  if(selectedPawn != null)
   highlightMoves(selectedPawn);
 }

 private void unhighlightCard()
 {
  if(lastCardHighlighted != null)
   lastCardHighlighted.setBackground(Color.WHITE);
 }

 private void move(MouseEvent e)
 {
  Coordinate from = null;
  Coordinate to = null;

  for(int x = 0; x < 5; x++)
   for(int y = 0; y < 5; y++)
   {
    if(lblBoard[y][x] == selectedPawn)
     from = new Coordinate(x,y);
    if(lblBoard[y][x] == e.getComponent())
     to = new Coordinate(x,y);
   }
  move(from,to);
  gameBoard.play(from, to);
  if(gameBoard.checkWin())
  {
   curPlayer.setText("Winner: "+curPlayer.getText());
   return;
  }

  switchPlayers();
  switchCard();
  selectedPawn = null;
 }

 private void unhighlightMoves()
 {
  Coordinate from = null;

  if(lastCardHighlighted == null)
  {
   System.out.println("Unhighlighted error: no card selected");
   return;
  }

  for(int x = 0; x < 5; x++)
   for(int y = 0; y < 5; y++)
    if(lblBoard[y][x] == selectedPawn)
    {
     from = new Coordinate(x,y);
     x=7;
     break;
    }

  if(from == null)
  {
   System.out.println("Unhighlighted error: no pawn selected");
   return;
  }

  for(int [] pair : Card.getCardByName(((CardGUI)lastCardHighlighted).getText()).moves)
  {
   int newX = pair[0]*gameBoard.getCurrentPlayer().getColor()*-1+from.getX();
   int newY = pair[1]*gameBoard.getCurrentPlayer().getColor()+from.getY();

   if(newX >= 0 && newX < 5 && newY >= 0 && newY < 5)
    if(lblBoard[newY][newX].getBackground() != lblBoard[from.getY()][from.getX()].getBackground())
    {
     if(lblBoard[newY][newX].getText().equals(""))
      lblBoard[newY][newX].setBackground(Color.WHITE);
     else
     {
      if(gameBoard.getCurrentPlayer().getColor() == Board.red)
       lblBoard[newY][newX].setBackground(Color.BLUE);
      else
       lblBoard[newY][newX].setBackground(Color.RED);
     }
    }
  }
 }

 private void highlightMoves(MouseEvent e)
 {
  highlightMoves(e.getComponent());
 }

 private void highlightMoves(Component c)
 {
  if(lastCardHighlighted == null)
  {
   System.out.println("Unhighlighted error: no card selected");
   return;
  }

  if(selectedPawn != null)
   unhighlightMoves();

  selectedPawn = c;
  Coordinate from = null;

  for(int x = 0; x < 5; x++)
   for(int y = 0; y < 5; y++)
    if(lblBoard[y][x] == selectedPawn)
    {
     from = new Coordinate(x,y);
     x=7;
     break;
    }
  if(from == null)
  {
   System.out.println("Highlighted error: no pawn selected");
   selectedPawn = null;
   return;
  }

  System.out.println("Moves are to :");

  for(int [] pair : Card.getCardByName(((CardGUI)lastCardHighlighted).getText()).moves)
  {
   int newX = pair[0]*gameBoard.getCurrentPlayer().getColor()*-1+from.getX();
   int newY = pair[1]*gameBoard.getCurrentPlayer().getColor()+from.getY();
   if(newX >= 0 && newX < 5 && newY >= 0 && newY < 5)
    if(lblBoard[newY][newX].getBackground() != lblBoard[from.getY()][from.getX()].getBackground())
     lblBoard[newY][newX].setBackground(Color.YELLOW);
  }
 }

 private void move(Coordinate from, Coordinate to)
 {
  unhighlightMoves();
  unhighlightCard();

  lblBoard[to.getY()][to.getX()].setText(lblBoard[from.getY()][from.getX()].getText());
  lblBoard[to.getY()][to.getX()].setBackground(lblBoard[from.getY()][from.getX()].getBackground());

  lblBoard[from.getY()][from.getX()].setText("");
  lblBoard[from.getY()][from.getX()].setBackground(Color.WHITE);

 }

 private void switchCard()
 {
  if(lastCardHighlighted == null)
  {
System.out.println("ERROR SWITCHING CARDS!");
System.exit(2);
  }
  CardGUI temp = new CardGUI((CardGUI) lastCardHighlighted);
  CardGUI tc   = (CardGUI) tableCard;

  if(lastCardHighlighted == red1)
   ((CardGUI) red1).set(tc);

  if(lastCardHighlighted == red2)
   ((CardGUI) red2).set(tc);

  if(lastCardHighlighted == blue1)
   ((CardGUI) blue1).set(tc);

  if(lastCardHighlighted == blue2)
   ((CardGUI) blue2).set(tc);

  ((CardGUI)tableCard).set(temp);
  lastCardHighlighted = null;
 }

 private void switchPlayers()
 {
  if(curPlayer.getText().equals("Red"))
   curPlayer.setText("Blue");
  else
   curPlayer.setText("Red");
 }
}
