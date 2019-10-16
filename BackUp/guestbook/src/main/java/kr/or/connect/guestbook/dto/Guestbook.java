package kr.or.connect.guestbook.dto;

import java.sql.Date;

public class Guestbook {
	private String content;
	private Long id;
	private String name;
	private Date regdate;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "Guestbook [content=" + content + ", id=" + id + ", name=" + name + ", regdate=" + regdate + "]";
	}
	
	

}
