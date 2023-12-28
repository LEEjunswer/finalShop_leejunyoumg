package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import util.Util;

public class MemberCart implements MenuCommand {
	MallController cont;
	CartDAO dao;
	@Override
	public void init() {
		dao= CartDAO.getInstance();
		cont = MallController.getInstance();
		System.out.println("=======[ 구매내역 ] ========");
		System.out.println("[1]쇼핑하기\n[2]뒤로가기\n[0]종료\n");
		System.out.println("========================");
		dao.showMyCart(cont.getLoginId());
	}

	@Override
	public boolean update() {
		int sel = Util.getValueI("메뉴입력", 0, 2);
		if (sel == 1) {
			System.out.println("쇼핑하기");
			cont.setNext("MemberShopping");
		} else if (sel == 2) {
			cont.setNext("MemberMain");
		} else {
			System.out.println("종료");
			cont.setNext(null);
		}
		

		return false;
	}

}
