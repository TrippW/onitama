import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class GUI implements MouseListener {
	private JFrame frame;
	private JPanel main, cards, board;
	private JMenuBar menuBar;
	private JMenu menu, menuInfo;
	private JMenuItem menuPlayers, menuDifficulty, menuReset, menuHelp;

	private JLabel curPlayer;

	private CardGUI blue1, blue2, red1, red2, tableCard;

	private JLabel [][] lblBoard = new JLabel[5][5];

	private Component lastCardHighlighted = null;
	private Component selectedPawn = null;

	private Board gameBoard;
	private Color colorRed = new Color(0xf5544e);
	private Color colorBlue = new Color(0x4e96f5);

	public GUI(Card [] list, Board b) {
		gameBoard = b;
		if(b == null) {
			System.out.println("Game board not initialized");
			System.exit(1);
		}
		frame = new JFrame("Onitama");
		curPlayer = new JLabel(b.getCurrentPlayer().getColorString());
		initFrame(list);
		initBar();
		finishFrame();
		initGUIBoard();
	}

	public void initBar() {
		menuBar = new JMenuBar();
		menu = new JMenu("Settings");
		menu.setMnemonic(KeyEvent.VK_S);
		menu.getAccessibleContext().setAccessibleDescription("The Settings for number of players and computer difficulty");
		menuBar.add(menu);

		menuReset = new JMenuItem("Reset");
		menuReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		menuReset.addActionListener(new MenuListener());
		menuReset.setActionCommand("Reset");
		menu.add(menuReset);

		menuPlayers = new JMenuItem("Set Number of Players");
		menuPlayers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menuPlayers.addActionListener(new MenuListener());
		menuPlayers.setActionCommand("PlayerNum");
		menu.add(menuPlayers);

		menuDifficulty = new JMenuItem("Set Computer Difficulty");
		menuDifficulty.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuDifficulty.addActionListener(new MenuListener());
		menuDifficulty.setActionCommand("Difficulty");
		menu.add(menuDifficulty);

		menuInfo = new JMenu("?");
		menuInfo.setMnemonic(KeyEvent.VK_I);
		menu.getAccessibleContext().setAccessibleDescription("Help");
		menuBar.add(menuInfo);

		menuHelp = new JMenuItem("How to play");
		menuHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		menuHelp.addActionListener(new MenuListener());
		menuHelp.setActionCommand("Help");
		menuInfo.add(menuHelp);

		frame.setJMenuBar(menuBar);
	}

	public void initGUIBoard() {

		for(Piece [] pRow : gameBoard.getBoard())
			for(Piece p : pRow) {
				if(p == null) continue;
				Coordinate c = p.getCoord();
				int x = c.getX();
				int y = c.getY();

				if(p.isKing())
					lblBoard[y][x].setText("K");
				else
					lblBoard[y][x].setText("P");

				if(p.getColor() == Board.blue)
					lblBoard[y][x].setBackground(colorBlue);
				else
					lblBoard[y][x].setBackground(colorRed);

			}
	}

	private void initFrame(Card [] list) {
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

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
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
	}

	private void finishFrame() {
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void reset(Card [] list, Board b) {

		frame.getContentPane().removeAll();

		lblBoard = new JLabel[5][5];

		lastCardHighlighted = null;
		selectedPawn = null;


		gameBoard = new Board(b);

		if(b == null) {
			System.out.println("Game board not initialized");
			System.exit(1);
		}
		curPlayer = new JLabel(b.getCurrentPlayer().getColorString());
		initFrame(list);
		initBar();
		frame.pack();
		frame.validate();
		initGUIBoard();
	}


	public void mouseExited(MouseEvent e) {

	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mousePressed(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		if(gameBoard.checkWin()) return;

		if(e.getComponent().getParent() == cards) {
			selectCard(e);
		}

		if(e.getComponent().getParent() == board) {
			if(selectedPawn != null && e.getComponent().getBackground()==Color.YELLOW) {
				play(e);
			} else {
				if((e.getComponent().getBackground() == colorRed && curPlayer.getText().equals("Red")) || (e.getComponent().getBackground() == colorBlue && curPlayer.getText().equals("Blue")))
					highlightMoves(e);
			}
		}
	}

	private void selectCard(MouseEvent e) {
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

	private void unhighlightCard() {
		if(lastCardHighlighted != null)
			lastCardHighlighted.setBackground(Color.WHITE);
	}

	private void play(MouseEvent e) {
		Coordinate from = null;
		Coordinate to = null;

		for(int x = 0; x < 5; x++)
			for(int y = 0; y < 5; y++) {
				if(lblBoard[y][x] == selectedPawn)
					from = new Coordinate(x,y);
				if(lblBoard[y][x] == e.getComponent())
					to = new Coordinate(x,y);
			}
		play(from, to);
	}


	/* private void play(Coordinate [] cords)
	 {
	  play(cords[0],cords[1]);
	 }
	*/
	private void play(Coordinate from, Coordinate to) {
		move(from,to);
		checkWin();

		selectedPawn = null;

		if(gameBoard.isComputerTurn() && !gameBoard.checkWin())
			compTurn();

	}

	public void compTurn() {
		gameBoard.computerTurn();
		update();
		checkWin();
		if(gameBoard.isComputerTurn() && !gameBoard.checkWin()) {
			try {
				Thread.sleep(500);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			compTurn();
		}
	}

	private void checkWin() {
		updateBoard();
		if(gameBoard.checkWin()) {
			curPlayer.setText("Winner: "+gameBoard.getWinner().getColorString());
			System.out.println(curPlayer.getText());
		}
	}

	private void move(Coordinate from, Coordinate to) {
		if(lastCardHighlighted != null) {
			if(selectedPawn != null)
				unhighlightMoves();
			unhighlightCard();
		}

		gameBoard.play(from, to);
		update();
		updateBoard();
	}

	private void updateBoard() {
		for(int y = 0; y < 5; y++)
			for(int x = 0; x < 5; x++)
				if(gameBoard.getPiece(x,y) == null) {
					lblBoard[y][x].setText("");
					lblBoard[y][x].setBackground(Color.WHITE);
				} else {
					if(gameBoard.getPiece(x,y).isKing())
						lblBoard[y][x].setText("K");
					else
						lblBoard[y][x].setText("P");

					if(gameBoard.getPiece(x,y).getColor() == Board.red)
						lblBoard[y][x].setBackground(colorRed);
					else
						lblBoard[y][x].setBackground(colorBlue);
				}
	}

	public void update() {
//updatePlayer
		switchPlayer();

//update Cards
		if(lastCardHighlighted != null)
			switchCard();
		else
			updateCards();

//update Board
		updateBoard();
	}


//Private methods for user selection/movement
	private void unhighlightMoves() {
		updateBoard();
	}


	private void highlightMoves(MouseEvent e) {
		highlightMoves(e.getComponent());
	}


	private void highlightMoves(Component c) {
		if(lastCardHighlighted == null) {
			System.out.println("Unhighlighted error: no card selected");
			return;
		}

		if(selectedPawn != null)
			unhighlightMoves();

		selectedPawn = c;
		Coordinate from = null;

		for(int x = 0; x < 5; x++)
			for(int y = 0; y < 5; y++)
				if(lblBoard[y][x] == selectedPawn) {
					from = new Coordinate(x,y);
					x=7;
					break;
				}
		if(from == null) {
			System.out.println("Highlighted error: no pawn selected");
			selectedPawn = null;
			return;
		}

		for(int [] pair : Card.getCardByName(((CardGUI)lastCardHighlighted).getText()).getMoves()) {
			int newX = pair[0]*gameBoard.getCurrentPlayer().getColor()*-1+from.getX();
			int newY = pair[1]*gameBoard.getCurrentPlayer().getColor()+from.getY();
			if(newX >= 0 && newX < 5 && newY >= 0 && newY < 5)
				if(lblBoard[newY][newX].getBackground() != lblBoard[from.getY()][from.getX()].getBackground())
					lblBoard[newY][newX].setBackground(Color.YELLOW);
		}
	}


	private void updateCards() {
		Card [] c = gameBoard.getPlayerOfColor(Board.red).getCards();
		red1.set(new CardGUI(c[0], Board.red));
		red2.set(new CardGUI(c[1], Board.red));

		c = gameBoard.getPlayerOfColor(Board.blue).getCards();
		blue1.set(new CardGUI(c[0], Board.blue));
		blue2.set(new CardGUI(c[1], Board.blue));

		tableCard.set(new CardGUI(gameBoard.getBoardCard(),Board.blue));
	}

	private void switchCard() {
		if(lastCardHighlighted == null) {
			System.out.println("ERROR SWITCHING CARDS!");
			System.exit(2);
		}

		CardGUI temp = new CardGUI((CardGUI) lastCardHighlighted);
		gameBoard.swapCard(Card.getCardByName(temp.getName()));
		lastCardHighlighted = null;
		updateCards();
	}

	private void switchPlayer() {
		gameBoard.switchPlayer();
		curPlayer.setText(gameBoard.getCurrentPlayer().getColorString());
	}
}
