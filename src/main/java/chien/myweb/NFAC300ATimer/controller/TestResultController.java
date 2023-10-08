package chien.myweb.NFAC300ATimer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chien.myweb.NFAC300ATimer.entity.RequestChecked;
import chien.myweb.NFAC300ATimer.entity.TestResult;
import chien.myweb.NFAC300ATimer.service.TestResultService;

@RequestMapping("/NFAC300A")
@Controller
public class TestResultController {
	
	@Autowired
	TestResultService testResultService;
	
	/*List<String> productTypeList = new ArrayList<>();
	List<String> ammeterList = new ArrayList<>();
	List<String> testPersonList = new ArrayList<>();
	List<String> finishedTestDateList = new ArrayList<>();  // 宣告List<泛型>集合*/
	
	List<TestResult> testResultList = new ArrayList<>();  // 宣告List<泛型>集合
	Map<String, Object> selectListMap = new HashMap<>(); // 宣告Map集合

	
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

	@PostMapping(value = "/viewjsonTestResult")
	@ResponseBody
	//public List<TestResult> jsonTestResult(HttpServletRequest request){
	public List<TestResult> jsonTestResult(@RequestBody List<RequestChecked> requestChecked){
		
		List<String> productTypeList = new ArrayList<>();
        List<String> poleNumList = new ArrayList<>();
        List<String> ammeterList = new ArrayList<>();
        List<String> testPersonList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        List<String> finishedTestDateList = new ArrayList<>();
		
		for (RequestChecked jsonData : requestChecked) {
            productTypeList = jsonData.getProductType();
            poleNumList = jsonData.getPoleNum();
            ammeterList = jsonData.getAmmeter();
            testPersonList = jsonData.getTestPerson();
            resultList = jsonData.getResult();
            finishedTestDateList = jsonData.getFinishedTestDate();
            
            System.out.println("====== From 前端 ======");
			System.out.println("ProductType from 前端: " + productTypeList);
	    	System.out.println("PoleNum from 前端: " + poleNumList);
	    	System.out.println("Ammeter from 前端: " +  ammeterList);
	    	System.out.println("TestPerson from 前端: " + testPersonList);
	    	System.out.println("Result from 前端: " + resultList);
	    	System.out.println("FinishedTestTime from 前端: " + finishedTestDateList);
	    	System.out.println("-----------------------");
        }
		
		
		List<TestResult> testResultList = testResultService.findTestResult(productTypeList, poleNumList, ammeterList, 
																			testPersonList, resultList, 
																			finishedTestDateList.get(0), finishedTestDateList.get(1));

		/*testResultList.forEach(p ->{
			System.out.println("====== From DB ======");
			System.out.println("ProductType from DB: " +p.getProductType());
	    	System.out.println("PoleNum from DB: " + p.getPoleNum());
	    	System.out.println("Ammeter from DB: " + p.getAmmeter());
	    	System.out.println("TestPerson from DB: " + p.getTestPerson());
	    	System.out.println("Result from DB: " + p.getResult());
	    	System.out.println("FinishedTestTime from DB: " + p.getFinishedTestTime());
	    	System.out.println("-----------------------");
		});*/
			
		new JSONObject(testResultList);
		return testResultList;
	}
	
	
	@GetMapping("/searchTestResult")
	public String showTestingPage() {	
		return "searchTestResult";
	}
}
