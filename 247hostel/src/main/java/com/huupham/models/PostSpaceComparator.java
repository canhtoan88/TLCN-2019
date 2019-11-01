package com.huupham.models;

import java.util.Comparator;

public class PostSpaceComparator implements Comparator<Post> {

	public int compare(Post post1, Post post2) {
		// TODO Auto-generated method stub
		if (post1.getHostel().getSpace() == post2.getHostel().getSpace())
			return 0;
		else if (post1.getHostel().getSpace() > post2.getHostel().getSpace())
			return 1;
		else
			return -1;
	}

}
