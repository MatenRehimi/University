package assignment14.Controller;
 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assignment14.Model.Details;
import assignment14.Model.Writer;
import assignment14.View.Frame;

public class ReminderListener implements KeyListener, MouseListener {
	
	private Frame frame;
	private static Pattern pRemindMeTo = Pattern.compile(
			"[Rr]emind\\sme\\sto\\s");
	private Writer writer;
	
	/*
	 * Constructor loads lines from text file to reminder dlm
	 */
	public ReminderListener(Frame frame, Writer writer) throws IOException {
		this.frame = frame;
		frame.addReminderListener(this);
		this.writer = writer;
		String[] lines = writer.readLine();
		for (int i = 0; i<lines.length; i++) {
			if (lines[i] != null) {
				frame.addTodlmReminder(lines[i]);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	/*
	 * lines of text are added to reminder dlm and text file
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			String input = frame.getReminderText();
			Matcher remindMeTo = pRemindMeTo.matcher(input);	
			
			if (remindMeTo.find()) {
				input = input.replaceAll("[Rr]emind\\sme\\sto\\s", "");
				Details details = new Details(input);
				String info = details.remainderString();
				writer.writeLine(info);
				frame.addTodlmReminder(info);
				
				frame.setReminderText(null);
			}	
			
		}	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	/*
	 * lines of text can be double clicked and removed from text file and reminder dlm
	 */
	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 2) {
			String line = frame.getReminderListValue();
			frame.removeFromdlmReminder(line);
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
