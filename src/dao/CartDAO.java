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

	CartDAO() {
		cartList = new ArrayList<Cart>();
		item = item.getInstance();
	}

	public CartDAO getInstance() {

		return instance;
	}

	private void addCart(String id, String itemName) {
	
	

	}

	public void addItem() {
		item.showItem();
	String input=Util.getValueS("추가하실 아이템을 입력하세요");
	if(item.checkItem(input)==null) {
		System.out.println("아이템이 존재하지 않습니다");
		return;
	}
	
	}
	private void checkCart(String id) {
		
	}

}
