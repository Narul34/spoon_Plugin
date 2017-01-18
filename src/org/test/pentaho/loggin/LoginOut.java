package org.test.pentaho.loggin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.test.pentaho.http.Connect;

public class LoginOut extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginOut() {
		super();

		ImageIcon img = new ImageIcon(LoginUI.class.getResource("/res/plugbiicon.png"));
		
		JLabel label = new JLabel("Voulez-vous vraiment vous déconnecter?");
		
		JPanel text = new JPanel();
		text.add(label);
		
		JButton yes = new JButton("Yes");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("yes");
				Connect.getInstance().Logout();
				dispose();
				//Here we'll set the disconnect state
			}
		});
		
		JButton no = new JButton("No");
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("no");
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
	
	public static void main(String[] args) {
		new LoginOut();
	}

}
