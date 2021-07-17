package preliminaries;

import preliminaries.Controller.ControlSpeed;
import preliminaries.Controller.ControlX;
import preliminaries.Model.Aeroplane;
import preliminaries.View.Frame;

public class Main {

	public static void main(String[] args) {

		Aeroplane plane = new Aeroplane(0, 5, 0, 0, 0);
		Frame frame = new Frame();
		ControlSpeed controlSpeed = new ControlSpeed(plane,frame.getjsCentreStick());
		ControlX controlX = new ControlX(plane, frame.getjsThrottle());
		frame.controlCentreStick(controlSpeed);
		frame.controlThrottle(controlX);
		frame.setPlane(plane);
		plane.addObserver(frame);
		plane.FlightSimulation(plane);
	}
}
