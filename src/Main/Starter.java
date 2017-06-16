package Main;

import Calculation.Finder;

public class Starter {

	public static void main(String[] args) {
		
//		new Finder().loadNumbers();
		
		Finder find = new Finder();
		find.getNumbers(find.loadNumbers(), 100);
	}

}
