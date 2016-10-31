package ann;

/**
 * @author Adam Mendenhall
 */
public interface Junction {
	
	/**
	 * Forces the Junction to calculate a new output
	 * @return the output
	 */
	public abstract double grab();
	
	/**
	 * Gets the last output of the Junction, doesn't calculate a new one
	 * @return the last output
	 */
	public abstract double observe();
	
}
