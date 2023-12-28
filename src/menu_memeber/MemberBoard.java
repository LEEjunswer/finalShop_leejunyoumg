package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dao.MemberDAO;
import dto.Board;
import util.Util;

public class MemberBoard implements MenuCommand{
	MallController cont;
	BoardDAO dao;

	@Override
	public void init() {
		cont= MallController.getInstance();
		System.out.println("=====[ 게시판 ] ======");
		System.out.println("[1]게시글보기\n[2]게시글추가\n[3]내게시글[삭제]\n[4]뒤로가기\n[0]종료");
		System.out.println("==============");
	}

	@Override
	public boolean update() {
		int sel=Util.getValueI("메뉴 선택", 0, 4);
		if(sel==0) {
			System.out.println("종료");
			cont.setNext(null);
		}else if(sel==1) {
			dao.show();
		}else if(sel==2) {
			dao.makeBoard(cont.getLoginId());
		}else if(sel==3) {
			dao.myBoard(cont.getLoginId());
		}else {
			System.out.println("뒤로가기");
			cont.setNext("MemberMain");
		}
		return false;
	}
}
