package src.p3

import a7.JSpotBoard
import a7.Spot
import a7.SpotListener
import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class ExampleWidget extends JPanel implements SpotListener {

	private enum Player {
		BLUE, GREEN
	}

	private JSpotBoard board
	private JLabel message
	private boolean gameWon
	private Spot secretSpot
	private Color secretSpotBG
	private Player nextToPlay

	ExampleWidget(){
		board = new JSpotBoard(5, 5)
		message = new JLabel()

		setLayout new BorderLayout()
		add(board, BorderLayout.CENTER)

		def resetMessagePanel = new JPanel()
		resetMessagePanel.setLayout(new BorderLayout())

		def resetButton = new JButton("Restart")
		resetButton.addActionListener {e -> resetGame()}
		resetMessagePanel.add resetButton, BorderLayout.EAST
		resetMessagePanel.add message, BorderLayout.CENTER

		add resetMessagePanel, BorderLayout.SOUTH

		board.addSpotListener this

		resetGame()
	}

	private void resetGame(){
		for(def spot : board)
			spot.clearSpot()

		if(secretSpot != null)
			secretSpot.background = secretSpotBG;

		// Pick a new secret spot.
		def secretX = (int) (Math.random() * board.spotWidth)
		def secretY = (int) (Math.random() * board.spotWidth)
		secretSpot = board.getSpotAt secretX, secretY
		secretSpotBG = secretSpot.background

		gameWon = false
		nextToPlay = Player.BLUE

		message.setText "Welcome to the Example. Blue to play"
	}

	@Override
	void spotClicked(Spot spot){
		if(gameWon)
			return

		String playerName
		String nextPlayerName
		Color playerColor

		if(nextToPlay == Player.BLUE){
			playerColor = Color.BLUE
			playerName = "Blue"
			nextPlayerName = "Green"
			nextToPlay = Player.GREEN
		}else{
			playerColor = Color.GREEN
			playerName = "Green"
			nextPlayerName = "Blue"
			nextToPlay = Player.BLUE
		}

		spot.setSpotColor(playerColor)
		spot.toggleSpot()

		gameWon = (spot == secretSpot)
		if(gameWon)
			spot.background = Color.RED;


		if(spot.isEmpty())
			message.setText("${playerName} cleared the spot at ${spot.getCoordString()}. ${nextPlayerName} to play.")
		else{
			if(gameWon){
				def score = board.spotWidth * board.spotHeight
				
				for(def boardSpot : board)
					if(!boardSpot.isEmpty())
						if(boardSpot.spotColor == playerColor)
							score--
						else
							score++

				message.setText("${playerName} found the secret spot at ${spot.coordString}. Score: ${score}. Game over.")
			}else
				message.setText("Spot at ${spot.coordString} is not the spot. Better luck next time. ${nextPlayerName} to play.")
		}
	}

	@Override
	void spotEntered(Spot spot){
		if(gameWon)
			return

		spot.highlightSpot()
	}

	@Override
	void spotExited(Spot spot){
		spot.unhighlightSpot()
	}
}