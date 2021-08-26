package design.lld.tic_tac_toe;

import java.io.IOException;

public class GamePlay {

	public static void main(String[] args) throws IOException {
		Board ticTacToe = new Board(3);
		ticTacToe.setPlayer1(1, "Player 1");
		ticTacToe.setPlayer2(2, "Player 2");
		ticTacToe.play();
	}

}
