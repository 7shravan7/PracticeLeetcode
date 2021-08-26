package design.lld.snake_ladder;


public class Dice {
		
	private int numOfDice;
	
	public Dice(int numOfDice) {
		this.numOfDice = numOfDice;
	}

	public int getNumOfDice() {
		return numOfDice;
	}

	public void setNumOfDice(int numOfDice) {
		this.numOfDice = numOfDice;
	}
	
	// generate random numbers within range
	// Math.random()*(max-min+1)+min
	// here 6 is max and 1 is min (1 dice value)
	public int rollDice() {
		return (int)(Math.random()*(6*numOfDice - 1*numOfDice+1))+1*numOfDice;
	}
	
}
