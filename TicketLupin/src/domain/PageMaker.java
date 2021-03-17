package domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PageMaker {
	
	private int totalCount;
	private int countList;
	private int pageNum;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private SearchCriteria scri;
	private int displayPageNum = 10;
	
	
	
	public SearchCriteria getScri() {
		return scri;
	}
	public void setScri(SearchCriteria scri) {
		this.scri = scri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCountList() {
		return countList;
	}
	public void setCountList(int countList) {
		this.countList = countList;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	

	
	public void calcData(){
		//첫페이
		endPage = 10;
		//endPage = (int) (Math.ceil(scri.getPage()/(double)displayPageNum) * displayPageNum);
		//마지막 페이지
		startPage = 1;
		//startPage = (endPage - displayPageNum)+1;
		
		//임시 마지막 페이지
		//int tempEndPage = (int) (Math.ceil(totalCount/(double) scri.getPerPageNum()));
		int tempEndPage = 8;
		
		if(endPage>tempEndPage) {
			endPage = tempEndPage;
		}			
		//���� ���� ��ư
		prev = startPage == 1 ? false : true;		
		next = endPage * scri.getPerPageNum() >= totalCount ? false : true;
		System.out.println("next:"+next);
	}
	
	public String encoding(String keyword){
		
		if(keyword==null || keyword.trim().length()==0){
			return "";
		}		
		try{
			return URLEncoder.encode(keyword, "UTF-8");
		}catch(UnsupportedEncodingException e){
			return "";			
		}
	}
	
}
