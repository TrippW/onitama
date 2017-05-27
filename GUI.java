import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GUI implements MouseListener
{
 JFrame frame;
 JPanel main, cards, board;

 JLabel curPlayer;
//Was JPanel
 CardGUI blue1, blue2, red1, red2, tableCard;

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
  frame = new JFrame("Onitama");

  GridBagLayout gridbag = new GridBagLayout();

  main = new JPanel();
  main.setLayout(gridbag);
  cards = new JPanel();
  cards.setLayout(new GridLayout(3,3));
  board = new JPanel();
  board.setLayout(new GridLayout(5,5));

  cards.setOpaque(true);
  cards.setBackground(Color.WHITE);

//create card labels
  red1 = new CardGUI(list[1], Board.red);
  red2 = new CardGUI(list[4], Board.red);
  blue1 = new CardGUI(list[0], Board.blue);
  blue2 = new CardGUI(list[3], Board.blue);
  tableCard = new CardGUI(list[2], Board.blue);

  updateCards();

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
  if(gameBoard.checkWin()) return;

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
   play(from, to);
 }

 private void play(Coordinate from, Coordinate to)
 {
  move(from,to);


  if(gameBoard.checkWin())
  {
   curPlayer.setText("Winner: "+gameBoard.getWinner().getColorString());
   return;
  }

//  checkWin();

//  switchPlayer();
//  switchCard();
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

  for(int [] pair : Card.getCardByName(((CardGUI)lastCardHighlighted).getText()).getMoves())
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

  for(int [] pair : Card.getCardByName(((CardGUI)lastCardHighlighted).getText()).getMoves())
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
  if(lastCardHighlighted != null)
  {
   if(selectedPawn != null)
    unhighlightMoves();
   unhighlightCard();
  }

  gameBoard.play(from, to);
  update();
 }

 private void updateBoard()
 {
  for(int y = 0; y < 5; y++)
   for(int x = 0; x < 5; x++)
    if(gameBoard.getPiece(x,y) == null)
    {
     lblBoard[y][x].setText("");
     lblBoard[y][x].setBackground(Color.WHITE);
    }
    else
    {
     if(gameBoard.getPiece(x,y).isKing())
      lblBoard[y][x].setText("K");
     else
      lblBoard[y][x].setText("P");

     if(gameBoard.getPiece(x,y).getColor() == Board.red)
      lblBoard[y][x].setBackground(Color.RED);
     else
      lblBoard[y][x].setBackground(Color.BLUE);
    }
 }

 public void update()
 {
//updatePlayer
  switchPlayer();//curPlayer.setText(gameBoard.getCurrentPlayer().getColorString());
//update Cards
  if(lastCardHighlighted != null)
   switchCard();
  else
   updateCards();
//update Board
  updateBoard();
 }

 private void updateCards()
 {
  Card [] c = gameBoard.getPlayerOfColor(Board.red).getCards();
  red1.set(new CardGUI(c[0], Board.red));
  red2.set(new CardGUI(c[1], Board.red));

  c = gameBoard.getPlayerOfColor(Board.blue).getCards();
  blue1.set(new CardGUI(c[0], Board.blue));
  blue2.set(new CardGUI(c[1], Board.blue));

  tableCard.set(new CardGUI(gameBoard.getBoardCard(),Board.blue));

 }

 private void switchCard()
 {
  if(lastCardHighlighted == null)
  {
   System.out.println("ERROR SWITCHING CARDS!");
   System.exit(2);
  }

  CardGUI temp = new CardGUI((CardGUI) lastCardHighlighted);
  gameBoard.swapCard(Card.getCardByName(temp.getName()));
  lastCardHighlighted = null;
  updateCards();
 }

 private void switchPlayer()
 {
  gameBoard.switchPlayer();
  curPlayer.setText(gameBoard.getCurrentPlayer().getColorString());
 }
}
