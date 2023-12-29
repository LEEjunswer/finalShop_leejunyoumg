package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		showAdminItem();
		String name = Util.getValueS("추가하실 아이템 입력");
		if (checkItem(name) != null) {
			System.out.println("아이템or카테고리가 이 존재합니다");
			return;
		}
		String cate = Util.getValueS("카테고리를 입력하세요");
		int input = Util.getValueI("가격 입력", 100, 1000000);
		insertItem(cate, name, input);
		System.out.println("카테고리=" + cate + "아이템=" + name + "아이템 추가 완료");
	}

	public void delItemAdmin() {
		cart = CartDAO.getInstance();
		showItem();
		int input = Util.getValueI("삭제할 아이템 번호를 입력하세요", 1, itemList.size());
		System.out.printf("%s 아이템 삭제 완료", itemList.get(input - 1).getItemName());
		itemList.remove(input - 1);
		cart.myCartCheck(input - 1);

	}

	public void delItemMy(String id) {
		cart = CartDAO.getInstance();

	}

	public void showItem() {
		if (itemList.size() == 0)
			return;
		for (Item i : itemList) {
			System.out.println(i.toString());
		}
	}

	public void buyItem(String cate) {
		int index = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getCategoryName().equals(cate)) {
				index = i;
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
		List<String> li = new ArrayList();
		for (int i = 0; i < itemList.size(); i++) {
			if (!li.contains(itemList.get(i).getCategoryName())) {
				li.add(itemList.get(i).getCategoryName());
			}
		}
		int t = 0;
		System.out.println("========== 쇼핌몰에 오신것을 환영합니다===========");
		for (int i = 0; i < li.size(); i++) {
			System.out.printf("[%d] %s \n", ++t, li.get(i));
		}

		int choice = Util.getValueI("메뉴입력", 1, li.size());
		String name = "";
		name = li.get(choice - 1);
		return name;

	}

	public int checkItemNum(String ite) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(ite)) {
				return itemList.get(i).getItemNum();
			}

		}
		return -1;

	}

	public void checkSellCur() {
		cart = CartDAO.getInstance();
		if (cart.cartList.size() == 0) {
			System.out.println("판매한 제품이 없습니다");
			return;
		}

		Map<String, Integer> sellCur = new HashMap<String, Integer>();
		for (int i = 0; i < itemList.size(); i++) {
			int itemN = 0;
			itemN = cart.cartSellCheck(itemList.get(i).getItemNum());
			sellCur.put(itemList.get(i).getItemName(), itemN);
		}
		List<String> keySet = new ArrayList<>(sellCur.keySet());
		keySet.sort((o1, o2) -> sellCur.get(o2).compareTo(sellCur.get(o1)));

		for (String key : keySet) {
			for (Item i : itemList) {
				if (i.getItemName().equals(key)) {
					if (sellCur.get(key) != 0) {
						System.out.printf("%s %d개\n", i, sellCur.get(key));
						break;
					} else {
						break;
					}
				}
			}
		}
	}

	private void showAdminItem() {
		if (itemList.size() == 0) {
			System.out.println("판매하시는 아이템이 존재하지 않습니다 먼저 추가해주세요");
			return;
		}
		List<Item> temp = itemList;
		temp.stream().sorted(Comparator.comparing(Item::getCategoryName)).forEach(System.out::println);

	}

	public String itemSave() {
		if (itemList.size() == 0)
			return "";
		String data = "";
		for (Item i : itemList) {
			data += i.SaveItemData();
		}
		return data;
	}

	public void loadItem(String data) {
		String[] s = data.split("\n");
		int check = s.length;
		if (check == 0) {
			return;
		}
		itemList.clear();
		for (String t : s) {
			String[] info = t.split("/");
			Item tes = new Item(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]));
			itemList.add(tes);
		}

	}
}
