package t4;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import t2.Gender;
import t2.Person;
import t2.PersonPanel;

@SuppressWarnings("serial")
public class PersonListPanel extends JPanel implements PersonPanelListener {

	private JList<Person> personList;
	private PersonPanel personPanel;
	
	private DefaultListModel<Person> listModel;
	
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("T4");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PersonListPanel panel = new PersonListPanel();
		Person p1 = new Person("Arnulf", "arnulf@carinthia.com", "12.04.850", Gender.male, 176);
		Person p2 = new Person("Django", "django@swingjazz.com", "23.01.1910", Gender.male, 173);
		Person p3 = new Person("Cyrille", "cyrille@ukulele.net", "19.09.1988", Gender.female, 168);
		DefaultListModel<Person> testModel = new DefaultListModel<Person>();
		testModel.addElement(p1);
		testModel.addElement(p2);
		testModel.addElement(p3);
		panel.setModel(testModel);
		mainFrame.setContentPane(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
	public PersonListPanel() {
		personList = new JList<Person>();
		personPanel = new PersonPanel();
		
		personList.setName("PersonList");
		personPanel.setName("PersonPanel");
		
		listModel = new DefaultListModel<Person>();
		
		personList.setModel(listModel);
		
		personList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		personList.setLayoutOrientation(JList.VERTICAL);
		personList.setVisibleRowCount(-1);
		personList.addListSelectionListener(new ListAction());
		personList.setCellRenderer(new PersonRenderer());
		
		personPanel.addPersonPanelListener(this);
		
		
		JScrollPane listScroller = new JScrollPane(personList);
		listScroller.setPreferredSize(new Dimension(350, 150));
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		
		container.add(listScroller);
		container.add(personPanel);
		
		add(container);
		
	}
	
	public void setModel(DefaultListModel<Person> model) {
		this.listModel = model;
		personList.setModel(model);
	}

	public DefaultListModel<Person> getModel() {
		return this.listModel;
	}
	
	public void setPersonModel(Person model) {
		personPanel.setModel(model);
	}
	
	public void newPersonAction() {
		Person newPerson = new Person();
		addPerson(newPerson);
	}
	
	public void deletePersonAction(Person person) {
		removePerson(person);
	}
	
	public void listNameChanged() {
		personList.updateUI();
	}
	
	private void addPerson(Person person) {
		listModel.addElement(person);
		setPersonModel(person);
		personList.setSelectedIndex(listModel.indexOf(person));
		personList.updateUI();
	}
	
	private void removePerson(Person person) {
		if (listModel.getSize() == 1) {
			addPerson(new Person());
		}
		listModel.removeElement(person);
		personList.clearSelection();
		personList.updateUI();
	}
	
	
	class ListAction implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			int i = ((JList<Person>)e.getSource()).getSelectedIndex();
			if (i < 0) {
				setPersonModel(null);
			}
			else {
				setPersonModel((Person)(((DefaultListModel<Person>)personList.getModel()).getElementAt(i)));
			}
		}
		
	}
}
