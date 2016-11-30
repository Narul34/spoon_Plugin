package org.test.pentaho.option;

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

public class OptionUI {

	private JFrame contentPane;
	private JPanel mainContainer;
	private JPanel topContainer, bottomContainer;
	private JLabel topLabel;
	private JButton cancelButton;
	private int screenWidth;
	private int screenHeight;
	private int xPosition;
	private int yPosition;
	private int xOptionSize = 600;
	private int yOptionSize = 350;
	private Dimension screenDimension;

	public OptionUI() {

		screenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = (int) screenDimension.getHeight();
		screenWidth = (int) screenDimension.getWidth();
		xPosition = screenWidth / 2 - xOptionSize / 2;
		yPosition = screenHeight / 2 - yOptionSize / 2;
	}

	public void createUI() {
		contentPane = new JFrame();
		contentPane.setTitle("Pentaho testFrame");
		contentPane.setBounds(xPosition, yPosition, xOptionSize, yOptionSize);
		contentPane.setVisible(true);
		contentPane.setResizable(false);
		contentPane.setAlwaysOnTop(true);

		topAndBotInit();
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

		mainContainer.add(topContainer);
		mainContainer.add(bottomContainer);
	}

	public void topAndBotInit() {

		topLabel = new JLabel("Option");

		cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

			}

		});

		topContainer = new JPanel();
		topContainer.setBackground(Color.ORANGE);
		topContainer.setPreferredSize(new Dimension(xOptionSize, 30));
		topContainer.setMaximumSize(new Dimension(xOptionSize, 40));
		topContainer.add(topLabel);

		bottomContainer = new JPanel();
		bottomContainer.setBackground(Color.ORANGE);
		bottomContainer.setPreferredSize(new Dimension(xOptionSize, 30));
		bottomContainer.setMaximumSize(new Dimension(xOptionSize, 40));
		bottomContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 3));
		bottomContainer.add(cancelButton);

	}

}
