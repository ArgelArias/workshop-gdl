package com.recluit.lab.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JDBC.DBConnect;

import com.opensymphony.xwork2.ActionSupport;

public class MakePaymentAction extends ActionSupport{
	
	private String payment;
	private String id2;
	ResultSet rs;
	
	
	public String execute() throws Exception{
		DBConnect dBConnect = new DBConnect();
		Connection conn = dBConnect.connectToOracle();
		Statement stmt=null;
		try{
			stmt = conn.createStatement();
//			System.out.println(query);
			String query = "SELECT * FROM PAYMENTS WHERE LOAN_ID='" + id2 + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()){
				query = "UPDATE PAYMENTS SET PAYMENT_DATE=sysdate+30, REMAINING_AMOUNT=(SELECT REMAINING_AMOUNT-" + payment + " FROM PAYMENTS WHERE LOAN_ID='" + id2 + "') WHERE LOAN_ID='" + id2 + "'";
			}
			else{
				query = "INSERT INTO PAYMENTS VALUES('"+ id2 + "', sysdate+30, 0, 1, (SELECT LOAN_AMOUNT-" + payment + " FROM LOANS WHERE ID='" + id2 + "'))";
			}
			rs = stmt.executeQuery(query);
		}catch(SQLException e){
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
		if(payment.length() == 0)
			addFieldError("payment", "Requerido");
		if(!payment.matches("[0-9]*"))
			addFieldError("payment", "Solo numeros");
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}
	
	
}
