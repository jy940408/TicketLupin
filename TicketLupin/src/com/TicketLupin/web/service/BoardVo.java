package com.ezen;

import java.sql.Date;

public class BoardVo {
	private int idx;
	private String title;
	private String contents;
	private Date regdate;
	
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardVo(int idx, String title, String contents, Date regdate) {
		super();
		this.idx = idx;
		this.title = title;
		this.contents = contents;
		this.regdate = regdate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "BoardVo [idx=" + idx + ", title=" + title + ", contents=" + contents + ", regdate=" + regdate + "]";
	}	
	
	
	
}
