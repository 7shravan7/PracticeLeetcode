package design.lld.snake_ladder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigBoard {
	
	private Map<Integer, Ladder> ladderMap;
	
	private Map<Integer, Snake> snakeMap;
	
	public ConfigBoard() {
		ladderMap = new HashMap<>();
		snakeMap = new HashMap<>();
	}
	
	public void addLadder(int startPosition, int endPosition) throws IOException {
		if(snakeMap.containsKey(startPosition)) {
			throw new IOException("There is already a snake present");
		} else {
			ladderMap.put(startPosition, new Ladder(startPosition, endPosition));
		}
	}
	
	public void addSnake(int startPosition, int endPosition) throws IOException {
		if(ladderMap.containsKey(startPosition)) {
			throw new IOException("There is already a ladder present");
		} else {
			snakeMap.put(startPosition, new Snake(startPosition, endPosition));
		}
	}
	
	public boolean isLadderPosition(int position) {
		return ladderMap.containsKey(position);
	}
	
	public boolean isSnakePosition(int position) {
		return snakeMap.containsKey(position);
	}
	
	public int getLadderEndPoint(int position) {
		if(isLadderPosition(position)) {
			return ladderMap.get(position).getEndPoint();
		} else {
			return -1;
		}
	}
	
	public int getSnakeEndPoint(int position) {
		if(isSnakePosition(position)) {
			return snakeMap.get(position).getEndPoint();
		} else {
			return -1;
		}
	}
	
}
