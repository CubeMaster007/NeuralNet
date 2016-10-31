package ann;

import static main.Constants.DEF_SIGMOID_RANGE;
import static main.Constants.DEF_SIGMOID_SLOPE;
import static main.Constants.HIDDEN_NEURON_COLOR;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;

/**
 * @author Adam Mendenhall
 */
public class NeuronHidden extends Neuron {
	
	public NeuronHidden(Collection<Neuron> inputNeurons) {
		super();
		for (Node n: inputNeurons) {
			addInput(n);
		}
	}

	@Override
	public void addInput(Node input) {
		addInput(input, Math.random());
	}
	
	@Override
	public void addInput(Node input, double weight) {
		this.inputs.put(input, weight);
	}

	@Override
	public double grab() {
		double sum = 0;
		for (Node n: inputs.keySet()) {
			sum += n.observe() * inputs.get(n);
		}
		lastOutput = sigmoid(sum, DEF_SIGMOID_SLOPE, DEF_SIGMOID_RANGE);
		return observe();
	}
	
	public double sigmoid(double input, double originSlope, double range) {
		if (range == 0) {
			lastOutput = 0;
			return 0;
		} else {
			//I forgot to update the derivative for this function, not sure if this is possible
//			lastOutput = ((2*range) / (1 + Math.exp((-2*originSlope*input)/range))) - range;
			lastOutput = (1 / (1+Math.exp(-input))) - 0.5;
			return lastOutput;
		}
		/* |  THEmaththeMATHTHEmaththeMATH |
		 * |                               |
		 * |  Sigmoid function             |
		 * |                               |
		 * |         1                     |
		 * |     ----------                |
		 * |     1 + e^(-x)                |
		 * |                               |
		 * |                               |
		 * |  Scalable sigmoid function,   |
		 * |  a=range of output            |
		 * |  b=slope at origin            |
		 * |                               |
		 * |  2 * range                    |
		 * |  ------------------- - range  |
		 * |  1 + e^((-2*b*x)/a))          |
		 */
	}
	
	public void draw(Graphics g, String text) {
		g.setColor(HIDDEN_NEURON_COLOR);
		super.draw(g, text);
	}
	
//	public double calcOutput() throws ExceptionActionNotYetOccured {
//		double sum = feedForwardValue;
//		for (int i=0; i<inputSynapses.size(); i++) {
//			Double in = inputVals.get(i);
//			if (in.isNaN())
//				throw new ExceptionActionNotYetOccured();
//			else
//				sum += inputVals.get(i);
//		}
//		
//		return sigmoid(sum/(inputSynapses.size()+1), 1, 1);
//	}
//	
//	
////	public void draw(Graphics g, int yPos) {
////		if (xPos == null)
////			xPos = 0;
////		if (radius == null)
////			radius = 10;
////		g.drawOval(xPos-radius, yPos-radius, radius*2, radius*2);
////	}
//	
//	public LinkedList<Double> cloneWeights() {
//		LinkedList<Double> weights = new LinkedList<Double>();
//		for (Synapse s: inputSynapses)
//			weights.add(s.getWeight());
//		return weights;
//	}
//	
//	public String toString() {
//		String ret = "Hidden neruon with weights: \n";
//		for (int i=0; i<inputSynapses.size(); i++)
//			ret += inputSynapses.get(i).getWeight() + ", \n";
//		return ret;
//	}
//	
//	public Synapse getSynapse(int idx) {
//		return inputSynapses.get(idx);
//	}
//
//	@Override
//	public LinkedList<Synapse> getSynapses() {
//		return inputSynapses;
//	}
//	
//	@Override
//	public void draw(Graphics g) {
//		g.setColor(HIDDEN_NEURON_COLOR);
//		super.draw(g);
//	}
//
//	@Override
//	public void update() throws ExceptionActionNotYetOccured {
//		lastOutput = calcOutput();
//		for (Synapse s: outputSynapses)
//			s.put(lastOutput);
//	}
//
//	@Override
//	public void addOutputSynapse(Synapse synapse) {
//		outputSynapses.add(synapse);
//	}
//
//	@Override
//	public void addInputSynapse(Synapse synapse) {
//		inputSynapses.add(synapse);
//		inputVals.add(Double.NaN);
//	}
//	
//	public void feedInput(Synapse synapse, double input) {
//		int idx = -1;
//		for (int i=0; i<inputSynapses.size(); i++) {
//			if (inputSynapses.get(i).equals(synapse))
//				idx = i;
//		}
//		if (idx!=-1)
//			inputVals.set(idx, input);
//	}
	
}
