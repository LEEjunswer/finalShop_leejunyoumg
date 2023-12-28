package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {
	MallController cont;
	MemberDAO dao;
	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("=======[내 정보]=======");
		System.out.println("[1]비밀번호변경\n[2]뒤로가기\n[3]종료");
		System.out.println("===========");
	}

	@Override
	public boolean update() {
		int sel=Util.getValueI("메뉴 선택", 0, 2);
		if(sel==0) {
			System.out.println("종료");
			cont.setNext(null);
		}else if(sel==1) {
			dao.changePw(cont.getLoginId());
		}else {
			cont.setNext("MemberMain");
		}
		return false;
	}
}
