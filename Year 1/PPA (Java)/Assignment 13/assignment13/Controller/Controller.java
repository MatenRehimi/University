package assignment13.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import assignment13.Model.Model;

public class Controller implements ActionListener {

	private Model model; // creating field

	public Controller(Model model) { // link parameter to field
		this.model = model;

	}

	/*
	 * Checks conditions, finds which button is pressed, then updates the model
	 * accordingly
	 */
	public void actionPerformed(ActionEvent e) {
		if (!model.checkFoxAndGoose() && !model.checkGooseAndBeans()
				&& !model.checkWin()) {
			if (((JButton) e.getSource()).getName().equals("leftFarmer")) {
				if (model.getBoatPosition().equals("west")) {
					if (model.getFarmerPosition().equals("middle")) {
						model.changePosition("farmer", "left");
						model.setBoatCapacity(model.getBoatCapacity() - 1);
					}
				}
				if (model.getBoatPosition().equals("east")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getFarmerPosition().equals("right")) {
							model.changePosition("farmer", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);
						}
					}
				}
			}

			if (((JButton) e.getSource()).getName().equals("rightFarmer")) {
				if (model.getBoatPosition().equals("east")) {
					if (model.getFarmerPosition().equals("middle")) {
						model.changePosition("farmer", "right");
						model.setBoatCapacity(model.getBoatCapacity() - 1);
					}
				}
				if (model.getBoatPosition().equals("west")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getFarmerPosition().equals("left")) {
							model.changePosition("farmer", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);
						}
					}
				}
			}

			if (((JButton) e.getSource()).getName().equals("leftFox")) {
				if (model.getBoatPosition().equals("west")) {
					if (model.getFoxPosition().equals("middle")) {
						model.changePosition("fox", "left");
						model.setBoatCapacity(model.getBoatCapacity() - 1);
					}
				}
				if (model.getBoatPosition().equals("east")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getFoxPosition().equals("right")) {
							model.changePosition("fox", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);

						}
					}
				}
			}
			if (((JButton) e.getSource()).getName().equals("rightFox")) {
				if (model.getBoatPosition().equals("east")) {
					if (model.getFoxPosition().equals("middle")) {
						model.changePosition("fox", "right");
						model.setBoatCapacity(model.getBoatCapacity() - 1);
					}
				}
				if (model.getBoatPosition().equals("west")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getFoxPosition().equals("left")) {
							model.changePosition("fox", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);

						}
					}
				}
			}
			if (((JButton) e.getSource()).getName().equals("leftGoose")) {
				if (model.getBoatPosition().equals("west")) {
					if (model.getGoosePosition().equals("middle")) {
						model.changePosition("goose", "left");
						model.setBoatCapacity(model.getBoatCapacity() - 1);

					}
				}
				if (model.getBoatPosition().equals("east")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getGoosePosition().equals("right")) {
							model.changePosition("goose", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);

						}
					}
				}
			}
			if (((JButton) e.getSource()).getName().equals("rightGoose")) {
				if (model.getBoatPosition().equals("east")) {
					if (model.getGoosePosition().equals("middle")) {
						model.changePosition("goose", "right");
						model.setBoatCapacity(model.getBoatCapacity() - 1);
					}
				}
				if (model.getBoatPosition().equals("west")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getGoosePosition().equals("left")) {
							model.changePosition("goose", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);

						}
					}
				}
			}
			if (((JButton) e.getSource()).getName().equals("leftBeans")) {
				if (model.getBoatPosition().equals("west")) {
					if (model.getBeansPosition().equals("middle")) {
						model.changePosition("beans", "left");
						model.setBoatCapacity(model.getBoatCapacity() - 1);
					}
				}
				if (model.getBoatPosition().equals("east")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getBeansPosition().equals("right")) {
							model.changePosition("beans", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);
						}
					}
				}
			}
			if (((JButton) e.getSource()).getName().equals("rightBeans")) {
				if (model.getBoatPosition().equals("east")) {
					if (model.getBeansPosition().equals("middle")) {
						model.changePosition("beans", "right");
						model.setBoatCapacity(model.getBoatCapacity() - 1);
					}
				}
				if (model.getBoatPosition().equals("west")) {
					if (model.getBoatCapacity() < model.MAX_BOAT_CAPACITY) {
						if (model.getBeansPosition().equals("left")) {
							model.changePosition("beans", "middle");
							model.setBoatCapacity(model.getBoatCapacity() + 1);
						}
					}
				}
			}
			if (((JButton) e.getSource()).getName().equals("leftBoat")) {
				if (model.getFarmerPosition().equals("middle")) {
					if (model.getBoatPosition().equals("east")) {
						model.changePosition("boat", "west");

					}
				}

			}
			if (((JButton) e.getSource()).getName().equals("rightBoat")) {
				if (model.getFarmerPosition().equals("middle")) {
					if (model.getBoatPosition().equals("west")) { 
						model.changePosition("boat", "east");

					}
				}

			}
		}

	}
}
