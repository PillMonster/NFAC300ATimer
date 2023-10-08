package chien.myweb.NFAC300ATimer.service;

import java.util.List;


import chien.myweb.NFAC300ATimer.entity.TestResult;

public interface TestResultService {
	
	List<String> findProduct();
	List<String> findAmmeter();
	List<String> findTestPerson();
	List<String> findTestDate();
	List<TestResult> findTestResult(List<String>productTypeList, List<String>poleNumList, List<String>ammeterList, 
									List<String>testPersonList, List<String>resultList, 
									String startDateTime, String endDateTime);
}
