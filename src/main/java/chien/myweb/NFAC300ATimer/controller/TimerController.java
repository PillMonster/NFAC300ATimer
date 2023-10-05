package chien.myweb.NFAC300ATimer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chien.myweb.NFAC300ATimer.entity.TestInfo;
import chien.myweb.NFAC300ATimer.entity.TestResult;
import chien.myweb.NFAC300ATimer.service.TimerService;

@Component
@RequestMapping("/NFAC300A")
@Controller
public class TimerController {
	
	@Autowired
	TimerService timerService;
	
	@Autowired
	LineMessagingClient lineMessagingClient;
	
	@Value("${line.bot.channel-token}")
	private String lineBotAccessToken;

	Map<String, Integer> testDateTimeMap = new HashMap<>(); // 宣告Map集合
	List<TestResult> testResultList = new ArrayList<>();  // 宣告List<泛型>集合
	
	TestInfo testInfo = new TestInfo();  // 宣告物件
	ObjectMapper objectMapper = new ObjectMapper(); // 宣告物件, 使用ObjectMapper將字串轉換為JSON格式
	
    String jsonString; // 宣告變數
    String currentSecond; // 宣告變數
	
	@GetMapping("/viewjsonTestInfo")
	@ResponseBody
	public TestInfo jsonTestInfo(HttpServletRequest request){
		testInfo = timerService.findTestInfo();
		new JSONObject(testInfo);
		return testInfo;
	}
	
	@GetMapping("/viewjsonRunState")
	@ResponseBody
	public Map<String, Integer> jsonRunState(){

		int runState105 = timerService.findRunState105();
		int runState130 = timerService.findRunState130();
		int runStopState = timerService.findRunStopState();

		testDateTimeMap.put("runState105", runState105);
		testDateTimeMap.put("runState130", runState130);
		testDateTimeMap.put("runStopState", runStopState);
		new JSONObject(testDateTimeMap);
		
		return testDateTimeMap;
	}
	
	@GetMapping("/viewjsonTimer")
	@ResponseBody
	public String jsonTimer(){
		currentSecond = timerService.findCurrentSecond();
		//System.out.println("當前秒數: " + currentSecond);
		
		// 創建一個JSON對象，將字串包裝在其中
		try {
			jsonString = objectMapper.writeValueAsString(currentSecond);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
	@GetMapping("/viewjsonTestResult")
	@ResponseBody
	public List<TestResult> jsonTestResult(){

		List<TestResult> testResultList = timerService.findAll();
		
		/*testResultList.forEach(p ->{
			System.out.println("ProductType: " +p.getProductType());
	    	System.out.println("FinishedTestTime: " + p.getFinishedTestTime());
	    	System.out.println("------------");
		});*/
			
		new JSONObject(testResultList);
			
		return testResultList;
	}
	
	
	
	// ========== 方法1 ==========
	/*@PostMapping("/sendLineBotMessage")
	public ResponseEntity<String> pushMessage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(lineBotAccessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String apiUrl = "https://api.line.me/v2/bot/message/push";
        String currentSecond = timerService.findCurrentSecond();

        // 构建请求体
        String requestBody = "{"
                + "\"to\":\"U32dee3c4a3f608e53d719eda6eb09011\","
                + "\"messages\":["
                + "{\"type\":\"text\","
                + "\"text\":\"" + "您所試驗的機種已達 " + currentSecond + "\"}"
                + "]}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        return response;
    }*/
	
	// ========== 方法2 ==========
	/*@PostMapping("/sendLineBotMessage")
	public ResponseEntity<String> sendLineBotMessage() {
       TextMessage textMessage = new TextMessage("这是一条 Line Bot 消息");
       PushMessage pushMessage = new PushMessage("U32dee3c4a3f608e53d719eda6eb09011", textMessage);
       try {
    	   BotApiResponse response = lineMessagingClient.pushMessage(pushMessage).get();
           return ResponseEntity.ok("消息已发送");
       } catch (InterruptedException | ExecutionException e) {
           e.printStackTrace();
           return ResponseEntity.status(500).body("发送消息时出错");
       }
   }*/
	
	@GetMapping("/testing")
	public String showTestingPage() {	
		return "testing";
	}
}
