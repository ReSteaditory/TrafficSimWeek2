package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TrafficLight extends Actor {

	private static final String[] colorImages = { "images/trafficLightGreen.png", "images/trafficLightYellow.png",
			"images/trafficLightRed.png" };

	public static enum Color {
		GREEN, YELLOW, RED;
	}

//	public void setImage() {
//		Intersection inter = new Intersection();
//		GreenfootImage image = new GreenfootImage(colorImages[inter.setLightImage()]);
//		this.setImage(image);
//	}
	public TrafficLight(Color initialColor){
		//initialColor.ordinal();
		GreenfootImage image = new GreenfootImage(colorImages[initialColor.ordinal()]);
		this.setImage(image);
		
	}
	public void setColor(Color newColor){
		setImage(colorImages[newColor.ordinal()]);
	}

}
