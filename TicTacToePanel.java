package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TicTacToePanel extends JPanel {
	/*
	 * Play Tic Tac Toe
	 * Authors: Aaja Christie and Abu Nadim M.H. Kabir
	 */
	private static final long serialVersionUID = 1L;
	private int moves = 9;
	ArrayList<JButton> list = new ArrayList<JButton>();

	JCheckBox f = new JCheckBox("");
	JLabel first = new JLabel("Play the Computer");
	JLabel go = new JLabel("Play Computer");
	ArrayList<Integer> spots = new ArrayList<Integer>();

	JButton newgame = new JButton("New Game");

	JLabel winner = new JLabel("");

	Boolean gameover = false;

	private boolean xWon = false;
	private boolean oWon = false;

	private boolean play = false;

	public TicTacToePanel() {
		// Clear SCREEN

		this.setUpGame();
		this.setUpLogic();
	}

	private void setUpGame() {

		System.out.println("Game set up");
		JPanel game = new JPanel(new GridLayout(3, 3));
		setBackground(Color.black);
		game.setPreferredSize(new Dimension(300, 40));

		first.setPreferredSize(new Dimension(270, 55));

		first.setFont(new Font("Arial", Font.PLAIN, 25));
		first.setForeground(Color.white);

		add(first);
		add(f);

		newgame.setPreferredSize(new Dimension(200, 50));

		for (int i = 0; i < 9; i++) {
			JButton b = new JButton("");
			list.add(b);
			spots.add(i);
		}

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPreferredSize(new Dimension(100, 100));
			list.get(i).setFont(new Font("Arial", Font.PLAIN, 50));
			add(list.get(i));
		}

		newgame.setFont(new Font("Arial", Font.PLAIN, 30));

		add(newgame);
		add(winner);
		winner.setFont(new Font("Arial", Font.PLAIN, 50));
		winner.setForeground(Color.white);

	}

	private void setUpLogic() {
		System.out.println("Logic set up");
		for (JButton currButton : list) {
			currButton.addActionListener(new PlayListener());
		}
		newgame.addActionListener(new PlayListener());
		f.addActionListener(new PlayListener());
	}

	//@SuppressWarnings("deprecation")
	private void compMove() {
		System.out.println("compMove was called");
		/*
		 * Random rand = new Random(); int index = 0; index =
		 * rand.nextInt(spots.size()); int num = 0; num = spots.get(index);
		 */

		ArrayList<JButton> emptyButtons = new ArrayList<JButton>();
		for (JButton currButton : list) {

			if (currButton.getText() != "X" && currButton.getText() != "O") {
				emptyButtons.add(currButton);
			}
		}

		//int randomNum = ThreadLocalRandom.current().nextInt(0, emptyButtons.size() + 1);


		if (emptyButtons.size() > 0) {
			System.out.println("O was added");
			Integer listIndex = list.indexOf(emptyButtons.get(0));

			list.get(listIndex).setText("O");
			list.get(listIndex).setForeground(Color.blue);
		}
	}

	private void newGame() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setText("");
			list.get(i).setEnabled(true);
		}

		spots.clear();
		list.clear();

		xWon = false;
		oWon = false;

		this.removeAll();
		this.revalidate();
		this.repaint();

		this.setUpGame();
		this.setUpLogic();

		winner.setText("");

		moves = 9;

		setBackground(Color.black);

		gameover=false;

	}

	private void whoWon() {

		// IF X WON
        System.out.println("Who won called");
		this.checkThreeSpacesForX(0, 1, 2);
		this.checkThreeSpacesForX(0, 4, 8);
		this.checkThreeSpacesForX(0, 3, 6);
		this.checkThreeSpacesForX(3, 4, 5);
		this.checkThreeSpacesForX(6, 7, 8);
		this.checkThreeSpacesForX(1, 4, 7);
		this.checkThreeSpacesForX(2, 5, 8);
		this.checkThreeSpacesForX(2, 4, 6);

		if (xWon) {
			winner.setText("You win!");
			setBackground(Color.green);
		}

		// IF O WON
		this.checkThreeSpacesForO(0, 1, 2);
		this.checkThreeSpacesForO(0, 4, 8);
		this.checkThreeSpacesForO(0, 3, 6);
		this.checkThreeSpacesForO(3, 4, 5);
		this.checkThreeSpacesForO(6, 7, 8);
		this.checkThreeSpacesForO(1, 4, 7);
		this.checkThreeSpacesForO(2, 5, 8);
		this.checkThreeSpacesForO(2, 4, 6);

		if (oWon) {
			winner.setText("You lost!");
			setBackground(Color.red);
		}
       if(play==true) {
		if (moves == 4 && gameover == false) {
			winner.setText("Cat's Game");
			gameover = true;
		}
       }else if(play==false) {
    		   if (moves == 1 && gameover == false) {
   			winner.setText("Cat's Game");
   			gameover = true;
    	    }
       }
	}

	private void checkThreeSpacesForX(int first, int second, int third) {

		if (gameover == false) {
			if (list.get(first).getText() == list.get(second).getText()
					&& list.get(second).getText() == list.get(third).getText() && (list.get(first).getText() == "X")) {
				gameover = true;
				xWon = true;
			}
		}

	}

	private void checkThreeSpacesForO(int first, int second, int third) {

		if (gameover == false) {
			if (list.get(first).getText() == list.get(second).getText()
					&& list.get(second).getText() == list.get(third).getText() && (list.get(first).getText() == "O")) {
				gameover = true;
				oWon = true;
			}
		}

	}

	private class PlayListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

		    System.out.println(moves);
		    String var = "";
		    String x = "X";
		    String o = "O";
		    if(f.isSelected()==true) {//computer
		    	play = true;

		    	System.out.println("Computer game");
		    }
		    if(f.isSelected()==false) {//friend
		    	play = false;

		    	System.out.println("Person game");
		    }

		    if(play==true) {



				if (event.getSource() == list.get(0)) {
					list.get(0).setForeground(Color.cyan);
					list.get(0).setText("X");
					moves--;
					System.out.println("Square 0 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(1)) {
					list.get(1).setForeground(Color.cyan);
					list.get(1).setText("X");
					moves--;
					System.out.println("Square 1 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(2)) {
					list.get(2).setForeground(Color.cyan);
					list.get(2).setText("X");
					moves--;
					System.out.println("Square 2 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(3)) {
					list.get(3).setForeground(Color.cyan);
					list.get(3).setText("X");
					moves--;
					System.out.println("Square 3 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(4)) {
					list.get(4).setForeground(Color.cyan);
					list.get(4).setText("X");
					moves--;
					System.out.println("Square 4 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(5)) {
					list.get(5).setForeground(Color.cyan);
					list.get(5).setText("X");
					moves--;
					System.out.println("Square 5 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(6)) {
					list.get(6).setForeground(Color.cyan);
					list.get(6).setText("X");
					moves--;
					System.out.println("Square 6 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(7)) {
					list.get(7).setForeground(Color.cyan);
					list.get(7).setText("X");
					moves--;
					System.out.println("Square 7 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == list.get(8)) {
					list.get(8).setForeground(Color.cyan);
					list.get(8).setText("X");
					moves--;
					System.out.println("Square 8 clicked");
					compMove();
					whoWon();
				} else if (event.getSource() == newgame) {
					newGame();

				}
		  }else if(play==false) {
			  if(moves % 2 == 1) {
					var = x;
				}

				if(moves % 2 == 0) {
					var = o;
				}

			  if (event.getSource() == list.get(0)) {
				 if(var == x)
					list.get(0).setForeground(Color.cyan);
				 else
					 list.get(0).setForeground(Color.blue);
					list.get(0).setText(var);
					moves--;
					System.out.println("Square 0 clicked");

					whoWon();
				} else if (event.getSource() == list.get(1)) {
					if(var == x)
						list.get(1).setForeground(Color.cyan);
					 else
						 list.get(1).setForeground(Color.blue);
					list.get(1).setText(var);
					moves--;
					System.out.println("Square 1 clicked");

					whoWon();
				} else if (event.getSource() == list.get(2)) {
					if(var == x)
						list.get(2).setForeground(Color.cyan);
					 else
						 list.get(2).setForeground(Color.blue);
					list.get(2).setText(var);
					moves--;
					System.out.println("Square 2 clicked");

					whoWon();
				} else if (event.getSource() == list.get(3)) {
					if(var == x)
						list.get(3).setForeground(Color.cyan);
					 else
						 list.get(3).setForeground(Color.blue);
					list.get(3).setText(var);
					moves--;
					System.out.println("Square 3 clicked");

					whoWon();
				} else if (event.getSource() == list.get(4)) {
					if(var == x)
						list.get(4).setForeground(Color.cyan);
					 else
						 list.get(4).setForeground(Color.blue);
					list.get(4).setText(var);
					moves--;
					System.out.println("Square 4 clicked");

					whoWon();
				} else if (event.getSource() == list.get(5)) {
					if(var == x)
						list.get(5).setForeground(Color.cyan);
					 else
						 list.get(5).setForeground(Color.blue);
					list.get(5).setText(var);
					moves--;
					System.out.println("Square 5 clicked");

					whoWon();
				} else if (event.getSource() == list.get(6)) {
					if(var == x)
						list.get(6).setForeground(Color.cyan);
					 else
						 list.get(6).setForeground(Color.blue);
					list.get(6).setText(var);
					moves--;
					System.out.println("Square 6 clicked");

					whoWon();
				} else if (event.getSource() == list.get(7)) {
					if(var == x)
						list.get(7).setForeground(Color.cyan);
					 else
						 list.get(7).setForeground(Color.blue);
					list.get(7).setText(var);
					moves--;
					System.out.println("Square 7 clicked");

					whoWon();
				} else if (event.getSource() == list.get(8)) {
					if(var == x)
						list.get(8).setForeground(Color.cyan);
					 else
						 list.get(8).setForeground(Color.blue);
					list.get(8).setText(var);
					moves--;
					System.out.println("Square 8 clicked");

					whoWon();
				} else if (event.getSource() == newgame) {
					newGame();

				}
		  }
		}

	}

}
