package flax.ie.client;

import com.google.gwt.user.client.Random;

public class Tweet {

	private String hashTag;
	private int time;
	
		
	public Tweet(String hashTag, int time) {
		super();
		this.hashTag = hashTag;
		this.time = time;
	}
	
	
	public String getHashTag() {
		return hashTag;
	}
	public int getTime() {
		return Random.nextInt();
	}
	
	
	
}
