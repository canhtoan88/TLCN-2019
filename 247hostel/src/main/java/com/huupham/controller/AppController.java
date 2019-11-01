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
import com.huupham.dao.DistrictDao;
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
import com.huupham.entities.District;
import com.huupham.entities.Hostel;
import com.huupham.entities.Image;
import com.huupham.entities.Street;
import com.huupham.entities.User;
import com.huupham.entities.Video;
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
	PostDao postDao;
	
	@Autowired
	AuthorizationDao authorizationDao;
	
	@GetMapping
	@RequestMapping("/")
	public String getDefault(HttpServletRequest request, ModelMap modelMap) {

		List<City> cities = cityDao.getCities();
		
		// get Ho Chi Minh city before
		List<City> cities2 = new ArrayList<City>();
		for(City city : cities) {
			if(city.getId() == 29) {
				cities2.add(city);
			}
		}
		for(City city : cities) {
			cities2.add(city);
		}
		
		// Get new hostels
		List<Hostel> hostels = hostelDao.getNewHostels_IsCensored(1, 10);
		List<Post> posts = new ArrayList<Post>();
		for(Hostel hostel: hostels) {
			
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

		boolean b = userDao.checkLogin(username, passwordEncodeMd5);
		if (b) { // Login success

			User user = userDao.getUserByUsername(username);
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
	@RequestMapping("sign-up")
	public String getSignUp() {

		return "sign-up";
	}

	@PostMapping
	@RequestMapping(value = "signUp", produces = "text/plain;charset=UTF-8")
	public String postSignUp(@ModelAttribute User user, @RequestParam String confirmPassword, 
			ModelMap modelMap, HttpServletRequest request) {
		
		System.out.println(user.getFullname() +"|"+ user.getEmail() +"|"+ user.getPhone() +"|"+ user.getPassword());
		System.out.println(confirmPassword);
		
		// Check email and phone empty
		if(user.getEmail().trim().equals("") && user.getPhone().trim().equals("")) {
			
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("confirmPassword", confirmPassword);
			modelMap.addAttribute("message", "Bạn cần cung cấp Email hoặc Số điện thoại để đăng ký.");
			return "sign-up";	
		}

		// Check email exists
		if(user.getEmail() != null && !user.getEmail().trim().equals("") 
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
		
		// Set timestamp register
		user.setTimeRegister(new Date());

		// Get User authorization
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
			
			modelMap.addAttribute("post", post);
			modelMap.addAttribute("districts", districts);
			
			return "hostel-detail";
		} else {

			return "redirect:/";
		}
	}

	@GetMapping
	@RequestMapping("user-info")
	public String getUserInfo(HttpServletRequest request, ModelMap modelMap) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			
			User user = userDao.getUserById(((User) session.getAttribute("user")).getId());
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
					
//					Employee employee = employeeService.selectEmployeeByIdUser(user.getIdUser());
//					employee.setUser(user);
//					modelMap.addAttribute("employee", employee);
//					return "employee-info";
	
				case 1: // admin
					
//					Admin admin = new Admin();
//					admin.setUser(user);
//					modelMap.addAttribute("admin", admin);
//					return "admin-info";
	
				default:
			
					session.invalidate();			
					return "redirect:/sign-in";
			}
		}
		else {
			
			session.invalidate();			
			return "redirect:/sign-in";
		}
	}

	@PostMapping
	@RequestMapping("updateUserInfo")
	public String postUpdateInfo(@RequestParam String fullname, @RequestParam String address,
			@RequestParam String birthday, @RequestParam String gender, 
			HttpServletRequest request, ModelMap modelMap) {
		
//		System.out.println("fullname: " + fullname);
//		System.out.println("address: " + address);
//		System.out.println("birthday: " + birthday);
//		System.out.println("gender: " + gender);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		User user2 = userDao.getUserById(user.getId());
		
		if(fullname != null && !user2.getFullname().equals(fullname.trim())) {
			user2.setFullname(fullname);
		}
		
		if(user2.getAddress() == null || 
				address != null && user2.getAddress() != null 
				&& !user2.getAddress().equals(address.trim())) {
			user2.setAddress(address);
		}
		
		if(!birthday.equals("")) {
			Date newBirthday;
			try {
				newBirthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
//				System.out.println("new birthday: " + newBirthday);
				if(user2.getBirthday() == null || 
						user2.getBirthday() != null && user2.getBirthday().compareTo(newBirthday) != 0) {			
					user2.setBirthday(newBirthday);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!(user2.isGender() ? "male" : "female").equals(gender)) {
			user2.setGender((gender.equals("male") ? true : false));
		}
		
//		System.out.println("new user: " + userDao.getUserInfo(user2));
		
		User userUpdate = userDao.updateUser(user2);		
		if(userUpdate != null) {
			
			session.setAttribute("user", userUpdate);
			modelMap.addAttribute("message", "Cập nhập thông tin thành công!");
			return "user-info";
		}
		else {
			
			modelMap.addAttribute("message", "Cập nhập thông tin thất bại!");
			return "user-info";
		}
	}

	@GetMapping
	@RequestMapping("user-manage-post")
	public String getUserManagePost(HttpServletRequest request, ModelMap modelMap) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null) {

			List<Hostel> hostels = hostelDao.getHostelsByIdUser(user.getId(), 1, 10);

			modelMap.addAttribute("hostels", hostels);
			return "user-manage-post";
			
		}
		else {
			
			session.invalidate();			
			return "redirect:/sign-in";
		}
	}

	@GetMapping
	@RequestMapping("post")
	public String getPost(ModelMap modelMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {

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

		System.out.println("idCity: " + idCity);
		System.out.println("idDistrict: " + idDistrict);
		System.out.println("idStreet : " + idStreet);
		System.out.println("hostelNumber = " + hostel.getHostelNumber());
		System.out.println("price = " + hostel.getPrice());
		System.out.println("space = " + hostel.getSpace());
		System.out.println("title = " + hostel.getTitle());
		System.out.println("description = " + hostel.getDescription());
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
		User ssUser = (User) session.getAttribute("user");
		User user = userDao.getUserById(ssUser.getId());

		hostel.setCity(city);
		hostel.setDistrict(district);
		hostel.setStreet(street);
		hostel.setUser(user);
		hostel.setIsRented(false);
		hostel.setIsCensored(0);
		hostel.setTimestamp(new Date());
		
		// Insert hostel
		Hostel hostelSave = hostelDao.saveHostel(hostel);
		if(hostelSave != null) {
			
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
						if(imageSave != null) {
							
							System.out.println("Insert image success");
						}
						else {
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
		}
		else {

			//
			System.out.println("Insert hostel fail");

			modelMap.addAttribute("message", "Thêm nhà trọ thất bại");
			return "post";			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		boolean bInsertHouse = houseService.insertHouse(house);
//		if (bInsertHouse) { // Insert house success
//
//			//
//			System.out.println("Insert house success");
//
//			House houseInsertNewest = houseService.getHouseInsertNewest();
//
//			//
//			System.out.println("idHouse insert = " + houseInsertNewest.getIdHouse());
//
//			if (houseInsertNewest != null) {
//
//				// Upload images
//				try {
//
//					for (int i = 0; i < 10; i++) {
//
//						MultipartFile mFile = imagesFile[i];
//
//						String fileName = MyUploadFile.renameFileByNanoTime(mFile.getOriginalFilename());
//						boolean bUploadFile = MyUploadFile.uploadFile(request, mFile, fileName, "image");
//						if (!bUploadFile) { // upload file fail
//
//							//
//							System.out.println("Upload file fail!");
//						} else { // upload file success
//
//							//
//							System.out.println("Upload file success!");
//
//							// Insert image
//							Image image = new Image();
//							image.setHouse(houseInsertNewest);
//							image.setLinkImage(fileName);
//							image.setHouse(houseInsertNewest);
//
//							boolean bInsertImage = imageService.insertImage(image);
//							if (bInsertImage) {
//
//								//
//								System.out.println("Insert image success");
//							} else {
//
//								//
//								System.out.println("Insert image fail");
//							}
//						}
//					}
//				} catch (Exception e) {
//
//					//
//					System.out.println("Error!!!");
//				}
//
//				// Upload videos
//				try {
//
//					for (MultipartFile mFile : videosFile) {
//
//						String fileName = MyUploadFile.renameFileByNanoTime(mFile.getOriginalFilename());
//						boolean bUploadFile = MyUploadFile.uploadFile(request, mFile, fileName, "video");
//						if (!bUploadFile) { // upload file fail
//
//							//
//							System.out.println("Upload file fail!");
//						} else { // upload file success
//
//							//
//							System.out.println("Upload file success!");
//
//							// Insert image
//							Video video = new Video();
//							video.setHouse(houseInsertNewest);
//							video.setLinkVideo(fileName);
//
//							boolean bInsertVideo = videoService.insertVideo(video);
//							if (bInsertVideo) {
//
//								//
//								System.out.println("Insert video success");
//							} else {
//
//								//
//								System.out.println("Insert video fail");
//							}
//						}
//					}
//				} catch (Exception e) {
//
//					//
//					System.out.println("Error!!!");
//				}
//
//			}
//
//			return "redirect:/user-manage-post";
//		} else { // Insert house fail
//
//			//
//			System.out.println("Insert house fail");
//
//			modelMap.addAttribute("message", "Thêm nhà trọ thất bại");
//			return "post";
//		}
	}
	
	
	
	
	
	
	
	
}
