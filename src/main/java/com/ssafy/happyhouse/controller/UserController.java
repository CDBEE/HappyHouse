package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	private static Properties mailServerProperties;
	private static Session getMailSession;
	private static MimeMessage generateMailMessage;
	
	/**
	 * 로그인 하기
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserDto userDto) throws Exception {		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.loginUser(userDto);
			if(loginUser != null) {
				String token = jwtService.create("userid", loginUser.getId(), "access-token");
				resultMap.put("access-token", token);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		}catch(Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}
	
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userid, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(jwtService.isUsable(request.getHeader("access-token"))) {
			try {
				UserDto userDto = userService.userInfo(userid);
				resultMap.put("userinfo", userDto);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<>(resultMap, status);
	}
	
	/**
	 * 유저 정보 업데이트
	 */
	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, HttpSession session) throws Exception{
		if(userService.updateUser(userDto)) {	
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
	}
	
	/**
	 * 회원 등록
	 */
	@PostMapping("/join")
	public ResponseEntity<String> registerUser(@RequestBody UserDto user) throws Exception{
		if(userService.registerUser(user)) {	
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}	
	}
	
	/**
	 * 유저 삭제
	 */
	@PostMapping("/deleteuser")
	public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto, HttpSession session) throws Exception {
		if(userService.deleteUser(userDto)) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}	
	}
	
	/**
	 * 비밀번호 찾기 메일
	 */
	@GetMapping("/findPassword")
	public ResponseEntity<?> findPassword( UserDto userDto) throws Exception{
		UserDto user = userService.findPassword(userDto);
		if(user == null) {
			return new ResponseEntity<String>("해당 유저가 존재하지 않습니다.", HttpStatus.NO_CONTENT);
		}else {
			try {
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			generateMailMessage.setSubject("HappyHouse 비밀번호 찾기 메일입니다.", "UTF-8");
			String emailBody = "비밀번호는 : " + user.getPassword() + "입니다.";
			generateMailMessage.setContent(emailBody, "text/html;charset=UTF-8");
			Transport transport = getMailSession.getTransport("smtp");
			 
			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("smtp.gmail.com", "happyhouseservice04@gmail.com", "happyhouse1004");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
			return new ResponseEntity<String>("메일 발송에 성공하였습니다.", HttpStatus.OK);
			}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("메일 발송 중 문제가 발생하였습니다.", HttpStatus.CONFLICT);				
			}
		}
	}
}
