package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.FileDAO;
import util.Util;

public class _MallMain implements MenuCommand {

	private MallController mall = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		
		
	}

	@Override
	public boolean update() {
		
		System.out.println("=====[ 쇼핑몰 ]=====");
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
		
		int sel = Util.getIntVal("메뉴 입력", 0, 2);
		if(sel == 0) {
			mall.setNext(null);
		}else if (sel ==1) {
			mall.setNext("MallJoin");
		}else if(sel ==2) {
			mall.setNext("MallLogin");
		}
		
		
		return false;
	}

}
