package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.util.Random;

public class Car extends Actor implements InterfaceListener {
	Random rand = new Random();
	private static final int WIDTH = 1000;
	private static final int LENGTH = 750;
	private static final String im = "images\\";
	private static final String[] files = { im + "topCarBlue.png", im + "topCarRed.png", im + "topCarPurple.png", im + "topCarYellow.png" };
	//private CarState carState = CarState.GO;
	private Direction direction;
	private static final int GO_SPEED = 2;
	private static final int SLOW_SPEED = 1;
	private static final int STOP_SPEED = 0;
	private int carSpeed = GO_SPEED;
	
	public Car(Direction direction) {
		int random = rand.nextInt(files.length);
		GreenfootImage image;
		image = new GreenfootImage(files[random]);
		this.setImage(image);
		setRotation(direction.getRotation());
		this.direction = direction;
	}

	public void act() {
		move(carSpeed);
		if (isAtEdge()) {
			if (getY() > LENGTH - 2) {
				setLocation(getX(), getY() - LENGTH);
			} else if (getY() < 1) {
				setLocation(getX(), getY() + LENGTH);
			} else if (getX() > LENGTH - 2) {
				setLocation(getX() - WIDTH, getY());
			} else if (getX() < 1) {
				setLocation(getX() + WIDTH, getY());
			}
			

		}
	}

	@Override
	public void ifInIntersection(Intersection inter) {
		if(this.direction.equals(Direction.EAST) || this.direction.equals(Direction.WEST)){
			switch (inter.horizColor){
			case GREEN:
				carSpeed = GO_SPEED;
				break;
			case RED:
				carSpeed = STOP_SPEED;
				break;
			case YELLOW:
				carSpeed = GO_SPEED;
				break;
			
			}
		}
		if(this.direction.equals(Direction.NORTH) || this.direction.equals(Direction.SOUTH)){
			switch(inter.vertColor){
			case GREEN:
				carSpeed = GO_SPEED;
				break;
			case RED:
				carSpeed = STOP_SPEED;
				break;
			case YELLOW:
				carSpeed = GO_SPEED;
				break;
			
			}
		}
		
	}

	@Override
	public void ifApproachingIntersection(Intersection inter) {
		if(this.direction.equals(Direction.EAST) || this.direction.equals(Direction.WEST)){
			switch(inter.horizColor){
			case GREEN:
				carSpeed = GO_SPEED;
				break;
			case RED:
				carSpeed = SLOW_SPEED;
				break;
			case YELLOW:
				carSpeed = SLOW_SPEED;
				break;
			
			}
		}
		if(this.direction.equals(Direction.NORTH) || this.direction.equals(Direction.SOUTH)){
			switch(inter.vertColor){
			case GREEN:
				carSpeed = GO_SPEED;
				break;
			case RED:
				carSpeed = SLOW_SPEED;
				break;
			case YELLOW:
				carSpeed = SLOW_SPEED;
				break;
			
			}
		}
		
		
	}

	@Override
	public void ifLeavingIntersection(Intersection inter) {
		carSpeed = GO_SPEED;
		
	}

}
