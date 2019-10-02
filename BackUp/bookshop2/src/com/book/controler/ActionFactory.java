package com.book.controler;

import com.book.controler.action.Action;
import com.book.controler.action.CartDeleteAction;
import com.book.controler.action.CartInsertAction;
import com.book.controler.action.CartListAction;
import com.book.controler.action.ContractAction;
import com.book.controler.action.FindZipNumAction;
import com.book.controler.action.IdCheckFormAction;
import com.book.controler.action.IndexAction;
import com.book.controler.action.JoinAction;
import com.book.controler.action.JoinFormAction;
import com.book.controler.action.LoginAction;
import com.book.controler.action.LoginFormAction;
import com.book.controler.action.LogoutAction;
import com.book.controler.action.OrderInsertAction;
import com.book.controler.action.OrderListAction;
import com.book.controler.action.ProductDetailAction;
import com.book.controler.action.ProductKindAction;

public class ActionFactory { //요청 받은 객체를 생성하는 공장 
	private ActionFactory() {}
	private static ActionFactory instance=new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory : "+command);
		if(command.equals("index")) {
			action = new IndexAction();
		}else if(command.equals("product_detail")) {
			action = new ProductDetailAction();
		}else if(command.equals("category")) {
			action = new ProductKindAction();
		}else if(command.equals("login_form")) {
			action = new LoginFormAction();
		}else if(command.equals("login")) { //DAO쪽으로 연결됨
			action = new LoginAction();
		}else if(command.equals("logout")) {
			action = new LogoutAction();	
		}else if(command.equals("contract")) {
			action = new ContractAction();	
		}else if(command.equals("join_form")) {
			action = new JoinFormAction();
		}else if(command.equals("id_check_form")) {
			action = new IdCheckFormAction();
		}else if(command.equals("find_zip_num")) {
			action = new FindZipNumAction();	
		}else if(command.equals("join")) { //DAO쪽으로 연결됨
			action = new JoinAction();
		}else if(command.equals("cart_insert")) {
			action = new CartInsertAction();
		}else if(command.equals("cart_list")) { 
			action = new CartListAction();
		}else if(command.equals("cart_delete")) { 
			action = new CartDeleteAction();
		}else if(command.equals("order_insert")) { 
			action = new OrderInsertAction();
		}else if(command.equals("order_list")) { 
			action = new OrderListAction();
		}
		return action;
	}
}
