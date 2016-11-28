package org.test.pentaho.loggin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class LoginUI {

	private JFrame contentPane;
	private JPanel mainContainer;
	private JPanel topContainer, buttonContainer, bottomContainer;
	private JLabel topLabel;
	private JButton cancelButton, connectButton, homeButton;
	private int screenWidth;
	private int screenHeight;
	private int xPosition;
	private int yPosition;
	private int xLogSize = 600;
	private int yLogSize = 350;
	private Dimension screenDimension;

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
		contentPane.setVisible(true);
		contentPane.setResizable(false);
		contentPane.setAlwaysOnTop(true);

		topAndBotInit();
		buttonContainerInit();
		containerInitializer();

		contentPane.setContentPane(mainContainer);
	}

	public void topAndBotInit() {

		topLabel = new JLabel("Connnexion au portail web");

		cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}

		});

		topContainer = new JPanel();
		topContainer.setBackground(Color.ORANGE);
		topContainer.setPreferredSize(new Dimension(xLogSize, 30));
		topContainer.setMaximumSize(new Dimension(xLogSize, 40));
		topContainer.add(topLabel);

		bottomContainer = new JPanel();
		bottomContainer.setBackground(Color.ORANGE);
		bottomContainer.setPreferredSize(new Dimension(xLogSize, 30));
		bottomContainer.setMaximumSize(new Dimension(xLogSize, 40));
		bottomContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottomContainer.add(cancelButton);

	}

	public void containerInitializer() {
		mainContainer = new JPanel();
		mainContainer.setSize(contentPane.getWidth(), contentPane.getHeight());
		mainContainer.setOpaque(true);
		mainContainer.setVisible(true);
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));

		PLogin pl1 = new PLogin("Paramètres de connexion", "Nom du portail web :", "Nom du serveur ou son adresse IP :");
		PLogin pl2 = new PLogin("Profil de connexion", "Nom d'utilisateur:", "Mot de passe:");
		pl1.addBox(pl1.getFirstRowPanel(), "Port HTTP", new Dimension(50,25));
		

		pl1.setMaximumSize(new Dimension(xLogSize, 100));
		pl1.setPreferredSize(new Dimension(xLogSize, 100));
		pl2.setMaximumSize(new Dimension(xLogSize, 100));
		pl2.setPreferredSize(new Dimension(xLogSize, 100));
		

		mainContainer.add(topContainer);
		mainContainer.add(pl1);
		mainContainer.add(pl2);
		mainContainer.add(buttonContainer);
		mainContainer.add(bottomContainer);
	}

	public void buttonContainerInit() {

		connectButton = new JButton("Connexion");
		homeButton = new JButton("Home");

		buttonContainer = new JPanel();
		buttonContainer.add(new JSeparator());
		buttonContainer.add(connectButton);
		buttonContainer.add(homeButton);
		buttonContainer.add(new JSeparator());
		buttonContainer.setMaximumSize(new Dimension(xLogSize, 50));
		buttonContainer.setPreferredSize(new Dimension(xLogSize, 50));
	}

	public static void main(String[] args) {
		LoginUI lui = new LoginUI();
		lui.createUI();
	}

}
