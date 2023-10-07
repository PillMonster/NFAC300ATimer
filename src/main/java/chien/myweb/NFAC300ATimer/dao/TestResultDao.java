package chien.myweb.NFAC300ATimer.dao;

import java.util.List;

import chien.myweb.NFAC300ATimer.entity.TestResult;

public interface TestResultDao {
	
	List<String> findProduct();
	List<String> findAmmeter();
	List<String> findTestPerson();
	List<String> findTestDate();
	List<TestResult> findByMultiple(String productType, String poleNum, String ammeter, String testPerson, String result, String startDateTime, String endDateTime);
}
