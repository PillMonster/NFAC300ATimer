package chien.myweb.NFAC300ATimer.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chien.myweb.NFAC300ATimer.dao.TimerDao;
import chien.myweb.NFAC300ATimer.entity.TestInfo;
import chien.myweb.NFAC300ATimer.entity.TestResult;

@Service
public class TimerServiceImpl implements TimerService {

	@Autowired
	TimerDao timerDao;
	
	@Override
	public TestInfo findTestInfo() {
		return timerDao.findTestInfo();
	}
	
	@Override
	public int findRunState105() {
		return timerDao.findRunState105();
	}
	
	@Override
	public int findRunState130() {
		return timerDao.findRunState130();
	}
	
	@Override
	public int findRunStopState() {
		return timerDao.findRunStopState();
	}
	
	@Override
	public Timestamp findCloseDateTime() {
		return timerDao.findCloseDateTime();
	}

	@Override
	public String findCloseSecond() {
		return timerDao.findCloseSecond();
	}

	@Override
	public String findCurrentSecond() {
		return timerDao.findCurrentSecond();
	}
	
	@Override
	public List<TestResult> findAll(){
		return timerDao.findAll();
	}
}
