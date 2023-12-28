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
	CartDAO cart;
	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("=====[회원 %s님]===== \n",cont.getLoginId());
	}

	@Override
	public boolean update() {
//		item = ItemDAO.getInstance();
//		item.showCate();
//		int sel=Util.getValueI("번호를 입력하세요", 0, 10)-1;
		cart = CartDAO.getInstance();
		cart.addItem(cont.getLoginId());
		
		return false;
	}

}
