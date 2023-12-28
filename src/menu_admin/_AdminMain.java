package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.FileDAO;
import util.Util;

public class _AdminMain implements MenuCommand {
	private MallController mall = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		System.out.println("==========[ 관리자 ]==========");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 회원 관리");
		System.out.println("[2] 상품 관리");
		System.out.println("[3] 게시판 관리");
		System.out.println("[4] 로그 아웃");
		System.out.println("[5] 파일 저장");
		System.out.println("[0] 종료");
		
		int sel = Util.getIntVal("메뉴 입력", 0, 5);
		if(sel == 1) {
			mall.setNext("AdminMember");
		}else if(sel == 2) {
			mall.setNext("AdminItem");
		}else if(sel == 3) {
			mall.setNext("AdminBoard");
		}else if(sel == 4) {
			mall.setNext("MallMain");
			mall.setLoginId(null);
		}else if(sel == 5) {
			mall.setNext("AdminFile");
		}else if(sel == 0) {
			mall.setNext(null);
		}

		return false;
	}

}
