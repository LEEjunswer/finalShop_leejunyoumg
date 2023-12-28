package menu_admin;

import java.util.Map;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class AdminItem implements MenuCommand {
	MallController cont;
	

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("======[관리자 쇼핑몰관리]======");
		// 카테고리 순으로 정렬 카테고리가 같으면 아이템 이름순으로 정렬하기
		System.out.println("[1]아이템 추가\n[2]아이템 삭제\n[3]총 매출 아이템 갯수 출력(판매량 높은순으로)\n[4]뒤로가기\n[0]종료");
	}

	@Override
	public boolean update() {
		ItemDAO  dao = ItemDAO.getInstance();
		int sel = Util.getValueI("메뉴 입력", 0, 4);
		if (sel == 0) {
			System.out.println("종료");
			cont.setNext(null);
		} else if (sel == 1) {
			dao.addItem();
		} else if (sel == 2) {
			dao.delItemAdmin();
		} else if (sel == 3) {
			
		} else {
			cont.setNext("AdminMain");
		}

		return false;
	}
}
