package njfu.FPMS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import njfu.FPMS.controller.LoginUserController;
import njfu.FPMS.mapper.GetTimeMapper;

import njfu.FPMS.utils.TokenUtils;

@SpringBootTest(classes = FPMSApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	private final Logger logger = 
	LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	GetTimeMapper getTimeMapper;

//	@Autowired
//	LoginUserMapper loginUserMapper;

	@Autowired
	LoginUserController loginUserController;

	@Test
	void contextLoads() {
		LocalDateTime now = getTimeMapper.now();
		String ans = "";
		String token = "";
		boolean isTokenVailable = false;
		logger.info("NOW={}", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
		token = TokenUtils.token("cova", "pj");
		logger.info("Got token: {}", token);
		isTokenVailable = TokenUtils.verify(token);
		logger.info("Token {}", isTokenVailable ? "correct" : "incorrect");
		logger.info('\n' + ans );
	}

}
