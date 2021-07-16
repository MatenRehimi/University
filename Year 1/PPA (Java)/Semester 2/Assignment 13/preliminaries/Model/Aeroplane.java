package preliminaries.Model;

import java.awt.Rectangle; //importing rectangle  
import java.util.Observable;

public class Aeroplane extends Observable { // making fields

	private int currentTime;
	private int x;
	private int y;
	private int speed;
	private int elevation;
	private int count;
	private Rectangle runway;

	public Aeroplane(int currentTime, int x, int y, int speed, int elevation) {

		this.currentTime = currentTime;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.elevation = elevation;
		runway = new Rectangle(11, 101); // parameters are width and length

	}

	/*
	 * getter for current time
	 */
	public int getCurrentTime() {
		return currentTime;
	}

	/*
	 * setter for current time
	 */
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	/*
	 * getter for x
	 */
	public int getX() {
		return x;
	}

	/*
	 * setter for x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * getter for y and adds the speed to y
	 */
	public int getY() {
		y = y + speed;
		return y;
	}

	/*
	 * setter for y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/*
	 * getter for speed
	 */
	public int getSpeed() {
		return speed;
	}

	/*
	 * setter for speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/*
	 * getter for elevation
	 */
	public int getElevation() {

		return elevation;
	}

	/*
	 * setter for elevation
	 */
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	/*
	 * checks if planes x and y is on the runway(rectangle)
	 */
	public boolean onRunway() {
		return runway.contains(x, y);
	}

	/*
	 * updates the elevation when speed is 10 5 times in a row
	 */
	public int updateElevation() {
		if (speed == 10) {
			count++;
			if (count >= 5) {
				elevation++;
			}
		} else {
			count = 0;
		}

		if (speed < 5 && elevation > 0) {
			elevation--;
		}
		return elevation;
	}
	
	public void FlightSimulation(Aeroplane plane) {
		while (plane.onRunway()) {
			setChanged();
			notifyObservers();
			notifyChanged(plane);
			try {
				// 1 second pause 
				Thread.sleep(1000);
				currentTime++;
				// checks if plane's elevation is greater than 4 and x value is 5
				if (plane.getElevation() > 4 && plane.getX() == 5) {
					break;
				}
			} catch (InterruptedException e) {
			}
		}
		
	}

	private void notifyChanged(Aeroplane plane) {
		// TODO Auto-generated method stub
		
	}


}
