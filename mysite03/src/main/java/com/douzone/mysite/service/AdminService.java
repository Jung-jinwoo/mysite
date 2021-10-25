package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryRepositoryException;
import com.douzone.mysite.repository.AdminRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class AdminService {
	
	private static String SAVE_PATH = "/admin-images";
	private static String URL_BASE = "/admin/images";

	@Autowired
	private AdminRepository adminRepository;
	
	public SiteVo findOne() {
		return adminRepository.findOne();
	}
	
	public SiteVo findByNo(Long no) {
		return adminRepository.findByNo(no);
	}
	
	public SiteVo getSite() {
		return adminRepository.getSite();
	}

	public void restore(SiteVo siteVo, MultipartFile file) throws GalleryRepositoryException{
		
		try {
			if(file.isEmpty()) {
				throw new GalleryRepositoryException("File Not Uploaded...");
			}

			UUID id = UUID.randomUUID();

			String origin = file.getOriginalFilename();
			String extName = origin.substring(origin.lastIndexOf('.') + 1);
			String saveName = id + "." + extName;

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveName);
			os.write(data);
			os.close();
		
			siteVo.setProfile(URL_BASE + "/" + saveName);
			
			adminRepository.update(siteVo);
			
		} catch (IOException e) {
			throw new GalleryRepositoryException("File Not Uploaded" + e);
		}
	}

	

	

}
