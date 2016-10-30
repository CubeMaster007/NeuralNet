package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public final class Constants {
	
	//DISPLAY CONSTANTS
	public static final int NEURON_RADIUS = 50;
	public static final int NEURON_GAP_DIST = (int)(NEURON_RADIUS*2.5);
	public static final int LAYER_GAP_DIST = (int)(NEURON_RADIUS*4);
	public static final int HORIZ_BUFFER = (int)(NEURON_RADIUS*1.5);
	public static final int VERT_BUFFER = (int)(NEURON_RADIUS*1.5);
	public static final int ARROW_LEN = 15;
	
	//FONT CONSTANTS
	public static final Font BIG_FONT = new Font("Courier New Bold", Font.BOLD, 32);
	public static final Font REG_FONT = new Font("Courier New", Font.PLAIN, 16);
	public static final DecimalFormat NUM_FORMAT = new DecimalFormat("####.###");
	
	//OTHER CONSTANTS
	public static final double DEF_SIGMOID_RANGE = 10;
	public static final double DEF_SIGMOID_SLOPE = 0.5;
	public static final double LEARNING_RATE = 50;
	
	//COLOR CONSTANTS
	public static final Color FRAME_BACKGROUND_COLOR = Color.WHITE;
	
	public static final Color GRID_NEURON_COLOR = Color.MAGENTA;
	public static final Color HIDDEN_NEURON_COLOR = Color.RED;
	public static final Color STATIC_NEURON_COLOR = Color.PINK;
	public static final Color SYNAPSE_COLOR = Color.ORANGE;
	public static final Color TEXT_COLOR = Color.BLACK;
	
}
