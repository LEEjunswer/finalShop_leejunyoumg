package dao;

import java.util.ArrayList;

import controller.MallController;
import dto.Cart;
import util.Util;

public class CartDAO {
	ArrayList<Cart> cartList;
	static private CartDAO instance = new CartDAO();
	MallController cont;

	ItemDAO item;
	int cnt;

	CartDAO() {
		cartList = new ArrayList<Cart>();
		item=ItemDAO.getInstance();
	}

	public static CartDAO getInstance() {

		return instance;
	}

	private void addCart(int cartNum,String id, int itemNum,int itemCnt) {
		Cart car= new Cart(cartNum,id,itemNum,itemCnt);
		cartList.add(car);
	}

	public void addItem(String id) {
		cont = MallController.getInstance();
		item=ItemDAO.getInstance();
		int t = 0;
		item.showCate();
		String idx=item.showCate();
		item.buyItem(idx);
		while (true) {
			String sel = Util.getValueS("구매하실 아이템을 입력하세요");
			if (item.checkItem(sel) == null) {
				System.out.println("아이템 이름 오류 다시 입력해주세요");
				continue;
			}
			int ea = Util.getValueI("아이템 구매 수량", 1, 100);
			int index=item.checkItemNum(sel);
			if(item.checkItemNum(sel)!=-1) {
			int es=cartList.get(index).getItemCnt();
			cartList.get(index).setItemCnt(es+ea);
			System.out.printf("기존에 장바구니 아이템이 존재하여 %s 기존%d 개수에서 추가하여 합 %d입니다\n",sel,es,es+ea);
			break;
			}
			addCart(++cnt,id,index,ea);
			System.out.printf("[%s %d개 구매 완료]",sel,ea);
			break;
		}

	}
	public void myCart(String id) {
		
		
		
	}
	

	
//	private boolean checkCart(String id,int itemNum) {
//		if(cartList.size()==0)return false;
//			for(Cart cart : cartList) {
//			if(cart.getId().equals(id)) {
//				for(Cart s : cartList) {
//					if(s.getItemNum()==itemNum) {
//						return true;
//					}
//				}
//			}
//		}
//			return false;
//	}

}
