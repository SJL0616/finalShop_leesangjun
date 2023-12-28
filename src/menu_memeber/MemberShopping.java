package menu_memeber;

import java.util.ArrayList;
import java.util.List;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dto.Item;
import util.Util;

public class MemberShopping implements MenuCommand {

	private MallController mall = null;
	private ItemDAO itemDAO = null;
	private CartDAO cartDAO = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		itemDAO = ItemDAO.getInstance();
		cartDAO = CartDAO.getInstance();
		System.out.println("=====[ 쇼핑몰에 오신것을 환영합니다. ]=====");

	}

	@Override
	public boolean update() {
		
		if(itemDAO.isItemListEmpty()) {
			System.out.println("아이템 리스트가 없습니다.");
			mall.setNext("MemberMain");
			return false;
		}
		
		List<String> categoryList = itemDAO.getCategorys();
		int num = 1;
		for (String category : categoryList) {
			System.out.println("[" + num++ + "] " + category);
		}
		
		int idx = Util.getIntVal("카테고리 입력", 1, categoryList.size()) - 1;
		List<Item> itemList = itemDAO.getItemListByCate(categoryList.get(idx));
		num = 1;
		System.out.println("[" + categoryList.get(idx) + " 카테고리 상품 목록 ]");
		for (Item i : itemList) {
			System.out.println("[" + num++ + "] " + i.getItemName() + " " + i.getPrice());
		}
		int itemIdx = Util.getIntVal("구매 상품 번호 입력", 1, itemList.size()) - 1;

		int cnt = Util.getIntVal("구매 수량 입력", 1, 9999);

		if (!cartDAO.addCart(mall.getLoginId(), itemList.get(itemIdx).getItemNum(), cnt)) {
			System.out.println("구매 실패");
		} else {
			System.out.println("[ " + itemList.get(itemIdx).getItemName() + " " + cnt + "개 구매 완료 ]");
			mall.setNext("MemberMain");
		}
		return false;
	}

}
