package flax.ie.client;

import java.util.ArrayList;
import java.util.Date;

public class Twitter {

	private ArrayList<Tweet> hashTags = new ArrayList<Tweet>();
	
	/**
	 * adds hashtag and stories date
	 * @param hashTag
	 */
	public void addHashTag(String hashTag)
	{
		hashTags.add(new Tweet(hashTag, new Date().getYear()));
	}
	
	public int getSize()
	{
		return hashTags.size();
	}
	
	/**
	 * Removes hashTag with given index
	 * @param index
	 */
	public void removeHashTag(int index)
	{
		hashTags.remove(index);
	}
	
	/**
	 * Returns given index
	 * @param index
	 * @return
	 */
	public Tweet getTweet(int index)
	{
		return hashTags.get(index);
	}
}
