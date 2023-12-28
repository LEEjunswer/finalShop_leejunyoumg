package dto;

public class Item {
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;

	public Item(int itemNum,String categoryName, String itemName, int price) {
		this.itemNum=itemNum;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		Item.num = num;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[%d] [%s] [%s] [%d원]".formatted(itemNum,categoryName,itemName,price); 
	}
	
	public String buyItem() {
		return "%s    %d".formatted(itemName,price);
	}
	
	public String cate() {
		
		return "%s\n".formatted(categoryName);
	}
	
	public String myBuyitem() {
		return " %s(    %d원)    ".formatted(itemName,price);
	}

}
