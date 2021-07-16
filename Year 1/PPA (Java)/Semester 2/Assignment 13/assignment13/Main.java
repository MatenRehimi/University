package assignment13;

import assignment13.Controller.Controller;
import assignment13.Model.Model;
import assignment13.View.Frame;

public class Main {

	/*
	 * shows MVC model in practice
	 */
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
		Frame frame = new Frame(model, controller);
		model.addObserver(frame);
	}
}
