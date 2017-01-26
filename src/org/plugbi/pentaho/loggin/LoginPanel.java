package org.plugbi.pentaho.loggin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
/**
 * A customize JPanel to create some components to the UI in a more readable way in the LoginUI class.
 * 
 * @author Adrien Blanes
 *
 *@see LoginUI
 */
public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The panel of the title.
	 */
	private JPanel titlePanel; 
	
	/**
	 * container of the columns
	 */
	private JPanel columnContainer;
	
	/**
	 * the first column panel, containing labels.
	 */
	private JPanel firstColumn;
	
	/**
	 * the second column panel, containing fields.
	 */
	private JPanel secondColumn;
	
	/**
	 * We use to different panel for the both rows of the second column, as this we can add field in the future on a row.
	 * 
	 * this is the first row panel.
	 * 
	 * @see addRow(JPanel jp, String name, Dimension dimension)
	 */
	private JPanel firstRow;
	
	/**
	 * We use to different panel for the both rows of the second column, as this we can add field in the future on a row.
	 * 
	 * this is the second row panel.
	 * 
	 * @see addRow(JPanel jp, String name, Dimension dimension)
	 */
	private JPanel secondRow;
	
	/**
	 * The first textField.
	 */
	private JTextField textField1;
	
	/**
	 * The second textField.
	 */
	private JTextField textField2;
	
	/**
	 * The constructor of this class will create a title, then two panel as columns, 
	 * the first containing labels, the second containing fields.
	 *  
	 * @param name
	 * 			name of the panel
	 * @param boxName1
	 * 			name of the first field
	 * @param boxName2
	 * 			name of the second field
	 * @param jtf
	 * 			type of the second field
	 */
	public LoginPanel(String name, String boxName1, String boxName2, JTextField jtf){
		super();
		
		JLabel label = new JLabel(name);
		titlePanel = new JPanel();
		titlePanel.add(label);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		colGeneration(boxName1, boxName2, jtf);		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(titlePanel);
		this.add(new JSeparator());
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(columnContainer);
		
	}
	
	/**
	 * This method create columns in UI. We need two column here, one for labels, an an other for fields.
	 * We set the first field as a normal textfield, and let the method sending the kind of the second.
	 * @param boxName1
	 * 			The name of the first field
	 * @param boxName2
	 * 			the name of the second field
	 * @param jtf
	 * 			the type of the second field.
	 */
	public void colGeneration(String boxName1, String boxName2, JTextField jtf){
		
		JLabel name1 = new JLabel(boxName1);
		JLabel name2 = new JLabel(boxName2);
		textField1 = new JTextField("");
		textField2 = jtf;
		
		firstRow = new JPanel();
		firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.LINE_AXIS));
		firstRow.add(textField1);
		secondRow = new JPanel();
		secondRow.setLayout(new BoxLayout(secondRow, BoxLayout.LINE_AXIS));
		secondRow.add(textField2);
		
		firstColumn = new JPanel();
		firstColumn.setLayout(new BoxLayout(firstColumn, BoxLayout.PAGE_AXIS));
		firstColumn.add(name1);
		firstColumn.add(Box.createRigidArea(new Dimension(0,20)));
		firstColumn.add(name2);
		
		secondColumn = new JPanel(new BoxLayout(secondColumn, BoxLayout.PAGE_AXIS));
		secondColumn.setLayout(new BoxLayout(secondColumn, BoxLayout.PAGE_AXIS));
		secondColumn.add(firstRow);
		secondColumn.add(Box.createRigidArea(new Dimension(0,20)));
		secondColumn.add(secondRow);
		
		columnContainer = new JPanel();
		columnContainer.setLayout(new BoxLayout(columnContainer, BoxLayout.LINE_AXIS));
		columnContainer.add(firstColumn, BorderLayout.WEST);
		columnContainer.add(Box.createRigidArea(new Dimension(20,0)));
		columnContainer.add(secondColumn, BorderLayout.CENTER);
		columnContainer.add(Box.createRigidArea(new Dimension(20,0)));
				
	}

	// you can add an other text field on a row.
	/**
	 * Add a JTextField to a row.
	 * @param jp
	 * 			the panel where you wnat to add the field
	 * @param name
	 * 			The label of the field.
	 * @param dimension
	 * 			The dimension of the field.
	 * @return a JTextField object.
	 */
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
/**
 * 
 * @return first text field.
 */
	public String getTextField1() {
		return textField1.getText();
	}

	/**
	 * 
	 * @return second text field.
	 */
	public String getTextField2() {
		return textField2.getText();
	}
	
	/**
	 * 
	 * @return the first row panel.
	 */
	public JPanel getFirstRow() {
		return firstRow;
	}
	/**
	 * 
	 * Set the first row panel.
	 */
	public void setFirstRow(JPanel firstRow) {
		this.firstRow = firstRow;
	}
	/**
	 * 
	 * @return the second row panel.
	 */
	public JPanel getSecondRow() {
		return secondRow;
	}
	/**
	 * 
	 * Set the second row panel.
	 */
	public void setSecondRow(JPanel secondRow) {
		this.secondRow = secondRow;
	}

}
