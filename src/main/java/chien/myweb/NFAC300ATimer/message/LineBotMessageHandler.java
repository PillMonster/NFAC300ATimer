package chien.myweb.NFAC300ATimer.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import chien.myweb.NFAC300ATimer.entity.TestInfo;
import chien.myweb.NFAC300ATimer.service.TimerService;

@RestController
@LineMessageHandler
@Component
public class LineBotMessageHandler {
	
	String message = ""; // 初始變數，用來儲存訊息
	String replyText = ""; // 初始變數，用來儲存訊息
	
	@Autowired
	TimerService timerService;
	
	@Autowired
	LineMessagingClient lineMessagingClient;
	
	@Value("${line.bot.channel-token}")
	private String lineBotAccessToken;
	
	Map<String, String> userMap = new HashMap<>(); // 宣告Map集合

	
	@Scheduled(fixedRate = 2000) // 每秒执行一次
	public void pushMessage() {
		//System.out.println("1");
		int runState105 = timerService.findRunState105();
		int runState130 = timerService.findRunState130();
		int runStopState = timerService.findRunStopState();
		
		TestInfo testInfo = timerService.findTestInfo();
		
		String result = testInfo.getSentMessage();
		if (result.equals("Y")) {
			
			System.out.println("SentMessage: true");
			HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(lineBotAccessToken);
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        String apiUrl = "https://api.line.me/v2/bot/message/push";
	        
	        String currentSecond = timerService.findCurrentSecond();
	        
	        if (runState105 == 1 && runStopState == 0) {
	        	message = "\"text\":\"" + "您在105%所試驗的機種已達 " + currentSecond + "\"}"; 
	        }
	        if (runState105 == 0 && runStopState == 1) {
	        	message = "\"text\":\"" + "您在130%所試驗的機種於 " + currentSecond + " 跳脫。" + "\"}"; 
	        }

	        // 构建请求体
	        String requestBody = "{"
	                + "\"to\":\"U32dee3c4a3f608e53d719eda6eb09011\","
	                + "\"messages\":["
	                + "{\"type\":\"text\","
	                + message
	                + "]}";

	        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
			
		}
    }
	
	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		
		String replyToken = event.getReplyToken(); // 獲取使用者的Token
		String messageText = event.getMessage().getText(); // 獲取使用者傳送的文字
		String userId = event.getSource().getUserId(); // 獲取使用者Line ID
		
        System.out.println("您的使用者ID是: " + userId);
        userMap.put(userId, userId);
        
        // 在這裡處理接收到的消息和使用者 ID，然後生成回應消息
        //String replyText = "您的使用者 ID 是：" + userId + "，您傳送的訊息是：" + messageText + "，您的Token：" + replyToken;
        if ("查詢試驗秒數".equals(messageText)) {
        	
        	int runState105 = timerService.findRunState105();
    		int runState130 = timerService.findRunState130();
    		int runStopState = timerService.findRunStopState();
        	
        	String currentSecond = timerService.findCurrentSecond(); // 獲取當前試驗秒數
        	
        	if (runState105 == 1 && runState130 == 0 && runStopState == 0) {
	        	replyText = "目前進行 105% 試驗，試驗秒數為: " + currentSecond;
        	}
        	
        	if (runState105 == 0 && runState130 == 1 && runStopState == 0) {
    	        replyText = "目前進行 130% 試驗，試驗秒數為: " + currentSecond;
            }
        	
        	if (runState105 == 0 && runState130 == 0 && runStopState == 1) {
    	        replyText = "試驗中止，130% 跳脫秒數為: " + currentSecond;
            }
        	
        }
        else if("查詢試驗資訊".equals(messageText)) {
        	
        	TestInfo testInfo = timerService.findTestInfo();
        	
        	String productType = testInfo.getProductType();
        	String poleNum = testInfo.getPoleNum();
        	String ammeter = testInfo.getAmmeter();
        	String testPerson = testInfo.getTestPerson();
        	
        	replyText = "試驗資訊如下\n" +
        				"機種： " + productType + "\n" +
        				"極數： " + poleNum + "\n" +
        				"安培數： " + ammeter + "\n" +
        				"試驗人員： " + testPerson;
        	
        }
        else if("查詢使用者ID".equals(messageText)) {
        	replyText = "您好！您的使用者 ID 是：" + userId;
        }
        else{
        	replyText = messageText;
        }

        return new TextMessage(replyText);
    }
	
	@EventMapping
    public void handleImageMessageEvent(MessageEvent<StickerMessageContent> event) {

		// 獲取用戶端發送的接圖的信息
		String replyToken = event.getReplyToken();

		// 從event中獲取貼圖消息的內容，並將消息內容賦值給stickerMessage變數，以便我們可以使用這些信息來建構回覆訊息
        StickerMessageContent stickerMessageContent = event.getMessage();
        
        // 創建一個貼圖消息的實例(stickerMessage)。
        StickerMessage stickerMessage = new StickerMessage(
            stickerMessageContent.getPackageId(), // 取得貼圖包的ID
            stickerMessageContent.getStickerId() // 取得貼圖包裡面的貼圖ID(具體的貼圖位置)
        );

        // 創建一個ReplyMessage用於回復用戶
        // replyToken: 是從用户發用的消息事件中獲取的，用於指示要將回覆消息發送给哪個用户
        // stickerMessage: 發送的貼圖信息
        ReplyMessage replyMessage = new ReplyMessage(replyToken, stickerMessage);

        // 使用LineMessagingClient發送回復訊息
        lineMessagingClient.replyMessage(replyMessage);
    }

}
