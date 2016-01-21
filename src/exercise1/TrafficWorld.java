package exercise1;

import java.awt.Color;

import greenfoot.GreenfootImage;
import greenfoot.World;
import java.util.Random;

public class TrafficWorld extends World {
	private static final int WIDTH = 1000;
	private static final int LENGTH = 750;
	private static final int CELL_SIZE = 1;
	public static final int ROAD_WIDTH = 50;
	private static final int EW_ROADS = 5;
	private static final int NS_ROADS = 7;
	private static final int CAR_WIDTH = 20;
	private static final int CAR_LENGTH = 40;
	private static final int VERT_SPACING_INCR = ((LENGTH - (EW_ROADS * ROAD_WIDTH)) / (EW_ROADS - 1)); // 175
	private static final int HORIZ_SPACING_INCR = ((WIDTH - (NS_ROADS * ROAD_WIDTH)) / (NS_ROADS - 1)); // 159
	private final static int CAR_SPACING_HORIZ = (HORIZ_SPACING_INCR + ROAD_WIDTH);
	private final static int CAR_SPACING_VERT = (VERT_SPACING_INCR + ROAD_WIDTH);
	public static Direction north = Direction.NORTH;
	public static Direction south = Direction.SOUTH;
	public static Direction east = Direction.EAST;
	private Random random = new Random();
	private static Road[] vertRoads = new Road[NS_ROADS];
	private static Road[] horizRoads = new Road[EW_ROADS];

	public TrafficWorld() {

		super(WIDTH, LENGTH, CELL_SIZE);
		GreenfootImage background = this.getBackground();
		background.setColor(Color.green);
		background.fill();
		setVertRoads();
		setHorizRoads();
		setCarsVertLeft();
		setCarsVertRight();
		setCarsHorizDown();
		setCarsHorizUp();
		setInter();
	}

	private void setCarsVertLeft() {
		int vertIncr = 0;
		for (int i = 0; i < EW_ROADS; i++) {
			int randomX = random.nextInt(LENGTH);
			Car car = new Car(Direction.WEST);
			this.addObject(car, randomX, vertIncr + (CAR_WIDTH / 2));
			vertIncr += CAR_SPACING_VERT + 1;

		}
	}

	private void setCarsVertRight() {
		int vertIncr = 0;

		for (int i = 0; i < EW_ROADS; i++) {
			int randomX = random.nextInt(LENGTH);
			Car car = new Car(Direction.EAST);
			this.addObject(car, randomX, vertIncr + (CAR_LENGTH));
			vertIncr += CAR_SPACING_VERT;
		}
	}

	private void setCarsHorizDown() {
		int horizIncr = 0;

		for (int i = 0; i < NS_ROADS; i++) {
			int randomY = random.nextInt(LENGTH);
			Car car = new Car(Direction.SOUTH);
			this.addObject(car, horizIncr + (CAR_WIDTH / 2), randomY);
			horizIncr += CAR_SPACING_HORIZ + 1;
		}
	}

	private void setCarsHorizUp() {
		int horizIncr = 0;

		for (int i = 0; i < NS_ROADS; i++) {
			int randomY = random.nextInt(LENGTH);
			Car car = new Car(Direction.NORTH);
			this.addObject(car, horizIncr + (CAR_WIDTH * 2), randomY);
			horizIncr += CAR_SPACING_HORIZ;
		}
	}

	private void setVertRoads() {
		int vertSpacing = 0;
		for (int i = 0; i < vertRoads.length; i++) {
			vertRoads[i] = new Road();
			this.addObject(vertRoads[i], ((ROAD_WIDTH / 2) + vertSpacing) - 1, LENGTH / 2);
			vertRoads[i].setRotation(90);

			vertSpacing += (HORIZ_SPACING_INCR + ROAD_WIDTH) + 1;

		}
	}

	private void setHorizRoads() {
		int horizSpacing = 0;
		for (int i = 0; i < horizRoads.length; i++) {
			horizRoads[i] = new Road();
			// Road road = new Road();
			this.addObject(horizRoads[i], WIDTH / 2, (ROAD_WIDTH / 2) + horizSpacing);
		
			horizSpacing += (VERT_SPACING_INCR + ROAD_WIDTH);
		}
	}

	private void setInter() {
		for (int i = 0; i < horizRoads.length; i++) {
			for (int j = 0; j < vertRoads.length; j++) {
				Intersection inter = new Intersection();
				this.addObject(inter, vertRoads[j].getX(), horizRoads[i].getY());
				inter.addLights();
			}

		}

	}

	// private void setVertLights() {
	// lightSpacingHoriz = (ROAD_WIDTH/2);
	// for (int j = 0; j < NS_ROADS; j++) {
	// lightSpacingVert = 0;
	// for (int i = 0; i < EW_ROADS; i++) {
	// TrafficLight light = new TrafficLight(TrafficLight.LightState.GREEN);
	// this.addObject(light, lightSpacingHoriz, lightSpacingVert);
	// TrafficLight light1 = new TrafficLight(TrafficLight.LightState.GREEN);
	// this.addObject(light1, lightSpacingHoriz, lightSpacingVert +
	// (ROAD_WIDTH));
	// light1.setRotation(vertRight.getRotation());
	// lightSpacingVert += (CAR_SPACING_VERT);
	// }
	// lightSpacingHoriz += ((CAR_SPACING_VERT - CAR_WIDTH) + (EW_ROADS - 1));
	//
	// }
}

// private void setHorizLights() {
// lightSpacingVert = ROAD_WIDTH;
// for (int j = 0; j < NS_ROADS; j++) {
// lightSpacingHoriz = (ROAD_WIDTH/2);
// for (int i = 0; i < EW_ROADS; i++) {
// TrafficLight light = new TrafficLight(Intersection.LightState.RED);
// this.addObject(light, lightSpacingVert, lightSpacingHoriz);
// light.setRotation(horizUp.getRotation());
// TrafficLight light1 = new TrafficLight(Intersection.LightState.RED);
// this.addObject(light1, (lightSpacingVert - ROAD_WIDTH) + EW_ROADS,
// lightSpacingHoriz);
// light1.setRotation(horizDown.getRotation());
// lightSpacingHoriz += (CAR_SPACING_HORIZ + CAR_WIDTH) - (EW_ROADS - 2);
//
// }
// lightSpacingVert += (CAR_SPACING_HORIZ);
// }
//
// }
// }
