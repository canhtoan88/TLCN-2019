package com.huupham.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.huupham.entities.Avatar;
import com.huupham.entities.City;
import com.huupham.entities.District;
import com.huupham.entities.Hostel;
import com.huupham.entities.Image;
import com.huupham.entities.Street;
import com.huupham.entities.User;
import com.huupham.entities.Video;
import com.huupham.models.Post;
import com.huupham.models.PostPriceComparator;
import com.huupham.models.PostRateAvgComparator;
import com.huupham.models.PostSpaceComparator;
import com.huupham.models.PostTimestampComparator;

@Repository
public class PostDao {

	public Post createMiniPostFromHostel(Hostel hostel) {
		// TODO Auto-generated method stub

		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		RateDao rateDao = new RateDao();

		if (hostel != null) {

			try {

				Post post = new Post();

				post.setHostel(hostel);

				if (userDao != null) {
					User user = userDao.getUserById(hostel.getUser().getId());
					if (user != null) {
						post.setUser(user);
					}
				}

				if (imageDao != null) {
					List<Image> images = imageDao.getImagesByIdHostel(hostel.getId());
					if (images.size() > 0) {
						post.setHostelAvatar(images.get(0));
					}
				}

				if (rateDao != null) {
					float rateAvg = rateDao.getRateAvgByIdHostel(hostel.getId());
					post.setRateAvg(rateAvg);
				}

				return post;

			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		} else {
			return null;
		}
	}

	public Post createFullPostFromHostel(Hostel hostel) {
		// TODO Auto-generated method stub

		HostelDao hostelDao = new HostelDao();
		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		RateDao rateDao = new RateDao();
		VideoDao videoDao = new VideoDao();
		AvatarDao avatarDao = new AvatarDao();
		CityDao cityDao = new CityDao();
		DistrictDao districtDao = new DistrictDao();
		StreetDao streetDao = new StreetDao();

		Hostel hostel2 = hostelDao.getHostelById(hostel.getId());

		if (hostel2 != null) {

			try {

				Post post = new Post();

				post.setHostel(hostel);

				User user = userDao.getUserById(hostel.getUser().getId());
				if (user != null) {
					post.setUser(user);
				}

				List<Image> images = imageDao.getImagesByIdHostel(hostel.getId());
				if (images.size() > 0) {
					post.setHostelAvatar(images.get(0));
					post.setImages(images);
				}

				float rateAvg = rateDao.getRateAvgByIdHostel(hostel.getId());
				post.setRateAvg(rateAvg);

				List<Video> videos = videoDao.getVideosByIdHostel(hostel.getId());
				if (videos.size() > 0) {
					post.setVideos(videos);
				}

				Avatar userAvatar = avatarDao.getAvatarByIdUser(hostel.getUser().getId());
				if (userAvatar != null) {
					post.setUserAvatar(userAvatar);
				}

				City city = cityDao.getCityById(hostel.getCity().getId());
				if (city != null) {
					post.setCity(city);
				}

				District district = districtDao.getDistrictById(hostel.getDistrict().getId());
				if (district != null) {
					post.setDistrict(district);
				}

				Street street = streetDao.getStreetById(hostel.getStreet().getId());
				if (street != null) {
					post.setStreet(street);
				}

				return post;
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		} else {
			return null;
		}
	}

	// Sap xep moi nhat
	public List<Post> reverseByTimestamp(List<Post> posts) {
		Collections.sort(posts, new PostTimestampComparator());
		Collections.reverse(posts);
		return posts;
	}

	// Sap xep pho bien nhat
	public List<Post> reverseByRateAvg(List<Post> posts) {
		Collections.sort(posts, new PostRateAvgComparator());
		Collections.reverse(posts);
		return posts;
	}

	// Sap xep gia tang dang
	public List<Post> sortByPrice(List<Post> posts) {
		Collections.sort(posts, new PostPriceComparator());
		return posts;
	}

	// Sap xep gia giam dan
	public List<Post> reverseByPrice(List<Post> posts) {
		Collections.sort(posts, new PostPriceComparator());
		Collections.reverse(posts);
		return posts;
	}

	// Sap xep dien tich tang dang
	public List<Post> sortBySpace(List<Post> posts) {
		Collections.sort(posts, new PostSpaceComparator());
		return posts;
	}

	// Sap xep dien tich giam dan
	public List<Post> reverseBySpace(List<Post> posts) {
		Collections.sort(posts, new PostSpaceComparator());
		Collections.reverse(posts);
		return posts;
	}

}
