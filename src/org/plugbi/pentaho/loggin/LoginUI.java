/*
 * An UI which  make the user to connect to the PlugBI server.
 */

package org.plugbi.pentaho.loggin;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.ui.xul.dom.Document;
import org.plugbi.pentaho.Globals;
import org.plugbi.pentaho.http.Connect;

/**
 * The user interface of the login frame.
 * 
 * @author Admin
 * 
 * @see Connect,IConnector, ColoredPanel, LoginPanel
 */
public class LoginUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Class<?> PKG = LoginUI.class; // for i18n purposes
	
	/**
	 * The component which contains every others.
	 */
	private JPanel mainContainer;
	/**
	 * The top panel.
	 */
	private JPanel topContainer; 
	/**
	 * the bottom panel.
	 */
	private JPanel bottomContainer;
	/**
	 * the label containing the title.
	 */
	private JLabel topLabel;
	/**
	 * The cancel button.
	 */
	private JButton cancelButton;
	/**
	 * The connection button.
	 */
	private JButton connectButton;
	/**
	 * The home button.
	 */
	private JButton homeButton;
	/**
	 * the x position of the frame on the screen
	 */
	private int xPosition;
	/**
	 * the y position of the frame on the screen
	 */
	private int yPosition;
	/**
	 * The width of the frame.
	 */
	public final int WIDTH = 580;
	/**
	 * The height of the frame.
	 */
	public final int HEIGHT = 318;
	/**
	 * The dimension of the home button.
	 */
	public final Dimension HOME_BUTTON_DIM = new Dimension(40, 36);
	/**
	 * The dimension of the top and bottom panel.
	 */
	public final Dimension TOP_BOT_DIM = new Dimension(WIDTH, 45);
	/**
	 * The dimension of a LoginPanel class.
	 */
	public final Dimension PLOGIN_DIM = new Dimension(WIDTH, 100);
	/**
	 * A container for the form.
	 */
	private LoginPanel pl1;
	/**
	 * A container for the form.
	 */
	private LoginPanel pl2;
	/**
	 * this textfield will be added to a row.
	 */
	private JTextField newJt;
	/**
	 * This parameter is used to stock the Pentaho natively generated 'document' in a spoon plug-in (thansk to AbstractXulEventHandler)
	 * 
	 */
	private static Document document;
	

	/*
	 * A Class which contains methods to generate a frame allowing the customer
	 * to connect to the server.
	 */
	/**
	 * This constructor load a Document object which will allow us to manage Xul files,
	 * then set the size of the frame before creating it.
	 * 
	 * @param document
	 * 			The document from the handler.
	 */
	public LoginUI(Document document) {
		super();
		LoginUI.document = document;
		xPosition = Globals.SCREEN_WIDTH / 2 - WIDTH / 2;
		yPosition = Globals.SCREEN_HEIGHT / 2 - HEIGHT / 2;
		createUI();
	}

	/*
	 * this methods create and show the login frame
	 */
	/**
	 * Set the icon, image, size,... then initialize the content.
	 */
	public void createUI() {
		
			ImageIcon img = new ImageIcon(LoginUI.class.getResource(BaseMessages.getString(PKG, "Loggin.Icon")));
			
			this.setTitle(BaseMessages.getString(PKG, "Loggin.Title"));		
			this.setIconImage(img.getImage());
			this.setBounds(xPosition, yPosition, WIDTH, HEIGHT);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			this.setResizable(false);
			this.setAlwaysOnTop(true);
			this.setAlwaysOnTop(false);
			topInit();
			botInit();
			containerInitializer();
			this.setContentPane(mainContainer);		
	}

	/*
	 * this method generate the top part of the frame
	 */
	/**
	 * Initalization of the top of the frame, containing only the title and a colored panel.
	 */
	public void topInit() {

		topLabel = new JLabel(BaseMessages.getString(PKG, "Loggin.Frame.Title"));

		topContainer = new ColoredPanel(0, 0, new Color(200, 200, 200), 0, 30, new Color(238, 238, 238));			
		topContainer.setPreferredSize(TOP_BOT_DIM);
		topContainer.setMaximumSize(TOP_BOT_DIM);
		topContainer.add(topLabel);

	}

	/*
	 * this method generate the bottom of the frame
	 */
	/**
	 * Initalization of the bottom of the frame, containing buttons and a colored panel.
	 */
	public void botInit() {

		ImageIcon homeBtn = new ImageIcon(LoginUI.class.getResource(BaseMessages.getString(PKG, "Loggin.Frame.HomeButtonIcon")));
		homeButton = new JButton();		
		homeButton.setIcon(homeBtn);
		homeButton.setToolTipText(BaseMessages.getString(PKG, "Loggin.Frame.HomeButtonToolTip"));
		homeButton.setPreferredSize(HOME_BUTTON_DIM);
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		
		connectButton = new JButton(BaseMessages.getString(PKG, "Loggin.Frame.ConnectButton"));
		cancelButton = new JButton(BaseMessages.getString(PKG, "Loggin.Frame.CancelButton"));
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
		bottomContainer.setPreferredSize(TOP_BOT_DIM);
		bottomContainer.setMaximumSize(TOP_BOT_DIM);
		bottomContainer.setLayout(gridLayout);
		bottomContainer.add(homePanel);
		bottomContainer.add(buttonPanel);

	}
	
	/*
	 * this method generate the big part of the frame
	 */
	/**
	 * The main part of the frame (fields and labels) are initalize with this method.
	 */
	public void containerInitializer() {
		
		pl1 = new LoginPanel(BaseMessages.getString(PKG, "Loggin.PanelOne.Name"),
				BaseMessages.getString(PKG, "Loggin.PanelOne.FirstLabel"),
				BaseMessages.getString(PKG, "Loggin.PanelOne.SecondLabel"), 
				new JTextField());
		
		pl2 = new LoginPanel(BaseMessages.getString(PKG, "Loggin.PanelTwo.Name"),
				BaseMessages.getString(PKG, "Loggin.PanelTwo.FirstLabel"),
				BaseMessages.getString(PKG, "Loggin.PanelTwo.SecondLabel"),  
				new JPasswordField());
		newJt = pl1.addRow(pl1.getFirstRow(), "Port HTTP", new Dimension(50,30));

		pl1.setMaximumSize(PLOGIN_DIM);
		pl1.setPreferredSize(PLOGIN_DIM);
		pl1.setBorder(new EmptyBorder(0, 3, 0, 0));
		pl2.setMaximumSize(PLOGIN_DIM);
		pl2.setPreferredSize(PLOGIN_DIM);
		pl2.setBorder(new EmptyBorder(0, 3, 0, 0));
		
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

	/**
	 * This method add listener to all the buttons
	 */
	public void setButtonListener() {
		
		connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				load();
			}

		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		homeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				openWebPage();
				
			}
		});

	}
	
	/**
	 * A static getter for the document
	 * @return document
	 */
	public static Document getDocument(){
		return document;
	}
	
	/**
	 * This method load the parameters from fields to create a connection with the server,
	 * then send the request.
	 */
	public void load(){
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
	
	/**
	 * For the home button, this will open a home web page. The user must add a correct portal name to make this method effective.
	 */
	public void openWebPage(){
		String portal = pl1.getTextField1() ;
		if(portal.equals(null) | portal.equals("")){
			JOptionPane.showMessageDialog(null,
					BaseMessages.getString(PKG, "Loggin.HomeDialog.Mess"),
					BaseMessages.getString(PKG, "Loggin.HomeDialog.Title"), 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		URI uri = URI.create("http://ns211649.ovh.net/" + portal + "/page/login/login.htmk");
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//BasicConfigurator.configure();
		
		LoginUI lui = new LoginUI(document);
		System.out.println(lui);
	}

}
