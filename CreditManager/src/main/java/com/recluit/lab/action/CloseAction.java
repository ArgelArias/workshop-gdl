package com.recluit.lab.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import JDBC.DBConnect;
import JDBC.Loan;

import com.opensymphony.xwork2.ActionSupport;
import com.recluit.lab.restclient.BookRestClient;
import com.recluit.lab.restclient.RFCRestClient;

public class CloseAction extends ActionSupport{
	
	private String[] id;
	ResultSet rs;
	private String result;
	RFCRestClient rfcRestClient;
	
	public String execute() throws Exception{
		DBConnect dBConnect = new DBConnect();
		Connection conn = dBConnect.connectToOracle();
		Statement stmt=null;
		try{
			stmt = conn.createStatement();
			for(int i=0;i<id.length; i++){
				String query = "UPDATE LOANS SET STATUS='N' WHERE ID=" + id[i];
				rs = stmt.executeQuery(query);
				query = "SELECT L.RFC, FNAME, ADDRESS, LOAN_AMOUNT, to_char(EXPIRATION_DATE, 'dd/mm/yyyy'), L.QUALIFICATION FROM LOANS L JOIN CUSTOMER C ON L.RFC=C.RFC WHERE ID='" + id[i] + "'";
				rs = stmt.executeQuery(query);
				if(rs.next()){
					StringBuilder strb = new StringBuilder("");
					strb.append("edit|111|").append(rs.getString(1)).append("|").append(rs.getString(2)).append("|").append(rs.getString(3)).append("|").append(rs.getString(4)).append("|").append(rs.getString(5)).append("|").append(rs.getString(6)).append("|Y");
					String close = strb.toString().toUpperCase();
					System.out.println(close);
					rfcRestClient = new RFCRestClient();
					result = rfcRestClient.findRFC(close);
				}
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

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}
	
}

