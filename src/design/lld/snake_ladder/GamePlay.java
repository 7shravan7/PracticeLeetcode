package design.lld.snake_ladder;

import java.io.IOException;

public class GamePlay {

	public static void main(String[] args) throws IOException {
		ConfigBoard configBoard = new ConfigBoard();
		configBoard.addLadder(4, 15);
		configBoard.addLadder(9, 48);
		configBoard.addLadder(11, 33);
		configBoard.addSnake(12, 0);
		configBoard.addSnake(49, 7);
		configBoard.addSnake(30, 12);
		Board board = new Board(50, 2, configBoard);
		board.addPlayer(1, "Player1", "p1@gmail.com");
		board.addPlayer(2, "Player2", "p1@gmail.com");
		board.play();
	}

}
