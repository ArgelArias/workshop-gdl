package com.recluit.lab.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import JDBC.DBConnect;
import JDBC.Loan;

import com.opensymphony.xwork2.ActionSupport;
import com.recluit.lab.restclient.RFCRestClient;

public class OperationEvaluateAction extends ActionSupport{
	
	private String operationQuery;
	private String loanQuery;
	private String result;
	private String bookResultTitle;
	private String bookResultAuthor;
	private String bank;
	private String rfc;
	private String name;
	private String address;
	private String loan;
	private String date;
	private String qualification;
	private String active;
	private int fquality = 0;
	ResultSet rs;
	RFCRestClient rfcRestClient;
	private ArrayList<Loan> loans = new ArrayList<Loan>();
	Scanner scanner;
	
	
	public String execute() throws Exception{
		if(operationQuery.equals("1")){
			DBConnect dBConnect = new DBConnect();
			Connection conn = dBConnect.connectToOracle();
			Statement stmt=null;
			try{
				stmt = conn.createStatement();
				String query = "SELECT ID, L.RFC, LOAN_AMOUNT, L.QUALIFICATION, EXPIRATION_DATE, STATUS, FNAME, ADDRESS FROM LOANS L JOIN CUSTOMER C ON L.RFC=C.RFC WHERE L.RFC='" + loanQuery + "'";
				System.out.println("asdasd");
				rs = stmt.executeQuery(query);
				while(rs.next()){	
					Loan loan = new Loan();
					loan.setID(rs.getInt(1));
					loan.setRFC(rs.getString(2));
					loan.setLOAN_AMOUNT(rs.getString(3));
					loan.setQUALIFICATION(rs.getString(4));
					loan.setEXPIRATION_DATE(rs.getString(5));
					loan.setSTATUS(rs.getString(6));
					loan.setNAME(rs.getString(7));
					loan.setADDRESS(rs.getString(8));
					loans.add(loan);
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "display";
		}
		if(operationQuery.equals("2")){
			DBConnect dBConnect = new DBConnect();
			Connection conn = dBConnect.connectToOracle();
			Statement stmt=null;
			try{
				stmt = conn.createStatement();
				String query = "SELECT ID, L.RFC, LOAN_AMOUNT, L.QUALIFICATION, EXPIRATION_DATE, STATUS, FNAME, ADDRESS FROM LOANS L JOIN CUSTOMER C ON L.RFC=C.RFC WHERE L.RFC='" + loanQuery + "' AND STATUS='Y'";
				System.out.println("asdasd");
				rs = stmt.executeQuery(query);
				while(rs.next()){	
					Loan loan = new Loan();
					loan.setID(rs.getInt(1));
					loan.setRFC(rs.getString(2));
					loan.setLOAN_AMOUNT(rs.getString(3));
					loan.setQUALIFICATION(rs.getString(4));
					loan.setEXPIRATION_DATE(rs.getString(5));
					loan.setSTATUS(rs.getString(6));
					loan.setNAME(rs.getString(7));
					loan.setADDRESS(rs.getString(8));
					loans.add(loan);
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "payment";
		}
		if(operationQuery.equals("3")){
			DBConnect dBConnect = new DBConnect();
			Connection conn = dBConnect.connectToOracle();
			Statement stmt=null;
			try{
				stmt = conn.createStatement();
				String query = "SELECT ID, L.RFC, LOAN_AMOUNT, L.QUALIFICATION, EXPIRATION_DATE, STATUS, FNAME, ADDRESS FROM LOANS L JOIN CUSTOMER C ON L.RFC=C.RFC JOIN PAYMENTS P ON P.LOAN_ID=L.ID WHERE L.RFC='" + loanQuery + "' AND STATUS='Y' AND REMAINING_AMOUNT=0";
				System.out.println("asdasd");
				rs = stmt.executeQuery(query);
				while(rs.next()){	
					Loan loan = new Loan();
					loan.setID(rs.getInt(1));
					loan.setRFC(rs.getString(2));
					loan.setLOAN_AMOUNT(rs.getString(3));
					loan.setQUALIFICATION(rs.getString(4));
					loan.setEXPIRATION_DATE(rs.getString(5));
					loan.setSTATUS(rs.getString(6));
					loan.setNAME(rs.getString(7));
					loan.setADDRESS(rs.getString(8));
					loans.add(loan);
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "close";
		}
		if(operationQuery.equals("4")){
			qualification = "";
			rfcRestClient = new RFCRestClient();
			result = rfcRestClient.findRFC(loanQuery);
			if(result != null){
				String[] arrayResult = result.split("%");
				for(int i=0; i<arrayResult.length-1; i++){
					System.out.println(arrayResult[i]);
					scanner = new Scanner(arrayResult[i]);
					scanner.useDelimiter("@");
					for(int j=0; j<6 ; j++)
						scanner.next();
					qualification = qualification.concat(scanner.next()).concat("@");
				}
				String[] qualifications = qualification.split("@");
				for(int i=0;i<qualifications.length;i++){
					if(qualifications[i].equals("VERY BAD")){
						fquality = fquality + 1;
					}
					if(qualifications[i].equals("BAD")){
						fquality = fquality + 2;
					}
					if(qualifications[i].equals("GOOD")){
						fquality = fquality + 3;
					}
					if(qualifications[i].equals("VERY GOOD")){
						fquality = fquality + 4;
					}
					if(qualifications[i].equals("EXCELLENT")){
						fquality = fquality + 5;
					}
				}
				fquality = fquality/qualifications.length;
				//System.out.println(fquality);
				if(fquality >= 3 || fquality == 0)
					return "approved";
				else
					return "denied";
			}
			return ERROR;
		}
		if(operationQuery.equals("-1"))
			return ERROR;
		return ERROR;
	}
	
	public void validate(){
		if(loanQuery.length()==0){
			addFieldError("loanQuery", "RFC Requerido");
		}
		if(loanQuery.length()<10 && loanQuery.length()>1){
			addFieldError("loanQuery", "RFC consta de 10 caracteres");
		}
		if(operationQuery.equals("-1")){
			addFieldError("operationQuery", "Selecciona una operacion");
		}
	}

	public String getOperationQuery() {
		return operationQuery;
	}

	public void setOperationQuery(String operationQuery) {
		this.operationQuery = operationQuery;
	}

	public String getLoanQuery() {
		return loanQuery;
	}

	public void setLoanQuery(String loanQuery) {
		this.loanQuery = loanQuery;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBookResultTitle() {
		return bookResultTitle;
	}

	public void setBookResultTitle(String bookResultTitle) {
		this.bookResultTitle = bookResultTitle;
	}

	public String getBookResultAuthor() {
		return bookResultAuthor;
	}

	public void setBookResultAuthor(String bookResultAuthor) {
		this.bookResultAuthor = bookResultAuthor;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoan() {
		return loan;
	}

	public void setLoan(String loan) {
		this.loan = loan;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public RFCRestClient getRfcRestClient() {
		return rfcRestClient;
	}

	public void setRfcRestClient(RFCRestClient rfcRestClient) {
		this.rfcRestClient = rfcRestClient;
	}
	
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

}
