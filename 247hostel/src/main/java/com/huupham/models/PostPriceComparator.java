package com.huupham.models;

import java.util.Comparator;

public class PostPriceComparator implements Comparator<Post> {

	public int compare(Post post1, Post post2) {
		// TODO Auto-generated method stub
		if (post1.getHostel().getPrice() == post2.getHostel().getPrice())
			return 0;
		else if (post1.getHostel().getPrice() > post2.getHostel().getPrice())
			return 1;
		else
			return -1;
	}

}
