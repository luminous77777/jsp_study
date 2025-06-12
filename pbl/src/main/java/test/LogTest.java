package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.ConsoleAppender;

public class LogTest {
	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	public static void main(String[] args) {
		//logger.
		//로그레벨
		
		logger.debug("디버그로그");
		logger.info("정보로그");
		logger.warn("경고로그");
		logger.error("에러 로그");
	}
}
