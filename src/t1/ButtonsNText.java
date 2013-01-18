package t1;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonsNText extends JPanel {
	
	private JTextField TextLine;
	private ButtonGroup toggleGroup;
	private int textCase;
	private boolean continuous;
	
	private static final int Upper = 1;
	private static final int Lower = 0;
	
 
	
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("T1");
		mainFrame.getContentPane().add(new ButtonsNText());
		mainFrame.pack();
		mainFrame.setVisible(true);	
	}
	
	/**
	 * Create the panel.
	 */
	public ButtonsNText() {		
		textCase = -1;
		continuous = false;
		TextLine = new JTextField();
		TextLine.setName("TextLine");
		add(TextLine);
		TextLine.setColumns(10);
		TextLine.addKeyListener(new TextFieldAction());
		
		toggleGroup = new ButtonGroup();
		JToggleButton UpperCaseButton = new JToggleButton("Upper case");
		UpperCaseButton.setName("UpperCaseButton");
		add(UpperCaseButton);
		toggleGroup.add(UpperCaseButton);
		UpperCaseButton.addActionListener(new UpperCaseAction());
		
		JToggleButton LowerCaseButton = new JToggleButton("Lower case");
		LowerCaseButton.setName("LowerCaseButton");
		add(LowerCaseButton);
		toggleGroup.add(LowerCaseButton);
		LowerCaseButton.addActionListener(new LowerCaseAction());
		
		JCheckBox ContinuousButton = new JCheckBox("Continuous?");
		ContinuousButton.setName("ContinuousButton");
		add(ContinuousButton);
		ContinuousButton.addItemListener(new CheckBoxAction());

	}
	
	class TextFieldAction implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (textCase == Upper) {
					TextLine.setText(TextLine.getText().toUpperCase());
				}
				else if (textCase == Lower) {
					TextLine.setText(TextLine.getText().toLowerCase());
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (continuous && Character.isLetter(e.getKeyChar())) {
				if (textCase == Upper) {
					int p = TextLine.getCaretPosition();
					TextLine.setText(TextLine.getText().toUpperCase());
					TextLine.setCaretPosition(p);
				}
				else if (textCase == Lower) {
					int p = TextLine.getCaretPosition();
					TextLine.setText(TextLine.getText().toLowerCase());
					TextLine.setCaretPosition(p);
				}
			}
		}

	
		
	}
	
	class LowerCaseAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			TextLine.setText(TextLine.getText().toLowerCase());
			textCase = Lower;
		}
	}
	
	class UpperCaseAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			TextLine.setText(TextLine.getText().toUpperCase());
			textCase = Upper;
		}
	}
	
	private class CheckBoxAction implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			if (((JCheckBox)e.getSource()).isSelected()) {
				continuous = true;
			}
			else {
				continuous = false;
			}
			
		}
	}
}
