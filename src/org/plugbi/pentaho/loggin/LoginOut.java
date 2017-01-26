package org.plugbi.pentaho.loggin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.pentaho.di.i18n.BaseMessages;
import org.plugbi.pentaho.http.Connect;

/**
 * A dialog which allow the user to validate or no his choice to disconnect.
 * 
 * @author Adrien Blanes
 *
 * @see PlubBiPerspectiveHandler 
 */

public class LoginOut extends JDialog {
	
	/**
	 * Used for internationalisation
	 */
	private static Class<?> PKG = LoginOut.class; // for i18n purposes
	
	/**
	 * The path where is stock your icon.
	 */
	private String iconPath;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginOut(String iconPath) {
		super();

		this.iconPath = iconPath;
		ImageIcon img = new ImageIcon(LoginUI.class.getResource(this.iconPath));
		
		JLabel label = new JLabel(BaseMessages.getString(PKG, "Loggout.Message"));
		
		JPanel text = new JPanel();
		text.add(label);
		
		JButton yes = new JButton(BaseMessages.getString(PKG, "Loggout.FirstButton"));
		yes.addActionListener(new ActionListener() {
			// here is set the disconnect state.
			@Override
			public void actionPerformed(ActionEvent e) {
				Connect.getInstance().Logout();
				dispose();
			}
		});
		
		JButton no = new JButton(BaseMessages.getString(PKG, "Loggout.SecondButton"));
		no.addActionListener(new ActionListener() {

			//only close the dialog.
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons.add(yes);
		buttons.add(no);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.add(text);
		container.add(buttons);
		
		this.setIconImage(img.getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setModal(true);
		this.setContentPane(container);
		this.pack();
		this.validate();
	}

}
