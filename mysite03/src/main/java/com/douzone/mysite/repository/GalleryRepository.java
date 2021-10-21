package com.douzone.mysite.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryRepositoryException;
import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {

	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/images";
	
	@Autowired
	private SqlSession sqlSession; 

	public String upload(MultipartFile file) throws Exception {
		String url = null;

		try {

			UUID id = UUID.randomUUID();

			String origin = file.getOriginalFilename();
			String extName = origin.substring(origin.lastIndexOf('.') + 1);
			String saveName = id + "." + extName;

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveName);
			os.write(data);
			os.close();

			url = URL_BASE + "/" + saveName;
			
		} catch (IOException e) {
			throw new GalleryRepositoryException("File Not Uploaded" + e);
		}

		return url;
	}

	public boolean insert(GalleryVo galleryVo) {
		int count = sqlSession.insert("gallery.insert", galleryVo);
		return count == 1;
	}
	
	public List<GalleryVo> findAll(){
		return sqlSession.selectList("gallery.findAll");
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("gallery.delete", no);
		return count == 1;
	}
}
