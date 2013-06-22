package com.recluit.lab.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JDBC.DBConnect;
import JDBC.Loan;

import com.opensymphony.xwork2.ActionSupport;

public class NewAction extends ActionSupport{
	
	private String loanAmount;
	private String loanQuery;
	ResultSet rs;
	
	public String execute() throws Exception{
		DBConnect dBConnect = new DBConnect();
		Connection conn = dBConnect.connectToOracle();
		Statement stmt=null;
		try{
			stmt = conn.createStatement();
			String query = "SELECT FNAME, QUALIFICATION, ADDRESS FROM CUSTOMER WHERE RFC='" + loanQuery + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				StringBuilder strb = new StringBuilder("");
//				strb.append("111")
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void validate(){
		if(loanAmount.length() == 0)
			addFieldError("loanAmount", "Campo requerido");
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanQuery() {
		return loanQuery;
	}

	public void setLoanQuery(String loanQuery) {
		this.loanQuery = loanQuery;
	}
	
	
	
	
}
