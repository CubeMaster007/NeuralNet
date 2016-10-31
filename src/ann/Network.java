package ann;

import static main.Constants.HORIZ_BUFFER;
import static main.Constants.LAYER_GAP_DIST;
import static main.Constants.NEURON_GAP_DIST;
import static main.Constants.VERT_BUFFER;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import exceptions.ExceptionActionNotYetOccured;
import exceptions.ExceptionIndexOutOfBounds;

/**
 * @author Adam Mendenhall
 */
public class Network {
	
	LinkedList<Layer> layers;
	
	public Network() {
		this(new LinkedList<Layer>());
	}
	
	public Network(LinkedList<Layer> layers) {
		this.layers = layers;
	}
	
	public void addLayer(Layer layer) {
		layers.add(layer);
	}
	
	public Layer getLayer(int index) {
		if (index>=layers.size())
			return layers.get(0);
		else
			return layers.get(index);
	}
	
	public LinkedList<Layer> getLayers() {
		return layers;
	}
	
	public void pass() {
		for (Layer l: layers)
			l.pass();
	}
	
	public void backPass(double[] expectedOutputs) {
		layers.getLast().initBackPass(expectedOutputs);
		for (int i=layers.size()-2; i>=0; i--) {
			layers.get(i).backPass(layers.get(i+1));
		}
	}
	
	public int numLayers() {
		return layers.size();
	}
	
	private int neuronsInLargestLayer() {
		int maxNeurons = 0;
		for (Layer l: layers)
			maxNeurons = Math.max(maxNeurons, l.numNeurons());
		return maxNeurons;
	}
	
	public Layer getLastLayer() {
		if (layers.size()<1)
			return null;
		else
			return layers.get(layers.size()-1);
	}
	
	public Dimension getPrefferedSize() {
		return new Dimension(LAYER_GAP_DIST*numLayers() + HORIZ_BUFFER*2,
				NEURON_GAP_DIST*neuronsInLargestLayer() + VERT_BUFFER*2);
	}
	
	public void save(FileWriter fw) throws IOException {
		fw.write(System.getProperty("line.separator"));
		for (int i=0; i<layers.size(); i++) {
			fw.write("l" + i + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.getProperty("line.separator"));
			layers.get(i).save(fw);
		}
		fw.write("Network saved on "+Calendar.getInstance().getTime());
		fw.write(System.getProperty("line.separator"));
		System.out.println("network saved");
	}
	
}
