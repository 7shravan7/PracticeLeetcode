package designPatterns.Creational.Factory;

/*
 * Scenario is the client which is initializing shapes based on its parameter
 * Shapes is the library(class) and Rectangle, Square and Triangle are its child classes
 * public Scenario(int type) : used to initialize shapes (Client side)
 * 
 * ISSUE : If a new shape type Pentagon is added to Shapes library, Client also needs to 
 *         change their public Scenario(int type) method to include Pentagon in switch case and recompiled
 * 
 */
public class Scenario {
	
	Shapes shape;
	
	public Scenario(int type) {
		switch(type) {
			case 1:
				shape = new Rectangle();
				break;
			case 2:
				shape = new Square();
				break;
			case 3:
			default:
				shape = new Trianlge();	
				break;
		}
	}
	
		
	public static void main(String[] args) {
		Scenario scenario = new Scenario(2);
		scenario.shape.print();
	}

}

class Shapes {
	void print() {System.out.println("Default");}
}

class Rectangle extends Shapes {
	void print() { System.out.println("I am a Rectangle");}
}
	
class Square extends Shapes {
	void print() { System.out.println("I am a Square");}
}	
	
class Trianlge extends Shapes {
	void print() { System.out.println("I am a Triangle");}
}
