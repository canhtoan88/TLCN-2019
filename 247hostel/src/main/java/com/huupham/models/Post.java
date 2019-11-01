package com.huupham.models;

import java.util.Calendar;
import java.util.List;

import com.huupham.entities.Avatar;
import com.huupham.entities.City;
import com.huupham.entities.District;
import com.huupham.entities.Hostel;
import com.huupham.entities.Image;
import com.huupham.entities.Street;
import com.huupham.entities.User;
import com.huupham.entities.Video;

public class Post {

	private Hostel hostel;
	private User user;
	private Image hostelAvatar;
	private float rateAvg;
	private List<Image> images;
	private List<Video> videos;
	private Avatar userAvatar;
	private City city;
	private District district;
	private Street street;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Hostel hostel, User user, Image hostelAvatar, int rateAvg, List<Image> images, List<Video> videos,
			Avatar userAvatar, City city, District district, Street street) {
		super();
		this.hostel = hostel;
		this.user = user;
		this.hostelAvatar = hostelAvatar;
		this.rateAvg = rateAvg;
		this.images = images;
		this.videos = videos;
		this.userAvatar = userAvatar;
		this.city = city;
		this.district = district;
		this.street = street;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Image getHostelAvatar() {
		return hostelAvatar;
	}

	public void setHostelAvatar(Image hostelAvatar) {
		this.hostelAvatar = hostelAvatar;
	}

	public float getRateAvg() {
		return rateAvg;
	}

	public void setRateAvg(float rateAvg2) {
		this.rateAvg = rateAvg2;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Avatar getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(Avatar userAvatar) {
		this.userAvatar = userAvatar;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public double getPriceHostel() {
		return Math.round(hostel.getPrice() / 10000) / 100.0;
	}

	public String getTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(hostel.getTimestamp());

		String strCalendar = String.valueOf(calendar.get(Calendar.YEAR)) + "-"
				+ String.valueOf(calendar.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.get(Calendar.DATE));

		return strCalendar;
	}

}
