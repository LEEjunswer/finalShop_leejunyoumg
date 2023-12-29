package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	MallController cont;
	BoardDAO dao;
	@Override
	public void init() {
		cont =MallController.getInstance();
		System.out.println("=====[관리자 게시판]======");
		System.out.println("[1]게시글목록\n[2]게시글삭제\n[3]뒤로가기\n[0]종료");
		System.out.println("======================");
	}

	@Override
	public boolean update() {
	dao = BoardDAO.getInstance();
		int sel=Util.getValueI("메뉴선택", 0, 3);
		if(sel==0) {
			System.out.println("종료");
			cont.setNext(null);
		}else if(sel==1) {
			dao.show();
		}else if(sel==2) {
			dao.adminDelBoard();
		}else {
			System.out.println("뒤로가기");
			cont.setNext("AdminMain");
		}
		return false;
	}

}
