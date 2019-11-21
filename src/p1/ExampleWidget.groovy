package src.p1;

import a7.JSpotBoard
import a7.Spot
import a7.SpotListener
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExampleWidget extends JPanel implements SpotListener {

	private enum Player {
		BLUE, GREEN
	};

	private JSpotBoard board;
	private JLabel message;
	private boolean gameWon;
	private Spot secretSpot;
	private Color secretSpotBG;
	private Player nextToPlay;

	public ExampleWidget(){
		board = new JSpotBoard(5, 5);
		message = new JLabel();

		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);

		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());

		JButton reset_button = new JButton("Restart");
		reset_button.addActionListener({e -> resetGame()});
		reset_message_panel.add(reset_button, BorderLayout.EAST);
		reset_message_panel.add(message, BorderLayout.CENTER);

		add(reset_message_panel, BorderLayout.SOUTH);

		board.addSpotListener(this);

		resetGame();
	}

	private void resetGame(){
		for(Spot s : board)
			s.clearSpot();
		
		if(secretSpot != null)
			secretSpot.setBackground(secretSpotBG);

		// Pick a new secret spot.
		int secret_x = (int) (Math.random() * board.getSpotWidth());
		int secret_y = (int) (Math.random() * board.getSpotWidth());
		secretSpot = board.getSpotAt(secret_x, secret_y);
		secretSpotBG = secretSpot.getBackground();

		gameWon = false;
		nextToPlay = Player.BLUE;

		message.setText("Welcome to the Example. Blue to play");
	}

	@Override
	public void spotClicked(Spot s){
		if(gameWon)
			return;

		String player_name = null;
		String next_player_name = null;
		Color player_color = null;

		if(nextToPlay == Player.BLUE){
			player_color = Color.BLUE;
			player_name = "Blue";
			next_player_name = "Green";
			nextToPlay = Player.GREEN;
		}else{
			player_color = Color.GREEN;
			player_name = "Green";
			next_player_name = "Blue";
			nextToPlay = Player.BLUE;
		}

		s.setSpotColor(player_color);
		s.toggleSpot();

		gameWon = (s == secretSpot);
		if(gameWon)
			s.setBackground(Color.RED);
		

		if(s.isEmpty())
			message.setText(player_name + " cleared the spot at " + s.getCoordString() + ". "
					+ next_player_name + " to play.");
		else{
			if(gameWon){
				int score = board.getSpotWidth() * board.getSpotHeight();
				for(Spot board_spot : board)
					if(!board_spot.isEmpty())
						if(board_spot.getSpotColor() == player_color)
							score -= 1;
						else
							score += 1;
				
				message.setText(player_name + " found the secret spot at " + s.getCoordString() + ". " + "Score: "
						+ score + ". Game over.");
			}else
				message.setText("Spot at " + s.getCoordString() + " is not the spot. Better luck next time. "
						+ next_player_name + " to play.");
			
		}
	}

	@Override
	public void spotEntered(Spot spot){
		if(gameWon)
			return;
		
		spot.highlightSpot();
	}

	@Override
	public void spotExited(Spot spot){
		spot.unhighlightSpot();
	}
}