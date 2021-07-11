package dp.cci_book;

import java.util.Stack;

public class TowersofHanoi {
	
	public void solveTowersOfHanoi(int n) {
		Tower origin = new Tower("t1");
		Tower dest = new Tower("t3");
		Tower temp = new Tower("t2");
		for(int i=n;i>0;i--) {
			origin.add(i);
		}
		origin.printElements();
		System.out.println("The Moves are ...");
		origin.moveElementsToTower(n, dest, temp);
		System.out.println("--------------- Destination ----------");
		dest.printElements();
	}

	public static void main(String[] args) {
		TowersofHanoi towersOfHanoi = new TowersofHanoi();
		int noOfDiscs = 4;
		towersOfHanoi.solveTowersOfHanoi(noOfDiscs);
	}

}

class Tower {
	
	Stack<Integer> stack;
	
	String name;
	
	Tower(String name){
		stack = new Stack<Integer>();
		this.name = name;
	}
	
	boolean add(int val) {
		if(stack.isEmpty()) {
			stack.push(val);
			return true;
		}
		if(stack.peek()<val) {
			return false;
		} else {
			stack.push(val);
			return true;
		}
	}
	
	void moveTopToTower(Tower t) {
		t.add(stack.pop());
		System.out.println("Move from tower "+name+" to tower "+t.name);
	}
	
	void moveElementsToTower(int n, Tower dest, Tower temp) {
		if(n>0) {
			moveElementsToTower(n-1,temp, dest);
			moveTopToTower(dest);
			temp.moveElementsToTower(n-1, dest, this);
		}
	}
	
	void printElements() {
		stack.stream().forEach(st->{
			System.out.println(st);
		});
	}
	
}