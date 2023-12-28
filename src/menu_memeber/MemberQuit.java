package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class MemberQuit implements MenuCommand {

	private MallController mall = null;
	private MemberDAO memberDAO = null;

	@Override
	public void init() {
		mall = MallController.getInstance();
		memberDAO = MemberDAO.getInstance();
		System.out.println("=====[ 회원 " + mall.getLoginId() + "님의 정보 페이지 ]=====");

	}

	@Override
	public boolean update() {
		System.out.println("정말로 탈퇴하시겠습니까?");
		System.out.println("[1] 예");
		System.out.println("[2] 아니요");
		int sel = Util.getIntVal("메뉴 입력", 1, 2);
		if (sel == 1) {
			while (true) {
				String pw = Util.getStrVal("패스워드 입력");
				if (memberDAO.isValidMember(mall.getLoginId(), pw) == null) {
					System.out.println("잘못된 비밀번호입니다.");
					continue;
				}
				if(CartDAO.getInstance().removeCartByMId(mall.getLoginId())) {
					System.out.println("구매 내역이 삭제되었습니다.");
				}
				memberDAO.removeMember(mall.getLoginId());
				System.out.println("탈퇴가 완료되었습니다.");
				mall.setNext("MallMain");
				mall.setLoginId(null);
				break;
			}
		} else {
			mall.setNext("MemberMain");
		}
		return false;
	}

}
