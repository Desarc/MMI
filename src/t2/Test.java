package t2;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Test extends JPanel {

    public Test()
    {   GridBagConstraints c;
    	setLayout(new GridBagLayout());
    	c = new GridBagConstraints();

    	c.gridx = 0;
    	c.gridy = 0;
    	add(new JButton("Button 1"), c);

    	c.gridx = 1;
    	c.gridy = 0;
    	add(new JButton("Button 2"), c);

    	c.gridx = 2;
    	c.gridy = 0;
    	add(new JButton("Button 3"), c);
  
    	// Legg til en tom "filler".
    	Dimension d = new Dimension(10,30);
    	Box.Filler filler = new Box.Filler(d,d,d);
       	c.gridx = 0;
    	c.gridy = 1;
        add(filler,c);

    	c.gridwidth = 3;
    	c.gridx = 0;
    	c.gridy = 2;
    	c.fill = GridBagConstraints.BOTH;
    	add(new JButton("Long-Named Button 4"), c);

    	c.gridx = 1;       //aligned with button 2
    	c.gridwidth = 2;   //2 columns wide
    	c.gridy = 3;       //third row
    	c.fill = GridBagConstraints.BOTH;
    	add(new JButton("5"), c);
    }
    
	public static void main(String[] args) {
        JFrame frame = new JFrame("GridBag example");
        frame.getContentPane().add(new Test());
        frame.pack();
        frame.setVisible(true);  
	}

}
