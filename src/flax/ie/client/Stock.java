package flax.ie.client;

public class Stock {

	private int value;
	private String name;
	
	
	public Stock(int value, String name) {
		this.value = value;
		this.name = name;
	}


	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	
	
	
}
