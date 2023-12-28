package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MallLogin implements MenuCommand {
	private MallController mall = null;

	@Override
	public void init() {
		System.out.println("=====[ 로그인 ]=====");
		mall = MallController.getInstance();

	}

	@Override
	public boolean update() {

		MemberDAO dao = MemberDAO.getInstance();

		String id = Util.getStrVal("아이디 ");
		String pw = Util.getStrVal("페스워드 ");
	
		if (dao.isValidMember(id, pw)!=null || (id.equals("admin") && pw.equals("admin"))) {
			if (id.equals("admin")) {
				mall.setLoginId("admin");
				mall.setNext("AdminMain");
			} else {
				mall.setLoginId(id);
				mall.setNext("MemberMain");
			}
			System.out.println("[ 로그인 성공 ]");
		} else {
			System.err.println("아이디 혹은 비밀번호를 확인해주세요");
			mall.setNext("MallMain");
		}
		return false;
	}

}
