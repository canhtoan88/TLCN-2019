package com.huupham.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huupham.dao.AuthorizationDao;
import com.huupham.dao.AvatarDao;
import com.huupham.dao.CityDao;
import com.huupham.dao.CommentDao;
import com.huupham.dao.DepartmentDao;
import com.huupham.dao.DistrictDao;
import com.huupham.dao.EmployeeDao;
import com.huupham.dao.HostelDao;
import com.huupham.dao.ImageDao;
import com.huupham.dao.PostDao;
import com.huupham.dao.RateDao;
import com.huupham.dao.StreetDao;
import com.huupham.dao.UserDao;
import com.huupham.dao.VideoDao;
import com.huupham.entities.Authorization;
import com.huupham.entities.Avatar;
import com.huupham.entities.City;
import com.huupham.entities.Department;
import com.huupham.entities.District;
import com.huupham.entities.Employee;
import com.huupham.entities.Hostel;
import com.huupham.entities.Image;
import com.huupham.entities.Rate;
import com.huupham.entities.Street;
import com.huupham.entities.User;
import com.huupham.entities.Video;
import com.huupham.models.HostelRangePriceFull;
import com.huupham.models.Post;
import com.huupham.utilities.MyUploadFile;
import com.huupham.utilities.PasswordEncodeMD5;

@Controller
public class AppController {

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
	CommentDao commentDao;

	@Autowired
	PostDao postDao;

	@Autowired
	AuthorizationDao authorizationDao;

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	DepartmentDao departmentDao;

	@GetMapping
	@RequestMapping("/")
	public String getDefault(HttpServletRequest request, ModelMap modelMap) {

		List<City> cities = cityDao.getCities();

		// get Ho Chi Minh city before
		List<City> cities2 = new ArrayList<City>();
		for (City city : cities) {
			if (city.getId() == 29) {
				cities2.add(city);
			}
		}
		for (City city : cities) {
			cities2.add(city);
		}

		// Get new hostels
		List<Hostel> hostels = hostelDao.getNewHostels_IsCensored(1, 10);
		List<Post> posts = new ArrayList<Post>();
		for (Hostel hostel : hostels) {

			Post post = postDao.createMiniPostFromHostel(hostel);
			posts.add(post);
		}

		HttpSession session = request.getSession();
//		session.invalidate();
		session.setAttribute("posts", posts);

		modelMap.addAttribute("cities", cities2);
		return "home";
	}

	@GetMapping
	@RequestMapping("home")
	public String getHome() {

		return "redirect:/";
	}

	@GetMapping
	@RequestMapping("backHome")
	public String backHome() {

		return "redirect:/";
	}

	@GetMapping
	@RequestMapping("sign-in")
	public String getSignIn() {

		return "sign-in";
	}

	@PostMapping
	@RequestMapping("signIn")
	public String postSignIn(@RequestParam String username, @RequestParam String password, ModelMap modelMap,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
//		session.invalidate();

		String passwordEncodeMd5 = PasswordEncodeMD5.createPasswordEncodeMD5(password);

		User user = userDao.checkLogin(username, passwordEncodeMd5);
		if (user != null) { // Login success

			Avatar avatar = avatarDao.getAvatarByIdUser(user.getId());

			session.setAttribute("user", user);
			session.setAttribute("avatar", avatar);

			return "redirect:/";
		} else { // Login fail

			session.invalidate();
			modelMap.addAttribute("message", "Tài khoản hoặc mật khẩu chưa đúng. Đăng nhập lại!");
			return "sign-in";
		}
	}

	@GetMapping
	@RequestMapping("signOut")
	public String getSignOut(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping
	@RequestMapping("admin/signOut")
	public String getAdminSignOut(HttpServletRequest request) {

		return getSignOut(request);
	}

	@GetMapping
	@RequestMapping("sign-up")
	public String getSignUp() {

		return "sign-up";
	}

	@PostMapping
	@RequestMapping(value = "signUp", produces = "text/plain;charset=UTF-8")
	public String postSignUp(@ModelAttribute User user, @RequestParam String confirmPassword, ModelMap modelMap,
			HttpServletRequest request) {

//		System.out
//				.println(user.getFullname() + "|" + user.getEmail() + "|" + user.getPhone() + "|" + user.getPassword());
//		System.out.println(confirmPassword);

		// Check email and phone empty
		if (user.getEmail().trim().equals("") && user.getPhone().trim().equals("")) {

			modelMap.addAttribute("user", user);
			modelMap.addAttribute("confirmPassword", confirmPassword);
			modelMap.addAttribute("message", "Bạn cần cung cấp Email hoặc Số điện thoại để đăng ký.");
			return "sign-up";
		}

		// Check email exists
		if (user.getEmail() != null && !user.getEmail().trim().equals("")
				&& userDao.checkExistsEmail(user.getEmail())) {

			user.setEmail("");
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("confirmPassword", confirmPassword);
			modelMap.addAttribute("message", "Email đã tồn tại!");
			return "sign-up";
		}

		// Phone exists
		if (user.getPhone() != null && !user.getPhone().trim().equals("")
				&& userDao.checkExistsPhone(user.getPhone())) {

			user.setPhone("");
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("confirmPassword", confirmPassword);
			modelMap.addAttribute("message", "Số điện thoại đã tồn tại!");
			return "sign-up";
		}

		// Check password length (>=6 charactor)
		if (user.getPassword().length() < 6) {

			user.setPassword("");
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("confirmPassword", "");
			modelMap.addAttribute("message", "Mật khẩu tối thiểu 6 ký tự!");
			return "sign-up";
		}

		// Password not confirm
		if (!user.getPassword().equals(confirmPassword)) {

			user.setPassword("");
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("confirmPassword", "");
			modelMap.addAttribute("message", "Mật khẩu không trùng khớp!");
			return "sign-up";
		}

		// Encode user password
		String passwordEncodeMd5 = PasswordEncodeMD5.createPasswordEncodeMD5(user.getPassword());
		user.setPassword(passwordEncodeMd5);

		user.setTimeRegister(new Date());
		user.setGender(false);
		user.setAddress("");

		// Get "User" authorization
		Authorization authorization = authorizationDao.getAuthorizationById(3);
		user.setAuthorization(authorization);

		User userSave = userDao.saveUser(user);
		if (userSave != null) { // Insert success

			// Create default avatar
			Avatar avatar = new Avatar();
			avatar.setUser(userSave);
			avatar.setUrl("resources/icons/user_color.svg");
			Avatar avatarSave = avatarDao.saveAvatar(avatar);

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("avatar", avatarSave);

			return "redirect:/";
		} else { // Insert fail

			modelMap.addAttribute("message", "Đăng ký thất bại. Thử lại!");
			return "sign-up";
		}
	}

	@GetMapping
	@RequestMapping("hostel-detail/{id}")
	public String getHouseDetail(@PathVariable int id, ModelMap modelMap, HttpServletRequest request) {

		Hostel hostel = hostelDao.getHostelById(id);
		if (hostel != null) {

			Post post = postDao.createFullPostFromHostel(hostel);
			List<District> districts = districtDao.getDistrictsByIdCity(hostel.getCity().getId());

			int rateCount = 0;

			HttpSession session = request.getSession();
			User userSession = (User) session.getAttribute("user");
			if (userSession != null) {

				Rate rate = rateDao.getRateByIdUserAndIdHostel(userSession.getId(), hostel.getId());
				if (rate != null) {

					rateCount = rate.getRate();
				} else {

					rateCount = 0;
				}
			} else {

				rateCount = 0;
			}

			modelMap.addAttribute("post", post);
			modelMap.addAttribute("districts", districts);
			modelMap.addAttribute("rate", rateCount);
			return "hostel-detail";
		} else {

			return "redirect:/";
		}
	}

	@GetMapping
	@RequestMapping("user-info")
	public String getUserInfo(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			User user = userDao.getUserById(userSession.getId());
			switch (user.getAuthorization().getId()) {
			case 3: // user

				int countHouse = hostelDao.getCountHostelByIdUser(user.getId());
				int countHouseIsRented = hostelDao.getCountHostelIsRentedByIdUser(user.getId());
				int countHouseIsNotRented = hostelDao.getCountHostelIsNotRentedByIdUser(user.getId());
				int countHouseIsCensored = hostelDao.getCountHostelIsCensoredByIdUser(user.getId());
				int countHouseIsWaittingCensored = hostelDao.getCountHostelIsWaittingCensorByIdUser(user.getId());
				int countHouseIsNotCensored = hostelDao.getCountHostelIsNotCensoredByIdUser(user.getId());

				modelMap.addAttribute("countHouse", countHouse);
				modelMap.addAttribute("countHouseIsRented", countHouseIsRented);
				modelMap.addAttribute("countHouseIsNotRented", countHouseIsNotRented);
				modelMap.addAttribute("countHouseIsCensored", countHouseIsCensored);
				modelMap.addAttribute("countHouseIsWaittingCensored", countHouseIsWaittingCensored);
				modelMap.addAttribute("countHouseIsNotCensored", countHouseIsNotCensored);

				return "user-info";

			case 2: // employee

//				Employee employee = employeeDao.getEmployeeByUser(user);
//				Department department = departmentDao.getDepartmentById(employee.getDepartment().getId());
//				
//				modelMap.addAttribute("employee", employee);
//				modelMap.addAttribute("department", department);
//				
//				
//				return "admin-user-profile";

			case 1: // admin

//					Admin admin = new Admin();
//					admin.setUser(user);
//					modelMap.addAttribute("admin", admin);
//					return "admin-info";

			default:

				session.invalidate();
				return "redirect:/sign-in";
			}
		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@PostMapping
	@RequestMapping("updateUserInfo")
	public String postUpdateInfo(@RequestParam String fullname, @RequestParam String address,
			@RequestParam String birthday, @RequestParam String gender, HttpServletRequest request, ModelMap modelMap) {

//		System.out.println("fullname: " + fullname);
//		System.out.println("address: " + address);
//		System.out.println("birthday: " + birthday);
//		System.out.println("gender: " + gender);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		User user2 = userDao.getUserById(user.getId());

		if (fullname != null && !user2.getFullname().equals(fullname.trim())) {
			user2.setFullname(fullname);
		}

		if (user2.getAddress() == null
				|| address != null && user2.getAddress() != null && !user2.getAddress().equals(address.trim())) {
			user2.setAddress(address);
		}

		if (!birthday.equals("")) {
			Date newBirthday;
			try {
				newBirthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
//				System.out.println("new birthday: " + newBirthday);
				if (user2.getBirthday() == null
						|| user2.getBirthday() != null && user2.getBirthday().compareTo(newBirthday) != 0) {
					user2.setBirthday(newBirthday);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!(user2.isGender() ? "male" : "female").equals(gender)) {
			user2.setGender((gender.equals("male") ? true : false));
		}

//		System.out.println("new user: " + userDao.getUserInfo(user2));

		User userUpdate = userDao.updateUser(user2);
		if (userUpdate != null) {

			session.setAttribute("user", userUpdate);
			modelMap.addAttribute("message", "Cập nhập thông tin thành công!");
			return "redirect:/user-info";
		} else {

			modelMap.addAttribute("message", "Cập nhập thông tin thất bại!");
			return "user-info";
		}
	}

	@GetMapping
	@RequestMapping("user-manage-post")
	public String getUserManagePost(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");

		if (userSession != null) {

			List<Hostel> hostels = hostelDao.getHostelsByIdUser(userSession.getId(), 1, 10);

			modelMap.addAttribute("hostels", hostels);
			return "user-manage-post";

		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("post")
	public String getPost(ModelMap modelMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			List<City> cities = cityDao.getCities();

			modelMap.addAttribute("cities", cities);
			return "post";
		} else {

			session.invalidate();
			return "sign-in";
		}
	}

	@Autowired
	ServletContext context;

	@PostMapping
	@RequestMapping("postUpload")
	public String postPost(@RequestParam String idCity, @RequestParam String idDistrict, @RequestParam String idStreet,
			@ModelAttribute Hostel hostel, @RequestParam MultipartFile[] imagesFile,
			@RequestParam MultipartFile[] videosFile, ModelMap modelMap, HttpServletRequest request) {

//		System.out.println("idCity: " + idCity);
//		System.out.println("idDistrict: " + idDistrict);
//		System.out.println("idStreet : " + idStreet);
//		System.out.println("hostelNumber = " + hostel.getHostelNumber());
//		System.out.println("price = " + hostel.getPrice());
//		System.out.println("space = " + hostel.getSpace());
//		System.out.println("title = " + hostel.getTitle());
//		System.out.println("description = " + hostel.getDescription());
//		for (MultipartFile imageFile : imagesFile) {
//			System.out.println("image name = " + imageFile.getOriginalFilename());
//		}
//		for (MultipartFile videoFile : videosFile) {
//			System.out.println("video name = " + videoFile.getOriginalFilename());
//		}

		City city = cityDao.getCityById(Integer.parseInt(idCity));
		District district = districtDao.getDistrictById(Integer.parseInt(idDistrict));
		Street street = streetDao.getStreetById(Integer.parseInt(idStreet));

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		User user = userDao.getUserById(userSession.getId());

		hostel.setCity(city);
		hostel.setDistrict(district);
		hostel.setStreet(street);
		hostel.setUser(user);
		hostel.setIsRented(false);
		hostel.setIsCensored(0);
		hostel.setTimestamp(new Date());

		// Insert hostel
		Hostel hostelSave = hostelDao.saveHostel(hostel);
		if (hostelSave != null) {

			System.out.println("Insert hostel success");

			// Upload images
			try {

				for (int i = 0; i < 10; i++) {

					MultipartFile mFile = imagesFile[i];

					String url = MyUploadFile.uploadFile(request, mFile, "image");

					if (url.equals("")) { // upload file fail

						//
						System.out.println("Upload file fail!");
					} else { // upload file success

						//
						System.out.println("Upload file success!");

						// Insert image
						Image image = new Image();
						image.setHostel(hostelSave);
						image.setUrl(url);

						Image imageSave = imageDao.saveImage(image);
						if (imageSave != null) {

							System.out.println("Insert image success");
						} else {
							System.out.println("Insert image fail");
						}
					}
				}
			} catch (Exception e) {

				//
				System.out.println("Error!!!");
			}

			// Upload videos
			try {

				for (MultipartFile mFile : videosFile) {

					String url = MyUploadFile.uploadFile(request, mFile, "video");

					if (url.equals("")) { // upload file fail

						//
						System.out.println("Upload file fail!");
					} else { // upload file success

						//
						System.out.println("Upload file success!");

						// Insert image
						Video video = new Video();
						video.setHostel(hostelSave);
						video.setUrl(url);

						Video videoSave = videoDao.saveVideo(video);
						if (videoSave != null) {

							//
							System.out.println("Insert video success");
						} else {

							//
							System.out.println("Insert video fail");
						}
					}
				}
			} catch (Exception e) {

				//
				System.out.println("Error!!!");
			}

			return "redirect:/user-manage-post";
		} else {

			//
			System.out.println("Insert hostel fail");

			modelMap.addAttribute("message", "Thêm nhà trọ thất bại");
			return "post";
		}
	}

	@GetMapping
	@RequestMapping("post-edit/{idHostel}")
	public String getPostEdit(@PathVariable int idHostel, HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			List<City> cities = cityDao.getCities();

			Hostel hostel = hostelDao.getHostelById(idHostel);
			Post post = postDao.createFullPostFromHostel(hostel);

			List<Image> images = imageDao.getImagesByIdHostel(idHostel);
			List<Video> videos = videoDao.getVideosByIdHostel(idHostel);

			modelMap.addAttribute("cities", cities);
			modelMap.addAttribute("post", post);
			modelMap.addAttribute("images", images);
			modelMap.addAttribute("videos", videos);
			return "post-edit";
		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@PostMapping
	@RequestMapping("post-edit/updatePost")
	public String postUpdatePost(@RequestParam String idCity, @RequestParam String idDistrict,
			@RequestParam String idStreet, @ModelAttribute Hostel hostel, @RequestParam MultipartFile[] imagesFile,
			@RequestParam MultipartFile[] videosFile, ModelMap modelMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			User user = userDao.getUserById(userSession.getId());

//			System.out.println("idHostel = " + hostel.getId());
//			System.out.println("hostelNumber = " + hostel.getHostelNumber());
//			System.out.println("price = " + hostel.getPrice());
//			System.out.println("space = " + hostel.getSpace());
//			System.out.println("title = " + hostel.getTitle());
//			System.out.println("description = " + hostel.getDescription());
//			for (MultipartFile imageFile : imagesFile) {
//				System.out.println("image name = " + imageFile.getOriginalFilename());
//			}
//			for (MultipartFile videoFile : videosFile) {
//				System.out.println("video name = " + videoFile.getOriginalFilename());
//			}

			City city = cityDao.getCityById(Integer.parseInt(idCity));
			District district = districtDao.getDistrictById(Integer.parseInt(idDistrict));
			Street street = streetDao.getStreetById(Integer.parseInt(idStreet));

			// //
			// System.out.println("idCity = " + city.getIdCity());
			// System.out.println("idDistrict = " + district.getIdDistrict());
			// System.out.println("idStreet = " + street.getIdStreet());
			// System.out.println("idUser = " + user2.getIdUser());

			Hostel hostelOld = hostelDao.getHostelById(hostel.getId());

			hostelOld.setCity(city);
			hostelOld.setDistrict(district);
			hostelOld.setStreet(street);
			hostelOld.setHostelNumber(hostel.getHostelNumber());
			hostelOld.setTitle(hostel.getTitle());
			hostelOld.setDescription(hostel.getDescription());
			hostelOld.setUser(user);
			hostelOld.setIsRented(false);
			hostelOld.setIsCensored(0);

			// update hostel
			Hostel hostelUpdate = hostelDao.updateHostel(hostelOld);
			if (hostelUpdate != null) { // Insert house success

				//
				System.out.println("Update hostel success");

				return "redirect:/user-manage-post";
			} else { // Insert house fail

				//
				System.out.println("Update hostel fail");

				modelMap.addAttribute("message", "Cập nhập nhà trọ thất bại");
				return "post-edit";
			}
		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("change-email")
	public String changeEmail(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

//			return "change-email";

			session.setAttribute("modelChange", "email");
			return "password-confirm";
		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@PostMapping
	@RequestMapping(value = "changeEmail", produces = "text/plain;charset=UTF-8")
	public String changeEmail(@RequestParam String email, ModelMap modelMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");

		// Check email empty
		if (email.trim().equals("")) {

			modelMap.addAttribute("message", "Email không được để trống");
			return "change-email";
		}

		// Check new email similar old email
		if (userSession.getEmail() != null && email.trim().equals(userSession.getEmail())) {

			modelMap.addAttribute("message", "Email không được giống email hiện tại");
			return "change-email";
		}

		// Check email exists
		if (userDao.checkExistsEmail(email)) {

			modelMap.addAttribute("message", "Email đã tồn tại!");
			return "change-email";
		}

		User user = userDao.getUserById(userSession.getId());
		user.setEmail(email);

		User userUpdate = userDao.updateUser(user);
		if (userUpdate != null) { // Update success

			session.setAttribute("user", user);
			return "redirect:/user-info";
		} else { // Insert fail

			modelMap.addAttribute("message", "Đổi email thất bại. Thử lại!");
			return "change-email";
		}
	}

	@GetMapping
	@RequestMapping("change-phone")
	public String changePhone(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

//			return "change-phone";

			session.setAttribute("modelChange", "phone");
			return "password-confirm";
		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@PostMapping
	@RequestMapping(value = "changePhone", produces = "text/plain;charset=UTF-8")
	public String changePhone(@RequestParam String phone, ModelMap modelMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");

		// Check phone empty
		if (phone.trim().equals("")) {

			modelMap.addAttribute("message", "Số điện thoại không được để trống");
			return "change-phone";
		}

		// Check new phone similar old phone
		if (userSession.getPhone() != null && phone.trim().equals(userSession.getPhone())) {

			modelMap.addAttribute("message", "Số điện thoại không được giống Số điện thoại hiện tại");
			return "change-phone";
		}

		// Check phone exists
		if (userDao.checkExistsPhone(phone)) {

			modelMap.addAttribute("message", "Số điện thoại đã tồn tại!");
			return "change-phone";
		}

		User user = userDao.getUserById(userSession.getId());
		user.setPhone(phone);

		User userUpdate = userDao.updateUser(user);
		if (userUpdate != null) { // Update success

			session.setAttribute("user", user);
			return "redirect:/user-info";
		} else { // Insert fail

			modelMap.addAttribute("message", "Đổi Số điện thoại thất bại. Thử lại!");
			return "change-phone";
		}
	}

	@GetMapping
	@RequestMapping("password-confirm")
	public String paswordConfirm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			return "password-confirm";
		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@PostMapping
	@RequestMapping(value = "passwordConfirm", produces = "text/plain;charset=UTF-8")
	public String paswordConfirm(@RequestParam String password, ModelMap modelMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");

		// Check password empty
		if (password.trim().equals("")) {

			modelMap.addAttribute("message", "Mật khẩu không được để trống");
			return "password-confirm";
		}

		String passwordEncodeMd5 = PasswordEncodeMD5.createPasswordEncodeMD5(password);

		// Check password correct
		if (!passwordEncodeMd5.equals(userSession.getPassword())) {

			modelMap.addAttribute("message", "Mật khẩu không chính xác!");
			return "password-confirm";
		} else {

			String modelChange = (String) session.getAttribute("modelChange");
			if (modelChange.equals("email")) {

				session.removeAttribute("modelChange");
				return "change-email";
			} else if (modelChange.equals("phone")) {

				session.removeAttribute("modelChange");
				return "change-phone";
			} else {

				return "redirect:/sign-in";
			}
		}
	}

	@GetMapping
	@RequestMapping("change-password")
	public String changePassword(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			return "change-password";
		} else {

			session.invalidate();
			return "redirect:/sign-in";
		}
	}

	@PostMapping
	@RequestMapping(value = "changePassword", produces = "text/plain;charset=UTF-8")
	public String changePassword(@RequestParam String passwordOld, @RequestParam String passwordNew,
			@RequestParam String passwordNewConfirm, ModelMap modelMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");

		// Check password empty
		if (passwordOld.trim().equals("") || passwordNew.trim().equals("") || passwordNewConfirm.trim().equals("")) {

			modelMap.addAttribute("message", "Mật khẩu không được để trống");
			return "change-password";
		}

		String passwordOldEncodeMd5 = PasswordEncodeMD5.createPasswordEncodeMD5(passwordOld.trim());
		String passwordNewEncodeMd5 = PasswordEncodeMD5.createPasswordEncodeMD5(passwordNew.trim());

		// Check password correct
		if (!passwordOldEncodeMd5.equals(userSession.getPassword())) {

			modelMap.addAttribute("message", "Mật khẩu cũ không chính xác!");
			return "change-password";
		}

		// Check password confirm correct
		if (!passwordNew.trim().equals(passwordNewConfirm.trim())) {

			modelMap.addAttribute("message", "Xác nhận mật khẩu chưa chính xác");
			return "change-password";
		}

		// Check new password similar old password
		if (passwordOld.trim().equals(passwordNew.trim())) {

			modelMap.addAttribute("message", "Mật khẩu mới không được giống Mật khẩu cũ");
			return "change-password";
		}

		User user = userDao.getUserById(userSession.getId());
		user.setPassword(passwordNewEncodeMd5);

		User userUpdate = userDao.updateUser(user);
		if (userUpdate != null) { // Update success

			session.setAttribute("user", user);
			return "redirect:/user-info";
		} else { // Insert fail

			modelMap.addAttribute("message", "Đổi Số điện thoại thất bại. Thử lại!");
			return "change-password";
		}
	}

	@GetMapping
	@RequestMapping("admin")
	public String getAdmin(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			User user = userDao.getUserById(userSession.getId());
			if (user != null) {

				Avatar avatar = avatarDao.getAvatarByIdUser(user.getId());

				session.setAttribute("user", user);
				modelMap.addAttribute("avatar", avatar);

				switch (user.getAuthorization().getId()) {
				case 3: // user
					return "redirect:/";

				case 2: // employee
					return "redirect:/admin/admin-index";

				case 1: // admin
					return "redirect:/admin/admin-index";

				default:
					return "redirect:/";
				}
			} else {
				session.invalidate();
				return "redirect:/admin/sign-in";
			}
		} else {
			session.invalidate();
			return "redirect:/admin/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("admin/index")
	public String getAdminIndex(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			User user = userDao.getUserById(userSession.getId());
			if (user != null) {

				Avatar avatar = avatarDao.getAvatarByIdUser(user.getId());
				int hostelsCount = hostelDao.getHostels().size();
				int usersCount = userDao.getUsers().size();
				int hostelsRentedCount = hostelDao.getHostelsByRented(true).size();
				int hostelsNotRentedCount = hostelDao.getHostelsByRented(false).size();
				int ratesCount = rateDao.getRates().size();
				int commentsCount = commentDao.getComments().size();

				int hostelCountRangePrice1 = hostelDao.getHostelsByRangePrice(1).size();
				int hostelCountRangePrice2 = hostelDao.getHostelsByRangePrice(2).size();
				int hostelCountRangePrice3 = hostelDao.getHostelsByRangePrice(3).size();
				int hostelCountRangePrice4 = hostelDao.getHostelsByRangePrice(4).size();
				int hostelCountRangePrice5 = hostelDao.getHostelsByRangePrice(5).size();
				int hostelCountRangePrice6 = hostelDao.getHostelsByRangePrice(6).size();

				List<HostelRangePriceFull> hostelRangePriceFull = new ArrayList<>();
				HostelRangePriceFull hostelRangePriceFull1 = new HostelRangePriceFull("Dưới 2 triệu",
						hostelCountRangePrice1,
						(double) Math.round((((double) (hostelCountRangePrice1)) * 100 / hostelsCount) * 100.0)
								/ 100.0);
				HostelRangePriceFull hostelRangePriceFull2 = new HostelRangePriceFull("2 - 3 triệu",
						hostelCountRangePrice2,
						(double) Math.round((((double) (hostelCountRangePrice2)) * 100 / hostelsCount) * 100.0)
								/ 100.0);
				HostelRangePriceFull hostelRangePriceFull3 = new HostelRangePriceFull("3 - 4 triệu",
						hostelCountRangePrice3,
						(double) Math.round((((double) (hostelCountRangePrice3)) * 100 / hostelsCount) * 100.0)
								/ 100.0);
				HostelRangePriceFull hostelRangePriceFull4 = new HostelRangePriceFull("4 - 6 triệu",
						hostelCountRangePrice4,
						(double) Math.round((((double) (hostelCountRangePrice4)) * 100 / hostelsCount) * 100.0)
								/ 100.0);
				HostelRangePriceFull hostelRangePriceFull5 = new HostelRangePriceFull("6 - 10 triệu",
						hostelCountRangePrice5,
						(double) Math.round((((double) (hostelCountRangePrice5)) * 100 / hostelsCount) * 100.0)
								/ 100.0);
				HostelRangePriceFull hostelRangePriceFull6 = new HostelRangePriceFull("Trên 10 triệu",
						hostelCountRangePrice6,
						(double) Math.round((((double) (hostelCountRangePrice6)) * 100 / hostelsCount) * 100.0)
								/ 100.0);
				hostelRangePriceFull.add(hostelRangePriceFull1);
				hostelRangePriceFull.add(hostelRangePriceFull2);
				hostelRangePriceFull.add(hostelRangePriceFull3);
				hostelRangePriceFull.add(hostelRangePriceFull4);
				hostelRangePriceFull.add(hostelRangePriceFull5);
				hostelRangePriceFull.add(hostelRangePriceFull6);

				String hostelsRangePriceCountString = "";
				for (int i = 0; i < hostelRangePriceFull.size(); i++) {
					hostelsRangePriceCountString += "-" + String.valueOf(hostelRangePriceFull.get(i).getPercent());
				}

//				List<Integer> usersRegisterCountByTime = userDao.getUsersRegisterCountByTime(-1);
				List<Integer> usersRegisterCountByTime0 = userDao.getUsersRegisterCountByTime(0);
				List<Integer> usersRegisterCountByTime1 = userDao.getUsersRegisterCountByTime(1);
				List<Integer> usersRegisterCountByTime2 = userDao.getUsersRegisterCountByTime(2);

//				List<Integer> hostelsPostCountByTime = hostelDao.getHostelsPostCountByTime(-1);
				List<Integer> hostelsPostCountByTime0 = hostelDao.getHostelsPostCountByTime(0);
				List<Integer> hostelsPostCountByTime1 = hostelDao.getHostelsPostCountByTime(1);
				List<Integer> hostelsPostCountByTime2 = hostelDao.getHostelsPostCountByTime(2);

				session.setAttribute("user", user);
				modelMap.addAttribute("avatar", avatar);
				modelMap.addAttribute("hostelsCount", hostelsCount);
				modelMap.addAttribute("usersCount", usersCount);
				modelMap.addAttribute("hostelsRentedCount", hostelsRentedCount);
				modelMap.addAttribute("hostelsNotRentedCount", hostelsNotRentedCount);
				modelMap.addAttribute("ratesCount", ratesCount);
				modelMap.addAttribute("commentsCount", commentsCount);
				modelMap.addAttribute("hostelsRangePriceCountString", hostelsRangePriceCountString);
				modelMap.addAttribute("hostelRangePriceFull", hostelRangePriceFull);

				modelMap.addAttribute("usersRegisterCountByTime0", usersRegisterCountByTime0);
				modelMap.addAttribute("usersRegisterCountByTime1", usersRegisterCountByTime1);
				modelMap.addAttribute("usersRegisterCountByTime2", usersRegisterCountByTime2);
				modelMap.addAttribute("hostelsPostCountByTime0", hostelsPostCountByTime0);
				modelMap.addAttribute("hostelsPostCountByTime1", hostelsPostCountByTime1);
				modelMap.addAttribute("hostelsPostCountByTime2", hostelsPostCountByTime2);

				switch (user.getAuthorization().getId()) {
				case 3: // user
					return "redirect:/";

				case 2: // employee
					return "admin-index";

				case 1: // admin
					return "admin-index";

				default:
					return "redirect:/";
				}
			} else {
				session.invalidate();
				return "redirect:/admin/sign-in";
			}
		} else {
			session.invalidate();
			return "redirect:/admin/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("admin/home")
	public String getHomeFromAdmin() {

		return "redirect:/";
	}

	@GetMapping
	@RequestMapping("admin/sign-in")
	public String getAdminSignIn() {

		return "admin-sign-in";
	}

	@PostMapping
	@RequestMapping("admin/signIn")
	public String postAdminSignIn(@RequestParam String username, @RequestParam String password, ModelMap modelMap,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
//		session.invalidate();

		String passwordEncodeMd5 = PasswordEncodeMD5.createPasswordEncodeMD5(password);

		User user = userDao.checkLogin(username, passwordEncodeMd5);
		if (user != null) { // Login success

			session.setAttribute("user", user);

			return "redirect:/admin/index";
		} else { // Login fail

			session.invalidate();
			modelMap.addAttribute("message", "Sai tài khoản hoặc mật khẩu. Đăng nhập lại!");
			return "admin-sign-in";
		}
	}

	@GetMapping
	@RequestMapping("admin/user-profile")
	public String getAdminUserProfile(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			User user = userDao.getUserById(userSession.getId());
			if (user != null) {

				Avatar avatar = avatarDao.getAvatarByIdUser(user.getId());
				Employee employee = employeeDao.getEmployeeByUser(user);
				Department department = departmentDao.getDepartmentById(employee.getDepartment().getId());

				session.setAttribute("user", user);
				modelMap.addAttribute("avatar", avatar);
				modelMap.addAttribute("employee", employee);
				modelMap.addAttribute("department", department);

				switch (user.getAuthorization().getId()) {
				case 3: // user
					return "redirect:/";

				case 2: // employee
					return "admin-user-profile";

				case 1: // admin
					return "admin-user-profile";

				default:
					return "redirect:/";
				}
			} else {
				session.invalidate();
				return "redirect:/admin/sign-in";
			}
		} else {
			session.invalidate();
			return "redirect:/admin/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("admin/users-manage")
	public String getAdminUsersManage(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			User user = userDao.getUserById(userSession.getId());
			if (user != null) {

				//
				Avatar avatar = avatarDao.getAvatarByIdUser(user.getId());
				Employee employee = employeeDao.getEmployeeByUser(user);
				Department department = departmentDao.getDepartmentById(employee.getDepartment().getId());

				session.setAttribute("user", user);
				modelMap.addAttribute("avatar", avatar);
				modelMap.addAttribute("employee", employee);
				modelMap.addAttribute("department", department);

				//
				List<User> users = userDao.getUsers(1, 10);
				modelMap.addAttribute("users", users);

				switch (user.getAuthorization().getId()) {
				case 3: // user
					return "redirect:/";

				case 2: // employee
					return "admin-users-manage";

				case 1: // admin
					return "admin-users-manage";

				default:
					return "redirect:/";
				}
			} else {
				session.invalidate();
				return "redirect:/admin/sign-in";
			}
		} else {
			session.invalidate();
			return "redirect:/admin/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("admin/hostels-manage")
	public String getAdminHostelsManage(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		if (userSession != null) {

			User user = userDao.getUserById(userSession.getId());
			if (user != null) {

				//
				Avatar avatar = avatarDao.getAvatarByIdUser(user.getId());
				Employee employee = employeeDao.getEmployeeByUser(user);
				Department department = departmentDao.getDepartmentById(employee.getDepartment().getId());

				session.setAttribute("user", user);
				modelMap.addAttribute("avatar", avatar);
				modelMap.addAttribute("employee", employee);
				modelMap.addAttribute("department", department);

				//
				List<Hostel> hostels = hostelDao.getHostels(1, 10, -2);
				modelMap.addAttribute("hostels", hostels);

				switch (user.getAuthorization().getId()) {
				case 3: // user
					return "redirect:/";

				case 2: // employee
					return "admin-hostels-manage";

				case 1: // admin
					return "admin-hostels-manage";

				default:
					return "redirect:/";
				}
			} else {
				session.invalidate();
				return "redirect:/admin/sign-in";
			}
		} else {
			session.invalidate();
			return "redirect:/admin/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("admin/analytics")
	public String getAdminAnalytics(HttpServletRequest request, ModelMap modelMap) {
		return "redirect:/admin/index";
	}

}
