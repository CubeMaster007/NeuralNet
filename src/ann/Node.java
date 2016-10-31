package ann;

import java.awt.Point;

import javax.swing.JComponent;

/**
 * @author Adam Mendenhall
 */
public abstract class Node implements Junction {
	
	protected Point pos;
	
	protected Node() {
		lastOutput = 0;
		lastUpdated = System.currentTimeMillis();
		pos = new Point(0,0);
	}
	
	protected double lastOutput;
	protected long lastUpdated;
	
	/**
	 * {@inheritDoc}
	 */
	public double observe() {
		return lastOutput;
	}
	
	public int getAge() {
		return (int)(System.currentTimeMillis()-lastUpdated);
	}
	
	public Point getPos() {
		return new Point(pos);
	}
	
	public void setPos(Point p) {
		pos = p;
	}
	
}
