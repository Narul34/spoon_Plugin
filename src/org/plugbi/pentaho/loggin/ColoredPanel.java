package org.plugbi.pentaho.loggin;



import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/*
 * This panel is just used to add graphics to the LoginUI as wanted by project manager. 
 * His only fonction his to color the top and bot of the UI.
 */
/**
 * A simple panel, only used to color some area of the login UI
 * 
 * @author Adrien Blanes
 * 
 * @see LoginUI
 *
 */
public class ColoredPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 1 for first color start point, 2 for second color start point;
	private float x1, y1, x2, y2;
	private Color color1, color2;

	public ColoredPanel(float x1, float y1, Color color1, float x2, float y2, Color color2){
		this.x1 = x1;
		this.y1 = y1;
		this.color1 = color1;
		this.x2 = x2;
		this.y2 = y2;
		this.color2 = color2;
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(x1, y1, color1, x2,
				y2, color2, false);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
}
