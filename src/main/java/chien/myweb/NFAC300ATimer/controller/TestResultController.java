package chien.myweb.NFAC300ATimer.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chien.myweb.NFAC300ATimer.entity.TestResult;
import chien.myweb.NFAC300ATimer.service.TestResultService;

@RequestMapping("/NFAC300A")
@Controller
public class TestResultController {
	
	@Autowired
	TestResultService testResultService;
	
	List<String> productTypeList = new ArrayList<>();
	List<String> ammeterList = new ArrayList<>();
	List<String> testPersonList = new ArrayList<>();
	List<String> finishedTestDateList = new ArrayList<>();  // 宣告List<泛型>集合
	
	List<TestResult> testResultList = new ArrayList<>();  // 宣告List<泛型>集合
	Map<String, Object> selectListMap = new HashMap<>(); // 宣告Map集合
	
	
	ObjectMapper objectMapper = new ObjectMapper(); // 宣告物件, 使用ObjectMapper將字串轉換為JSON格式
	String jsonString; // 宣告變數
	
	@GetMapping("/viewjsonSelectList")
	@ResponseBody
	public Map<String, Object> jsonSelectList(){
		
		List<String> productTypeList = testResultService.findProduct();
		List<String> ammeterList = testResultService.findAmmeter();
		List<String> testPersonList = testResultService.findTestPerson();
		List<String> finishedTestDateList = testResultService.findTestDate();
		
		selectListMap.put("productType", productTypeList);
		selectListMap.put("ammeter", ammeterList);
		selectListMap.put("testPerson", testPersonList);
		selectListMap.put("finishedTestDate", finishedTestDateList);
		
		new JSONObject(selectListMap);
		
		return selectListMap;
	} 
	
	
	/*@GetMapping("/viewjsonAmmeter")
	@ResponseBody
	public List<String> jsonAmmeter(){
		List<String> ammeterList = testResultService.findAmmeter();
		new JSONObject(ammeterList);
		return ammeterList;
	}
	
	@GetMapping("/viewjsonTestPerson")
	@ResponseBody
	public List<String> jsonTestPerson(){
		List<String> testPersonList = testResultService.findTestPerson();
		new JSONObject(testPersonList);
		return testPersonList;
	}
	
	@GetMapping("/viewjsoTestDate")
	@ResponseBody
	public String jsonTestDate(){
		String TestDate = testResultService.findTestDate();
		// 創建一個JSON對象，將字串包裝在其中
		try {
			jsonString = objectMapper.writeValueAsString(TestDate);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}*/
	
	@PostMapping(value = "/viewjsonTestResult")
	@ResponseBody
	public List<TestResult> jsonTestResult(HttpServletRequest request){

		String productType = request.getParameter("productType");
		String poleNum = request.getParameter("poleNum");
		String ammeter = request.getParameter("ammeter");
		String testPerson = request.getParameter("testPerson");
		String result = request.getParameter("result");
		String startDateTime = request.getParameter("start-date");
		String endDateTime = request.getParameter("end-date");
		
		System.out.println("productType: " + productType);
		System.out.println("poleNum: " + poleNum);
		System.out.println("ammeter: " + ammeter);
		System.out.println("testPerson: " + testPerson);
		System.out.println("result: " + result);
		System.out.println("startDateTime: " + startDateTime);
		System.out.println("endDateTime: " + endDateTime);
	
		List<TestResult> testResultList = testResultService.findTestResult(productType, poleNum, ammeter, testPerson, result, startDateTime, endDateTime);
		
		/*testResultList.forEach(p ->{
			System.out.println("ProductType: " +p.getProductType());
	    	System.out.println("PoleNum: " + p.getPoleNum());
	    	System.out.println("------------");
		});*/
			
		new JSONObject(testResultList);
			
		return testResultList;
	}
	
	
	@GetMapping("/searchTestResult")
	public String showTestingPage() {	
		return "searchTestResult";
	}
}
