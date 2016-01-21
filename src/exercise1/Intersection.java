package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.awt.Color;
import java.util.ArrayList;

public class Intersection extends Actor {
	private static final int GREEN_COUNT = 100, YELLOW_COUNT = 75, RED_COUNT = GREEN_COUNT + YELLOW_COUNT;
	private int lightCounter = 0;
	public TrafficLight.Color horizColor = TrafficLight.Color.RED;
	public TrafficLight.Color vertColor = TrafficLight.Color.GREEN;
	public TrafficLight trafficLightE, trafficLightW, trafficLightS, trafficLightN;
	ArrayList<InterfaceListener> inside, approaching;
	ArrayList<InterfaceListener> oldInside = new ArrayList<InterfaceListener>();
	ArrayList<InterfaceListener> oldApproaching = new ArrayList<InterfaceListener>();

	public Intersection() {
		GreenfootImage intersect = new GreenfootImage(TrafficWorld.ROAD_WIDTH, TrafficWorld.ROAD_WIDTH);
		this.setImage(intersect);

	}

	public void addLights() {
		addVertLights();
		addHorizLights();

	}

	private void addHorizLights() {
		trafficLightE = new TrafficLight(horizColor);
		getWorld().addObject(trafficLightE, this.getX() - (TrafficWorld.ROAD_WIDTH / 2), this.getY());
		trafficLightE.setRotation(TrafficWorld.north.getRotation());
		trafficLightW = new TrafficLight(horizColor);
		getWorld().addObject(trafficLightW, getX() + (TrafficWorld.ROAD_WIDTH / 2), getY());
		trafficLightW.setRotation(TrafficWorld.south.getRotation());

	}

	public void addVertLights() {
		trafficLightN = new TrafficLight(vertColor);
		getWorld().addObject(trafficLightN, this.getX(), this.getY() + (TrafficWorld.ROAD_WIDTH / 2));
		trafficLightS = new TrafficLight(vertColor);
		getWorld().addObject(trafficLightS, this.getX(), this.getY() - (TrafficWorld.ROAD_WIDTH / 2));
		trafficLightS.setRotation(TrafficWorld.east.getRotation());
	}

	public void act() {
		createArrays();
		notifyIfApproaching();
		notifyIfIn();
		notifyIfLeaving();
		lightCounter++;
		switch (vertColor) {
		case GREEN:
			if (lightCounter == GREEN_COUNT) {
				vertColor = TrafficLight.Color.YELLOW;
				setColorN();
				lightCounter = 0;
			}
			break;
		case YELLOW:
			if (lightCounter == YELLOW_COUNT) {
				vertColor = TrafficLight.Color.RED;
				setColorN();
				horizColor = TrafficLight.Color.GREEN;
				setColorW();
				lightCounter = 0;
			}
			break;
		case RED:
			if (lightCounter == GREEN_COUNT) {
				horizColor = TrafficLight.Color.YELLOW;
				setColorW();
			} else if (lightCounter == RED_COUNT) {
				vertColor = TrafficLight.Color.GREEN;
				setColorN();
				horizColor = TrafficLight.Color.RED;
				setColorW();
				lightCounter = 0;
			}
			break;

		}

		oldApproaching = approaching;
		oldInside = inside;
	}

	public void setColorN() {
		trafficLightN.setColor(vertColor);
		trafficLightS.setColor(vertColor);
	}

	public void setColorW() {
		trafficLightE.setColor(horizColor);
		trafficLightW.setColor(horizColor);
	}

	@SuppressWarnings("unchecked")
	public void createArrays() {
		inside = (ArrayList<InterfaceListener>) getObjectsInRange(TrafficWorld.ROAD_WIDTH, InterfaceListener.class);
		approaching = (ArrayList<InterfaceListener>) getObjectsInRange(TrafficWorld.ROAD_WIDTH * 2,
				InterfaceListener.class);

	}

	public void notifyIfIn() {
		for (InterfaceListener il : oldApproaching) {
			if (inside.contains(il)) {
				il.ifInIntersection(this);
			}
		}

	}

	public void notifyIfApproaching() {
		for (InterfaceListener il : approaching) {
			if (!oldApproaching.contains(il)) {
				il.ifApproachingIntersection(this);
			}
		}
	}

	public void notifyIfLeaving() {
		for (InterfaceListener il : oldInside) {
			if (!inside.contains(il)) {
				il.ifLeavingIntersection(this);
			}
		}
	}

}
