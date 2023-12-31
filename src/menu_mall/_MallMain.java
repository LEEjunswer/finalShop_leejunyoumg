package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MallMain implements MenuCommand {
	private MallController cont;
	

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("====[ 쇼핑몰 ]=====");
		System.out.println("[1]회원가입\n[2]로그인\n[0]종료");
		System.out.println("==============");
	}

	@Override
	public boolean update() {
		int sel=Util.getValueI("메뉴를 입력하세요", 0, 2);
		if(sel==0){
			cont.setNext(null);
		}else if(sel==1) { // 회원가입 
			cont.setNext("MallJoin");
		}else {
			cont.setNext("MallLogin");
		}
		return false;
	}

}
