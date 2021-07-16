package assessment;

/*
 * Create Person class with interface Comparable
 */
public class Person implements Comparable<Person> {

	private String name; // making fields
	private int friendliness;

	/*
	 * Setting the fields through the constructor
	 */
	public Person(String name, int friendliness) {

		this.name = name;
		this.friendliness = friendliness;

	}

	/*
	 * get name
	 */
	public String getName() {
		return name;
	}

	/*
	 * get friendliness
	 */
	public int getFriendliness() {
		return friendliness;
	}

	/*
	 * set name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * set friendliness
	 */
	public void setFriendliness(int friendliness) {
		this.friendliness = friendliness;
	}

	/*
	 * overriding compare method that distinguishes different Person objects in
	 * TreeMap
	 */
	public int compareTo(Person person) {

		return name.compareTo(person.name);
	}

	/*
	 * Overriding toString method to print name and friendliness
	 */
	public String toString() {
		return "Name:" + getName() + ", Friendliness:" + getFriendliness();
	}
}
