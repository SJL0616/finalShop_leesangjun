package menu_memeber.board;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class RemoveBoard implements MenuCommand {

	private MallController mall = null;
	private BoardDAO boardDAO = null;

	@Override
	public void init() {
		mall = MallController.getInstance();
		boardDAO = BoardDAO.getInstance();
		System.out.println("=========[ 게시글 삭제 ]=========");
		System.out.println("=        내 게시물 목록          =");
		
	}

	@Override
	public boolean update() {
		
		int cnt =boardDAO.showBoardById(mall.getLoginId());
		if(cnt == 0) {
			System.out.println("       --게시글이 없습니다.--        ");
		}
		
		System.out.println("[1] 삭제");
		System.out.println("[0] 뒤로가기");
		
		int sel = Util.getIntVal("메뉴 입력", 0, 1);
		if (sel == 1 && cnt > 0) {
			int num = Util.getIntVal("번호 입력", 1, Board.getNum());
			if(boardDAO.removeByNum(num,mall.getLoginId())) {
				System.out.println("삭제가 완료되었습니다.");
			}
		}else if(sel == 0){
			if(mall.getLoginId().equals("admin")) {
				mall.setNext("AdminBoard");
			}else {
				mall.setNext("MemberBoard");
			}
		}
		return false;
	}
}
