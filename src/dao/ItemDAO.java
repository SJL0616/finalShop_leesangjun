package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import dto.Board;
import dto.Item;
import dto.Member;

public class ItemDAO {
	
    private ArrayList<Item> itemList;
	
	private ItemDAO() {
		itemList = new ArrayList<Item>();
	}

	private static ItemDAO instance = new ItemDAO();

	static public ItemDAO getInstance() {
		return instance;
	}
	
	public void parseData(String[] data) {
		Item.setNum(data.length);
		for(String str : data) {
			String[] one = str.split("/");
			itemList.add(new Item(Integer.parseInt(one[0]), one[1], one[2], Integer.parseInt(one[3])));
		}
		
	}
	
	public List<String> getCategorys() {
		List<String> temp = itemList.stream().map(i->i.getCategoryName()).collect(Collectors.toList());
		return temp.stream().distinct().toList();
	}
	
	
	public List<Item> getItemListByCate(String category) {
		return itemList.stream().filter(i -> i.getCategoryName().equals(category)).collect(Collectors.toList());
	}
	
	public Item getItemByNum(int num) {
		List<Item> list = itemList.stream().filter(i -> i.getItemNum() == num).collect(Collectors.toList());
		
		return list.size() != 0 ? list.get(0) : null;
	}
	
	public boolean printAllItems() {
		if(itemList.size() == 0) {
			System.out.println("등록된 상품 정보가 없습니다.");
			return false;
		}
		List<Item> list = itemList;
		// 1 2 비교할 때 결과=> -1 그대로 | 1 순서 바뀜 (오름차순 역이용- > 내림차순)
 		Collections.sort(list, (i1, i2) -> i2.getCategoryName().compareTo(i1.getCategoryName()));
		for(Item i : list) {
			System.out.printf("[%3d] [%5s] [%6s] [%10d원]\n", i.getItemNum(), i.getCategoryName(), i.getItemName(),i.getPrice());
		}
		return true;
	}
	
	//아이템 이름 중복검사
	public boolean isValidName(String itemName) {
		return !itemList.stream().map(i->i.getItemName()).collect(Collectors.toList()).contains(itemName);
	}
	
	//아이템 추가 메서드
	public boolean addNewItem(String name, String category,int price) {
		try {
			Item.setNum(Item.getNum()+1);
			itemList.add(new Item(Item.getNum(), category, name, price));
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//아이템 추가 메서드
	public boolean removeItem(int num) {
		try {
			itemList.remove(getItemByNum(num));
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getStringData() {
		StringBuilder data = new StringBuilder();
		for(Item i: itemList) {
			data.append(i+"\n");
		}
		return data.toString();
	}
	
    public boolean isItemListEmpty() {
		
		return itemList.size() == 0;
	}
}
