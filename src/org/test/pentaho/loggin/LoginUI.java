/*
 * An UI which  make the user to connect to the PlugBI server.
 */

package org.test.pentaho.loggin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.pentaho.ui.xul.dom.Document;
import org.test.pentaho.GlobalFeature;
import org.test.pentaho.http.Connect;

public class LoginUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainContainer;
	private JPanel topContainer, bottomContainer;
	private JLabel topLabel;
	private JButton cancelButton, connectButton, homeButton;
	private int xPosition;
	private int yPosition;
	private int width = 580;
	private int height = 318;
	private Dimension homeButtonDim = new Dimension(40, 36);
	private Dimension topBotDim = new Dimension(width, 45);
	private Dimension pLoginDim = new Dimension(width, 100);
	private LoginPanel pl1, pl2;
	private JTextField newJt;
	private static Document document;
	

	/*
	 * A Class which contains methods to generate a frame allowing the customer
	 * to connect to the server.
	 */
	public LoginUI(Document document) {
		super();
		LoginUI.document = document;
		xPosition = GlobalFeature.SCREEN_WIDTH / 2 - width / 2;
		yPosition = GlobalFeature.SCREEN_HEIGHT / 2 - height / 2;
		createUI();
	}

	/*
	 * this methods create and show the login frame
	 */
	public void createUI() {
		
			ImageIcon img = new ImageIcon(LoginUI.class.getResource("/res/plugbiicon.png"));
			
			this.setTitle("Pentaho testFrame");		
			this.setIconImage(img.getImage());
			this.setBounds(xPosition, yPosition, width, height);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			this.setResizable(false);
			//this.setAlwaysOnTop(true);
			topInit();
			botInit();
			containerInitializer();
			this.setContentPane(mainContainer);		
	}

	/*
	 * this method generate the big part of the frame
	 */
	public void containerInitializer() {
		
		pl1 = new LoginPanel("Paramètres de connexion", "Nom du portail web :",
				"Nom du serveur ou son adresse IP :", new JTextField());
		pl2 = new LoginPanel("Profil de connexion", "Nom d'utilisateur:",
				"Mot de passe:", new JPasswordField());
		newJt = pl1.addRow(pl1.getRowForCol1(), "Port HTTP", new Dimension(50,30));

		pl1.setMaximumSize(pLoginDim);
		pl1.setPreferredSize(pLoginDim);
		pl1.setBorder(new EmptyBorder(0, 3, 0, 0));
		pl2.setMaximumSize(pLoginDim);
		pl2.setPreferredSize(pLoginDim);
		
		mainContainer = new JPanel();
		mainContainer.setSize(this.getWidth(), this.getHeight());
		mainContainer.setOpaque(true);
		mainContainer.setVisible(true);
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
		mainContainer.add(topContainer);
		mainContainer.add(pl1);
		mainContainer.add(pl2);
		mainContainer.add(bottomContainer);
	}

	/*
	 * this method generate the top part of the frame
	 */
	public void topInit() {

		topLabel = new JLabel("Connnexion au portail web");

		topContainer = new ColoredPanel(0, 0, new Color(200, 200, 200), 0, 30, new Color(238, 238, 238));			
		topContainer.setPreferredSize(topBotDim);
		topContainer.setMaximumSize(topBotDim);
		topContainer.add(topLabel);

	}

	/*
	 * this method generate the bottom of the frame
	 */
	public void botInit() {

		ImageIcon homeBtn = new ImageIcon(LoginUI.class.getResource("/res/home.png"));
		homeButton = new JButton();		
		homeButton.setIcon(homeBtn);
		homeButton.setToolTipText("Accueil");
		homeButton.setPreferredSize(homeButtonDim);
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		
		connectButton = new JButton("Connexion");
		cancelButton = new JButton("Annuler");
		setButtonListener();

		GridLayout gridLayout = new GridLayout(1, 5);
		gridLayout.setHgap(190);
		
		JPanel homePanel = new JPanel();
		homePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		homePanel.add(homeButton);
		homePanel.setOpaque(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(connectButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setOpaque(false);
		
		bottomContainer = new ColoredPanel(0, 0, new Color(238, 238, 238), 0, 30, new Color(200, 200, 200));
		bottomContainer.setPreferredSize(topBotDim);
		bottomContainer.setMaximumSize(topBotDim);
		bottomContainer.setLayout(gridLayout);
		bottomContainer.add(homePanel);
		bottomContainer.add(buttonPanel);

	}

	public void setButtonListener() {
		
		connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String portal = pl1.getTextField1() ;
				String port = newJt.getText() ;
				String host = pl1.getTextField2();
				String user = pl2.getTextField1() ;
				String password = pl2.getTextField2() ;
				
				try {
					Connect.getInstance().Load(portal, port, host, user, password);
					if(Connect.getInstance().Login()){
						LoginUI.document.getElementById("open-item").setAttribute("disabled", "false");
					}
					
					
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}

			}

		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	public static void main(String[] args) {
		//BasicConfigurator.configure();
		
		LoginUI lui = new LoginUI(document);
		System.out.println(lui);
	}

}
