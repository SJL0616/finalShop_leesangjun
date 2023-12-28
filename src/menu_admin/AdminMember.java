package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	private MallController mall = null;
	private MemberDAO memberDAO = null;

	@Override
	public void init() {
		mall = MallController.getInstance();
		memberDAO = MemberDAO.getInstance();
		System.out.println("==========[ 관리자 회원 목록 ]==========");

	}

	@Override
	public boolean update() {
		System.out.println("[1] 회원 목록");
		System.out.println("[2] 회원 삭제");
		System.out.println("[3] 뒤로가기");
		System.out.println("[0] 종료");

		int sel = Util.getIntVal("메뉴 입력", 0, 3);
		if (sel == 1) {
			if(memberDAO.printAllMembers()) {
				return false;
			}
		} else if (sel == 2) {
			if(!memberDAO.printAllMembers()) {
				return false;
			}
			String id = Util.getStrVal("삭제할 회원 아이디");
			if (id.equals("admin")) {
				System.out.println("관리자는 삭제할 수 없습니다.");
				return false;
			}
			if (memberDAO.getMemberById(id) == null) {
				System.out.println("없는 아이디입니다.");
				return false;
			}
			if (CartDAO.getInstance().removeCartByMId(id)) {
				System.out.println("구매 내역이 삭제되었습니다.");
			}
			if (!memberDAO.removeMember(id)) {
				System.out.println("삭제 실패");
				return false;
			}
			System.out.println("회원이 삭제되었습니다.");
		} else if (sel == 3) {
			mall.setNext("AdminMain");
		} else if (sel == 0) {
			mall.setNext(null);
		}
		return false;
	}
}
