package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAO {
	BoardDAO boardDao;
	CartDAO cartDao;
	ItemDAO itemDao;
	MemberDAO memberDao;

	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");

		private String name;

		FileName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	private FileDAO() {
	}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}

	private void saveFile(FileName name, String data) {
		Path path = Paths.get("src/files/" + name.getName());
		try (FileOutputStream fos = new FileOutputStream(path.toString());
				OutputStreamWriter ow = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(ow);) {
			bw.write(data);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String loadFile(FileName name) {

		Path path = Paths.get("src/files/"+name.getName());
StringBuilder data = new StringBuilder();
		
		try( FileInputStream fis = new FileInputStream(path.toString());
			 InputStreamReader ir = new InputStreamReader(fis);
			 BufferedReader br = new BufferedReader(ir);
				){
			String line;
			while((line= br.readLine()) != null) {
				data.append(line);
				data.append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return data.toString().substring(0, data.toString().length()-1);
	}
	

//	private void init() {
//
//		createFile(FileName.BOARD);
//		createFile(FileName.MEMBER);
//		createFile(FileName.ITEM);
//		createFile(FileName.CART);
//
//	}
	public void saveData() {
		boardDao = BoardDAO.getInstance();
		cartDao = CartDAO.getInstance();
		itemDao = ItemDAO.getInstance();
		memberDao = MemberDAO.getInstance();
		String saveBoardData = boardDao.boardSave();
		String saveCartData = cartDao.cartSave();
		String saveItemData = itemDao.itemSave();
		String saveMemberData = memberDao.memberSave();
		saveFile(FileName.BOARD, saveBoardData);
		saveFile(FileName.MEMBER, saveCartData);
		saveFile(FileName.ITEM, saveItemData);
		saveFile(FileName.CART, saveMemberData);
	}

	public void loadFile() {
		boardDao = BoardDAO.getInstance();
		cartDao = CartDAO.getInstance();
		itemDao = ItemDAO.getInstance();
		memberDao = MemberDAO.getInstance();
		String boardLoad = loadFile(FileName.BOARD);
		String cartLoad = loadFile(FileName.CART);
		String itemLoad = loadFile(FileName.ITEM);
		String memberLoad = loadFile(FileName.MEMBER);
		boardDao.loadBoard(boardLoad);
		cartDao.loadCart(cartLoad);
		itemDao.loadItem(itemLoad);
		memberDao.loadMember(memberLoad);
	}

}
