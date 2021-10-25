package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryRepositoryException;
import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/gallery/images";
	
	@Autowired
	private GalleryRepository galleryRepository;

	public void restore(MultipartFile file, String comments) throws GalleryRepositoryException {
		GalleryVo vo = new GalleryVo();
		
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

			vo.setUrl(URL_BASE + "/" + saveName);
			vo.setComments(comments);
			galleryRepository.insert(vo);
			
		} catch (IOException e) {
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
