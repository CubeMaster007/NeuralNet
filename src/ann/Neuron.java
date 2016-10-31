package ann;

import static main.Constants.*;

import java.awt.Graphics;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Adam Mendenhall
 */
public abstract class Neuron extends Node {
	
	private double delta;
	
	protected Neuron() {
		super();
		inputs = new HashMap<Node, Double>();
	}
	
	protected Neuron(HashMap<Node, Double> inputs) {
		super();
		this.inputs = inputs;
	}
	
	protected HashMap<Node, Double> inputs;
	
	/**
	 * Adds an input to the neuron with a random weight
	 * @param input the input node
	 */
	public abstract void addInput(Node input);
	/**
	 * Adds an input to the neuron with the given weight
	 * @param input the input node
	 * @param weight the weight between this neuron and the node
	 */
	public abstract void addInput(Node input, double weight);
	
	/**
	 * Gets the weight between neuron n and this neuron
	 * @param n
	 * @return the weight, NaN if n is not an input to this neuron
	 */
	public double getAssociatedWeight(Neuron n) {
		for (Node neuron: inputs.keySet()) {
			if (neuron.equals(n))
				return inputs.get(n);
		}
		return Double.NaN;
	}
	
	public void draw(Graphics g, String text) {
		//draw neuron
		text = NUM_FORMAT.format(Double.parseDouble(text));
		g.drawOval(pos.x - NEURON_RADIUS, pos.y - NEURON_RADIUS, NEURON_RADIUS*2, NEURON_RADIUS*2);
		g.setColor(TEXT_COLOR);
		
		g.drawString(text,
				pos.x-(int)(g.getFontMetrics().stringWidth(text)/2),
				pos.y-(int)(g.getFontMetrics().getHeight()/2));
		//draw synapses
		for (Node n: inputs.keySet()) {
			text = NUM_FORMAT.format(inputs.get(n));
			Point p1 = n.getPos();
			Point p2 = getPos();
			
			//calculate the midPoint
			Point mid = new Point((int)(p1.x*0.5+p2.x*0.5), (int)(p1.y*0.5+p2.y*0.5));
			
			//calculate the slope between the neurons and the angle
			double slope = (double)(p1.y-p2.y) / (double)(p1.x-p2.x);
			double theta = Math.atan(slope);
			
			//move to edge of each neuron
			p1.translate((int)(NEURON_RADIUS*Math.cos(theta)), (int)(NEURON_RADIUS*Math.sin(theta)));
			p2.translate((int)(NEURON_RADIUS*Math.cos(theta-Math.PI)), (int)(NEURON_RADIUS*Math.sin(theta-Math.PI)));
			
			g.setColor(TEXT_COLOR);
			g.drawString(text, mid.x - (int)(g.getFontMetrics().stringWidth(text)/2), mid.y - (int)(g.getFontMetrics().getHeight()/2));
			g.setColor(SYNAPSE_COLOR);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);                                                                                                
			g.drawLine(p2.x, p2.y, p2.x + (int)(Math.cos(theta+Math.PI*0.75)*ARROW_LEN), p2.y + (int)(Math.sin(theta+Math.PI*0.75)*ARROW_LEN));
			g.drawLine(p2.x, p2.y, p2.x + (int)(Math.cos(theta-Math.PI*0.75)*ARROW_LEN), p2.y + (int)(Math.sin(theta-Math.PI*0.75)*ARROW_LEN));
		}
	}
	
	/**
	 * Calculates delta assuming the neuron is a hidden neuron
	 */
	public void calcDelta(Layer nextLayer) {
		double sum = 0;
		for (Neuron n: nextLayer.getNeurons())
			sum += (n.getDelta() * n.getAssociatedWeight(this));
		sum *= (lastOutput * (1 - lastOutput));
		/*    THEMATHTHEMATHTHEMATHTHEMATH
		 *    
		 *    delta(j) = ((delta(l(0))+delta(l(1))...)*w(j,l)) * o(j) * (1-o(j))
		 *    for each neuron l(n) in the next layer
		 *    for some weight j
		 *    
		 */
		delta = sum;
	}
	
	/**
	 * Calculates delta assuming the neuron is an output neuron
	 */
	public void calcDelta(double expectedOutput) {
		delta = (lastOutput - expectedOutput) * lastOutput * (1 - lastOutput);
		/*   THEMATHTHEMATHTHEMATHTHEMATH
		 *   for an output neuron,
		 *   delta(j) = (o(j) - t(j)) * o(j) * (1 - o(j))
		 */
	}
	
	public double getDelta() {
		return delta;
	}

	public void changeWeight(Node input, double learningRate) {
		double old = inputs.get(input);
		double changeInWeight = - (learningRate * getDelta() * input.observe());
		inputs.replace(input, old + changeInWeight);
	}

	public void changeWeights(double learningRate) {
		for (Node n: inputs.keySet()) {
			changeWeight(n, learningRate);
		}
	}

	public void save(FileWriter fw) throws IOException {
		int i = 0;
		for (Double weight: inputs.values()) {
			fw.write(System.getProperty("line.separator"));
			fw.write("w" + i + "=" + weight);
			++i;
		}
	}
}
