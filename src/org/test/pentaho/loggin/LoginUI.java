package org.test.pentaho.loggin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.test.pentaho.Screen;
import org.test.pentaho.http.Connection;

public class LoginUI {

	private JFrame contentPane;
	private JPanel mainContainer;
	private JPanel topContainer, bottomContainer;
	private JLabel topLabel;
	private JButton cancelButton, connectButton, homeButton;
	private int xPosition;
	private int yPosition;
	private int xLogSize = 580;
	private int yLogSize = 308;
	private Dimension homeButtonDim = new Dimension(40, 32);
	private Dimension topBotPrefDim = new Dimension(xLogSize, 30);
	private Dimension topBotMaxDim = new Dimension(xLogSize, 40);
	private Dimension pLoginDim = new Dimension(xLogSize, 100);

	private PLogin pl1, pl2;
	private JTextField newJt;

	/**
	 * A Class which contains methods to generate a frame allowing the customer
	 * to connect to the server.
	 */
	public LoginUI() {

		xPosition = Screen.SCREEN_WIDTH / 2 - xLogSize / 2;
		yPosition = Screen.SCREEN_HEIGHT / 2 - yLogSize / 2;
	}

	/**
	 * this methods create and show the login frame
	 */
	public void createUI() {
		contentPane = new JFrame();
		contentPane.setTitle("Pentaho testFrame");
		ImageIcon img = new ImageIcon(
				LoginUI.class.getResource("/res/plugbiicon.png"));
		contentPane.setIconImage(img.getImage());
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

	/**
	 * this method generate the big part of the frame
	 */
	public void containerInitializer() {
		mainContainer = new JPanel();
		mainContainer.setSize(contentPane.getWidth(), contentPane.getHeight());
		mainContainer.setOpaque(true);
		mainContainer.setVisible(true);
		mainContainer.setLayout(new BoxLayout(mainContainer,
				BoxLayout.PAGE_AXIS));

		pl1 = new PLogin("Paramètres de connexion", "Nom du portail web :",
				"Nom du serveur ou son adresse IP :", new JTextField());
		pl2 = new PLogin("Profil de connexion", "Nom d'utilisateur:",
				"Mot de passe:", new JPasswordField());
		newJt = pl1.addRow(pl1.getRowForCol1(), "Port HTTP", new Dimension(50,
				30));

		pl1.setMaximumSize(pLoginDim);
		pl1.setPreferredSize(pLoginDim);
		pl2.setMaximumSize(pLoginDim);
		pl2.setPreferredSize(pLoginDim);

		mainContainer.add(topContainer);
		mainContainer.add(pl1);
		mainContainer.add(pl2);
		mainContainer.add(bottomContainer);
	}

	/**
	 * this method generate the top part of the frame
	 */
	public void topInit() {

		topLabel = new JLabel("Connnexion au portail web");

		topContainer = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2L;

			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				GradientPaint gp = new GradientPaint(30, 150, Color.WHITE, 0,
						0, new Color(101, 232, 95), false);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		};
		topContainer.setPreferredSize(topBotPrefDim);
		topContainer.setMaximumSize(topBotMaxDim);
		topContainer.add(topLabel);

	}

	/**
	 * this method generate the bottom of the frame
	 */
	public void botInit() {

		// TODO add Listener
		homeButton = new JButton();
		ImageIcon homeBtn = new ImageIcon(
				LoginUI.class.getResource("/res/home.png"));
		homeButton.setIcon(homeBtn);
		homeButton.setPreferredSize(homeButtonDim);
		connectButton = new JButton("Connexion");
		cancelButton = new JButton("Annuler");

		setButtonListener();
		
		bottomContainer = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 30,
						150, new Color(101, 232, 95), false);
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

	public void setButtonListener() {

		ActionListener al = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Connection(pl1.getTextField1(), newJt.getText(), pl1.getTextField2(),
						pl2.getTextField1(), pl2.getTextField2());
				
			}
			
		};
		
		connectButton.addActionListener(al);

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.dispose();
			}
		});

	}

	public static void main(String[] args) {
		LoginUI lui = new LoginUI();
		lui.createUI();
	}

}
