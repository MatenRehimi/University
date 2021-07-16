package preliminaries.Controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import preliminaries.Model.Aeroplane;

public class ControlSpeed implements ChangeListener {

	private Aeroplane aeroplane;
	private JSlider jsCentreStick;

	public ControlSpeed(Aeroplane aeroplane, JSlider jsCentreStick) {
		this.aeroplane = aeroplane;
		this.jsCentreStick = jsCentreStick;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		aeroplane.setX(jsCentreStick.getValue());
	}

}
