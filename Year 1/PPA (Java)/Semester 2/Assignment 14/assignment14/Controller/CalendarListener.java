package assignment14.Controller;

import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import assignment14.Model.Details;
import assignment14.Model.Writer;
import assignment14.View.Frame;

public class CalendarListener implements KeyListener,MouseListener {
	private Frame frame;
	private Writer writer;
	
	/*
	 * Constructor loads lines from text file to calendar dlm
	 */
	public CalendarListener(Frame frame, Writer writer) throws IOException  {

		this.frame = frame;
		frame.addCalendarListener(this);
		this.writer = writer;
		String[] lines = writer.readLine();
		for (int i = 0; i<lines.length; i++) {
			if (lines[i] != null) {
				frame.addTodlmCalendar(lines[i]);
			}
		}

	}
	/*
	 * lines of text are added to calendar dlm and text file
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {

			String input = frame.getCalendarText();
			Details details = new Details(input);
			writer.writeLine(details.tostring());
			frame.addTodlmCalendar(details.tostring());
			frame.setCalendarText(null);
			
			
		}
	}

	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	
	public void mouseClicked(MouseEvent e) {}

	/*
	 * lines of text can be double clicked and removed from text file and calendar dlm
	 */
	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 2) {
			String line = frame.getCalendarListValue();
			frame.removeFromdlmCalendar(line);
			try {
				writer.RemoveLine(line);
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	
}
