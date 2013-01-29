package t4;

import t2.Person;

public interface PersonPanelListener {
	public void newPersonAction();
	public void deletePersonAction(Person person);
	public void listNameChanged();
}
