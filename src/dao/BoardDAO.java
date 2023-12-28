package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.MallController;
import dto.Board;
import util.Util;

public class BoardDAO {
	ArrayList<Board> boardList;
	MallController cont;
	int cnt;
	int num;
	int forwardPage = 1;
	int backPage = 5;
	LocalDate now = LocalDate.now();

	BoardDAO() {
		boardList = new ArrayList<Board>();
		cnt = boardList.size();
		cont = MallController.getInstance();
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
		System.out.printf("총 게시글 %d개\n", cnt);
		System.out.println("페이지"); // 미구현
		while (true) {
			for (int i = forwardPage-1; i < backPage; i++) {
				System.out.println(boardList.toString());
				if (forwardPage >= cnt) {
					break;
				}
			}
			System.out.println("[1]이전\n[2]이후\n[3]게시글보기\n[0]종료");
			int sel = Util.getValueI("메뉴입력", 0, 3);
			if (sel == 1) {
				if (forwardPage == 1) {
					System.out.println("이전페이지 존재안함");
					continue;
				}
				forwardPage -= 5;
				backPage -= 5;
			} else if (sel == 2) {
				if (backPage >= cnt) {
					System.out.println("마지막 페이지입니다");
					continue;
				}
				forwardPage += 5;
				backPage += 5;
			} else if (sel == 3) {	 //게시글 보기
				lookBoard();
			} else {
				System.out.println("종료");
				cont.setNext(null);
			}
		}
	}

	public void myBoard(String id) {
		int i = 0;
		for (Board b : boardList) {
			if (b.getId().equals(id)) {
				System.out.printf("[%d]" + b.MyString()+"\n", ++i);
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
		int sel = Util.getValueI("삭제할 게시글 번호 입력", forwardPage, backPage) - 1;
		for (int i = 0; i < cnt; i++) {
			if (boardList.get(i).getId().equals(id)) {
				if (sel == i) {
					boardList.remove(i);
					System.out.println("삭제 완료");
					i--;
					return;
				}
			}
		}
		System.out.println("본인 게시글만 삭제할 수 있습니다");
	}
	private void lookBoard() {
		int sel=Util.getValueI("게시글 번호 입력", 0, 5)-1;
		int hit=boardList.get(sel).getHits();
		boardList.get(sel).setHits(++hit);
		System.out.println(boardList.get(sel).toString());
		System.out.println("--------");
		System.out.println(boardList.get(sel).content());
		cont.setNext("MemberBoard");
	}

}