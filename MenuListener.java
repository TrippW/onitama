import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MenuListener implements ActionListener {
	public MenuListener() {
	}

	public void actionPerformed(ActionEvent e) {
		final String instructions = "To win you need to eliminate your opponents king piece or get your king piece to the opponents center square.\n" +
				"First, click on one of the two cards on your side of the board. Then click on the piece you want to move.\n" +
				"You can change either the card or the piece you want to use that turn before finalizing your move.\nTo finalize " +
				"the move, click on any of the yellow highlighted squares.\nYour piece will move there and you will swap the card " +
				"you used for the card in the center.\nGame continues until there is a winner.";
		String input = "";
		int count = -2;
		switch(e.getActionCommand()) {
		case "Difficulty":
			System.out.println("Set Difficulty...");
			while(count < 0 || count > 2) {
				while(!input.matches("-?[0-9]+")) {
					input = JOptionPane.showInputDialog(null, "Select an option:\n0: Easy\n1: Medium\n2: Hard");
					if(input == null) return;
				}
				count = Integer.parseInt(input);
				input = "";
			}

			if(count == -1) return;
			if(count == 0)
				Onitama.updateDifficulty(Player.EASY);
			else if (count == 1)
				Onitama.updateDifficulty(Player.MEDIUM);
			else
				Onitama.updateDifficulty(Player.HARD);
			break;

		case "PlayerNum":
			System.out.println("Set PlayerNum...");
			while(count < 0 || count > 2) {
				while(!input.matches("-?[0-9]+")) {
					input = JOptionPane.showInputDialog(null, "Select an option:\n0: Computer v Computer\n1: Human v Computer\n2: Human v Human");
					if(input == null) return;
				}
				count = Integer.parseInt(input);
				input = "";
			}

			Onitama.updatePlayerCount(count);

		case "Reset":
			System.out.println("Reseting...");
			Onitama.reset();
			break;
		case "Help":
			JOptionPane.showMessageDialog(null, instructions, "How to play", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
