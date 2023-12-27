package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MallLogin implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		System.out.println("====로그인====");
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		String id =Util.getValueS("아이디를 입력하세요");
		String pw =Util.getValueS("비밀번호 입력하세요");
		
		if(dao.getLogCheck(id, pw)!=null) {
			if(id.equals("admin")) {
				cont.setLoginId("admin");
				cont.setNext("AdminMain");
			}else {
				cont.setLoginId(id);
				cont.setNext("MemberMain");
			}
			System.out.println("로그인 성공");
		}else {
			System.out.println("아이디 비밀번호가 틀립니다");
			cont.setNext("MallMain");
		}
		
		return false;
	}

}
