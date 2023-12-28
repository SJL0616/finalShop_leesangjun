package menu_admin;

import java.util.Map;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import dto.Item;
import util.Util;

public class AdminItem implements MenuCommand {
	private MallController mall = null;
	private ItemDAO itemDAO = null;
	private CartDAO cartDAO = null;

	@Override
	public void init() {
		mall = MallController.getInstance();
		itemDAO = ItemDAO.getInstance();
		cartDAO = CartDAO.getInstance();
		System.out.println("==========[ 관리자 쇼핑몰 관리 ]==========");

	}

	@Override
	public boolean update() {
		System.out.println("[1] 아이템 추가");
		System.out.println("[2] 아이템 삭제");
		System.out.println("[3] 총 매출 아이템 갯수 출력(판매량 높은순으로)");
		System.out.println("[4] 뒤로가기");
		System.out.println("[0] 종료");

		int sel = Util.getIntVal("메뉴 입력", 0, 4);
		if (sel == 1) {
			System.out.println("--- 카테고리순으로 정렬됩니다 ---");
			if (!itemDAO.printAllItems()) {
				return false;
			}
			while (true) {
				String itemName = Util.getStrVal("신규 아이템 이름 입력 ");
				if (!itemDAO.isValidName(itemName)) {
					System.out.println("이름이 중복입니다.");
					continue;
				}
				String cateName = Util.getStrVal("카테고리 이름 입력 ");
				int price = Util.getIntVal("가격 [100 - 1000000]", 100, 1000000);

				if (!itemDAO.addNewItem(itemName, cateName, price)) {
					System.out.println("오류");
					return false;
				}
				System.out.println("아이템이 추가되었습니다.");
				break;
			}
		} else if (sel == 2) {

			System.out.println("--- 카테고리순으로 정렬됩니다 ---");
			if (!itemDAO.printAllItems()) {
				return false;
			}
			System.out.println("[-- 아이템 삭제시 구매 내역이 삭제됩니다. --]");
			int num = Util.getIntVal("삭제할 아이템 번호 입력", 1, Item.getNum());
			if (itemDAO.getItemByNum(num) == null) {
				System.out.println("없는 아이템 번호입니다.");
				return false;
			}
			if(cartDAO.removeCartByINum(num)) {
				System.out.println("구매내역이 삭제되었습니다.");
			}
			itemDAO.removeItem(num);
			System.out.println("아이템이 삭제되었습니다.");

		} else if (sel == 3) {
			if (!cartDAO.printAllCart()) {
				return false;
			}
		} else if (sel == 4) {
			mall.setNext("AdminMain");
		} else if (sel == 0) {
			mall.setNext(null);
		}
		return false;
	}
}
