package dao;

import java.util.ArrayList;

import dto.Member;

public class MemberDAO {
	private ArrayList<Member> memberList;
	static private MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {

		return instance;
	}

	MemberDAO() {
		memberList = new ArrayList<Member>();
	}

	public String getcheckId(String id) {
		if (memberList.size() == 0)
			return null;
		for (Member s : memberList) {
			if(s.getId().equals(id)) {
				return id;
			}
		}
		return null;
	}

	public void makeId(String id, String pw, String memberName) {
		Member make = new Member(id, pw, memberName);
		memberList.add(make);
	}

	public String getLogCheck(String id, String pw) {
		for (Member m : memberList) {
			if (m.getId().equals(id) && m.getPw().equals(pw)) {
				return id;
			}
		}
		return null;
	}

}
