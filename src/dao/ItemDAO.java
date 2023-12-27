package dao;

import java.util.ArrayList;

import dto.Item;
import util.Util;

public class ItemDAO {
	ArrayList<Item> itemList;
	private static ItemDAO instance = new ItemDAO();

	ItemDAO() {
		itemList = new ArrayList<Item>();
	}

	public static ItemDAO getInstance() {

		return instance;
	}

	private void insertItem(String cate, String item, int price) {
		Item mak = new Item(cate, item, price);
		itemList.add(mak);
	}

	public String checkItem(String item) {
		if (itemList.size() == 0)
			return null;
		for (Item s : itemList) {
			if (s.getItemName().equals(item)) {
				return item;
			}
		}

		return null;
	}

	public void addItem() {
		String name = Util.getValueS("추가하실 아이템 입력");
		if (checkItem(name) != null) {
			System.out.println("아이템이 존재합니다");
			return;
		}
		String cate = Util.getValueS("카테고리를 입력하세요");
		int input = Util.getValueI("가격 입력[100-1000000]", 100, 1000000);
		insertItem(cate, name, input);
		System.out.println("카테고리=" + cate + "아이템=" + name + "아이템 추가 완료");
	}

	public void delItem() {
		showItem();
		int input=Util.getValueI("삭제할 아이템 번호를 입력하세요", 1, itemList.size());
	}
	
	public void showItem() {
		if(itemList.size()==0) return;
		for(Item i : itemList) {
			System.out.println(i.toString());
		}
	}
	public void showCate() {
		if(itemList.size()==0) return;
		for(int i=0; i<itemList.size(); i++) {
			System.out.println("["+i+1+"]"  +  itemList.get(i).getCategoryName());
		}
	}
	

}
