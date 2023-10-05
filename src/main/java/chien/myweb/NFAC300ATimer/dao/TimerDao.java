package chien.myweb.NFAC300ATimer.dao;

import java.sql.Timestamp;
import java.util.List;

import chien.myweb.NFAC300ATimer.entity.TestInfo;
import chien.myweb.NFAC300ATimer.entity.TestResult;

public interface TimerDao{

	Timestamp findCloseDateTime();
	String findCloseSecond();
	String findCurrentSecond();
	
	TestInfo findTestInfo();
	int findRunState105();
	int findRunState130();
	int findRunStopState();
	
	
	List<TestResult> findAll();
}
