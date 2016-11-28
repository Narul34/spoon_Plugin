package org.test.pentaho.loggin;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class PLogin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel titlePanel, firstRowPanel, secondRowpanel;
	private static final Dimension NORMAL_DIM = new Dimension(350,25);
	private static final Dimension SHORT_DIM = new Dimension(200,25);
	
	public PLogin(String name, String boxName1, String boxName2) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		titlePanelInit(name);
		rowGeneration(boxName1, boxName2);

		this.add(titlePanel);
		this.add(new JSeparator());
		this.add(firstRowPanel);
		this.add(secondRowpanel);
	}

	public void titlePanelInit(String name) {
		JLabel label = new JLabel(name);

		titlePanel = new JPanel();
		titlePanel.add(label);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

	}

	public void rowGeneration(String boxName1, String boxName2) {
		firstRowPanel = new JPanel();
		secondRowpanel = new JPanel();

		firstRowPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,0));
		secondRowpanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

		addBox(firstRowPanel, boxName1, SHORT_DIM);
		addBox(secondRowpanel, boxName2, NORMAL_DIM);
	}

	public void addBox(JPanel jp, String name, Dimension dimension) {
		JLabel label = new JLabel(name);
		JTextField textField = new JTextField(" ");
		textField.setPreferredSize(dimension);
		
		textField.setAlignmentX(CENTER_ALIGNMENT);
		
		jp.add(label);
		jp.add(textField);
	}

	public JPanel getFirstRowPanel() {
		return firstRowPanel;
	}

	public void setFirstRowPanel(JPanel firstRowPanel) {
		this.firstRowPanel = firstRowPanel;
	}

	public JPanel getSecondRowpanel() {
		return secondRowpanel;
	}

	public void setSecondRowpanel(JPanel secondRowpanel) {
		this.secondRowpanel = secondRowpanel;
	}

}
