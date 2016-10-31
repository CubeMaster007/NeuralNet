package ann;

import static main.Constants.*;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import exceptions.ExceptionIndexOutOfBounds;

/**
 * @author Adam Mendenhall
 */
public class NetworkWindow extends JPanel implements WindowListener {
	
	private JFrame frame;
	private Network network;
	private FileWriter fw;
	
	public NetworkWindow(Network network, Component locationRelativeTo) {
		this.network = network;
		
		try {
			fw = new FileWriter("netdat.txt");
			System.out.println("opened netdat.txt");
		} catch (IOException e) {
			System.err.println("error opening netdat.txt");
		}
		
		setPreferredSize(network.getPrefferedSize());
		
		frame = new JFrame("Neural Network");
		frame.setContentPane(this);
		frame.addWindowListener(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(locationRelativeTo);
		frame.setLocation(500, 0);
		frame.setVisible(true);
	}
	
	public NetworkWindow(Network network) {
		this(network, null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		setBackground(FRAME_BACKGROUND_COLOR);
		super.paintComponent(g);
		
		drawNet(network, g);
	}
	
	private void drawNet(Network net, Graphics g) {
		Rectangle bounds = g.getClipBounds();
		for (int i_l=0; i_l<net.numLayers(); i_l++) {
//			System.out.println("layer "+i_l);
			Layer layer = net.getLayer(i_l);
			for (int i_n=0; i_n<layer.getNeurons().size(); i_n++) {
//				System.out.println("neuron "+i_n);
				Neuron neuron = null;
				try {
				neuron = layer.getNeuron(i_n);
				}catch(ExceptionIndexOutOfBounds e){}
				
				int yPos = (int)(bounds.height/2 - NEURON_GAP_DIST * (i_n - (double)(layer.getNeurons().size())/2));
				neuron.setPos(new Point(i_l*LAYER_GAP_DIST + HORIZ_BUFFER + NEURON_RADIUS, yPos + NEURON_RADIUS));
			}
		}
		
		for (Layer l: net.getLayers()) {
			for (Neuron n: l.getNeurons()) {
				n.draw(g, Double.toString(n.observe()));
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			network.save(fw);
			System.out.println("saved netdat.txt");
		} catch (IOException e1) {
			System.err.println("error while saving network");
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
