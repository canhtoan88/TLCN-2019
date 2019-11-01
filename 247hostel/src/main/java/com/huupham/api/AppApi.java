package com.huupham.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huupham.dao.AvatarDao;
import com.huupham.dao.CityDao;
import com.huupham.dao.DistrictDao;
import com.huupham.dao.HostelDao;
import com.huupham.dao.ImageDao;
import com.huupham.dao.PostDao;
import com.huupham.dao.RateDao;
import com.huupham.dao.StreetDao;
import com.huupham.dao.UserDao;
import com.huupham.dao.VideoDao;
import com.huupham.entities.City;
import com.huupham.entities.District;
import com.huupham.entities.Hostel;
import com.huupham.entities.Image;
import com.huupham.entities.Rate;
import com.huupham.entities.Street;
import com.huupham.entities.User;
import com.huupham.entities.Video;
import com.huupham.models.Post;
import com.huupham.models.RangePrice;
import com.huupham.models.RangeSpace;
import com.huupham.utilities.DistanceCaculator;
import com.huupham.utilities.UrlPercentEncodingFormat;

@Controller
@RequestMapping("api/")
public class AppApi {

	@Autowired
	CityDao cityDao;

	@Autowired
	DistrictDao districtDao;

	@Autowired
	StreetDao streetDao;

	@Autowired
	UserDao userDao;

	@Autowired
	AvatarDao avatarDao;

	@Autowired
	HostelDao hostelDao;

	@Autowired
	ImageDao imageDao;

	@Autowired
	VideoDao videoDao;

	@Autowired
	RateDao rateDao;

	@Autowired
	PostDao postDao;

	private RangePrice[] rangePrices = { new RangePrice(0, 2), new RangePrice(2, 3), new RangePrice(3, 4),
			new RangePrice(4, 6), new RangePrice(6, 10), new RangePrice(10, 999) };
	private RangeSpace[] rangeSpaces = { new RangeSpace(0, 20), new RangeSpace(20, 30), new RangeSpace(30, 40),
			new RangeSpace(40, 60), new RangeSpace(60, 100), new RangeSpace(100, 999) };

	@GetMapping
	@RequestMapping(value = "changeCity", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeCity(@RequestParam String idCity) {

//		System.out.println("idCity = " + idCity);

		String html = "<option value=\"0\">Quận/Huyện</option>";

		List<District> districts = districtDao.getDistrictsByIdCity(Integer.parseInt(idCity));

		// get Thu Duc district befo
		for (District district : districts) {
			if (district.getId() == 336) {
				html += "<option value=\"" + district.getId() + "\">" + district.getName() + "</option>";
				break;
			}
		}
		for (District district : districts) {
			html += "<option value=\"" + district.getId() + "\">" + district.getName() + "</option>";
		}

		return html;
	}

	@GetMapping
	@RequestMapping(value = "changeDistrict", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeDistrict(@RequestParam String idDistrict) {

//		System.out.println("idDistrict = " + idDistrict);

		String html = "<option value=\"0\">Tên đường</option>";

		List<Street> streets = streetDao.getStreetsByIdDistrict(Integer.parseInt(idDistrict));
		for (Street street : streets) {
			html += "<option value=\"" + street.getId() + "\">" + street.getName() + "</option>";
		}

		return html;
	}

	// get html string from posts
	public String getHtmlStringFromPosts(List<Post> posts) {

		String html = "<div class=\"container\">";

		if (posts.size() == 0) {
			html += "<div class=\"field-results-none-item\"><h4>Không tìm thấy kết quả phù hợp</h4></div>";
		} else {
			for (Post post : posts) {
				html += "<div class=\"field-results-item\">";
				html += "<a href=\"hostel-detail/" + post.getHostel().getId() + "\" target=\"_blank\">";
				html += "<div class=\"field-results-item-img\">";
				html += "<img src=\"" + post.getHostelAvatar().getUrl() + "\" alt=\"\" />";
				html += "</div>";
				html += "</a>";
				html += "<div class=\"field-results-item-desc\">";
				html += "<div class=\"field-results-item-desc-title\">";
				html += "<a href=\"hostel-detail/" + post.getHostel().getId() + "\" target=\"_blank\">"
						+ post.getHostel().getTitle() + "</a>";
				html += "</div>";
				html += "<div>";
				html += "<span class=\"field-results-item-desc-price\"> Giá: " + post.getPriceHostel() + " tr/tháng\r\n"
						+ "</span>";
				html += "<span class=\"field-results-item-desc-space\">" + post.getHostel().getSpace() + "/m2</span>";
				html += "<span class=\"field-results-item-desc-time-up\">" + post.getTimestamp() + "</span>";
				html += "</div>";
				html += "<div class=\"field-results-item-desc-sub-desc\">" + post.getHostel().getDescription()
						+ "</div>";
				html += "<div>";
				html += "<span class=\"field-results-item-desc-rate\">";

				// rate avg
				for (int i = 0; i < post.getRateAvg(); i++) {
					html += "<img alt=\"\" src=\"resources/icons/star_liked.svg\" />";
				}
				for (int j = 0; j < 5 - post.getRateAvg(); j++) {
					html += "<img alt=\"\" src=\"resources/icons/star_not_liked.svg\" />";
				}

				html += "</span>";
				html += "<span class=\"field-results-item-desc-contact\">";
				html += "<img alt=\"\" src=\"resources/icons/phone_color.svg\" />";
				html += "<span class=\"field-results-item-desc-contact-phone\"><a href=\"tel:"
						+ post.getUser().getPhone() + "\" data-phone=\"" + post.getUser().getPhone() + "\">"
						+ post.getUser().getPhone() + "</a></span>";
				html += "</span>";
				html += "</div>";
				html += "</div>";
				html += "<hr class=\"field-results-item-hr\" />";
				html += "</div>";
			}
		}

		html += "</div>";

		return html;
	}

	@GetMapping
	@RequestMapping(value = "searchHostels", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String searchHostels(HttpServletRequest request, @RequestParam String idCity,
			@RequestParam String idDistrict, @RequestParam String idStreet, @RequestParam String idRangePrice,
			@RequestParam String idRangeSpace) {

		System.out.println("idCity=" + idCity + "&idDistrict=" + idDistrict + "&idStreet=" + idStreet + "&idRangePrice="
				+ idRangePrice + "&idRangeSpace=" + idRangeSpace);

		int intIdCity = Integer.parseInt(idCity);
		int intIdDistrict = Integer.parseInt(idDistrict);
		int intIdStreet = Integer.parseInt(idStreet);
		int intIdRangePrice = Integer.parseInt(idRangePrice);
		int intIdRangeSpace = Integer.parseInt(idRangeSpace);

		RangePrice rangePrice;
		if (intIdRangePrice == -1) {
			rangePrice = null;
		} else {
			rangePrice = rangePrices[intIdRangePrice];
		}
		RangeSpace rangeSpace;
		if (intIdRangeSpace == -1) {
			rangeSpace = null;
		} else {
			rangeSpace = rangeSpaces[intIdRangeSpace];
		}

		List<Hostel> hostels = hostelDao.searchHostels(intIdCity, intIdDistrict, intIdStreet, rangePrice, rangeSpace, 1,
				10);
		List<Post> posts = new ArrayList<Post>();
		for (Hostel hostel : hostels) {

			Post post = postDao.createMiniPostFromHostel(hostel);
			posts.add(post);
		}

		HttpSession session = request.getSession();
//		session.invalidate();
		session.setAttribute("posts", posts);

		return getHtmlStringFromPosts(posts);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping
	@RequestMapping(value = "resultsDropdownFilterChange", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String resultsDropdownFilterChange(HttpServletRequest request, @RequestParam String value) {

//		System.out.println("value = " + value);

		int filterValue = 0;
		try {
			filterValue = Integer.parseInt(value);
		} catch (Exception e) {
			filterValue = 0;
		}

		HttpSession session = request.getSession();
//		session.invalidate();

		List<Post> posts = (List<Post>) session.getAttribute("posts");
		List<Post> posts2 = new ArrayList();

		switch (filterValue) {
		case 0:
			// sap xep moi nhat
			posts2 = postDao.reverseByTimestamp(posts);
			break;
		case 1:
			// sap xep pho bien nhat
			posts2 = postDao.reverseByRateAvg(posts);
			break;
		case 2:
			// sap xep gia tang dan
			posts2 = postDao.sortByPrice(posts);
			break;
		case 3:
			// sap xep gia giam dan
			posts2 = postDao.reverseByPrice(posts);
			break;
		case 4:
			// sawp xep dien tich tang dan
			posts2 = postDao.sortBySpace(posts);
			break;
		case 5:
			// sap xep dien tich giam dan
			posts2 = postDao.reverseBySpace(posts);
			break;
		case 6:
			// sap xep ban kinh tang dan
			posts2 = postDao.reverseByTimestamp(posts);
			break;
		case 7:
			// sap xep ban kinh giam gian
			posts2 = postDao.reverseByTimestamp(posts);
			break;
		}

		return getHtmlStringFromPosts(posts2);
	}

	@GetMapping
	@RequestMapping("getUrlLocation")
	@ResponseBody
	public String getUrlLocation(@RequestParam String idCity, @RequestParam String idDistrict,
			@RequestParam String idStreet, @RequestParam String houseNumber) {

		//
//		System.out.println(idCity +"-"+ idDistrict +"-"+ idStreet +"-"+ houseNumber);

		String cityStr = "";
		String districtStr = "";
		String streetStr = "";

		int zoom = 10;

		if (!idCity.equals("")) {
			City city = cityDao.getCityById(Integer.parseInt(idCity));
			cityStr += city.getName();
			zoom = 10;
		}
		if (!idDistrict.equals("")) {
			District district = districtDao.getDistrictById(Integer.parseInt(idDistrict));
			districtStr += district.getName();
			zoom = 15;
		}
		if (!idStreet.equals("")) {
			Street street = streetDao.getStreetById(Integer.parseInt(idStreet));
			streetStr += street.getName();
			zoom = 15;
		}
		if (!houseNumber.equals("")) {
			zoom = 18;
		}

		String parameter = houseNumber + " " + streetStr + " " + districtStr + " " + cityStr;

		//
		System.out.println(parameter);

		String parameterFormat = UrlPercentEncodingFormat.createUrlPercentEncodingFormat(parameter);

		String url = "<iframe class=\"map-iframe\" id=\"\" src=\"https://maps.google.com/maps?q=" + parameterFormat
				+ "&t=&z=" + zoom
				+ "&ie=UTF8&iwloc=&output=embed\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\"></iframe>";

		System.out.println(url);
		
		return url;
	}

	@GetMapping
	@RequestMapping(value = "changeRentedStatus", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeRentedStatus(@RequestParam String idHostel) {

		//
//		System.out.println("idHostel = " + idHostel);

		Hostel hostel = hostelDao.getHostelById(Integer.parseInt(idHostel));
		if (hostel.isIsRented()) {
			hostel.setIsRented(false);
		} else {
			hostel.setIsRented(true);
		}

		String html = "";

		Hostel hostelUpdate = hostelDao.updateHostel(hostel);
		if (hostelUpdate != null) { // update rented status success

			if (hostelUpdate.isIsRented()) {

//				html += "<span class=\"isRented isRented-True\">Đã cho thuê</span>";
				html += "true";
			} else {

				html += "<span  class=\"isRented isRented-False\">Chưa cho thuê</span>";
				html += "false";
			}
		}

		return html;
	}

	@GetMapping
	@RequestMapping(value = "deleteHostel", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteHouse(@RequestParam String idHostel, HttpServletRequest request) {

		//
//		System.out.println("idHostel = " + idHostel);

		Hostel hostel = hostelDao.getHostelById(Integer.parseInt(idHostel));

		// Delete videos
		List<Video> videos = videoDao.getVideosByIdHostel(hostel.getId());
		for (Video video : videos) {

			boolean b = videoDao.deleteVideo(video);
			if (!b) { // Delete from database fail

				//
				System.out.println("Delete video from database fail");

				return "false";
			} else {

				//
				System.out.println("Delete video from database success");
			}
		}

		// Delete images
		List<Image> images = imageDao.getImagesByIdHostel(hostel.getId());
		for (Image image : images) {

			boolean b = imageDao.deleteImage(image);
			if (!b) { // Delele from database fail

				//
				System.out.println("Delete image from database fail");

				return "false";
			} else {

				//
				System.out.println("Delete image from database success");
			}
		}

		// Delete rates
		List<Rate> rates = rateDao.getRatesByIdHostel(hostel.getId());
		for (Rate rate : rates) {

			boolean b = rateDao.deleteRate(rate);
			if (!b) { // Delele from database fail

				//
				System.out.println("Delete rate from database fail");

				return "false";
			} else {

				//
				System.out.println("Delete rate from database success");
			}
		}

		// Delete reply_comment
		// Delete comments
		// Delete vip_hostel

		// Delete hostel
		boolean b = hostelDao.deleteHostel(hostel);
		if (!b) { // Delete hostel fail

			//
			System.out.println("Delete hostel fail");

			return "false";
		} else {

			//
			System.out.println("Delete hostel success");

			return "true";
		}
	}

	@GetMapping
	@RequestMapping(value = "userGetHostels", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String searchHouse(@RequestParam String page, HttpServletRequest request) {

		//
//		System.out.println("page = " + page);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		List<Hostel> hostels = hostelDao.getHostelsByIdUser(user.getId(), Integer.parseInt(page), 10);

		String html = "";

		for (Hostel hostel : hostels) {

			html += "<tr>";
			html += "<td class=\"text-center\">" + hostel.getId() + "</td>";
			html += "<td class=\"td-title\" style=\"max-width: 250px !important;\">";
			html += "<a href=\"house-detail/" + hostel.getId() + "\" target=\"_blank\">" + hostel.getTitle() + "</a>";
			html += "</td>";
			html += "<td class=\"text-center\">" + hostel.getTimestamp() + "</td>";

			switch (hostel.getIsCensored()) {

			case -1:
				html += "<td class=\"text-center\" style=\"color: red;\">Không được phê duyệt</td>";
				break;

			case 1:
				html += "<td class=\"text-center\" style=\"color: blue;\">Đã phê duyệt</td>";
				break;

			default:
				html += "<td class=\"text-center\">Đang đợi phê duyệt..</td>";
				break;
			}

			html += "<td class=\"text-center\">";
			html += "<span id=\"" + hostel.getId() + "\" class=\"span-click\" data-value=\"" + hostel.getId() + "\">";
			if (hostel.isIsRented()) {
				html += "<span class=\"isRented isRented-True\" style=\"padding: 5px 10px;\">Đã cho thuê</span>";
			} else {
				html += "<span class=\"isRented isRented-False\" style=\"padding: 5px 10px;\">Chưa cho thuê</span>";
			}
			html += "</span>";
			html += "</td>";

			html += "<td class=\"text-center\">";
			html += "<span class=\"i-button i-button-edit\" id=\"\">";
			html += "<a href=\"post-edit/" + hostel.getId()
					+ "\" target=\"_blank\"><i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i></a>";
			html += "</span>";
			html += "</td>";

			html += "<td class=\"text-center\">";
			html += "<span class=\"i-button i-button-edit\" id=\"\">";
			html += "<i class=\"fa fa-trash-o fa-trash-o-click\" aria-hidden=\"true\" data-value=\"" + hostel.getId()
					+ "\"></i>";
			html += "</span>";
			html += "</td>";
			html += "</tr>";
		}

		return html;
	}

	@GetMapping
	@RequestMapping(value = "getHostelAddress", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getHostelAddress(@RequestParam String idHostel) {

		int intIdHostel = Integer.parseInt(idHostel);

		Hostel hostel = hostelDao.getHostelById(intIdHostel);
		if (hostel != null && hostel.getLocationLat() == 0 || hostel.getLocationLat() == -1) {
			City city = cityDao.getCityById(hostel.getCity().getId());
			District district = districtDao.getDistrictById(hostel.getDistrict().getId());
			Street street = streetDao.getStreetById(hostel.getStreet().getId());

			String address = hostel.getHostelNumber() + " " + street.getName() + ", " + district.getName() + ", "
					+ city.getName();

			return address;
		} else {
			return "";
		}
	}

	@GetMapping
	@RequestMapping(value = "updateHostelLocation", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String updateHostelLocation(@RequestParam String idHostel, @RequestParam String lat,
			@RequestParam String lng) {

		int intIdHostel = Integer.parseInt(idHostel);

		Hostel hostel = hostelDao.getHostelById(intIdHostel);
		if (hostel != null) {

			float fLat = Float.parseFloat(lat);
			float fLng = Float.parseFloat(lng);

			hostel.setLocationLat(fLat);
			hostel.setLocationLng(fLng);

			Hostel hostelUpdate = hostelDao.updateHostel(hostel);
			if (hostelUpdate != null) {
				return "true";
			}

			return "false";
		} else {
			return "false";
		}
	}

	@SuppressWarnings("null")
	@GetMapping
	@RequestMapping(value = "getHostelsNearLocation", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getHostelsNearLocation(@RequestParam String lat, @RequestParam String lng,
			@RequestParam String idRangePrice, @RequestParam String idRangeSpace, @RequestParam String distance) {

		System.out.println("lat=" + lat + "&lng=" + lng + "&idRangePrice=" + idRangePrice + "&idRangeSpace="
				+ idRangeSpace + "&distance=" + distance);

		double doubleLat = Double.parseDouble(lat);
		double doubleLng = Double.parseDouble(lng);
		int intIdRangePrice = Integer.parseInt(idRangePrice);
		int intIdRangeSpace = Integer.parseInt(idRangeSpace);
		double doubleDistance = Double.parseDouble(distance) * 0.85; // meter

		RangePrice rangePrice;
		if (intIdRangePrice == -1) {
			rangePrice = null;
		} else {
			rangePrice = rangePrices[intIdRangePrice];
		}
		RangeSpace rangeSpace;
		if (intIdRangeSpace == -1) {
			rangeSpace = null;
		} else {
			rangeSpace = rangeSpaces[intIdRangeSpace];
		}

		List<Hostel> hostels = new ArrayList<Hostel>();

		boolean flag = true;
		int page = 1;
		int count = 10;
		while (hostels.size() < 50 && flag) {

			List<Hostel> hostels2 = hostelDao.searchHostels2(rangePrice, rangeSpace, page, count);
			if (hostels2 == null || hostels2.size() == 0) {
				flag = false;
			} else {
				for (int i = 0; i < hostels2.size(); i++) {

					if (hostels2.get(i).getLocationLat() != -1 && hostels2.get(i).getLocationLng() != -1) {

						double distance2 = DistanceCaculator.getDistanceTransferMeter(doubleLat, doubleLng,
								hostels2.get(i).getLocationLat(), hostels2.get(i).getLocationLng());

						if (distance2 < doubleDistance) {

							hostels.add(hostels2.get(i));
						}
					}
				}

				page++;

			}
		}

		System.out.println("hostels.sive()=" + hostels.size());

		String strPositions = "";
		for (Hostel hostel : hostels) {
			strPositions += hostel.getLocationLat() + "-" + hostel.getLocationLng() + "-" + hostel.getId() + ";";
		}

		return strPositions;
	}

	@GetMapping
	@RequestMapping(value = "getMarkerPopupContent", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getMarkerPopupContent(@RequestParam String idHostel) {

		int intIdHostel = Integer.parseInt(idHostel);

		Hostel hostel = hostelDao.getHostelById(intIdHostel);

		String html = "";
		if (hostel != null) {

			List<Image> images = imageDao.getImagesByIdHostel(hostel.getId());
			Image image = null;
			if (images.size() > 0) {
				image = images.get(0);
			}

			float rateAvg = rateDao.getRateAvgByIdHostel(hostel.getId());

			html += "<div class=\"marker-popup-content\">";
			html += "<a href=\"hostel-detail/" + hostel.getId() + "\" target=\"_blank\">";
			html += "<div>";
			html += "<div class=\"marker-popup-content-img\">";
			html += "<img alt=\"\" src=\"" + image.getUrl() + "\">";
			html += "</div>";
			html += "<div class=\"marker-popup-content-text\">";
			html += "<div class=\"marker-popup-content-text-title\">" + hostel.getTitle() + "</div>";
			html += "<div class=\"marker-popup-content-text-div\"><span class=\"marker-popup-content-text-price\">"
					+ hostel.getPrice() + " tr/tháng</span></div>";
			html += "<div class=\"marker-popup-content-text-div\"><span class=\"marker-popup-content-text-space\">"
					+ hostel.getSpace() + " m2</span></div>";
			html += "<div class=\"marker-popup-content-rate\">";

			// rate avg
			for (int i = 0; i < rateAvg; i++) {
				html += "<img alt=\"\" src=\"resources/icons/star_liked.svg\" />";
			}
			for (int j = 0; j < 5 - rateAvg; j++) {
				html += "<img alt=\"\" src=\"resources/icons/star_not_liked.svg\" />";
			}

			html += "<span class=\"marker-popup-content-show-detail\">Xem chi tiết..</span>";
			html += "</div></div></div></a></div>";
		}

		return html;
	}

}
