package chien.myweb.NFAC300ATimer.entity;

import java.util.List;
import java.util.Map;

public class RequestChecked {
	
	private List<String> productType;
    private List<String> poleNum;
    private List<String> ammeter;
    private List<String> testPerson;
    private List<String> result;
    private List<String> finishedTestDate;
    
	public RequestChecked(List<String> productType, List<String> poleNum, List<String> ammeter, List<String> testPerson,
			List<String> result, List<String> finishedTestDate) {
		super();
		this.productType = productType;
		this.poleNum = poleNum;
		this.ammeter = ammeter;
		this.testPerson = testPerson;
		this.result = result;
		this.finishedTestDate = finishedTestDate;
	}

	public List<String> getProductType() {
		return productType;
	}

	public void setProductType(List<String> productType) {
		this.productType = productType;
	}

	public List<String> getPoleNum() {
		return poleNum;
	}

	public void setPoleNum(List<String> poleNum) {
		this.poleNum = poleNum;
	}

	public List<String> getAmmeter() {
		return ammeter;
	}

	public void setAmmeter(List<String> ammeter) {
		this.ammeter = ammeter;
	}

	public List<String> getTestPerson() {
		return testPerson;
	}

	public void setTestPerson(List<String> testPerson) {
		this.testPerson = testPerson;
	}

	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

	public List<String> getFinishedTestDate() {
		return finishedTestDate;
	}

	public void setFinishedTestDate(List<String> finishedTestDate) {
		this.finishedTestDate = finishedTestDate;
	}
    
}
