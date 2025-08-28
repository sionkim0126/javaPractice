package sec05.ex01;

public class MemberDTO {
	private String id;
	private String pw;
	
	public MemberDTO() {
		System.out.println("memberDTO session memberDTO 메서드 호출");
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
}
