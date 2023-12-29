package dao;

import java.util.ArrayList;

import controller.MallController;
import dto.Board;
import dto.Cart;
import dto.Item;
import util.Util;

public class CartDAO {
	ArrayList<Cart> cartList;
	static private CartDAO instance = new CartDAO();
	MallController cont;

	ItemDAO item;
	int cnt;

	CartDAO() {
		cartList = new ArrayList<Cart>();
	}

	public static CartDAO getInstance() {

		return instance;
	}

	private void addCart(int cartNum, String id, int itemNum, int itemCnt) {
		Cart car = new Cart(cartNum, id, itemNum, itemCnt);
		cartList.add(car);
	}

	private int cartHaveCheck(String id, int itemNum) {
		if (cartList.size() == 0)
			return -1;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getId().equals(id) && cartList.get(i).getItemNum() == itemNum) {
				return i;
			}
		}
		return -1;
	}

	public void addItem(String id) {
		cont = MallController.getInstance();
		item = ItemDAO.getInstance();
		String idx=item.showCate();
		if (idx.equals("back")) {
			cont.setNext("MemberMain");
		}
		item.buyItem(idx);
		while (true) {
			String sel = Util.getValueS("구매하실 아이템을 입력하세요");
			if (item.checkItem(sel) == null) {
				System.out.println("아이템 이름 오류 다시 입력해주세요");
				continue;
			}
			int ea = Util.getValueI("아이템 구매 수량", 1, 100);
			int index = item.checkItemNum(sel);
			//int cartNum,String id, int itemNum,int itemCnt
			if (cartHaveCheck(id, index) != -1) {
				int t =cartHaveCheck(id, index);
				int es = cartList.get(t).getItemCnt();
				cartList.get(index).setItemCnt(es + ea);
				System.out.printf("%s 기존 %d개에서 +%d개 추가하여 합 %d입니다\n", sel, es,ea,es + ea);
				break;
			}
			addCart(++cnt, id, index, ea);
			System.out.printf("[%s %d개 구매 완료]\n", sel, ea);
			break;
		}
		cont.setNext("MemberMain");
	}

	public void myCartCheck(int it) {
		item = ItemDAO.getInstance();
		int cnt = 0;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getItemNum() == it) {
				cnt++;
			}
		}
		if (cnt == 0) {
			return;
		}
		// int cartNum,String id, int itemNum,int itemCnt
		ArrayList<Cart> temp = cartList;
		cartList = new ArrayList<>();
		for (int i = 0; i < cartList.size(); i++) {
			if (temp.get(i).getItemCnt() != it) {
				Cart tes = new Cart(temp.get(i).getCartNum(), temp.get(i).getId(), temp.get(i).getItemNum(),
						temp.get(i).getItemCnt());
				cartList.add(tes);
			}
		}

	}

	public void showMyCart(String id) {
		item = ItemDAO.getInstance();
		int count = 0;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getId().equals(id)) {
				count++;
			}
		}
		if (count == 0) {
			System.out.println("장바구니가 비어있습니다");
			return;
		}
		ArrayList<Cart> tes = new ArrayList<Cart>();
		for (Cart c : cartList) {
			Cart teq = new Cart(c.getCartNum(), c.getId(), c.getItemNum(), c.getItemCnt());
			tes.add(teq);
		}
		int hap = 0;
		int s = 0;
		int iCnt=0;
		for (Item i : item.itemList) {
			for (Cart c : tes) {
				if (i.getItemNum() == c.getItemNum()) {
					System.out.printf("[%d]  %s(    %d원)    %d개 총 %d원\n", ++iCnt, i.getItemName(), i.getPrice(),
							c.getItemCnt(), i.getPrice() * c.getItemCnt());
					hap += c.getItemCnt() * i.getPrice(); // 총가격
					s += c.getItemCnt(); // 총개수
				}
			}
		}
		System.out.printf("총  %d 개  ( %d )원\n", s, hap);

	}

	public String cartSave() {
		if (cartList.size() == 0)
			return "";
		String data = "";
		for (Cart c : cartList) {
			data += c.saveCartData();
		}
		return data;
	}
	public void loadCart(String data) {
		String[] s=data.split("\n");
		cnt=s.length;
		if(cnt==0) {
			return;
		}
		cartList.clear();
		for(String t : s) {
			String[] info =t.split("/");
			Cart tes= new Cart(Integer.parseInt(info[0]),info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]));
			cartList.add(tes);
		}
	
	}
	public int cartSellCheck( int itemNum) {
		int hap=0;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getItemNum() == itemNum) {
				hap+=cartList.get(i).getItemCnt();
				}
		}
		return hap;
	}
}
