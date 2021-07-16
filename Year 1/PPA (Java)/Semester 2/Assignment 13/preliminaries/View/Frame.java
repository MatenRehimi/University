package preliminaries.View;

import java.awt.BorderLayout;   
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import preliminaries.Model.Aeroplane;

public class Frame implements Observer {
	private JSlider jsCentreStick;
	private JSlider jsThrottle;
	private JTextArea jtArea;
	private Aeroplane plane;

	public Frame() {

		JFrame myFrame = new JFrame("Plane Control");
		myFrame.setSize(650, 600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creating widgets
		jsCentreStick = new JSlider(JSlider.HORIZONTAL, 0, 10, 5); // min max,
																	// current,value
		jsThrottle = new JSlider(JSlider.VERTICAL, 0, 10, 0);
		JButton jbReset = new JButton("Reset");
		jtArea = new JTextArea();

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
		myFrame.setVisible(true);

		// loop to check if plane is on the runway
	}

	public JTextArea getText() {
		return jtArea;
	}

	public JSlider getjsCentreStick() {
		return jsCentreStick;
	}

	public JSlider getjsThrottle() {
		return jsThrottle;
	}

	public void controlCentreStick(ChangeListener changeListener) {
		jsCentreStick.addChangeListener(changeListener);
	}

	public void controlThrottle(ChangeListener changeListener) {
		jsThrottle.addChangeListener(changeListener);
	}
	public void setPlane (Aeroplane plane) {
		this.plane = plane;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (plane.onRunway()) {
				// print message of plane details
				jtArea.append("Seconds: " + plane.getCurrentTime() + " \n"
						+ "X: " + plane.getX() + " Y: " + plane.getY()
						+ " Speed: " + plane.getSpeed() + " Elevation: "
						+ plane.updateElevation() + "\n");
		
		
				// checks if plane's elevation is greater than 4 and x value is
				// 5
				if (plane.getElevation() > 4 && plane.getX() == 5) {
					jtArea.append("Plane has taken off! \n");
				}
				if (plane.onRunway() == false) {
					jtArea.append("Take off failed!");
				}
		}
	}
}
