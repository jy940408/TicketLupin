package domain;

public class Criteria {
	
	private int page;		 //첫페이지
	private int perPageNum;  //한 페이지에 들어가는 글 개수
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 15;
	}	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
}
