package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MemberMain implements MenuCommand {
	MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("[1]상품구매\n[2]구매내역\n[3]게시판\n[4]나의 정보\n[5]회원 탈퇴\n[6]로그아웃\n[7]종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		int sel=Util.getValueI("메뉴 입력 ", 0, 6);
		if(sel==0) {
			System.out.println("종료");
			cont.setNext(null);
		}else if(sel==1) {
			
		}else if(sel==2) {
			
		}else if(sel==3) {
			
		}else if(sel==4) {
		
		}else if(sel==5) {
			
		}else {
			
		}
		
		
		return false;
	}
}