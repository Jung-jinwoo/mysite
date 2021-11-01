package com.douzone.mysite.exception;

public class GalleryRepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GalleryRepositoryException() {
		super("File Not Upload Exception Occurs");
	}
	
	public GalleryRepositoryException(String message) {
		super(message);
	}
}
