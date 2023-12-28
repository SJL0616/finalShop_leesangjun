package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MemberMain implements MenuCommand {

	private MallController mall = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		System.out.println("=====[ 회원 "+mall.getLoginId()+"님 ]=====");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 상품구매");
		System.out.println("[2] 구매내역");
		System.out.println("[3] 게시판");
		System.out.println("[4] 나의 정보");
		System.out.println("[5] 회원 탈퇴");
		System.out.println("[6] 로그 아웃");
		System.out.println("[0] 종료");
		
		int sel = Util.getIntVal("메뉴 입력", 0, 6);
		if(sel == 1) {
			mall.setNext("MemberShopping");
		}else if(sel == 2) {
			mall.setNext("MemberCart");
		}else if(sel == 3) {
			mall.setNext("MemberBoard");
		}else if(sel == 4) {
			mall.setNext("MemberInfo");
		}else if(sel == 5) {
			mall.setNext("MemberQuit");
		}else if(sel == 6) {
			mall.setNext("MallMain");
			mall.setLoginId(null);
		}else if(sel == 0) {
			mall.setNext(null);
		}
		
		
		return false;
	}
}
