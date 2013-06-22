package JDBC;

public class Loan {
	private int ID;
	private String RFC;
	private String NAME;
	private String ADDRESS;
	private String LOAN_AMOUNT;
	private String QUALIFICATION;
	private String EXPIRATION_DATE;
	private String STATUS;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	public String getLOAN_AMOUNT() {
		return LOAN_AMOUNT;
	}
	public void setLOAN_AMOUNT(String lOAN_AMOUNT) {
		LOAN_AMOUNT = lOAN_AMOUNT;
	}
	public String getQUALIFICATION() {
		return QUALIFICATION;
	}
	public void setQUALIFICATION(String qUALIFICATION) {
		QUALIFICATION = qUALIFICATION;
	}
	public String getEXPIRATION_DATE() {
		return EXPIRATION_DATE;
	}
	public void setEXPIRATION_DATE(String eXPIRATION_DATE) {
		EXPIRATION_DATE = eXPIRATION_DATE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

}