package t2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Person {
	
	public final static String NAME_PROPERTY = "name";
	public final static String BIRTHDAY_PROPERTY = "dateOfBirth";
	public final static String EMAIL_PROPERTY = "email";
	public final static String GENDER_PROPERTY = "gender";
	public final static String HEIGHT_PROPERTY = "height";

	private PropertyChangeSupport pcs;
	
	private String name;
	private String dateOfBirth;
	private Gender gender;
	private String email;
	private int height;
	
	public Person() {
		pcs = new PropertyChangeSupport(this);
	}
	
	public Person(String name) {
		this();
		this.name = name;
	}
	
	public Person(String name, String email, String dateOfBirth, Gender gender, int height) {
		this(name);
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.height = height;
	}
	
	public void setName(String newValue) {
		String oldValue = this.name;
		this.name = newValue;
		if (newValue != oldValue) {
			pcs.firePropertyChange(NAME_PROPERTY, oldValue, newValue);
		}
	}
	
	public void setDateOfBirth(String newValue) {
		String oldValue = this.dateOfBirth;
		this.dateOfBirth = newValue;
		if (newValue != oldValue) {
			pcs.firePropertyChange(BIRTHDAY_PROPERTY, oldValue, newValue);
		}
	}
	
	public void setGender(Gender newValue) {
		Gender oldValue = this.gender;
		this.gender = newValue;
		if (newValue != oldValue) {
			pcs.firePropertyChange(GENDER_PROPERTY, oldValue, newValue);
		}
	}
	
	public void setEmail(String newValue) {
		String oldValue = this.email;
		this.email = newValue;
		if (newValue != oldValue) {
			pcs.firePropertyChange(EMAIL_PROPERTY, oldValue, newValue);
		}
	}
	
	public void setHeight(int newValue) {
		int oldValue = this.height;
		this.height = newValue;
		if (newValue != oldValue) {
			pcs.firePropertyChange(HEIGHT_PROPERTY, oldValue, newValue);
		}
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	
	public String toString() {
		return name;
				
	}

}
