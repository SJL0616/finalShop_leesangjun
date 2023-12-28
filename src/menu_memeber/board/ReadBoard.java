package menu_memeber.board;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class ReadBoard implements MenuCommand {

	private MallController mall = null;
	private BoardDAO boardDAO = null;

	@Override
	public void init() {
		mall = MallController.getInstance();
		boardDAO = BoardDAO.getInstance();
		boardDAO.boardReset();
		System.out.println("=========[ 게시글 보기 ]=========");

	}

	@Override
	public boolean update() {

		if(boardDAO.isBoardListEmpty()) {
			System.out.println("게시글이 없습니다.");
			if(mall.getLoginId().equals("admin")) {
				mall.setNext("AdminBoard");
			}else {
				mall.setNext("MemberBoard");
			}
			return false;
		}
		while (true) {
			
			boardDAO.showBoard();

			System.out.println("[1] 이전");
			System.out.println("[2] 이후");
			System.out.println("[3] 게시글보기");
			System.out.println("[0] 뒤로가기");

			int sel = Util.getIntVal("메뉴 입력", 0, 3);
			if (sel == 1) {
				boardDAO.showPrePage();
			} else if (sel == 2) {
				boardDAO.showNextPage();
			} else if (sel == 3) {
				boardDAO.showBoardByNum();
			} else if (sel == 0) {
				if(mall.getLoginId().equals("admin")) {
					mall.setNext("AdminBoard");
				}else {
					mall.setNext("MemberBoard");
				}
				break;
			}
		}
		return false;
	}
}
