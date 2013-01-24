package t3;

import static java.awt.GridBagConstraints.HORIZONTAL;

import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;

import t2.Person;
import t2.PersonPanel;

@SuppressWarnings("serial")
public class PassivePersonPanel extends PersonPanel {

	JTextField textGenderField;
	JTextField textHeightField;
	
	public PassivePersonPanel() {
		
		textGenderField = new JTextField();
		textHeightField = new JTextField();
		
		remove(testButton);
		remove(genderField);
		remove(heightField);
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1;
    	c.gridy = 3;
    	c.fill = HORIZONTAL;
    	add(textGenderField, c);

    	c.gridx = 1;
    	c.gridy = 4;
    	c.fill = HORIZONTAL;
    	add(textHeightField, c);
		
		nameField.setEditable(false);
		emailField.setEditable(false);
		dateOfBirthField.setEditable(false);
		textGenderField.setEditable(false);
		textHeightField.setEditable(false);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == Person.NAME_PROPERTY) {
			nameField.setText(model.getName());
		}
		else if (evt.getPropertyName() == Person.EMAIL_PROPERTY) {
			emailField.setText(model.getEmail());
		}
		else if (evt.getPropertyName() == Person.BIRTHDAY_PROPERTY) {
			dateOfBirthField.setText(model.getDateOfBirth());
		}
		else if (evt.getPropertyName() == Person.GENDER_PROPERTY) {
			textGenderField.setText(model.getGender().toString());
		}
		else if (evt.getPropertyName() == Person.HEIGHT_PROPERTY) {
			textHeightField.setText(String.valueOf(model.getHeight()));
		}
		
	}
}
