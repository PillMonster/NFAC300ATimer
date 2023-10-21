package chien.myweb.NFAC300ATimer.dao;

import java.util.List;

import chien.myweb.NFAC300ATimer.entity.TestResult;

public interface TestResultDao {
	
	List<String> findProduct();
	List<String> findAmmeter();
	List<String> findTestPerson();
	String findTestDate();
	List<TestResult> findByMultiple(String productTypeSerial, String poleNumSerial, String ammeterSerial,
									String testPersonSerial, String resultSerial, String startDateTime, String endDateTime);
	int updateTestMessage(int id, String testMessage);
}
