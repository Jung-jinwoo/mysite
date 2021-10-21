package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryRepositoryException;
import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryRepository galleryRepository;

	public String restore(MultipartFile file) throws GalleryRepositoryException {

		try {
			if (file.isEmpty()) {
				return null;
			}
			
			return galleryRepository.upload(file);
		} catch (Exception e) {
			throw new GalleryRepositoryException("File Not Uploaded" + e);
		}
	}
	
	public void insert(GalleryVo galleryVo) {
		galleryRepository.insert(galleryVo);
	}
	
	public List<GalleryVo> findAll(){
		return galleryRepository.findAll();
	}
	
	public void delete(Long no) {
		galleryRepository.delete(no);
	}

}
