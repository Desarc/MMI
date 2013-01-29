package t2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import t3.PassivePersonPanel;
import t4.PersonPanelListener;

import static java.awt.GridBagConstraints.*;

@SuppressWarnings("serial")
public class PersonPanel extends JPanel implements PropertyChangeListener {

	protected Person model = null;
	
	protected JLabel name;
	protected JLabel dateOfBirth;
	protected JLabel email;
	protected JLabel gender;
	protected JLabel height;
	protected JTextField nameField;
	protected JTextField dateOfBirthField;
	protected JTextField emailField;
	protected JComboBox<Gender> genderField;
	protected JSlider heightField;
	protected JButton newButton;
	protected JButton deleteButton;
	
	private Vector<PersonPanelListener> personPanelListeners;
	
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("T2 + T3");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		PersonPanel editable = new PersonPanel();
		editable.setModel(new Person());
		container.add(editable);
		PassivePersonPanel passive = new PassivePersonPanel();
		passive.setModel(editable.getModel());
		container.add(passive);
		mainFrame.setContentPane(container);
        mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
	public PersonPanel() {
		name = new JLabel("Name:");
		dateOfBirth = new JLabel("Birthday:");
		email = new JLabel("Email:");
		gender = new JLabel("Gender:");
		height = new JLabel("Height:");
		newButton = new JButton("New");
		deleteButton = new JButton("Delete");
		
		nameField = new JTextField();
		dateOfBirthField = new JTextField();
		emailField = new JTextField();
		genderField = new JComboBox<Gender>(Gender.values());
		heightField = new JSlider(120, 220, 170);
		
		nameField.setName("NamePropertyComponent");
		dateOfBirthField.setName("DateOfBirthPropertyComponent");
		emailField.setName("EmailPropertyComponent");
		genderField.setName("GenderPropertyComponent");
		heightField.setName("HeightPropertyComponent");
		newButton.setName("NewPersonButton");
		deleteButton.setName("DeletePersonButton");
		
		nameField.setColumns(25);
		emailField.setColumns(25);
		dateOfBirthField.setColumns(25);
		heightField.setMajorTickSpacing(10);
		heightField.setPaintLabels(true);
		heightField.setPaintTicks(true);
		heightField.setPaintTrack(true);
		
		nameField.addKeyListener(new ValueChangeListener());
		dateOfBirthField.addKeyListener(new ValueChangeListener());
		emailField.addKeyListener(new ValueChangeListener());
		genderField.addItemListener(new ValueChangeListener());
		heightField.addChangeListener(new ValueChangeListener());
		newButton.addActionListener(new NewButtonListener());
		deleteButton.addActionListener(new DeleteButtonListener());
		
		GridBagConstraints c;
    	setLayout(new GridBagLayout());
    	c = new GridBagConstraints();
    	
    	c.gridx = 0;
    	c.gridy = 0;
    	add(name, c);

    	c.gridx = 0;
    	c.gridy = 1;
    	add(email, c);

    	c.gridx = 0;
    	c.gridy = 2;
    	add(dateOfBirth, c);
    	
    	c.gridx = 0;
    	c.gridy = 3;
    	add(gender, c);
    	
    	c.gridx = 0;
    	c.gridy = 4;
    	add(height, c);

    	c.anchor = LINE_START;
    	c.gridx = 1;
    	c.gridy = 3;
    	add(genderField, c);

    	c.gridx = 1;
    	c.gridy = 0;
    	add(nameField, c);

    	c.gridx = 1;
    	c.gridy = 1;
    	add(emailField, c);

    	c.gridx = 1;
    	c.gridy = 2;
    	add(dateOfBirthField, c);

    	c.gridx = 1;
    	c.gridy = 4;
    	c.fill = HORIZONTAL;
    	add(heightField, c);
    	
    	c.gridx = 0;
    	c.gridy = 5;
    	add(newButton, c);
    	
    	c.gridx = 1;
    	c.gridy = 5;
    	add(deleteButton, c);
    	
    	personPanelListeners = new Vector<PersonPanelListener>();
    	setModel(null);
	}
	
	public Person getModel() {
		return model;
	}
	
	public void setModel(Person model) {
		if (this.model != null) {
			this.model.removePropertyChangeListener(this);
		}
		else if (model != null) {
			enableFields();
		}
		this.model = model;
		updateFields();
		if (this.model != null) {
			this.model.addPropertyChangeListener(this);
		}
		else {
			disableFields();
		}
	}
	
	protected void clearFields() {
		nameField.setText(null);
		emailField.setText(null);
		dateOfBirthField.setText(null);
		genderField.setSelectedItem(null);
		heightField.setValue(heightField.getMinimum());
	}
	
	protected void updateFields() {
		if (model != null) {
			nameField.setText(model.getName());
			emailField.setText(model.getEmail());
			dateOfBirthField.setText(model.getDateOfBirth());
			genderField.setSelectedItem(model.getGender());
			heightField.setValue(model.getHeight());
		}
		else {
			clearFields();
		}
	}
	
	public void addPersonPanelListener(PersonPanelListener listener) {
		personPanelListeners.add(listener);
	}
	
	public void removePersonPanelListener(PersonPanelListener listener) {
		personPanelListeners.remove(listener);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == Person.NAME_PROPERTY) {
			updateKeepCaretPosition(nameField, model.getName());
			notifyListChanged();
		}
		else if (evt.getPropertyName() == Person.EMAIL_PROPERTY) {
			updateKeepCaretPosition(emailField, model.getEmail());
			notifyListChanged();
		}
		else if (evt.getPropertyName() == Person.BIRTHDAY_PROPERTY) {
			updateKeepCaretPosition(dateOfBirthField, model.getDateOfBirth());
		}
		else if (evt.getPropertyName() == Person.GENDER_PROPERTY) {
			genderField.setSelectedItem(model.getGender());
			notifyListChanged();
		}
		else if (evt.getPropertyName() == Person.HEIGHT_PROPERTY) {
			heightField.setValue(model.getHeight());
		}
	}

	private void notifyNewPerson() {
		Iterator<PersonPanelListener> listenerIterator = personPanelListeners.iterator();
		while (listenerIterator.hasNext()) {
			listenerIterator.next().newPersonAction();
		}
	}
	
	private void notifyDeletePerson() {
		Iterator<PersonPanelListener> listenerIterator = personPanelListeners.iterator();
		while (listenerIterator.hasNext()) {
			listenerIterator.next().deletePersonAction(model);
		}
	}
	
	private void notifyListChanged() {
		Iterator<PersonPanelListener> listenerIterator = personPanelListeners.iterator();
		while (listenerIterator.hasNext()) {
			listenerIterator.next().listNameChanged();
		}
	}
	
	private void disableFields() {
		nameField.setEditable(false);
		emailField.setEditable(false);
		dateOfBirthField.setEditable(false);
		genderField.setEnabled(false);
		heightField.setEnabled(false);
		updateUI();
	}
	
	private void enableFields() {
		nameField.setEditable(true);
		emailField.setEditable(true);
		dateOfBirthField.setEditable(true);
		genderField.setEnabled(true);
		heightField.setEnabled(true);
		updateUI();
	}
	
	private void updateKeepCaretPosition(JTextField field, String value) {
		int p = field.getCaretPosition();
		field.setText(value);
		field.setCaretPosition(p);
	}
	
	class ValueChangeListener implements KeyListener, ItemListener, ChangeListener {

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
			if (model == null) {
				return;
			}
			if (e.getSource().equals(nameField)) {
				model.setName(nameField.getText());
			}
			if (e.getSource().equals(emailField)) {
				model.setEmail(emailField.getText());
			}
			if (e.getSource().equals(dateOfBirthField)) {
				model.setDateOfBirth(dateOfBirthField.getText());
			}
		}
		
		public void itemStateChanged(ItemEvent e) {
			if (model == null) {
				return;
			}
			model.setGender((Gender)genderField.getSelectedItem());
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if (model == null) {
				return;
			}
			model.setHeight(heightField.getValue());
		}
	}
	
	
	class NewButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			notifyNewPerson();
		}
	}
	
	class DeleteButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			notifyDeletePerson();
		}
	}
	
}