package assignment12;

import assignment5.Aeroplane;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

public class Main {

	public static void main(String[] args) {
		// Creates the frame, sets the size and closes the application when you
		// press the x
		JFrame myFrame = new JFrame("Plane Control");
		myFrame.setSize(650, 600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creating widgets
		JSlider jsCentreStick = new JSlider(JSlider.HORIZONTAL, 0, 10, 5); // min
																			// max
																			// current
																			// value
																			// parameters
		JSlider jsThrottle = new JSlider(JSlider.VERTICAL, 0, 10, 0);
		JButton jbReset = new JButton("Reset");
		JTextArea jtArea = new JTextArea();

		// Automatically scrolls down
		DefaultCaret caret = (DefaultCaret) jtArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scroll = new JScrollPane(jtArea);
		myFrame.setLayout(new GridLayout(2, 1));
		myFrame.add(scroll);

		// Creates panel, sets layout and adds widgets to south panel
		JPanel jpSouth = new JPanel();
		jpSouth.setLayout(new BorderLayout());
		jpSouth.add(jsCentreStick, BorderLayout.NORTH);
		jpSouth.add(jsThrottle, BorderLayout.CENTER);
		jpSouth.add(jbReset, BorderLayout.SOUTH);

		// adds panel to frame
		myFrame.add(jpSouth);

		// Creates an aeroplane and the initial x and y value is from the
		// throttle and centrestick
		Aeroplane plane = new Aeroplane(0, jsCentreStick.getValue(),
				jsThrottle.getValue(), 0, 0);

		// Creates an inner class
		jsCentreStick.addChangeListener(new ChangeListener() {
			/*
			 * updates planes x value with the centresticks value on the GUI
			 */
			public void stateChanged(ChangeEvent e) {
				plane.setX(jsCentreStick.getValue());
			}
		});
		// Creates an inner class
		jsThrottle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				/*
				 * updates planes speed value with the throttle value on the GUI
				 */
				plane.setSpeed(jsThrottle.getValue());
			}
		});
		// allows the user to see the GUI
		myFrame.setVisible(true);

		while (plane.onRunway()) {
			try {
				// print message of plane details
				jtArea.append("Seconds: " + plane.getCurrentTime() + " \n"
						+ "X: " + plane.getX() + " Y: " + plane.getY()
						+ " Speed: " + plane.getSpeed() + " Elevation: "
						+ plane.updateElevation() + "\n");
				// 1 second pause and adds 1 second
				Thread.sleep(1000);
				plane.setCurrentTime(plane.getCurrentTime() + 1);
				// checks if plane's elevation is greater than 4 and x value is
				// 5
				if (plane.getElevation() > 4 && plane.getX() == 5) {
					jtArea.append("Plane has taken off! \n");
					break;
				}
			} catch (InterruptedException e) {
			}
		}
		// checks if the plane is not on the runway
		if (!plane.onRunway()) {
			jtArea.append("Take off failed!");
		}

	}

}
