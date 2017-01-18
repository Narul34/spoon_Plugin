package org.test.pentaho.loggin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel titlePanel, columnContainer, firstColumn, secondColumn, rowForCol1, rowForCol2;
	private JTextField textField1, textField2;
	
	public LoginPanel(String name, String boxName1, String boxName2, JTextField jtf){
		super();
		
		titlePanelInit(name);
		colGeneration(boxName1, boxName2, jtf);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(titlePanel);
		this.add(new JSeparator());
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(columnContainer);
		
	}

	public void titlePanelInit(String name) {
		
		JLabel label = new JLabel(name);
		titlePanel = new JPanel();
		titlePanel.add(label);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

	}
	
	public void colGeneration(String boxName1, String boxName2, JTextField jtf){
		
		JLabel name1 = new JLabel(boxName1);
		JLabel name2 = new JLabel(boxName2);
		textField1 = new JTextField("");
		textField2 = jtf;
		
		rowForCol1 = new JPanel();
		rowForCol1.setLayout(new BoxLayout(rowForCol1, BoxLayout.LINE_AXIS));
		rowForCol1.add(textField1);
		rowForCol2 = new JPanel();
		rowForCol2.setLayout(new BoxLayout(rowForCol2, BoxLayout.LINE_AXIS));
		rowForCol2.add(textField2);
		
		firstColumn = new JPanel();
		firstColumn.setLayout(new BoxLayout(firstColumn, BoxLayout.PAGE_AXIS));
		firstColumn.add(name1);
		firstColumn.add(Box.createRigidArea(new Dimension(0,20)));
		firstColumn.add(name2);
		
		secondColumn = new JPanel(new BoxLayout(secondColumn, BoxLayout.PAGE_AXIS));
		secondColumn.setLayout(new BoxLayout(secondColumn, BoxLayout.PAGE_AXIS));
		secondColumn.add(rowForCol1);
		secondColumn.add(Box.createRigidArea(new Dimension(0,20)));
		secondColumn.add(rowForCol2);
		
		columnContainer = new JPanel();
		columnContainer.setLayout(new BoxLayout(columnContainer, BoxLayout.LINE_AXIS));
		columnContainer.add(firstColumn, BorderLayout.WEST);
		columnContainer.add(Box.createRigidArea(new Dimension(20,0)));
		columnContainer.add(secondColumn, BorderLayout.CENTER);
		columnContainer.add(Box.createRigidArea(new Dimension(20,0)));
				
	}

	public JTextField addRow(JPanel jp, String name, Dimension dimension) {
		JLabel rowLabel = new JLabel(name);
		JTextField rowTextField = new JTextField("");
		rowTextField.setPreferredSize(dimension);
		rowTextField.setMaximumSize(dimension);
		
		jp.add(Box.createRigidArea(new Dimension(20,0)));
		jp.add(rowLabel);
		jp.add(Box.createRigidArea(new Dimension(10,0)));
		jp.add(rowTextField);
		return rowTextField;
	}

	public String getTextField1() {
		return textField1.getText();
	}

	public String getTextField2() {
		return textField2.getText();
	}
	
	public JPanel getRowForCol1() {
		return rowForCol1;
	}

	public void setRowForCol1(JPanel rowForCol1) {
		this.rowForCol1 = rowForCol1;
	}

	public JPanel getRowForCol2() {
		return rowForCol2;
	}

	public void setRowForCol2(JPanel rowForCol2) {
		this.rowForCol2 = rowForCol2;
	}

}
