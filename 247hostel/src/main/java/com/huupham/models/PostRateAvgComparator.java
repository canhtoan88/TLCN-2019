package com.huupham.models;

import java.util.Comparator;

public class PostRateAvgComparator implements Comparator<Post> {

	public int compare(Post post1, Post post2) {
		// TODO Auto-generated method stub
		if (post1.getRateAvg() == post2.getRateAvg())
			return 0;
		else if (post1.getRateAvg() > post2.getRateAvg())
			return 1;
		else
			return -1;
	}

}
