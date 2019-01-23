package main;

import engine.LogicEngine;
import robertefry.penguin.engine.Engine;

public class Main {
	
	public Engine logic = new LogicEngine();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.initialise();
	}
	
	public void initialise() {
		logic.start();
	}

}
