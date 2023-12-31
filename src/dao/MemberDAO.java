package dao;

import java.util.ArrayList;

import controller.MallController;
import dto.Board;
import dto.Member;
import util.Util;

public class MemberDAO {
	private ArrayList<Member> memberList;
	MallController cont;
	int num=0;
	static private MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {

		return instance;
	}

	MemberDAO() {
		cont= MallController.getInstance();
		memberList = new ArrayList<Member>();
	}

	public String getcheckId(String id) {
		if (memberList.size() == 0)
			return null;
		for (Member s : memberList) {
			if (s.getId().equals(id)) {
				return id;
			}
		}
		return null;
	}
	public void makeIdAdmin(String id, String pw, String memberName) {
		
		Member make = new Member(1000,id, pw, memberName);
		memberList.add(make);
	}

	public void makeId(String id, String pw, String memberName) {
		
		Member make = new Member(++num+1000,id, pw, memberName);
		memberList.add(make);
		num=++num;
	}

	public String getLogCheck(String id, String pw) {
		for (Member m : memberList) {
			if (m.getId().equals(id) && m.getPw().equals(pw)) {
				return id;
			}
		}
		return null;
	}

	public void adminDelUser() { //adminUser 나중에 아이템도 한꺼번에 삭제
		showUser();
		if (memberList.size() == 0)
			return;
		String input = Util.getValueS("삭제할 아이디를 입력하세요");

		if (input.equals("admin")) {
			System.out.println("관리자는 삭제 불가");
			return;
		}
		if (getcheckId(input) == null) {
			System.out.println("아이디가 존재하지않습니다");
			return;
		}
		int idx = checkNumId(input);
		System.out.println(input + "아이디 삭제 완료");
		memberList.remove(idx);

	}

	public void showUser() {
		if (memberList.size() == 0) {
			System.out.println("회원이 존재하지 않습니다");
			return;
		}
		for (Member s : memberList) {
			System.out.println(s.toString());
		}
	}

	private int checkNumId(String id) {
		if (memberList.size() == 0)
			return -1;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;

	}
	private int checkPw(String id) {
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;

	}
	public void changePw(String id) {
		String pw=Util.getValueS("패스워드 입력");
		String changepw=Util.getValueS("신규 패스워드 입력");
		if(pw.equals(changepw)) {
			System.out.println("다른비밀번호 입력해주세요");
			return;
		}
		int idx=checkPw(id);
		memberList.get(idx).setPw(changepw);
		System.out.println("비밀번호 변경완료");
	}
	
	public void myAccountDel(String id) {
		int idx=checkNumId(id);
		memberList.remove(idx);
		cont.setLoginId(null);
		System.out.println("회원탈퇴 완료");
		cont.setNext("MallMain");
		
	}
	public String memberSave() {
		if(memberList.size()==0) {
			return"";
		}
		String data="";
		for(Member m : memberList) {
			data+=m.saveMemberData();
		}
		return data;

}
	public void loadMember(String data) {
		String[] s=data.split("\n");
		int check =s.length;
		if(check==0) {
			return;
		}
		memberList.clear();
		for(int i=0; i<check; i++) {
			String[] info =s[i].split("/");
			Member tes= new Member(Integer.parseInt(info[0]),info[1],info[2],info[3]);
			memberList.add(tes);
		}
		num=memberList.size();
		
	
	}
	
}
