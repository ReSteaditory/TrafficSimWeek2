package exercise1;

public enum Direction {
	NORTH(270), SOUTH(90), EAST(180), WEST(0);
	int rotation;

	Direction(int rotation) {
		this.rotation = rotation;
	}

	int getRotation() {
		return rotation;
	}
	//Build the simulation like:
	//DO ALL THE BULLSHIT
	//Overload constructor. Woot.
	//Cry deeply.
	//Swallow sadness.
	//Eat a bagel.
	//car.setRotation(d.getRotation)
}
