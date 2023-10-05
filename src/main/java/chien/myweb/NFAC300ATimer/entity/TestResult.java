package chien.myweb.NFAC300ATimer.entity;

import java.sql.Timestamp;

public class TestResult {
	
	private int id;
	private String productType;
	private String poleNum;
	private String ammeter;
	private String testPerson;
	private String trip105;
	private String trip130;
	private String result;
	private Timestamp finishedTestTime;
	private String testMessage;
	
	public TestResult(int id, String productType, String poleNum, String ammeter, String testPerson, String trip105,
			String trip130, String result, Timestamp finishedTestTime, String testMessage) {
		super();
		this.id = id;
		this.productType = productType;
		this.poleNum = poleNum;
		this.ammeter = ammeter;
		this.testPerson = testPerson;
		this.trip105 = trip105;
		this.trip130 = trip130;
		this.result = result;
		this.finishedTestTime = finishedTestTime;
		this.testMessage = testMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPoleNum() {
		return poleNum;
	}

	public void setPoleNum(String poleNum) {
		this.poleNum = poleNum;
	}

	public String getAmmeter() {
		return ammeter;
	}

	public void setAmmeter(String ammeter) {
		this.ammeter = ammeter;
	}

	public String getTestPerson() {
		return testPerson;
	}

	public void setTestPerson(String testPerson) {
		this.testPerson = testPerson;
	}

	public String getTrip105() {
		return trip105;
	}

	public void setTrip105(String trip105) {
		this.trip105 = trip105;
	}

	public String getTrip130() {
		return trip130;
	}

	public void setTrip130(String trip130) {
		this.trip130 = trip130;
	}
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Timestamp getFinishedTestTime() {
		return finishedTestTime;
	}

	public void setFinishedTestTime(Timestamp finishedTestTime) {
		this.finishedTestTime = finishedTestTime;
	}

	public String getTestMessage() {
		return testMessage;
	}

	public void setTestMessage(String testMessage) {
		this.testMessage = testMessage;
	}
	
}
