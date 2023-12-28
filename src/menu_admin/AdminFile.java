package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.FileDAO;
import util.Util;

public class AdminFile implements MenuCommand {
	private MallController mall = null;
	@Override
	public void init() {
		mall = MallController.getInstance();
		System.out.println("==========[ 파일 저장 ]==========");
	}

	@Override
	public boolean update() {
		
		if(FileDAO.getInstance().dataSave() != 4) {
			System.out.println("파일 저장 실패");
		}else {
			System.out.println("== 데이터 저장 완료 ==");
		}
		mall.setNext("AdminMain");
		return false;
	}

}
