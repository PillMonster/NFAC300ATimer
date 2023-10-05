package chien.myweb.NFAC300ATimer.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LineBotController {
	
	@PostMapping("/robot/test")
	public ResponseEntity<String> test() {
        return new ResponseEntity<String>("Hello!! Java!",  HttpStatus.OK);
    }

	
}
