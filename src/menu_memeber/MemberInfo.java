package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {

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
		System.out.println("[1] 비밀번호변경");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getIntVal("메뉴 입력", 0, 2);
		if (sel == 1) {
			while (true) {
				String pw = Util.getStrVal("기존 패스워드 입력");
				if (memberDAO.isValidMember(mall.getLoginId(), pw) == null) {
					System.out.println("잘못된 비밀번호입니다.");
					continue;
				}
				pw = Util.getStrVal("신규 패스워드 입력");
				if (!memberDAO.setNewPw(mall.getLoginId(), pw)) {
					System.out.println("오류");
					continue;
				}
				System.out.println("비밀번호가 변경되었습니다.");
				break;
			}
		} else if (sel == 2) {
			mall.setNext("MemberMain");
		} else if (sel == 0) {
			mall.setNext(null);
		}
		return false;
	}
}
