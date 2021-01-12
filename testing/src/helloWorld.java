/*
* File: hello_world.java
* The program displays 'hello world' on the screen.
* It is inspired by the K&R.
*/

// Imports
import acm.program.*;
import acm.graphics.*;

// Main
public class helloWorld extends GraphicsProgram {
	public void run() {
		add(new GLabel("hello world"), 100, 75);
	}
}