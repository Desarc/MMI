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
		this.name = name;
		pcs = new PropertyChangeSupport(this);
	}
	
	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		pcs.firePropertyChange(NAME_PROPERTY, oldValue, name);
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		String oldValue = this.dateOfBirth;
		this.dateOfBirth = dateOfBirth;
		pcs.firePropertyChange(BIRTHDAY_PROPERTY, oldValue, dateOfBirth);
	}
	
	public void setGender(Gender gender) {
		Gender oldValue = this.gender;
		this.gender = gender;
		pcs.firePropertyChange(GENDER_PROPERTY, oldValue, gender);
	}
	
	public void setEmail(String email) {
		String oldValue = this.email;
		this.email = email;
		pcs.firePropertyChange(EMAIL_PROPERTY, oldValue, email);
	}
	
	public void setHeight(int height) {
		int oldValue = this.height;
		this.height = height;
		pcs.firePropertyChange(HEIGHT_PROPERTY, oldValue, height);
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
		return name+"\n"+email+"\n"+dateOfBirth+"\n"+gender+"\n"+height;
				
	}

}
