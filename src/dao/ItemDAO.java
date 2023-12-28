package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import controller.MallController;
import dto.Item;
import util.Util;

public class ItemDAO {
	MallController cont;
	ArrayList<Item> itemList;
	static private ItemDAO instance = new ItemDAO();
	CartDAO cart;
	int count;

	ItemDAO() {
		cart = CartDAO.getInstance();
		itemList = new ArrayList<Item>();
	}

	public static ItemDAO getInstance() {

		return instance;
	}

	private void insertItem(String cate, String item, int price) {
		Item mak = new Item(++count, cate, item, price);

		itemList.add(mak);
	}

	public String checkItem(String item) {
		if (itemList.size() == 0)
			return null;
		for (Item s : itemList) {
			if (s.getItemName().equals(item) || s.getCategoryName().equals(item)) {
				return item;
			}
		}

		return null;
	}

	public void addItem() {
		String name = Util.getValueS("추가하실 아이템 입력");
		if (checkItem(name) != null) {
			System.out.println("아이템or카테고리가 이 존재합니다");
			return;
		}
		String cate = Util.getValueS("카테고리를 입력하세요");
		int input = Util.getValueI("가격 입력[100-1000000]", 100, 1000000);
		insertItem(cate, name, input);
		System.out.println("카테고리=" + cate + "아이템=" + name + "아이템 추가 완료");
	}

	public void delItem() {
		showItem();
		int input = Util.getValueI("삭제할 아이템 번호를 입력하세요", 0, itemList.size()) - 1;
	}

	public void showItem() {
		if (itemList.size() == 0)
			return;
		for (Item i : itemList) {
			System.out.println(i.toString());
		}
	}

	public void buyItem(String cate) {
		int index=0;
		for(int i=0; i<itemList.size(); i++) {
			if(itemList.get(i).getCategoryName().equals(cate)) {
				index=i;
			}
		}
		int iCnt = 0;
		System.out.printf("[%s]의 아이템 목록\n", itemList.get(index).getCategoryName());
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getCategoryName().equals(itemList.get(index).getCategoryName())) {
				System.out.printf("[%d] %s  %d\n", ++iCnt, itemList.get(i).getItemName(), itemList.get(i).getPrice());
			}
		}

	}

	public String showCate() {
		cont=MallController.getInstance();
		List<String> li = new ArrayList();
		for(int i=0; i<itemList.size(); i++){
			if(!li.contains(itemList.get(i).getCategoryName())) {
				li.add(itemList.get(i).getCategoryName());
			}
		}
		int t=0;
		System.out.println("========== 쇼핌몰에 오신것을 환영합니다===========");
		for(int i=0; i<li.size(); i++) {
			System.out.printf("[%d] %s \n",++t,li.get(i));
		}
		System.out.println("[0]뒤로가기");
			int inputcate = Util.getValueI("메뉴입력", 0,li.size());
			if (inputcate == 0) {
				System.out.println("뒤로가기");
				cont.setNext("MemberMain");
				return "";
			}
			String name="";
			name=li.get(inputcate-1);
			return name;
			}
		

	

	public int checkItemNum(String ite) {
		for(int i=0; i<itemList.size(); i++) {
			if(itemList.get(i).getItemName().equals(ite)) {
				return i;
			}
			
	}
		return -1;

}

	void myItemCart() {

	}

}
