package sec02.ex01;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String email;
	private Date joinDate;
	

	public MemberDTO() {
		System.out.println("pro12 회원정보 조회 memberDTO 메서드 호출");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
