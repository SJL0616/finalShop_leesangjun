package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import dto.Member;
import util.Util;

public class MallJoin implements MenuCommand {

	private MallController mall = null;

	@Override
	public void init() {
		System.out.println("=====[ 회원가입 ]=====");
		mall = MallController.getInstance();

	}

	@Override
	public boolean update() {
		
		MemberDAO dao = MemberDAO.getInstance();
		String id = Util.getStrVal("아이디 ");

		if (dao.getMemberById(id) != null) {
			System.out.println("이미 사용하는 아이디");
			return false;
		}

		String pw = Util.getStrVal("비밀번호 ");
		String name = Util.getStrVal("이름 ");

		if (dao.insertMember(id, pw, name)) {
			System.out.println("[ 회원 추가 완료 ]");
		} else {
			System.out.println("[ 회원 추가 실패]");
		}
		mall.setNext("MallMain");
		return false;
	}

}
