package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import dto.Member;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController cont;
	
	@Override
	public void init() {
		 cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		System.out.println("===회원가입===");
		String id =Util.getValueS("회원가입하실 아이디를 입력하세요.");
		if(dao.getcheckId(id)!=null) {
			System.out.println("이미 중복된 아이디가 존재합니다");
			return false;
		}
		String pw =Util.getValueS("회원가입하실 비밀번호를 입력하세요");
		String name=Util.getValueS("회원가입하실 이름을 입력하세요");
		if(id.equals("admin")) {
			dao.makeIdAdmin(id,pw,name);
		}
		dao.makeId(id, pw, name);
		System.out.println("회원가입완료");
		cont.setNext("MallMain");
		return false;
	}

}
