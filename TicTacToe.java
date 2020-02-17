package Game;

import javax.swing.JFrame;

public class TicTacToe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TicTacToePanel panel = new TicTacToePanel();
		frame.getContentPane().add(panel);

		frame.setSize(400, 600);
		frame.setVisible(true);

	}

}
