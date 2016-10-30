package ann;

import static main.Constants.*;

import java.awt.Graphics;
import java.awt.Point;

public class NeuronStatic extends Neuron {
	
	public NeuronStatic() {
		super();
	}
	
	public NeuronStatic(double output) {
		super();
		lastOutput = output;
	}

	public void draw(Graphics g, String text) {
		g.setColor(STATIC_NEURON_COLOR);
		super.draw(g, text);
	}

	@Override
	public void addInput(Node input) {}

	@Override
	public void addInput(Node input, double weight) {}

	@Override
	public double grab() {
		return observe();
	}
	
}
