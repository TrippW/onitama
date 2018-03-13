import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class CardGUI extends JPanel {
	JLabel [][] moves = new JLabel[5][5];

	JLabel name;

	JPanel movePanel;

	int color;

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public CardGUI(CardGUI cg) {
		this(cg, cg.getColor());
	}

	public CardGUI(CardGUI cg, int color) {
		init();
		this.color = color;
		set(cg);
	}

	public CardGUI(Card c, int color) {
		init();

		this.color = color;

		name.setText(c.getName());

		for(int[] m : c.getMoves()) {
			int x = 2+m[0]*color*-1, y = 2+m[1]*color;
			moves[y][x].setBackground(Color.YELLOW);
		}
		gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gridbag.setConstraints(movePanel,gbc);
		add(movePanel);
	}

	private void init() {
		setBackground(Color.WHITE);

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
			for(int x = 0; x < 5; x++) {
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

	public void setText(String n) {
		name.setText(n);
	}

	public void setMoves(JLabel [][] mp) {
		for(int y = 0; y < 5; y++)
			for(int x = 0; x < 5; x++)
				moves[y][x].setBackground(mp[y][x].getBackground());
	}

	public void swap(JLabel [][] mp) {
		for(int y = 0; y < 5; y++)
			for(int x = 0; x < 5; x++)
				moves[y][x].setBackground(mp[4-y][4-x].getBackground());
	}

	public JLabel[][] getMoves() {
		return moves;
	}

	public String getText() {
		return name.getText();
	}

	public void set(CardGUI cg, int color) {
		set(cg);
		this.color = color;
	}

	public void set(CardGUI cg) {
		setText(cg.getText());
		if(color != cg.getColor())
			swap(cg.getMoves());
		else
			setMoves(cg.getMoves());
		color = cg.getColor();
	}

	public int getColor() {
		return color;
	}

	public String getName() {
		return name.getText();
	}
}
