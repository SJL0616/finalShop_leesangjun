package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class MemberBoard implements MenuCommand{

	private MallController mall = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		System.out.println("=========[ 게시판 ]=========");

	}

	@Override
	public boolean update() {
		System.out.println("[1] 게시글보기");
		System.out.println("[2] 게시글추가");
		System.out.println("[3] 내게시글(삭제)");
		System.out.println("[4] 뒤로가기");
		System.out.println("[0] 종료");
		
		int sel = Util.getIntVal("메뉴 입력", 0, 4);
		if(sel == 1) {
			mall.setNext("ReadBoard");
		}else if(sel ==2) {
			mall.setNext("CreateBoard");
		}else if(sel ==3) {
			mall.setNext("RemoveBoard");
		}else if(sel ==4) {
			mall.setNext("MemberMain");
		}else if(sel ==0) {
			mall.setNext(null);
		}
		
		return false;
	}
}
