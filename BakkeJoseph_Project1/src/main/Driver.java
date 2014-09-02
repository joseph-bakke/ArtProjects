/*
 * Project Title: Geometric Anxiety
 * Name: Joseph Bakke
 * Date: 4/28/2014
 * Description: 
 * I struggle with anxiety frequently, and during anxiety attacks it is extremely difficult
 * to formulate rational thoughts. Yet I feel that when I am in a rational state of mind I
 * try to follow an intentionally extremely logical thought process (In order to counter act 
 * the anxious, inherently irrational thoughts that often form). This project attempts to illustrate the
 * juxtaposition between anxious thoughts and rational ones. Every thought begins as a 
 * collection of ideas that are continuously rewritten until a full, reasonable thought is created.
 * If you observe each variation of the randomly generated geometric pattern, they all eventually
 * form the same grid, or a completed thought regardless of the starting point or perception. 
 * Yet during a period of anxiety, rather than immediately arriving at that completed thought it 
 * takes a much longer time to form a reasonable thought due to the unexpectedness of my mindset.			
 * I included the opportunity for the user to change the perspective in order to represent how
 * different perspectives may lead to thoughts forming in different ways, yet still eventually
 * being completed. The differing colors exist for purely aesthetic purposes.
 */

package main;

import java.util.Random;
import processing.core.PApplet;

public class Driver extends PApplet {

	private static final long serialVersionUID = -6049383787256257452L;

	private float x, y, px, py;
	private float xDir, yDir;
	private float midX, midY;
	private float red, green, blue, alpha;
	private final int X_MAX = 1280;
	private final int Y_MAX = 768;
	private Random rand = new Random();
	
	public void setup() {		
		frameRate(60);
		size(X_MAX,Y_MAX);
		background(0);
		
		midX = X_MAX/2;
		midY = Y_MAX/2;
		
		xDir = 10;
		yDir = 10;
		x = px = width/4;
		y = py = width/2;
	}
	
	public void draw() {
		hitWall();
		drawLines();	
	}
	
	public void hitWall() {
		if( x+xDir >= X_MAX || x+xDir <= 0 ) {
			xDir = -xDir;
		} else if( y+yDir >= Y_MAX || y+yDir <= 0 ) {
			yDir = -yDir;
		}
	}
	
	public void mousePressed() {
		updateColor(frameCount);
		background(0);
		x = px = X_MAX/2;
		y = py = Y_MAX/2;
	
		xDir = mouseX/(float)X_MAX*25;
		yDir = mouseY/(float)Y_MAX*25;
		
		xDir = xDir < 5 ? 5 : xDir;
		yDir = yDir < 5 ? 5 : yDir;
		
		xDir = (rand.nextBoolean() == true) ? xDir : -xDir;
		yDir = (rand.nextBoolean() == true) ? yDir : -yDir;
		
		switch( rand.nextInt(3) ) {
			case 0:
				red = 232;
				green = 232;
				blue = 39;
				alpha = 180;
				break;
			case 1:
				red = 89;
				green = 62;
				blue = 184;
				alpha = 143;
				break;
			case 2:
				red = green = blue = 127;
				alpha = 100;
				break;
		}
		
	}
	
	public void drawLines() {
		int speed = 1;
		
		if( frameCount % 10 == 0 ) {
			xDir = (rand.nextBoolean() == false) ? xDir : -xDir;
			yDir = (rand.nextBoolean() == true) ? yDir : -yDir;	
			updateColor(frameCount);
		}
		
		line(x, y, px, py);
		
		if( frameCount % 10 == 5 ) {
			xDir = (rand.nextBoolean() == false) ? xDir : -xDir;
			yDir = (rand.nextBoolean() == true) ? yDir : -yDir;	
			updateColor(frameCount);
		}
		
		line(midX + (midX - x), midY + (midY - y), midX + (midX - px), midY + (midY - py));	

		px = x;
		py = y;
		x += xDir*speed;
		y += yDir*speed;
	}
	
	public void updateColor(int mod) {
		float r = red;
		float b = blue;
		float g = green;
		float a = alpha;
		
		r = r + (r*(float)Math.sin(mod))-a;
		g = g + (g*(float)Math.sin(mod))-a;
		b = b + (b*(float)Math.sin(mod))-a;
		
		stroke( r, g, b);
	}
	
	public static void main( String[] args ) {
		PApplet.main(new String[] { "--present", "main.Driver" });
	}
}
