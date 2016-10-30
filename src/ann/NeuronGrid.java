package ann;

import static main.Constants.*;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;

public class NeuronGrid extends Neuron {
	
	public NeuronGrid() {
		super();
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void addInput(Node input) {
		addInput(input, Math.random());
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void addInput(Node input, double weight) {
		inputs.clear();
		inputs.put(input, weight);
	}

	@Override
	public double grab() {
		for (Node n: inputs.keySet()) {
			lastOutput = n.grab();
		}
		return observe();
	}
	
	public void draw(Graphics g, String text) {
		g.setColor(GRID_NEURON_COLOR);
		super.draw(g, text);
	}
	
}
