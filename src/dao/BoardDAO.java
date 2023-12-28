package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dto.Board;
import dto.Member;
import util.Util;

public class BoardDAO {

	
	private ArrayList<Board> boardList;
	private final int MAX = 5;
	private int currPage;
	private int totalPage;
	private int start;
	private int end;
	// 보드의 번호용 변수 하나 추가해야함(삭제지 감소 X)
	private BoardDAO() {
		boardList = new ArrayList<Board>();
	}

	private static BoardDAO instance = new BoardDAO();

	static public BoardDAO getInstance() {
		return instance;
	}
	
	public void parseData(String[] data) {
		Board.setNum(data.length);
		for(String str : data) {
			String[] one = str.split("/");
			boardList.add(new Board(Integer.parseInt(one[0]), one[1], one[2], one[3], one[4], Integer.parseInt(one[5])));
		}
	}
	
	public void boardReset() {
		currPage = 1;
		totalPage = Board.getNum()%MAX  == 0 ? Board.getNum()/MAX : Board.getNum()/MAX+1;
	}
	
	public void showBoard() {
		
		System.out.println(" 총 게시글 "+Board.getNum());
		System.out.println(" 현재 페이지 "+currPage +" / "+totalPage+"]");
		start = (currPage-1)*MAX;
		end = start+MAX < Board.getNum() ? start+MAX : Board.getNum();
		for(int i = start; i < end; i++) {
			Board b = boardList.get(i);
			System.out.printf("(%2d) [ [%d] 제목 : %-8s 작성자 : %-6s 날짜 : %10s 조회수 : %d ]\n",i+1,b.getBoradNum(),b.getTitle(),b.getId(),b.getDate(),b.getHits());
		}
		
	}
	
	
	//특정 게시물 출력 메뉴
	public void showBoardByNum() {
		int num = Util.getIntVal("번호 입력", start+1, end);
		Board b = boardList.get(num-1);
		if(b != null) {
			b.setHits(b.getHits()+1); //조회수 1증가
			System.out.printf("(%2s) [ 제목 : %-8s 내용 : %-8s 작성자 : %-6s 날짜 : %10s 조회수 : %d ]\n",b.getBoradNum(),b.getTitle(),b.getContents(),b.getId(),b.getDate(),b.getHits());
		}
	}
	
	//특정 유저의 게시물 출력 메뉴
	public int showBoardById(String id) {
		int cnt = 0;
		for(Board b : boardList) {
			if(b.getId().equals(id) || id.equals("admin")) {
				cnt++;
				System.out.printf("(%2s) [ 제목 : %-8s 작성자 : %-6s 날짜 : %10s 조회수 : %d ]\n",b.getBoradNum(),b.getTitle(),b.getId(),b.getDate(),b.getHits());				
			}
		}
		return cnt;
	}
	
	public boolean removeByNum(int num,String id) {
		try {
			Board b = getBoardByNum(num); 
			if(!b.getId().equals(id) && !id.equals("admin")) {
				System.out.println("자신의 게시물만 삭제 가능합니다.");
				return false;
			}
			boardList.remove(getBoardByNum(num));
			Board.setNum(boardList.size());
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//게시물 번호로 해당 게시물 반환
	public Board getBoardByNum(int num) {
		for(Board b : boardList) {
			if(b.getBoradNum() == num) {
				return b;
			}
		}
		return null;
	}
	
	public void showNextPage() {
		currPage +=1;
		if(currPage > totalPage) {
			currPage = totalPage;
		}
	}
	
	public void showPrePage() {
		currPage -=1;
		if(currPage <= 0) {
			currPage = 1;
		}
	}
	
	
	//게시글 생성
	public boolean createBoard(String title, String contents, String id) {
		
		try {
			Board.setNum(Board.getNum()+1);
			Date nowDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strNowDate = dateFormat.format(nowDate);
			boardList.add(new Board(Board.getNum(), title, contents, id, strNowDate, 0));
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
		Board b = boardList.get(Board.getNum()-1);
		System.out.printf("(%2s) [ 제목 : %-8s 작성자 : %-6s 날짜 : %10s 조회수 : %d ]\n",b.getBoradNum(),b.getTitle(),b.getId(),b.getDate(),b.getHits());

		return true;
	}
	
	public String getStringData() {
		StringBuilder data = new StringBuilder();
		for(Board b : boardList) {
			data.append(b+"\n");
		}
		return data.toString();
	}
	
	 public boolean isBoardListEmpty() {
			
			return boardList.size() == 0;
	}
}
