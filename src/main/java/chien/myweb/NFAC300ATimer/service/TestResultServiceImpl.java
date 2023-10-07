package chien.myweb.NFAC300ATimer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chien.myweb.NFAC300ATimer.dao.TestResultDao;
import chien.myweb.NFAC300ATimer.entity.TestResult;

@Service
public class TestResultServiceImpl implements TestResultService{
	@Autowired
	TestResultDao testResultDao;
	
	@Override
	public List<String> findProduct(){
		return testResultDao.findProduct();
	}
	
	@Override
	public List<String> findAmmeter(){
		return testResultDao.findAmmeter();
	}
	
	@Override
	public List<String> findTestPerson(){
		return testResultDao.findTestPerson();
	}
	
	@Override
	public List<String> findTestDate() {
		return testResultDao.findTestDate();
	}
	
	@Override
	public List<TestResult> findTestResult(String productType, String poleNum, String ammeter, String testPerson, String result, String startDateTime, String endDateTime) {
		return testResultDao.findByMultiple(productType, poleNum, ammeter, testPerson, result, startDateTime, endDateTime);
	}
}
