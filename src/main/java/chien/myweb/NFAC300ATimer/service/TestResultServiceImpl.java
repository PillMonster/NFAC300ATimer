package chien.myweb.NFAC300ATimer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		
		String minFinishedTestDate = testResultDao.findTestDate();
		String todayFinishedTestDate = "";
		
		List<String> fidishedTestDateList = new ArrayList<>();
		
		Date currentDate = new Date();

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // 創建一個一个 SimpleDateFormat 類別來定義日期的格式
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        // 解析日期字串為 Date 類別
        try {
			Date date = inputFormat.parse(minFinishedTestDate);

			minFinishedTestDate = outputFormat.format(date); // 格式化日期
			todayFinishedTestDate = outputFormat.format(currentDate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        fidishedTestDateList.add(minFinishedTestDate);
        fidishedTestDateList.add(todayFinishedTestDate);
        //System.out.println(todayFinishedTestDate);
		
		return fidishedTestDateList;
	}

	@Override
	public List<TestResult> findTestResult(List<String>productTypeList, List<String>poleNumList, List<String>ammeterList, 
											List<String>testPersonList, List<String>resultList, 
											String startDateTime, String endDateTime) {
		
		String productTypeSerial = addQuoteAndComma(productTypeList);
		String poleNumSerial = addQuoteAndComma(poleNumList);
		String ammeterSerial = addQuoteAndComma(ammeterList);
		String testPersonSerial = addQuoteAndComma(testPersonList);
		String resultSerial = addQuoteAndComma(resultList);
		
		return testResultDao.findByMultiple(productTypeSerial, poleNumSerial, ammeterSerial, testPersonSerial, 
											resultSerial, startDateTime, endDateTime);
	}
	
	public String addQuoteAndComma(List<String> inputList){
		
		List<String> quoteList = new ArrayList<>();
		
		for (String quote : inputList) {
			// List內每一個String, 添加單引號, 並包裝起來
			quoteList.add("'" + quote + "'");
        }
		
		String commaString = String.join(",", quoteList);
		return commaString;
	}
}
