package designPatterns.Creational.Factory;
/*
 * Factory Design Pattern
 * 
 * Decoupled the selection of type for object creation from Solution(Client)
 * 
 * The library is now responsible to decide which object type to create based on an input. 
 * Client just needs to make call to library’s factory Create method and 
 * pass the type it wants without worrying about the actual implementation of creation of objects.
 */
public class Solution {
	
	Shape shape;
	
	public Solution(ShapeTypes type) {
		shape = Shape.getShape(type);
	}

	public static void main(String[] args) {
		Solution factory = new Solution(ShapeTypes.TRIANGLE);
		factory.shape.print();
	}

}

enum ShapeTypes
{
    RECTANGLE, SQUARE, TRIANGLE;
}

class Shape {
	
	void print() {System.out.println("Default");}
	
	// factory pattern
	public static Shape getShape(ShapeTypes type) {
		Shape shape;
		switch (type) {
			case RECTANGLE:
				shape = new Rectangle1();
				break;
			case SQUARE:
				shape = new Square1();
				break;
			case TRIANGLE:
			default:
				shape = new Trianlge1();
				break;
		}
		return shape;
	}
}

class Rectangle1 extends Shape {
	void print() { System.out.println("I am a Rectangle");}
}
	
class Square1 extends Shape {
	void print() { System.out.println("I am a Square");}
}	
	
class Trianlge1 extends Shape {
	void print() { System.out.println("I am a Triangle");}
}