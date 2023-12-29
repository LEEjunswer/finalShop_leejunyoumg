package dto;


public class Board {
	private static int num;

	private int boardNum;
	private String title;
	private String id;
	private String date;
	private String contents;
	private int hits;
	
	public Board(int boardNum, String title, String id, String date, String contents, int hits) {
		this.boardNum = boardNum;
		this.title = title;
		this.id = id;
		this.date = date;
		this.contents = contents;
		this.hits = hits;
	}



	
	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		Board.num = num;
	}

	public int getBoradNum() {
		return boardNum;
	}

	public void setBoradNum(int boradNum) {
		this.boardNum = boradNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}
	@Override
	public String toString() {
		return "(%d) [제목 : %s   작성자:%s  날짜:%s 조회수:%d]\n".formatted(boardNum,title,id,date,hits);
	}
	public String MyString() {
		return "[제목 : %s   작성자:%s  날짜:%s  조회수:%d]".formatted(title,id,date,hits);
	}
	
	public String content() {
		return "%s".formatted(contents);
	}
	public String saveBoardData() {
		
		return "%d/%s/%s/%s/%s/%d\n".formatted(boardNum,title,id,date,contents,hits);
	}


}
