package dto;

import java.util.Objects;

public class Cart implements Comparable<Cart>{
	private static int num;
	private int cartNum;
	private String id;
	private int itemNum;
	private int itemCnt;

	public Cart(int cartNum, String id, int itemNum, int itemCnt) {
		super();
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
		// TODO Auto-generated method stub
		return cartNum +"/"+id+"/"+itemNum+"/"+itemCnt;
	}

	@Override
	public int compareTo(Cart o) {
		if(o.getItemCnt() > this.itemCnt) {
			return 1;
		}else if(o.getItemCnt() < this.itemCnt) {
			return -1;
		}
		return 0;
	}
	

}
