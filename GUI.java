import java.awt.*;
import javax.swing.*;

public class GUI
{
 JFrame frame;
 JPanel main, cards, board;


 public GUI()
 {
  initFrame();
 }

 private void initFrame()
 {
  frame = new JFrame("Oniama");

  main = new JPanel();
  main.setLayout(new GridLayout(2,2));
  cards = new JPanel();
  cards.setLayout(new GridLayout(3,3));
  board = new JPanel();
  board.setLayout(new GridLayout(5,5));

  main.add(cards,1,0);
  main.add(board,1,1);

  frame.add(main);

  frame.setSize(300,300);
  frame.setLocationRelativeTo(null);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
 }

}
