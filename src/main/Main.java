package main;

import model.Model;

public class Main {
	
	private final Model model = new Model();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.initialise();
	}
	
	public void initialise() {
		model.start();
	}

}
