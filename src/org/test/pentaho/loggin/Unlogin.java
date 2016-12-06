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

public class Unlogin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Unlogin() {
		super();

		ImageIcon img = new ImageIcon(LoginUI.class.getResource("/res/plugbiicon.png"));
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		JPanel text = new JPanel();
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("Voulez-vous vraiment vous déconnecter?");
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");

		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("yes");
			}
		});

		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("no");

			}
		});
		
		buttons.add(yes);
		buttons.add(no);
		text.add(label);
		container.add(text);
		container.add(buttons);
		
		this.setIconImage(img.getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setModal(true);
		this.setContentPane(container);
		this.pack();
		this.validate();
		this.setResizable(false);
		this.setLocationRelativeTo(null);

	}
	
	public static void main(String[] args) {
		new Unlogin();
	}

}
