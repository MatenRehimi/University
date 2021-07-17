package assignment13.Model;

import java.util.Observable;

public class Model extends Observable {
	private String foxPosition; // creating fields
	private String goosePosition;
	private String beansPosition;
	private String farmerPosition;
	private String boatPosition;
	private int score;
	private int boatCapacity;
	public final int MAX_BOAT_CAPACITY = 2;

	/*
	 * initial positions and score
	 */
	public Model() {
		foxPosition = "right";
		goosePosition = "right";
		beansPosition = "right";
		farmerPosition = "right";
		boatPosition = "east";
		score = 0;
	}

	/*
	 * checks which piece is being moved,updates model position and notifies observers
	 */

	public void changePosition(String image, String position) {

		if (image.equals("farmer")) {
			farmerPosition = position;
		}
		if (image.equals("fox")) {
			foxPosition = position;
		}
		if (image.equals("goose")) {
			goosePosition = position;
		}
		if (image.equals("beans")) {
			beansPosition = position;
		}
		if (image.equals("boat")) {
			score--;
			boatPosition = position;
		}

		setChanged();
		notifyObservers();
	}

	/*
	 * getter for farmerPosition
	 */
	public String getFarmerPosition() {
		return farmerPosition;
	}

	/*
	 * getter for foxPosition
	 */
	public String getFoxPosition() {
		return foxPosition;
	}

	/*
	 * getter for goosePosition
	 */
	public String getGoosePosition() {
		return goosePosition;
	}

	/*
	 * getter for beansPosition
	 */
	public String getBeansPosition() {
		return beansPosition;
	}

	/*
	 * getter for boatPosition
	 */
	public String getBoatPosition() {
		return boatPosition;
	}

	/*
	 * checks fox and goose are not isolated on grass
	 */
	public boolean checkFoxAndGoose() {
		if (foxPosition == "right" && goosePosition == "right"
				&& beansPosition != "right" && farmerPosition != "right") {
			return true;
		}
		if (foxPosition == "left" && goosePosition == "left"
				&& beansPosition != "left" && farmerPosition != "left") {
			return true;
		}

		return false;
	}

	/*
	 * checks goose and beans are not isolated on grass
	 */
	public boolean checkGooseAndBeans() {
		if (goosePosition == "right" && beansPosition == "right"
				&& farmerPosition != "right" && foxPosition != "right") {
			return true;
		}
		if (goosePosition == "left" && beansPosition == "left"
				&& farmerPosition != "left" && foxPosition != "left") {
			return true;
		}

		return false;
	}

	/*
	 * checks if all the peices are on the left grass
	 */
	public boolean checkWin() {
		if (goosePosition == "left" && beansPosition == "left"
				&& foxPosition == "left" && farmerPosition == "left") {
			return true;
		}
		return false;
	}

	/*
	 * getter for score
	 */
	public int getScore() {
		return score;
	}

	/*
	 * getter for boatCapacity
	 */
	public int getBoatCapacity() {
		return boatCapacity;
	}

	/*
	 * setter for boatCapacity
	 */
	public void setBoatCapacity(int boatCapacity) {
		this.boatCapacity = boatCapacity;
	}
}
