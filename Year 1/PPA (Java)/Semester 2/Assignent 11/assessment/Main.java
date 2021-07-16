package assessment;

/*
 * Creates a Banker object, puts 4 Person objects into the list and chooses the current guardian
 */
public class Main {

	public static void main(String[] args) {

		Banker mrPoe = new Banker("Mr Poe", 5);

		mrPoe.put(new Person("Josephine", 5), 100);
		mrPoe.put(new Person("Olaf", -10), 10);
		mrPoe.put(new Person("Sir", 0), 20);
		mrPoe.put(new Person("Uncle Monty", 10), 20);

		mrPoe.pickGuardian();

	}

}
