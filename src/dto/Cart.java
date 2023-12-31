package dto;

import java.util.Objects;

public class Cart {
	private static int num;
	private int cartNum;
	private String id;
	private int itemNum;
	private int itemCnt;

	public Cart(int cartNum,String id, int itemNum,int itemCnt) {
		this.cartNum = cartNum;
		this.id = id;
		this.itemNum = itemNum;
		this.itemCnt = itemCnt;
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		Cart.num = num;
	}

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getItemCnt() {
		return itemCnt;
	}

	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}

	@Override
	public String toString() {
		return "";
	}
	public String saveCartData() {
		return "%d/%s/%d/%d\n".formatted(cartNum,id,itemNum,itemCnt);
	}


	

}
