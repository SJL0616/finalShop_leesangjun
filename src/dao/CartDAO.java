package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

import dto.Cart;
import dto.Item;

public class CartDAO {

	private ArrayList<Cart> cartList;

	private CartDAO() {
		cartList = new ArrayList<Cart>();
	}

	private static CartDAO instance = new CartDAO();

	static public CartDAO getInstance() {
		return instance;
	}

	public void parseData(String[] data) {
		Cart.setNum(data.length);
		for (String str : data) {
			String[] one = str.split("/");
			cartList.add(
					new Cart(Integer.parseInt(one[0]), one[1], Integer.parseInt(one[2]), Integer.parseInt(one[3])));
			;
		}
	}

	public boolean addCart(String id, int itemNum, int itemCnt) {
		try {
			Cart.setNum(Cart.getNum() + 1);
			cartList.add(new Cart(Cart.getNum(), id, itemNum, itemCnt));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
		return true;
	}

	public void printMyCartListById(String id) {
		List<Cart> myCartList = cartList.stream().filter(i -> i.getId().equals(id)).collect(Collectors.toList());
		if (myCartList.size() == 0) {
			System.out.println(" -- 구매 내역이 없습니다 --");
			return;
		}

		Map<Integer, Integer> totalMap = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < myCartList.size(); i++) {
			Cart c = myCartList.get(i);
			if (totalMap.containsKey(c.getItemNum())) {
				int cnt = totalMap.get(c.getItemNum());
				totalMap.put(c.getItemNum(), c.getItemCnt() + cnt);
			} else {
				totalMap.put(c.getItemNum(), c.getItemCnt());
			}
		}

		Iterator<Integer> iter = totalMap.keySet().iterator();
		int num = 1;
		int totalCnt = 0;
		int totalPrice = 0;
		while (iter.hasNext()) {
			int key = iter.next();
			int val = totalMap.get(key);
			Item i = ItemDAO.getInstance().getItemByNum(key);
			totalCnt += val;
			int price = (i.getPrice() * val);
			totalPrice += price;
			System.out.printf("[%3s]\t%5s(%6d원) \t%d개 총 %d원\n", num++, i.getItemName(), i.getPrice(), val, price);
		}
		System.out.println("총 " + totalCnt + " 개 (" + totalPrice + "원)");
	}

	public boolean removeCartByMId(String id) {
		List<Cart> mcartList = cartList.stream().filter(i -> i.getId().equals(id)).collect(Collectors.toList());
		if (mcartList.size() == 0)
			return false;
		for (Cart c : mcartList) {
			cartList.remove(c);
		}
		return true;
	}
	
	public boolean removeCartByINum(int num) {
		List<Cart> mcartList = cartList.stream().filter(i -> i.getItemNum() == num ).collect(Collectors.toList());
		if (mcartList.size() == 0)
			return false;
		for (Cart c : mcartList) {
			cartList.remove(c);
		}
		return true;
	}
	//모든 아이템의 판매실적 출력
	public boolean printAllCart() {
		if(cartList.size() == 0) {
			System.out.println("구매 목록이 없습니다.");
			return false;
		}
		
		ItemDAO itemDAO = ItemDAO.getInstance();
		Map<Item, Integer> totalMap = new LinkedHashMap<Item, Integer>();
		for(int i = 0; i < cartList.size(); i++) {
			Cart c = cartList.get(i);
			Item item = itemDAO.getItemByNum(c.getItemNum());
			if(totalMap.containsKey(item)) {
				int cnt = totalMap.get(item);
				totalMap.put(item,c.getItemCnt()+cnt);
			}else {
				totalMap.put(item,c.getItemCnt());
			}
		}
		totalMap = sortMapByValue(totalMap);
		
		int num =1;
		for (Item i : totalMap.keySet()) {
			System.out.printf("[%3s] [%5s] [%5s] [%6d원] \t%d개 \n", num++,i.getCategoryName() ,i.getItemName(), i.getPrice(), totalMap.get(i));
		}
		return true;
	}
	
	public LinkedHashMap<Item,Integer> sortMapByValue(Map<Item, Integer> map){
		
		List<Map.Entry<Item, Integer>> entries = new LinkedList<>(map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Item, Integer>>() {
			@Override
			public int compare(Entry<Item, Integer> o1, Entry<Item, Integer> o2) {
				if (o1.getValue() - o2.getValue() == 0) {
					if (o1.getKey().getCategoryName().compareTo(o2.getKey().getCategoryName()) > 0) {
						return 1;
					} else if (o1.getKey().getCategoryName().compareTo(o2.getKey().getCategoryName()) < 0) {
						return -1;
					}
				}
				return o2.getValue() - o1.getValue();
			}
		});

		LinkedHashMap<Item, Integer> totalMap = new LinkedHashMap<Item, Integer>();
		for (Map.Entry<Item, Integer> entry : entries) {
			totalMap.put(entry.getKey(), entry.getValue());
		}
		return totalMap;
	}
	
	public String getStringData() {
		StringBuilder data = new StringBuilder();
		for(Cart c: cartList) {
			data.append(c+"\n");
		}
		return data.toString();
	}
	
	
	

}
