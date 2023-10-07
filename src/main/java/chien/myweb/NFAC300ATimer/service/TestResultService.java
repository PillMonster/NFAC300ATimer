package chien.myweb.NFAC300ATimer.service;

import java.util.List;


import chien.myweb.NFAC300ATimer.entity.TestResult;

public interface TestResultService {
	
	List<String> findProduct();
	List<String> findAmmeter();
	List<String> findTestPerson();
	List<String> findTestDate();
	List<TestResult> findTestResult(String productType, String poleNum, String ammeter, String testPerson, String result, String startDateTime, String endDateTime);
}
