import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MenuListener implements ActionListener {
	public MenuListener() {
	}

	public void actionPerformed(ActionEvent e) {
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
		}
	}
}
