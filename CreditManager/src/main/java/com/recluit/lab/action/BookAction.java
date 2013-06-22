package com.recluit.lab.action;

import java.util.Scanner;

import com.opensymphony.xwork2.ActionSupport;
import com.recluit.lab.restclient.BookRestClient;
//import com.recluit.lab.model.Book;
//import com.recluit.lab.model.BookStore;

public class BookAction extends ActionSupport{

	private static final long serialVersionUID = -4582433504308602381L;
	
//	private BookStore bookStore;
	private String loanQuery;
	private String operationQuery;
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

	public String execute() throws Exception{
//		bookStore = new BookStore();
//		for(Book book : bookStore.getAllBooks()){
//			if(bookQuery.equals(book.getTitle())){
//				HelloClient helloClient = new HelloClient();
				BookRestClient bookRestClient = new BookRestClient();
				result = bookRestClient.findBookByTitle(loanQuery);
				if(result != null){
					Scanner scanner = new Scanner(result);
					scanner.useDelimiter("@");
					bank = scanner.next();
					rfc = scanner.next();
					name = scanner.next();
					address = scanner.next();
					loan = scanner.next();
					date = scanner.next();
					qualification = scanner.next();
					active = scanner.next();
//					bookResultAuthor = scanner.next();
					scanner.close();
//					bookResultTitle = book.getTitle();
//					bookResultAuthor = book.getAuthor();
					return SUCCESS;
				}
				else
					return ERROR;
//			}
//		}
//		return ERROR;
	}

//	public BookStore getBookStore() {
//		return bookStore;
//	}
//
//	public void setBookStore(BookStore bookStore) {
//		this.bookStore = bookStore;
//	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLoanQuery() {
		return loanQuery;
	}

	public void setLoanQuery(String bookQuery) {
		this.loanQuery = bookQuery;
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
	
	public String getOperationQuery() {
		return operationQuery;
	}

	public void setOperationQuery(String oprarationQuery) {
		this.operationQuery = oprarationQuery;
	}
	
	
}
