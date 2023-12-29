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

		cart = CartDAO.getInstance();
		cart.addItem(cont.getLoginId());
		
		return false;
	}

}
