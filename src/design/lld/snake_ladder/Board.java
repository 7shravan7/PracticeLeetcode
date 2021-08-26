package design.lld.snake_ladder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Board {
	
	private int boardMaxSize;
	
	private Queue<Player> playersQueue;
	
	private int numOfDice;
	
	private Dice dice;
	
	private ConfigBoard configBoard;
	
	private Scanner scanner;
	
	private Map<Long, Integer> playerPositionMap;
	
	public Board(int boardMaxSize, int numOfDice, ConfigBoard configBoard) {
		this.boardMaxSize = boardMaxSize;
		this.playersQueue = new LinkedList<>();
		this.numOfDice = numOfDice;
		dice = new Dice(numOfDice);
		this.configBoard = configBoard;
		scanner = new Scanner(System.in);
		playerPositionMap = new HashMap<>();
	}
	
	public void addPlayer(long id, String name, String email) {
		playersQueue.add(new Player(id, name, email));
	}
	
	public void play() {
		// initialize all players start position to 0
		playersQueue.forEach(player->playerPositionMap.put(player.getId(), 0));
		while(playersQueue.size()>=2) { // loop will exit only when there is only one player left
			Player player = playersQueue.poll();
			System.out.println(player.getName()+"'s turn to roll the dice. Press 0 to roll the dice");
//			int input = scanner.nextInt();
			int diceValue = dice.rollDice();
			System.out.println(player.getName()+" Dice value : "+diceValue);
			int playerPrevPosition = playerPositionMap.get(player.getId());
			int newPostion = playerPrevPosition + diceValue;
			System.out.println(player.getName()+" New position : "+newPostion);
			if(newPostion>boardMaxSize) {
				playersQueue.offer(player);
			} else {
				if(newPostion == boardMaxSize) {
					System.out.println("!!! "+player.getName() +" wins !!!!");
				} else {
					if(configBoard.isLadderPosition(newPostion)) {
						playerPositionMap.put(player.getId(), configBoard.getLadderEndPoint(newPostion));
						System.out.println(player.getName()+" climbed ladder to : "+configBoard.getLadderEndPoint(newPostion));
					} else if (configBoard.isSnakePosition(newPostion)) {
						playerPositionMap.put(player.getId(), configBoard.getSnakeEndPoint(newPostion));
						System.out.println(player.getName()+" bitten by snake to : "+configBoard.getSnakeEndPoint(newPostion));
					} else {
						playerPositionMap.put(player.getId(), newPostion);
					}
					playersQueue.offer(player);
				}
			}
		}
	}
	
}
