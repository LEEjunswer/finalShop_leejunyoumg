package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.MallController;
import dto.Board;
import dto.Item;
import util.Util;

public class BoardDAO {
	ArrayList<Board> boardList;
	MallController cont;
	int cnt;
	int num;
	int forwardPage = 1;
	int backPage = 5;
	static private BoardDAO instance = new BoardDAO();
	LocalDate now = LocalDate.now();

	BoardDAO() {
		boardList = new ArrayList<Board>();
		cnt = boardList.size();
		cont = MallController.getInstance();
	}

	public static BoardDAO getInstance() {
		return instance;
	}

	// int boradNum, String title, String id, String date, String contents, int hits
	private void addBoard(int boardNum, String title, String id, String date, String contents, int hits) {
		Board board = new Board(boardNum, title, id, date, contents, hits);
		boardList.add(board);
		System.out.println("게시글 추가완료");
	}

	public void makeBoard(String id) {
		String title = Util.getValueS("추가하실 게시글 제목을 입력하세요");
		String content = Util.getValueS("추가하실 게시글 내용을 입력하세요");
		String date = "" + now;
		addBoard(++num, title, cont.getLoginId(), date, content, 0);
	}

	public void show() {
		if (boardList.size() == 0) {
			System.out.println("게시글이 존재하지않습니다");
			return;
		}
		System.out.printf("총 게시글 %d개 \n", boardList.size());
		System.out.println("페이지"); // 미구현
		while (true) {
			for (int i = forwardPage - 1; i < backPage; i++) {
				System.out.printf(boardList.get(i).toString());
				if (i >= boardList.size() - 1) {
					break;
				}

			}
			System.out.println("[1]이전\n[2]이후\n[3]게시글보기\n[0]뒤로가기");
			int sel = Util.getValueI("메뉴입력", 0, 3);
			if (sel == 1) {
				if (forwardPage == 1) {
					System.out.println("이전페이지 존재안함");
					continue;
				}
				forwardPage -= 5;
				backPage -= 5;
			} else if (sel == 2) {
				if (backPage >= boardList.size()) {
					System.out.println("마지막 페이지입니다");
					continue;
				}
				forwardPage += 5;
				backPage += 5;
			} else if (sel == 3) { // 게시글 보기
				lookBoard();
			} else {
				System.out.println("종료");
				break;
			}
		}
	}

	public void myBoard(String id) {
		int i = 0;
		for (Board b : boardList) {
			if (b.getId().equals(id)) {
				System.out.printf("[%d]" + b.MyString() + "\n", ++i);
			}
		}
		System.out.println("[1]삭제\n[0]돌아가기");
		int sel = Util.getValueI("메뉴 입력", 0, 1);
		if (sel == 1) {
			delBoard(cont.getLoginId());
		} else {
			System.out.println("돌아가기");
			cont.setNext("MemberBoard");
		}

	}

	boolean hasData() {
		if (boardList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다");
			return false;
		}
		return true;
	}

	private void delBoard(String id) {
		if (!hasData())
			return;
		int sel = Util.getValueI("삭제할 게시글 번호 입력", forwardPage, boardList.size());
		int c = sel - 1;
		for (int i = 0; i < cnt; i++) {
			if (boardList.get(i).getId().equals(id)) {
				if (c == i) {
					boardList.remove(c);
					System.out.println("삭제 완료");
					i--;
					return;
				}
			}
		}
		System.out.println("본인 게시글만 삭제할 수 있습니다");
	}

	private void lookBoard() {
		int sel = Util.getValueI("게시글 번호 입력", forwardPage, boardList.size());
		int hit = boardList.get(sel - 1).getHits();
		boardList.get(sel - 1).setHits(++hit);
		System.out.println(boardList.get(sel - 1).toString());
		System.out.println("--------");
		System.out.println(boardList.get(sel - 1).content());
		cont.setNext("MemberBoard");
	}

	public String boardSave() {
		if (boardList.size() == 0)
			return "";
		String data = "";
		for (Board b : boardList) {
			data += b.saveBoardData();
		}
		return data;
	}

	public void loadBoard(String data) {
		String[] s = data.split("\n");
		for (String t : s) {
			System.out.println(t);
		}
		num = s.length;
		System.out.println(cnt);
		if (cnt == 0) {
			return;
		}
		boardList.clear();
		for (int i = 0; i < cnt; i++) {
			String[] info = s[i].split("/");
			Board tes = new Board(Integer.parseInt(info[0]), info[1], info[2], info[3], info[4],
					Integer.parseInt(info[5]));
			boardList.add(tes);
		}

	}

	public void adminDelBoard() {
		if (!hasData())
			return;
		int sel = Util.getValueI("삭제할 게시글 번호 입력", forwardPage, boardList.size());
		boardList.remove(sel-1);
		System.out.println("삭제완료");
			
	}

}