package org.test.pentaho.loggin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginUI {

	private JFrame contentPane;
	private JPanel mainContainer;
	private JPanel topContainer, bottomContainer;
	private JLabel topLabel;
	private JButton cancelButton, connectButton, homeButton;
	private int screenWidth;
	private int screenHeight;
	private int xPosition;
	private int yPosition;
	private int xLogSize = 580;
	private int yLogSize = 308;
	private Dimension screenDimension;
	private Dimension homeButtonDim = new Dimension(40, 32);
	private Dimension topBotPrefDim = new Dimension(xLogSize, 30);
	private Dimension topBotMaxDim = new Dimension(xLogSize, 40);
	private Dimension pLoginDim = new Dimension(xLogSize, 100);

	public LoginUI() {

		screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = (int) screenDimension.getHeight();
		screenWidth = (int) screenDimension.getWidth();
		xPosition = screenWidth / 2 - xLogSize / 2;
		yPosition = screenHeight / 2 - yLogSize / 2;
	}

	public void createUI() {
		contentPane = new JFrame();
		contentPane.setTitle("Pentaho testFrame");
		contentPane.setBounds(xPosition, yPosition, xLogSize, yLogSize);
		contentPane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane.setVisible(true);
		contentPane.setResizable(false);
		contentPane.setAlwaysOnTop(true);

		topInit();
		botInit();
		containerInitializer();

		contentPane.setContentPane(mainContainer);
	}

	public void containerInitializer() {
		mainContainer = new JPanel();
		mainContainer.setSize(contentPane.getWidth(), contentPane.getHeight());
		mainContainer.setOpaque(true);
		mainContainer.setVisible(true);
		mainContainer.setLayout(new BoxLayout(mainContainer,
				BoxLayout.PAGE_AXIS));

		PLogin pl1 = new PLogin("Paramètres de connexion",
				"Nom du portail web :", "Nom du serveur ou son adresse IP :");
		PLogin pl2 = new PLogin("Profil de connexion", "Nom d'utilisateur:",
				"Mot de passe:");
		pl1.addRow(pl1.getRowForCol1(), "Port HTTP", new Dimension(50, 25));

		pl1.setMaximumSize(pLoginDim);
		pl1.setPreferredSize(pLoginDim);
		pl2.setMaximumSize(pLoginDim);
		pl2.setPreferredSize(pLoginDim);

		mainContainer.add(topContainer);
		mainContainer.add(pl1);
		mainContainer.add(pl2);
		mainContainer.add(bottomContainer);
	}

	public void topInit() {

		topLabel = new JLabel("Connnexion au portail web");

		topContainer = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 2L;

			public void paintComponent(Graphics g){
			    Graphics2D g2d = (Graphics2D)g;         
			    GradientPaint gp = new GradientPaint(30, 150, Color.WHITE, 0, 0, new Color(101,232,95), false);                
			    g2d.setPaint(gp);
			    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());                
			  } 
		};
		topContainer.setPreferredSize(topBotPrefDim);
		topContainer.setMaximumSize(topBotMaxDim);
		topContainer.add(topLabel);
	

	}

	public void botInit() {

		// TODO add Listener
		homeButton = new JButton();
		ImageIcon homeBtn = new ImageIcon(LoginUI.class.getResource("/res/home.png"));
		homeButton.setIcon(homeBtn);
		homeButton.setPreferredSize(homeButtonDim);
		connectButton = new JButton("Connexion");
		cancelButton = new JButton("Annuler");
		

		bottomContainer = new JPanel(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g){
			    Graphics2D g2d = (Graphics2D)g;         
			    GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 30, 150, new Color(101,232,95), false);                
			    g2d.setPaint(gp);
			    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());                
			  } 
			
		};
		bottomContainer.setPreferredSize(topBotPrefDim);
		bottomContainer.setMaximumSize(topBotMaxDim);
		GridLayout gl = new GridLayout(1, 5);
		gl.setHgap(190);
		JPanel homePanel = new JPanel();
		homePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		homePanel.add(homeButton);
		homePanel.setOpaque(false);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(connectButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setOpaque(false);
		bottomContainer.setLayout(gl);
		bottomContainer.add(homePanel);
		bottomContainer.add(buttonPanel);

	}

	public static void main(String[] args) {
		LoginUI lui = new LoginUI();
		lui.createUI();
	}

}
