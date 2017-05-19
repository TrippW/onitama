import java.awt.*;
import javax.swing.*;

public class GUI
{
 JFrame frame;
 JPanel main, cards, board;

 JLabel curPlayer;

 JLabel blue1, blue2, red1, red2, tableCard;

 JLabel [][] lblBoard = new JLabel[5][5];

 public GUI(Card [] list, Player p)
 {
  curPlayer = new JLabel(p.getColorString());
  initFrame(list);
 }


 public void initGUIBoard(Piece [][] allPieces)
 {

  for(Piece [] pRow : allPieces)
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

  red1 = new JLabel(list[1].name, SwingConstants.CENTER);
  red2 = new JLabel(list[4].name, SwingConstants.CENTER);

  blue1 = new JLabel(list[0].name, SwingConstants.CENTER);
  blue2 = new JLabel(list[3].name, SwingConstants.CENTER);

  tableCard = new JLabel(list[2].name, SwingConstants.CENTER);


  for(int i = 0; i < 5; i++)
  {
   for(int j = 0; j < 5; j++)
   {
    int width = 50, height = 50;

    JLabel label = new JLabel(" ", SwingConstants.CENTER);
    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    label.setMinimumSize(new Dimension(width, height));
    label.setPreferredSize(new Dimension(width, height));
    label.setMaximumSize(new Dimension(width, height));
    label.setOpaque(true);

    lblBoard[i][j] = label;
    board.add(label);
   }
  }

  cards.add(blue1);
  cards.add(new JLabel("< Red's Hand >", SwingConstants.CENTER));
  cards.add(blue2);

  cards.add(new JLabel(""));
  cards.add(tableCard);
  cards.add(new JLabel(""));

  cards.add(red1);
  cards.add(new JLabel("< Blue's Hand >", SwingConstants.CENTER));
  cards.add(red2);

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

}
