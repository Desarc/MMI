package t4;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import t2.Gender;
import t2.Person;

@SuppressWarnings("serial")
public class PersonRenderer extends JLabel implements ListCellRenderer {

	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
	
	public PersonRenderer() {
    }
	
	public Component getListCellRendererComponent(JList list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		Person person = (Person)value;
		if (person == null) {
			return new JLabel();
		}
		String text = "";
		if (person.getName() == null || person.getName().equals("")) {
			text += "<no name>, ";
		}
		else {
			text += person.getName()+", ";
		}
		if (person.getEmail() == null || person.getEmail().equals("")) {
			text += "<no email>";
		}
		else {
			text += person.getEmail();
		}
		setText(text);
		if (person.getGender() == null) {
			setIcon(new ImageIcon("/home/desarc/projects/MMI/resources/none.jpg"));
		}
		else if (person.getGender().equals(Gender.male)) {
			setIcon(new ImageIcon("/home/desarc/projects/MMI/resources/mars.jpg"));
		}
		else if (person.getGender().equals(Gender.female)) {
			setIcon(new ImageIcon("/home/desarc/projects/MMI/resources/venus.png"));
		}
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		    } 
		else {
			setBackground(Color.white);
			setForeground(Color.black);
		    }
		return this;
	}
}
