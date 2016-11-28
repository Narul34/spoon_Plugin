package org.test.pentaho.loggin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LoginUI {

	private JFrame contentPane;
	private JPanel mainContainer;
	private JPanel topContainer, connectionParameterContainer,connectionProfilContainer,buttonContainer,bottomContainer;
	private JLabel topLabel;
	private JButton botButton;
	private int screenWidth;
	private int screenHeight;
	private int xPosition;
	private int yPosition;
	private int xLogSize = 900;
	private int yLogSize = 450;
	private Dimension dimension;

	public LoginUI() {
		
		dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = (int)dimension.getHeight();
		screenWidth  = (int)dimension.getWidth();
		xPosition = screenWidth/2 - xLogSize/2;
		yPosition = screenHeight/2 - yLogSize/2;
	}

	public void createUI() {
		this.contentPane = new JFrame();
		this.contentPane.setTitle("Pentaho testFrame");
		this.contentPane.setBounds(xPosition, yPosition, xLogSize, yLogSize);
		this.contentPane.setVisible(true);
		this.contentPane.setResizable(false);
		this.contentPane.setAlwaysOnTop(true);
		
		topAndBotConfiguration();
		containerInitializer();
		
		this.contentPane.setContentPane(mainContainer);
	}
	
	public void topAndBotConfiguration(){
		topLabel = new JLabel("Connnexion au portail web");
		topLabel.setHorizontalAlignment(SwingUtilities.CENTER);
		botButton = new JButton("Annuler");
		botButton.setHorizontalAlignment(SwingUtilities.RIGHT);
	}
	
	public void containerInitializer(){
		mainContainer = new JPanel();
		mainContainer.setSize(contentPane.getWidth(), contentPane.getHeight());
		mainContainer.setOpaque(true);
		mainContainer.setBackground(new Color(229,114,114));
		mainContainer.setVisible(true);
		mainContainer.setLayout(new GridLayout(5,1));
		
		topContainer = new JPanel();
		topContainer.setBackground(Color.GREEN);
		topContainer.add(topLabel);
		
		connectionParameterContainer = new JPanel();
		connectionParameterContainer.setLayout(new BoxLayout(connectionParameterContainer, BoxLayout.LINE_AXIS));
		connectionParameterContainer.add(new JLabel("pouet"));
		
		connectionProfilContainer= new JPanel();
		buttonContainer = new JPanel();
		bottomContainer = new JPanel();
		
		connectionProfilContainer.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonContainer.setBorder(BorderFactory.createLineBorder(Color.black));
		bottomContainer.setBorder(BorderFactory.createLineBorder(Color.black));
		bottomContainer.add(botButton);
		
		mainContainer.add(topContainer);
		mainContainer.add(connectionParameterContainer);
		mainContainer.add(connectionProfilContainer);
		mainContainer.add(buttonContainer);
		mainContainer.add(bottomContainer);
	}
	
	public static void main(String[] args){
		LoginUI lui = new LoginUI();
		lui.createUI();
	}
	
}
