package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import util.Util;

public class MemberCart implements MenuCommand {

	private MallController mall = null;
	private ItemDAO itemDAO = null;
	private CartDAO cartDAO = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		itemDAO = ItemDAO.getInstance();
		cartDAO = CartDAO.getInstance();
		System.out.println("=====[ "+mall.getLoginId()+"님의 구매 내역 ]=====");

	}

	@Override
	public boolean update() {
		cartDAO.printMyCartListById(mall.getLoginId());
		System.out.println("=============================================");
		System.out.println("[1] 쇼핑하기");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getIntVal("메뉴 선택", 0, 2);
		if(sel == 1) {
			mall.setNext("MemberShopping");
		}else if (sel ==2) {
			mall.setNext("MemberMain");
		}else {
			mall.setNext(null);
		}
		return false;
	}

}
