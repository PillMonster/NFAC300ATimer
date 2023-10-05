package chien.myweb.NFAC300ATimer.service;

import java.sql.Timestamp;
import java.util.List;

import chien.myweb.NFAC300ATimer.entity.TestInfo;
import chien.myweb.NFAC300ATimer.entity.TestResult;

public interface TimerService {
	
	TestInfo findTestInfo();
	
	int findRunState105();
	int findRunState130();
	int findRunStopState();
	
	Timestamp findCloseDateTime();
	String findCloseSecond();
	String findCurrentSecond();

	List<TestResult> findAll();
	
	
	
}
