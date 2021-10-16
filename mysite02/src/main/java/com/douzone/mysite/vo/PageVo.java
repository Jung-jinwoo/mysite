package com.douzone.mysite.vo;

public class PageVo {
	private int currentno; // 현재 번호
	private int prev; // 이전 번호
	private int next; // 다음 번호
	private double totalpage; // 페이지 최종 크기
	private int start = 1;
	private int end = 5;

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public double getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(double d) {
		this.totalpage = d;
	}
	
	public int getCurrentno() {
		return currentno;
	}
	public void setCurrentno(int currentno) {
		this.currentno = currentno;
	}
	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	
	
}
