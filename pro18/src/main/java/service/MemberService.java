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
}
