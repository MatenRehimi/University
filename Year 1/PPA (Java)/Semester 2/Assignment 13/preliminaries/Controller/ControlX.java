package preliminaries.Controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import preliminaries.Model.Aeroplane;

public class ControlX implements ChangeListener {

	private Aeroplane aeroplane;
	private JSlider jsThrottle;

	public ControlX(Aeroplane aeroplane, JSlider jsThrottle) {
		this.aeroplane = aeroplane;
		this.jsThrottle = jsThrottle;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		aeroplane.setSpeed(jsThrottle.getValue());
	}
}
