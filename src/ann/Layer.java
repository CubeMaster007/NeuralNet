package ann;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import static main.Constants.*;

import exceptions.ExceptionIndexOutOfBounds;

/**
 * @author Adam Mendenhall
 */
public class Layer {
	
	LinkedList<Neuron> neurons;
	
	/**
	 * Creates an empty layer, one with no neurons
	 */
	public Layer() {
		this(new LinkedList<Neuron>());
	}
	
	/**
	 * Creates a layer with the given neurons
	 * @param neurons the neurons to be put in the layer
	 */
	public Layer(LinkedList<Neuron> neurons) {
		this.neurons = neurons;
	}
	
	/**
	 * Creates a layer with static neurons
	 * @param numNeurons the amount of static neurons
	 */
	public Layer(int numNeurons) {
		this();
		for (int i=0; i<numNeurons; i++) {
			neurons.add(new NeuronStatic());
		}
	}
	
	/**
	 * Creates a layer with static neurons
	 * @param numNeurons the amount of static neurons
	 * @param val the output value of each static neuron
	 */
	public Layer(int numNeurons, double val) {
		this();
		for (int i=0; i<numNeurons; i++) {
			neurons.add(new NeuronStatic(val));
		}
	}
	
//	/**
//	 * Creates a layer with grid neurons
//	 * @param p the positions of the network neurons
//	 * @param grid the grid which the neurons should monitor
//	 */
//	public Layer(Grid grid, Point ... positions) {
//		this();
////		for (Point p: positions)
////			neurons.add(new NeuronGrid(grid, p));
//	}
	
	/**
	 * Creates a layer with some neurons and connections
	 * @param prevLayer the layer that this layer is connected to,
	 * 		There will be a synapse between every neuron in this layer and the previous layer
	 * 		Weights will all be random, with the range [0,1)
	 * @param numNeurons the amount of neurons in this layer
	 */
	public Layer(Layer prevLayer, int numNeurons) {
		this();
		for (int i=0; i<numNeurons; i++) {
			makeNeuron(prevLayer);
		}
	}
	
//	public void draw(Graphics g, int xPos) {
//		Rectangle bounds = g.getClipBounds();
//		//array to store the coordinates for the top left corner of each neuron
//		Point[] neuronCorners = new Point[neurons.size()];
//		//store all of the coordinates for the top left corner of each neuron
//		for (int neuronIdx=0; neuronIdx<neurons.size(); neuronIdx++) {
//			//calculate the coordinates of the neuron
//			int leftest = xPos-LAYER_GAP_DIST;
//			int uppest = (int)(bounds.height/2 - NEURON_GAP_DIST*(neuronIdx-((double)neurons.size())/2));
//			neuronCorners[neuronIdx] = new Point(leftest, uppest);
//		}
//		//loop through each neuron
//		for (int neuronIdx=0; neuronIdx<neuronCorners.length; neuronIdx++) {
//			//store the coordinates of the top left corner of the neuron
//			Point tlCorner = neuronCorners[neuronIdx];
//			//draw the neuron
//			g.drawOval(tlCorner.x, tlCorner.y, NEURON_RADIUS, NEURON_RADIUS);
//			//loop through all of the synapses of the neuron
//			for (int synapseIdx=0; synapseIdx<neurons.get(neuronIdx).numSynapses(); synapseIdx++) {
//				Synapse s = neurons.get(neuronIdx).getSynapse(synapseIdx);
//				g.drawLine(tlCorner.x+NEURON_RADIUS, tlCorner.y+NEURON_RADIUS, arg2, arg3);
//			}
//		}
//	}
	
	public void addNeuron(NeuronHidden neuron) {
		neurons.add(neuron);
	}
	
	public void makeNeuron(Layer prevLayer) {
		neurons.add(new NeuronHidden(prevLayer.getNeurons()));
	}
	
	public Neuron getNeuron(int index) throws ExceptionIndexOutOfBounds {
		if (index >= neurons.size())
			throw new ExceptionIndexOutOfBounds();
		else
			return neurons.get(index);
	}
	
	public LinkedList<Neuron> getNeurons() {
		return neurons;
	}
	
	public int numNeurons() {
		return neurons.size();
	}
	
	public void pass() {
		for (Neuron n: neurons) {
			n.grab();
		}
	}

	public void backPass(Layer nextLayer) {
		int i=0;
		for (Neuron n: neurons) {
			n.calcDelta(nextLayer);
			n.changeWeights(LEARNING_RATE);
			i++;
		}
	}
	
	public void initBackPass(double[] expectedOutputs) {
		for (int i=0; i<expectedOutputs.length; i++) {
			neurons.get(i).calcDelta(expectedOutputs[i]);
			neurons.get(i).changeWeights(LEARNING_RATE);
		}
	}

	public void save(FileWriter fw) throws IOException {
		for (int i=0; i<neurons.size(); i++) {
			fw.write("n" + i + "~~~~~~~~~~~~~~~~~~~~");
			neurons.get(i).save(fw);
			fw.write(System.getProperty("line.separator"));
		}
	}
	
}
