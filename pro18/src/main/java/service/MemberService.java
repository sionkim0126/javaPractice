package service;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberService {
	MemberDAO memberDAO;
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public void addMember(MemberDTO member) {
		memberDAO.addMember(member);
	}
	
	public MemberDTO login(String id, String pwd) {
		MemberDTO member = memberDAO.login(id, pwd);
		return member;
	}
}
