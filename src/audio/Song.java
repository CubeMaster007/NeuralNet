package audio;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * @author Adam Mendenhall
 * 
 */
public class Song {
	
	private static Synthesizer synth;
	
	static {
		try {
			synth = MidiSystem.getSynthesizer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
			System.out.println("Kill me we have no synth");
			synth = null;
		}
	}
	
	public static void main(String[] args) {
		try {
			synth.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
			System.out.println("Kill me we can't open synth");
		}
		MidiChannel[] channels = synth.getChannels();
		
		double a = 0.5;
		for (double i=60; i<=120; i+=1) {
			channels[0].noteOn((int)i, 40);
			channels[1].noteOn((int)(i*a), 40);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Kill me the thread can't wait");
			}
			channels[0].noteOff((int)i);
			channels[1].noteOff((int)(i*a));
		}
	}
	
}

class Note {
	
	protected enum INTERPOLATION_MODE {
		LINEAR, SIGMOIDAL, QUADRATIC, INV_QUADRATIC;
	}
	
	private final INTERPOLATION_MODE MODE;
	private final long START_TIME, FINAL_TIME;
	private final int START_FREQ, END_FREQ;
	
	/**
	 * 
	 * @param beginFreq The initial frequency of the note
	 * @param endFreq The final frequency of the note
	 * @param length The length of the note in millis
	 */
	public Note(int beginFreq, int endFreq, int length) {
		START_FREQ = beginFreq;
		END_FREQ = endFreq;
		START_TIME = System.currentTimeMillis();
		FINAL_TIME = START_TIME + length;
		MODE = INTERPOLATION_MODE.LINEAR;
	}
	
	/**
	 * 
	 * @param beginFreq The initial frequency of the note
	 * @param endFreq The final frequency of the note
	 * @param length The length of the note in millis
	 * @param mode Describes how to interpolate between the start and end pitch
	 */
	public Note(int beginFreq, int endFreq, int length, INTERPOLATION_MODE mode) {
		START_FREQ = beginFreq;
		END_FREQ = endFreq;
		START_TIME = System.currentTimeMillis();
		FINAL_TIME = START_TIME + length;
		MODE = mode;
	}
	
}
