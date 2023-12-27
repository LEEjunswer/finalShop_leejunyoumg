package menu_memeber;

import java.util.ArrayList;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand {
	MallController cont;
	ItemDAO item;
	@Override
	public void init() {
		item = ItemDAO.getInstance();
		cont = MallController.getInstance();
		System.out.printf("=====[회원 %s님]=====",cont.getLoginId());

	}

	@Override
	public boolean update() {
		item.showCate();
		int sel=Util.getValueI("번호를 입력하세요", 0, 10)-1;
	

		return false;
	}

}
