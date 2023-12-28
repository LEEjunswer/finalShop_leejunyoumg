package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	MallController cont;
	MemberDAO dao;

	@Override
	public void init() {
		cont = MallController.getInstance();
		dao= MemberDAO.getInstance();
		System.out.println("======[ 관리자 회원목록 ]======");
		System.out.println("[1]회원목록\n[2]회원삭제\n[3]뒤로가기\n[0]종료");
	}

	@Override
	public boolean update() {
		int sel=Util.getValueI("메뉴 선택", 0, 3);
		if(sel==0) {
			System.out.println("종료");
			cont.setNext(null);
		}else if(sel==1) { //회원목록
			dao.showUser();
		}else if(sel==2) {
			dao.adminDelUser();
		}else {
			cont.setNext("AdminMain");
		}
		return false;
	}
}
