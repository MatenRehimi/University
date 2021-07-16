package assessment;

import java.util.Map; //importing packages
import java.util.TreeMap;

/*
 * Create Banker class of type person
 */
public class Banker extends Person {

	private TreeMap<Person, Integer> relativeDistance; // creating fields
	private Person currentGuardian;

	/*
	 * Setting the fields through the constructor
	 */
	public Banker(String name, int friendliness) {

		super(name, friendliness);
		relativeDistance = new TreeMap<Person, Integer>();
	}

	/*
	 * Adds a key and value to the treeMap.
	 */
	public void put(Person person, Integer distance) {

		relativeDistance.put(person, distance);
	}

	/*
	 * pick a guardian from relatives
	 */
	public void pickGuardian() {

		int minDistance = 100;
		// chooses the closest distance relative to be the guardian
		for (Map.Entry<Person, Integer> entry : relativeDistance.entrySet()) {

			if (minDistance >= entry.getValue()) {
				minDistance = entry.getValue();
				currentGuardian = entry.getKey();
			}
		}

		System.out.println("The children are moved to live with "
				+ currentGuardian.getName());
		// verifies the friendliness of the guardian
		if (currentGuardian.getFriendliness() < 5) {

			System.out.println(currentGuardian.getName()
					+ " is not friendly(fired) and a new Guardian is found");
			relativeDistance.remove(currentGuardian);
			pickGuardian();
		} else {

			System.out.println(currentGuardian.getName()
					+ " is the closest friendly Guardian");
		}
	}

	/*
	 * Overriding toString method to print the current guardian and the current
	 * list of relatives.
	 */
	public String toString() {

		String total = "";

		for (Map.Entry<Person, Integer> entry : relativeDistance.entrySet()) {

			total = entry.getKey() + ", Distance:" + entry.getValue() + " \n"
					+ total;
		}
		return "Current Guardian: " + currentGuardian + "\nRelatives:\n"
				+ total;
	}
}
