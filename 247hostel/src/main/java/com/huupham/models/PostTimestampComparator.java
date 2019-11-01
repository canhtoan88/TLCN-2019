package com.huupham.models;

import java.util.Comparator;

public class PostTimestampComparator implements Comparator<Post> {

	public int compare(Post post1, Post post2) {
		// TODO Auto-generated method stub
		return post1.getHostel().getTimestamp().compareTo(post1.getHostel().getTimestamp());
	}

}
