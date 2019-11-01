package com.huupham.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadFile {
	
	private static final String uploadRootPath = "C:\\PHAMHUU\\workspace-spring-tool-suite\\247hostel\\src\\main\\webapp\\WEB-INF\\resources\\upload";

	public static String renameFileByNanoTime(String fileName) {

		String[] arrImg = fileName.split("\\.");
		String fileType = arrImg[arrImg.length - 1];

		return (System.nanoTime() + "." + fileType);
	}
	
	public static String getUploadFolderPath(String fileType) {

		String uploadFolderPath = uploadRootPath;
		
		if(fileType.equals("image")) {
			uploadFolderPath += "\\images";
		}
		else if(fileType.equals("video")) {
			uploadFolderPath += "\\videos";
		}
		else if(fileType.equals("avatar")) {
			uploadFolderPath += "\\avatars";
		}
		
		return uploadFolderPath;		
	}
	
	public static String getUrlFileAfterUpload(String fileType, String fileName) {

		String url = "resources\\upload";
		
		if(fileType.equals("image")) {
			url += "\\images";
		}
		else if(fileType.equals("video")) {
			url += "\\videos";
		}
		else if(fileType.equals("avatar")) {
			url += "\\avatars";
		}
		
		return url + "\\" + fileName;		
	}
	
	public static String uploadFile(HttpServletRequest request, MultipartFile multipartFile, String fileType) {
		
		String filePath = "";

		try {

			String uploadFolderPath = getUploadFolderPath(fileType);
			
			File uploadFolder = new File(uploadFolderPath);
			// Create upload folder if not exists
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			// Change file name
			String fileName = renameFileByNanoTime(multipartFile.getOriginalFilename());
			// Create file
			File file = new File(uploadFolderPath + "\\" + fileName);
			// Upload file
			multipartFile.transferTo(file);

			filePath = getUrlFileAfterUpload(fileType, fileName);
		} catch (Exception e) {

			filePath = "";
		}
		
		System.out.println(filePath);
		
		return filePath;
	}

	public static boolean deleteFileByFileName(HttpServletRequest request, String fileType, String fileName) {

		try {

			String uploadFolderPath = getUploadFolderPath(fileType);

			// Create file
			File file = new File(uploadFolderPath + "\\" + fileName);
			if (file.exists()) {

				file.delete();
				return true;
			}
			
			return false;
		} catch (Exception e) {

			return false;
		}
	}

	public static List<File> getAllChildrenFile(HttpServletRequest request, String uploadFolderName) {
		
		try {

			// Create file
			File file = new File(uploadRootPath + "\\" + uploadFolderName);
			if (!file.exists()) {
				return null;
			} else {
				// Get all children files
				File[] childrenFiles = file.listFiles();
				List<File> files = new ArrayList<File>();
				for (File childrenFile : childrenFiles) {
					files.add(childrenFile);
				}

				return files;
			}
		} catch (Exception e) {

			return null;
		}
	}

}
