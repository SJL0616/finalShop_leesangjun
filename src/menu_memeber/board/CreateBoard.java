package menu_memeber.board;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class CreateBoard implements MenuCommand {

	private MallController mall = null;
	private BoardDAO boardDAO = null;

	@Override
	public void init() {
		mall = MallController.getInstance();
		boardDAO = BoardDAO.getInstance();
		System.out.println("=========[ 게시글 추가 ]=========");

	}

	@Override
	public boolean update() {

		String title = Util.getStrVal("게시글 제목 입력");
		String contents = Util.getStrVal("내용 입력");
		if (boardDAO.createBoard(title, contents, mall.getLoginId())) {
			System.out.println("게시글 추가 성공");
		} else {
			System.out.println("게시글 추가 실패");
		}

		mall.setNext("MemberBoard");
		return false;
	}
}
