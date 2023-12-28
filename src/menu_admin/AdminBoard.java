package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard implements MenuCommand {

	private MallController mall = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		System.out.println("=========[관리자용 게시판 ]=========");

	}

	@Override
	public boolean update() {
		System.out.println("[1] 게시글 보기");
		System.out.println("[2] 게시글 삭제");
		System.out.println("[3] 뒤로가기");
		System.out.println("[0] 종료");
		
		int sel = Util.getIntVal("메뉴 입력", 0, 3);
		if(sel == 1) {
			mall.setNext("ReadBoard");
		}else if(sel ==2) {
			mall.setNext("RemoveBoard");
		}else if(sel ==3) {
			mall.setNext("AdminMain");
		}else if(sel ==0) {
			mall.setNext(null);
		}
		
		return false;
	}

}
