package main;
import ann.Layer;
import ann.Network;
import ann.NetworkWindow;

/**
 * @author Adam Mendenhall
 */
public class Main{
	
	public static void main(String[] args) {
		
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		
//		Font[] fonts = ge.getAllFonts();
//		
//		for (int i=0; i<fonts.length; i++) {
//			System.out.println(fonts[i].getFontName());
//		}
		
		Network network = new Network();
		network.addLayer(new Layer(4, 0.5));
		network.addLayer(new Layer(network.getLastLayer(), 5));
		network.addLayer(new Layer(network.getLastLayer(), 2));
		network.addLayer(new Layer(network.getLastLayer(), 1));
		
		NetworkWindow window = new NetworkWindow(network);
		
		while(true) {
			network.pass();
			window.repaint();
			try {Thread.sleep(100);}
			catch (InterruptedException e) {e.printStackTrace();}
			network.backPass(new double[] {0.24});
			try {Thread.sleep(100);}
			catch (InterruptedException e) {e.printStackTrace();}
			window.repaint();
		}
	}
}
