package dao;

import java.util.ArrayList;

import dto.Board;
import dto.Item;
import dto.Member;

public class MemberDAO {
    private ArrayList<Member> memberList;
	
	private MemberDAO() {
		memberList = new ArrayList<Member>();
	}

	private static MemberDAO instance = new MemberDAO();

	static public MemberDAO getInstance() {
		return instance;
	}
	
	public void parseData(String[] data) {
		Member.setNum(data.length);
		for(String str : data) {
			String[] one = str.split("/");
			memberList.add(new Member(Integer.parseInt(one[0]), one[1], one[2], one[3]));
		}
	}
	
	public Member getMemberById(String id) {
		for(Member m : memberList) {
			if(m.getId().equals(id)) {
				return m;
			}
		}
		return null;
	}
	
	public Member isValidMember(String id, String pw) {
		for(Member m : memberList) {
			if(m.getId().equals(id) && m.getPw().equals(pw)) {
				return m;
			}
		}
		return null;
	}
	
	public boolean setNewPw(String id, String pw) {
		try {
			getMemberById(id).setPw(pw);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean insertMember(String id, String pw, String name) {
		try {
			Member.setNum(Member.getNum()+1);
			memberList.add(new Member(Member.getNum()+1000, id, pw, name));
		}catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}
	
	//회원 삭제 - 아이디 파라미터
	public boolean removeMember(String id) {
		try {
			Member m =getMemberById(id);
			if(m == null) {
				System.out.println("없는 아이디입니다.");
				return false;
			}
			memberList.remove(m);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	//관리자용 회원 목록 출력 
	public boolean printAllMembers() {
		if(memberList.size() == 0) {
			System.out.println("회원 정보가 없습니다.");
			return false;
		}
		
		for(Member m : memberList) {
			System.out.printf("[%5d] [%10s] [%10s] [%10s]\n",m.getMemberNum(),m.getId(),m.getPw(),m.getMemberName());
		}
		return true;
	}
	
	public String getStringData() {
		StringBuilder data = new StringBuilder();
		for(Member m : memberList) {
			data.append(m+"\n");
		}
		return data.toString();
	}
}
