package com.recluit.lab.action;

import com.opensymphony.xwork2.ActionSupport;

public class PaymentAction extends ActionSupport{
	
	private String id;
	
	public String execute() throws Exception{
		return SUCCESS;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
