package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class MemberQuit implements MenuCommand{
	MallController cont;
	MemberDAO dao;
	@Override
	public void init() {
		cont=MallController.getInstance();
		System.out.printf("======[%s회원탈퇴]======\n",cont.getLoginId());
		System.out.println("회원탈퇴시 구매 내역이 사라집니다");
		System.out.println("정말 탈퇴하시겠습니까?");
		System.out.println("[1]예\n[2]뒤로가기\n[0]종료");
	}

	@Override
	public boolean update() {
		int sel=Util.getValueI("메뉴입력", 0, 2);
		if(sel==0) {
			System.out.println("종료");
			cont.setNext(null);
		}else if(sel==1) {
			dao.myAccountDel(cont.getLoginId());
		}else {
			System.out.println("뒤로가기");
			cont.setNext("MemberMain");
		}
		return false;
	}
	
}
